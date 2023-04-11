package org.nhnacademy.tip;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class MovableBox extends Box implements Movable {
    Thread thread;
    long interval;
    boolean stop;
    final Vector motion;
    final List<Vector> effectList;

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public MovableBox(Point location, int width, int height, Color color) {
        super(location, width, height, color);

        thread = new Thread(this);
        motion = new Vector();
        interval = 100;
        effectList = new LinkedList<>();
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }

    public void setMotion(Vector motion) {
        this.motion.set(motion);
    }

    public Vector getMotion() {
        return motion;
    }

    public void addEffect(Vector effect) {
        effectList.add(effect);
    }

    public void next() {
        for (Vector effect : effectList) {
            getMotion().add(effect);
        }
        location.move(motion.getDisplacement());
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                next();
                Thread.sleep(interval);
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }

        }
    }
}
