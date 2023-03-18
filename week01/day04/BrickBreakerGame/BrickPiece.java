package BrickBreakerGame;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 벽돌 조각 클래스
 */
public class BrickPiece {

    /**
     * 필드들
     */
    private int x, y;   // 조각 벽돌 위치 값
    private double dx, dy, gravity;   // 조각 벽돌 변위 값, 중력
    private Color color;    // 조각 벽돌 색상
    private Map gameMap;    // 게임 맵
    private int size;   // 사이즈

    /**
     * @param brickx 조각 벽돌 x
     * @param bricky 조각 벽돌 y
     * @param gameMap 게임 맵
     */
    public BrickPiece(int brickx, int bricky, Map gameMap) {
        this.gameMap = gameMap;
        this.x = brickx + gameMap.getBrickWidth() / 2;
        this.y = bricky + gameMap.getBrickHeight() / 2;
        dx = (Math.random() * 30 - 15);
        dy = (Math.random() * 30 - 15);
        size = (int)(Math.random()*15 + 2);
        color = new Color(0, 200, 200);
        gravity = .8;
    }

    /**
     * 업데이트
     */
    public void update() {
        x += dx;
        y += dy;
        dy += gravity;
    }

    /**
     * @param g 그리기
     */
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect((int) x, (int) y, size, size);
    }

}
