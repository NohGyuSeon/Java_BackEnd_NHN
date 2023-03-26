package org.nhnacademy.tip;

import javax.swing.event.EventListenerList;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class BoundedBox extends MovableBox implements Bounded {
    Rectangle bounds;
    List<Bounds> boundsList;
    EventListenerList listenerList;

    public BoundedBox(Point location, int width, int height, Color color) {
        super(location, width, height, color);

        bounds = new Rectangle(Integer.MIN_VALUE / 2, Integer.MIN_VALUE / 2, Integer.MIN_VALUE, Integer.MIN_VALUE);
        boundsList = new LinkedList<>();
        listenerList = new EventListenerList();
    }

    public void setBounds(int x, int y, int width, int height) {
        bounds = new Rectangle(x, y, width, height);
    }

    public void addExcludedBounds(Bounds bounds) {
        synchronized (boundsList) {
            boundsList.add(bounds);
        }
    }

    public void next() {
        super.next();

        if ((getLocation().getX() - getWidth() / 2 < bounds.getX())
                || (bounds.getX() + bounds.getWidth() < getLocation().getX() + getWidth() / 2)) {
            motion.flipX();
        }

        if ((getLocation().getY() - getHeight() / 2 < bounds.getY())
                || (bounds.getY() + bounds.getHeight() < getLocation().getY() + getHeight() / 2)) {
            motion.flipY();
        }

        synchronized (boundsList) {
            for (Bounds bounds : boundsList) {
                if (isCollision(bounds)) {
                    Rectangle intersection = getIntersection(bounds);
                    if ((getWidth() == intersection.getWidth())
                            || (bounds.getWidth() == intersection.getWidth())) {
                        motion.flipY();
                    } else if ((getHeight() == intersection.getHeight())
                            || (bounds.getHeight() == intersection.getHeight())) {
                        motion.flipX();
                    } else {
                        motion.flipY();
                        motion.flipX();
                    }
                    CollisionEventListener[] listeners = listenerList.getListeners(CollisionEventListener.class);
                    for (int i = 0; i < listeners.length; i++) {
                        listeners[i].collisionEvent(new CollisionEvent(this, bounds));
                    }
                }
            }
        }
    }

    public void addCollisionEventListener(CollisionEventListener listener) {
        listenerList.add(CollisionEventListener.class, listener);
    }
}
