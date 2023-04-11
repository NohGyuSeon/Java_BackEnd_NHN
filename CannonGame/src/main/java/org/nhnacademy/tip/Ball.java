package org.nhnacademy.tip;

import java.awt.Color;
import java.awt.Graphics;
import java.util.function.UnaryOperator;

/**
 * 게임을 구성하는 둥근 물체.
 */
public class Ball extends Bounds implements Drawable {
    UnaryOperator<Point> transformer;
    Color color;

    /**
     * 생성자.
     *
     * @param location 볼 위치
     * @param radius   반지름
     * @param color    색깔
     */
    public Ball(Point location, int radius, Color color) {
        super(location, 2 * radius, 2 * radius);
        this.transformer = p -> p;
        this.color = color;
    }

    /**
     * 볼의 색 설정.
     *
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * 반지름을 얻어온다.
     *
     * @return
     */
    public int getRadius() {
        return getWidth() / 2;
    }

    /**
     * 좌표계를 변환할 수 있는 변환기를 설정한다.
     *
     * @param transformer
     */
    public void coordinateTransformer(UnaryOperator<Point> transformer) {
        this.transformer = transformer;
    }

    /**
     * 볼 그리기.
     *
     * @param g 그래픽 컨텍스트
     */
    public void draw(Graphics g) {
        g.setColor(color);
        Point p1 = transformer.apply(location);

        g.fillOval(p1.getX() - getRadius(), p1.getY() - getRadius(), getWidth(), getHeight());
    }
}
