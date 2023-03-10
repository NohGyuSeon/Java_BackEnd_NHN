package BrickBreakerGame;

import javax.swing.JFrame;

/**
 * "벽돌깨기 만들기" 메인 클래스
 */
public class Main {

    /**
     * 프레임 너비, 높이
     */
    public static final int WIDTH = 640,
        Height = 480;

    /**
     * 메인 메소드
     * @author : NohGyuSeon
     * @date : 2023.03.06.
     */
    public static void main(String[] args) {

        JFrame gameFrame = new JFrame("Brick Breaker");

        BrickBreakerGame.GamePanel gamePanel = new BrickBreakerGame.GamePanel();

        Thread gameThread = new Thread(gamePanel);

        gameFrame.setLocationRelativeTo(null); //
        gameFrame.setResizable(false);
        gameFrame.setSize(WIDTH, Height);

        gameFrame.add(gamePanel);

        gameThread.start();

        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);

    }
}
