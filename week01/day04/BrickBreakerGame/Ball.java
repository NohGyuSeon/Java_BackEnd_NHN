package BrickBreakerGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * 볼 클래스
 */
public class Ball implements Movable {

    /**
     * 필드들
     */
    private Rectangle ballRectangle;    // 볼 영역
    private double x, y, dx, dy;    // x, y 좌표 값 dx, dy 좌표 변위 값
    private int ballSize = 30;  // 볼 사이즈

    /**
     * 볼 생성자
     */
    public Ball() {
        x = 200;
        y = 200;
        dx = 1;
        dy = 3;
    }

    /**
     * 변위 업데이트
     */
    @Override
    public void update() {
        setPosition();
    }

    /**
     * 변위 지정
     */
    public void setPosition() {
        x += dx;
        y += dy;

        if (x < 0) {
            dx = -dx;
        }
        if (y < 0) {
            dy = -dy;
        }
        if (x > Main.WIDTH - ballSize) {
            dx = -dx;
        }
        if (y > Main.Height - ballSize) {
            dy = -dy;
        }

    }

    /**
     * @param g 그리기
     */
    public void draw(Graphics2D g) {
        g.setColor(Color.CYAN);
        g.setStroke(new BasicStroke(4));
        g.drawOval((int) x, (int) y, ballSize, ballSize);
    }

    /**
     * @return 볼 범위 반환
     */
    public Rectangle getRect() {
        return new Rectangle((int) x, (int) y, ballSize, ballSize);
    }

    public double getDY() {
        return this.dy;
    }

    public void setDY(double dy) {
        this.dy = dy;
    }

    public double getDX() {
        return this.dx;
    }

    public void setDX(double dx) {
        this.dx = dx;
    }

    public double getX() {
        return x;
    }

    /**
     * @return 패배 플래그
     */
    public boolean youLose() {
        boolean loser = false;

        if (y > Main.Height - ballSize * 2) {
            loser = true;
        }

        return loser;
    }

}
