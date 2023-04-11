package org.nhnacademy.tip;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class MovableBall extends Ball implements Movable {
    Thread thread;
    boolean stop;
    long interval;
    final Vector motion;
    final List<Vector> effectList;

    public MovableBall(Point location, int radius, Color color) {
        super(location, radius, color);

        thread = new Thread(this);
        interval = 100;
        motion = new Vector();
        effectList = new LinkedList<>();
    }

    public void start() {
        this.thread.start();
    }

    public void stop() {
        this.thread.interrupt();
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

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
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
