package org.nhnacademy.tip;

import java.awt.Color;
import java.awt.Graphics;
import java.util.function.UnaryOperator;

public class Cannon extends Bounds implements Drawable {
    UnaryOperator<Point> transformer;
    Color color;
    int magnitude;
    int angle;

    public Cannon(Point location, int width, int height, Color color) {
        super(location, width, height);
        this.transformer = p -> p;
        this.color = color;
        this.magnitude = 20;
        this.angle = 40;
    }

    public int getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(int magnitude) {
        this.magnitude = magnitude;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void coordinateTransformer(UnaryOperator<Point> transformer) {
        this.transformer = transformer;
    }

    public void draw(Graphics g) {
        Point p1 = transformer.apply(new Point(getMinX(), getMinY()));
        Point p2 = transformer.apply(new Point(getMaxX(), getMaxY()));

        int x1 = p1.getX();
        int y1 = p1.getY();
        int x2 = p2.getX();
        int y2 = p2.getY();
        Vector center = new Vector(new Point(x1 + Math.abs(y1 - y2) / 2, Math.min(y1, y2) + Math.abs(y1 - y2) / 2));

        g.setColor(color);
        g.drawOval(Math.min(x1, x2), Math.min(y1, y2), Math.abs(y2 - y1), Math.abs(y2 - y1));
        Vector v1 = new Vector(new Point(0, Math.abs(y1 - y2) / 4)).rotate(-angle).add(center);
        Vector v2 = new Vector(new Point(0, -Math.abs(y1 - y2) / 4)).rotate(-angle).add(center);
        Vector v3 = new Vector(new Point(Math.abs(x2 - x1) - Math.abs(y2 - y1) / 4,
            -Math.abs(y1 - y2) / 4)).rotate(-angle).add(center);
        Vector v4 = new Vector(new Point(Math.abs(x2 - x1) - Math.abs(y2 - y1) / 4,
            Math.abs(y1 - y2) / 4)).rotate(-angle).add(center);

        int[] x = {v1.getDisplacement().getX(), v2.getDisplacement().getX(),
            v3.getDisplacement().getX(), v4.getDisplacement().getX()};
        int[] y = {v1.getDisplacement().getY(), v2.getDisplacement().getY(),
            v3.getDisplacement().getY(), v4.getDisplacement().getY()};
        g.fillPolygon(x, y, x.length);
    }
}
