package BrickBreakerGame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * 가속도 벽돌, 증가 패들에 대한 PowerUp 클래스
 */
public class PowerUp {

    /**
     * 필드들
     */
    private int x, y, dy, type, width, height;
    private boolean isOnScreen;
    private boolean wasUsed;    // 이전에 패들이 증가된 적이 있는지에 대한 확인 플래그
    private Color color;    // 색상

    public final static int WIDEPADDLE = 4;     // 증가 패들 벽돌 hit 수
    public final static int FASTBALL = 5;       // 가속도 벽돌 hit 수
    public final static Color WIDECOLOR = new Color(70, 70, 140);   // 증가 패들 벽돌 색 지정
    public final static Color FASTCOLOR = new Color(170, 60, 60);   // 가속도 벽돌 색 지정

    /**
     * @param xStart 시작 x
     * @param yStart 시작 y
     * @param gameType 벽돌 hit 타입
     * @param gameWidth 너비
     * @param gameHeight 높이
     */
    public PowerUp(int xStart, int yStart, int gameType, int gameWidth, int gameHeight) {
        this.x = xStart;
        this.y = yStart;
        this.type = gameType;
        this.width = gameWidth;
        this.height = gameHeight;

        if (type < 4) {
            type = 4;
        }
        if (type > 5) {
            type = 5;
        }
        if (type == WIDEPADDLE) {
            color = WIDECOLOR;
        }
        if (type == FASTBALL) {
            color = FASTCOLOR;
        }

        this.dy = (int) (Math.random() * 6 + 1);

        wasUsed = false;
    }

    /**
     * @param g 그리기
     */
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    /**
     * 업데이트
     */
    public void update() {
        y += dy;

        if (y > Main.Height) {
            isOnScreen = false;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    /**
     * @return 벽돌 hit 수 타입
     */
    public int getType() {
        return type;
    }

    public void setIsOnScreen(boolean onScreen) {
        isOnScreen = onScreen;
    }

    /**
     * @return 증가 패들 벽돌 사용 유무 확인 플래그 반환
     */
    public boolean getWasUsed() {
        return wasUsed;
    }

    /**
     * @param wasUsed 증가 패들 벽돌 사용 유무 확인 플래그 지정
     */
    public void setWasUsed(boolean wasUsed) {
        this.wasUsed = wasUsed;
    }

    /**
     * @return 사각형 범위 반환
     */
    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

}
