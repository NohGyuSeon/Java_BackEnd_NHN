package BrickBreakerGame;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * 벽돌 폭팔 클래스
 */
public class BrickSplosion {

    /**
     * 필드들
     */
    private ArrayList<BrickPiece> pieces;   // 벽돌 조각들을 담을 ArrayList
    private int x, y;   // x, y 값
    private Map gameMap;    // 게임 맵
    private boolean isActive;   // 활성화 상태 플래그
    private long startTime; // 시작 시간

    /**
     * @param x       x 값
     * @param y       y 값
     * @param gameMap 게임 맵
     */
    public BrickSplosion(int x, int y, Map gameMap) {
        this.x = x;
        this.y = y;
        this.gameMap = gameMap;
        isActive = true;
        startTime = System.nanoTime();
        pieces = new ArrayList<BrickPiece>();

        init();
    }

    /**
     * 초기화
     */
    public void init() {
        int randNum = (int) (Math.random() * 20 + 5); // 최소 5개에서 24개까지 조각날 수 있도록 난수 생성

        for (int i = 0; i < randNum; i++) {
            pieces.add(new BrickPiece(x, y, gameMap));
        }
    }

    /**
     * 업데이트
     */
    public void update() {
        for (BrickPiece brickPiece : pieces) {
            brickPiece.update();
        }

        if ((System.nanoTime() - startTime) / 1000000 > 2000 && isActive) {
            isActive = false;
        }
    }

    /**
     * @param g 그리기
     */
    public void draw(Graphics2D g) {
        for (BrickPiece brickPiece : pieces) {
            brickPiece.draw(g);
        }
    }

    /**
     * @return 활성화 플래그
     */
    public boolean getIsActive() {
        return isActive;
    }

}
