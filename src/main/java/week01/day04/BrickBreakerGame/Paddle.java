package BrickBreakerGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * 패들 클래스
 */
public class Paddle {

    /**
     * 필드들
     */
    private Rectangle paddleRectangle;  // 패들 범위
    private double x;   // x 값
    private int width, height, startWidth;  // 너비, 높이, 시작 너비
    private long widthTimer;    // 패들이 증가하는 타이머 시간
    private boolean altWidth;   // 증가 패들 플래그

    public final int YPOS = Main.Height - 100;  // 패들의 초기 y 위치 값

    /**
     * @param width 패들 너비
     * @param height 패들 높이
     */
    public Paddle(int width, int height) {
        this.altWidth = false;
        this.width = width;
        this.startWidth = width;
        this.height = height;

        x = Main.WIDTH / 2 - width / 2;

    }

    /**
     * 패들 업데이트
     */
    public void update() {
        if((System.nanoTime() - widthTimer) / 1000 > 4000000 && altWidth == true) {
            width = startWidth;
            altWidth = false;
        }
    }

    /**
     * @param g 그리기
     */
    public void draw(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect((int) x, YPOS, width, height);

        if(altWidth == true) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Courier New", Font.BOLD, 18));
            g.drawString("Shrinking in " + (4 - (System.nanoTime() - widthTimer) / 1000000000), (int)x, YPOS + 18);
        }
    }

    /**
     * @param mouseXPOS 마우스 x 위치
     */
    public void mouseMoved(int mouseXPOS) {
        x = mouseXPOS;

        if (x > Main.WIDTH - width) {
            x = Main.WIDTH - width;
        }
    }

    /**
     * @return 패들 범위 반환
     */
    public Rectangle getRect() {
        return new Rectangle((int) x, YPOS, width, height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        altWidth = true;
        this.width = width;
        setWidthTimer();
    }

    /**
     * 증가 패들 지속시간 타이머
     */
    public void setWidthTimer() {
        widthTimer = System.nanoTime();
    }

}
