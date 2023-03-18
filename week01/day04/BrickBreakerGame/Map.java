package BrickBreakerGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * 게임 맵 클래스
 */
public class Map {

    /**
     * 필드들
     */
    static int infinitex;   // 무한 벽돌 행
    static int infinitey;   // 무한 벽돌 열
    static int initBricksCount;    // 초기 벽돌 수
    static int bricksRemaining;     // 남아있는 벽돌 hit 수, 무한 벽돌은 100으로 고정
    static int breakCount;          // 깨진 벽돌 수
    static int R, G, B;     // 무한 벽돌 R,G,B 색상, 초기값은 0,0,0(검정)
    private int[][] gameMap;    // 게임 맵 배열
    private int brickHeight, brickWidth;    // 벽돌 높이, 너비
    public final int HOR_PAD = 80, VERT_PAD = 50; // 수평 방향 패딩, 수직 방향 패딩

    /**
     * @param row 행 수
     * @param col 열 수
     */
    public Map(int row, int col) {
        initMap(row, col);

        brickWidth = (Main.WIDTH - 2 * HOR_PAD) / col; // 벽 너비
        brickHeight = (Main.WIDTH / 2 - HOR_PAD * 2) / row; // 벽 높이
        initBricksCount = row * col; // 벽돌 수
        breakCount = 0; // 깨진 벽돌 수
    }

    /**
     * @param row 행
     * @param col 열
     */
    public void initMap(int row, int col) {
        gameMap = new int[row][col];
        infinitex = (int) (Math.random() * row);
        infinitey = (int) (Math.random() * col);

        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[0].length; j++) {
                int rand = (int) (Math.random() * 3 + 1);

                gameMap[i][j] = rand;
            }
        }
        gameMap[infinitex][infinitey] = 100;
        gameMap[3][2] = 4;
        gameMap[3][6] = 5;
    }

    /**
     * @param g 그리기
     */
    public void draw(Graphics2D g) {
        for (int row = 0; row < gameMap.length; row++) {
            for (int col = 0; col < gameMap[0].length; col++) {
                if (gameMap[row][col] > 0) {

                    if(gameMap[row][col] == 1) {
                        g.setColor(new Color(200, 200, 200));
                    }
                    if(gameMap[row][col] == 2) {
                        g.setColor(new Color(150, 150, 150));
                    }
                    if(gameMap[row][col] == 3) {
                        g.setColor(new Color(100, 100, 100));
                    }
                    if(gameMap[row][col] == PowerUp.WIDEPADDLE) {
                        g.setColor(PowerUp.WIDECOLOR);
                    }
                    if(gameMap[row][col] == PowerUp.FASTBALL) {
                        g.setColor(PowerUp.FASTCOLOR);
                    }
                    if(gameMap[row][col] == 100) {
                        g.setColor(new Color(R, G, B));
                    }

                    g.fillRect(col * brickWidth + HOR_PAD, row * brickHeight + VERT_PAD, brickWidth,
                        brickHeight);
                    g.setStroke(new BasicStroke(2));
                    g.setColor(Color.WHITE);
                    g.drawRect(col * brickWidth + HOR_PAD, row * brickHeight + VERT_PAD, brickWidth,
                        brickHeight);
                }
            }
        }
    }

    /**
     *
     * @return 깨지지 않는 벽돌을 제외하고 모두 깰 경우 승리
     */
    public boolean isThereAWin() {
        boolean thereisAWin = false;

        bricksRemaining = 0;

        for (int row = 0; row < gameMap.length; row++) {
            for (int col = 0; col < gameMap[0].length; col++) {
                bricksRemaining += gameMap[row][col];
            }
        }

        if (bricksRemaining == 100) {
            thereisAWin = true;
        }

        return thereisAWin;
    }

    /**
     * @return 맵 객체 반환
     */
    public int[][] getMapArray() {
        return gameMap;
    }

    /**
     * @param row 벽돌 행
     * @param col 벽돌 열
     * @param value 벽돌의 hit 가능 횟수 값
     */
    public void setBrick(int row, int col, int value) {
        gameMap[row][col] = value;
    }

    /**
     * @return 벽돌 너비
     */
    public int getBrickWidth() {
        return brickWidth;
    }

    /**
     * @return 남아있는 벽돌 수 반환
     */
    public static int getBricksCount() {
        return initBricksCount - breakCount;
    }

    /**
     * @return 벽돌 높이
     */
    public int getBrickHeight() {
        return brickHeight;
    }

    /**
     * 벽돌의 hit 횟수를 계산하는 메서드
     * @param row 벽돌 행
     * @param col 벽돌 열
     */
    public void hitBrick(int row, int col) {
        gameMap[row][col] -= 1;
        if (gameMap[row][col] <= 0) {
            gameMap[row][col] = 0;
            breakCount += 1;
        }
        gameMap[infinitex][infinitey] = 100;
        if (gameMap[row][col] == gameMap[infinitex][infinitey]) {   // 무한 벽돌 hit 시, 랜덤으로 R,G,B 색상 갱신
            R = (int) (Math.random() * 255);
            G = (int) (Math.random() * 255);
            B = (int) (Math.random() * 255);
        }
    }

    /**
     * @return 무한 벽돌 행
     */
    public static int getInfinitex() {
        return infinitex;
    }

    /**
     * @return 무한 벽돌 열
     */
    public static int getInfinitey() {
        return infinitey;
    }

}
