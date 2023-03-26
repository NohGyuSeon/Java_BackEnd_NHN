package org.nhnacademy.tip;

import java.awt.Color;
import java.awt.Graphics;
import java.util.function.UnaryOperator;

public class Box extends Bounds implements Drawable {
    UnaryOperator<Point> transformer;
    Color color;

    public Box(Point location, int width, int height, Color color) {
        super(location, width, height);
        this.transformer = p -> p;
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void coordinateTransformer(UnaryOperator<Point> transformer) {
        this.transformer = transformer;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        Point[] points = {
            transformer.apply(new Point(getMinX(), getMinY())),
            transformer.apply(new Point(getMaxX(), getMinY())),
            transformer.apply(new Point(getMaxX(), getMaxY())),
            transformer.apply(new Point(getMinX(), getMaxY()))
        };

        int[] xs = {points[0].getX(), points[1].getX(), points[2].getX(), points[3].getX()};
        int[] ys = {points[0].getY(), points[1].getY(), points[2].getY(), points[3].getY()};

        g.fillPolygon(xs, ys, xs.length);
    }
}
