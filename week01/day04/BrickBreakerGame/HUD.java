package BrickBreakerGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class HUD {

    /**
     * 필드들
     */
    static int count;  // 공의 hit 횟수
    private int score;  // 점수

    /**
     * 전광판 생성자
     */
    public HUD() {
        init();
    }

    /**
     * 초기화
     */
    public void init() {
        score = 0;
        count = 0;
    }

    /**
     * @param g 그래픽
     */
    public void draw(Graphics2D g) {
        g.setFont(new Font("Courier New", Font.PLAIN, 16));
        g.setColor(Color.PINK);
        g.drawString("Score: " + score, 20, 18);

        g.setFont(new Font("Courier New", Font.ITALIC, 12));
        g.setColor(Color.BLUE);
        g.drawString("Hit: " + count, 220, 20);

        g.setColor(Color.RED);
        g.drawString("Bricks: " + Map.getBricksCount(), 420, 20);
    }

    /**
     * @return 점수 반환
     */
    public int getScore() {
        return score;
    }

    /**
     * @param scoreToAdd 점수 추가, 카운트 추가
     */
    public void addScore(int scoreToAdd) {
        score += scoreToAdd;
        count += 1;
    }

}
