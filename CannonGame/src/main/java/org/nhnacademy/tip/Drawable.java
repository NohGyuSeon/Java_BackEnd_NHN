package org.nhnacademy.tip;

import java.awt.Color;
import java.awt.Graphics;
import java.util.function.UnaryOperator;

/**
 * 인스턴스 중 화면 출력이 가능한 타입
 */
public interface Drawable {
    /**
     * 화면 출력시 호출되면, 주어진 그래픽 컨텍스트를 이용해 그린다.
     *
     * @param g
     */
    public void draw(Graphics g);

    /**
     * 컬러 설정이 가능하다.
     *
     * @param color
     */
    public void setColor(Color color);

    /**
     * 화면의 조건에 맞게 좌표 변환이 가능한 변환기 설정이 가능하다.
     *
     * @param transformer
     */
    public void coordinateTransformer(UnaryOperator<Point> transformer);
}
