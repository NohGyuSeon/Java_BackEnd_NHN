package org.nhnacademy.tip;

import java.awt.Rectangle;

public class Bounds {
    Point location;
    int width;
    int height;

    public Bounds(Point location, int width, int height) {
        this.location = location.clone();
        this.width = width;
        this.height = height;
    }

    public Point getLocation() {
        return  location;
    }

    public int getWidth() {
        return  width;
    }

    public int getHeight() {
        return  height;
    }

    public int getMinX() {
        return  location.getX() - width / 2;
    }

    public int getMinY() {
        return  location.getY() - height / 2;
    }

    public int getMaxX() {
        return  location.getX() + width / 2;
    }

    public int getMaxY() {
        return  location.getY() + height / 2;
    }

    public boolean isCollision(Bounds other) {
        return  getRectangle().intersects(other.getRectangle());
    }

    protected Rectangle getIntersection(Bounds other) {
        return  getRectangle().intersection(other.getRectangle());
    }

    protected Rectangle getRectangle() {
        return  new Rectangle(getMinX(), getMinY(), getWidth(), getHeight());
    }
}
