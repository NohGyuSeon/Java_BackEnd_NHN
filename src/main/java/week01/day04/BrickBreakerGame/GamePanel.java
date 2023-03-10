package BrickBreakerGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * 게임 패널 클래스
 */
public class GamePanel extends JPanel implements Runnable {
    // 필드들
    boolean running;    // 게임 실행 동작 확인 플래그
    private BufferedImage image;    // 이미지
    private Graphics2D g;   // 그래픽
    private MyMouseMotionListener gameMouseListener;    // 직접 정의한 마우스 모션 리스너

    private int mousex;     // 마우스 x 위치 값

    // 객체들
    private Ball gameBall;  // 볼
    private Paddle gamePaddle;  // 패들
    private Map gameMap;    // 맵
    private HUD gameHud;    // 전광판
    private ArrayList<PowerUp> powerUps;    // 볼의 가속도 증가를 위한 PowerUp 타입의 ArrayList
    private ArrayList<BrickSplosion> brickSplosions;    // 벽돌의 폭팔을 위한 BrickSplosion 타입의 ArrayList

    /**
     * 생성자
     */
    public GamePanel() {
        init();
    }

    /**
     * 초기화
     */
    public void init() {
        mousex = 0;
        gameBall = new Ball();
        gamePaddle = new Paddle(100, 20);
        gameMap = new Map(6, 10);
        gameHud = new HUD();
        gameMouseListener = new MyMouseMotionListener();
        powerUps = new ArrayList<>();
        brickSplosions = new ArrayList<BrickSplosion>();

        addMouseMotionListener(gameMouseListener);
        running = true;
        image = new BufferedImage(Main.WIDTH, Main.Height, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    /**
     * 게임 실행, 쓰레드로 동작 Runnable
     */
    @Override
    public void run() {

        // Movable 타입의 볼인 경우에만 게임을 진행할 수 있도록 설정
        while (running && gameBall instanceof Movable) {

            // 업데이트
            update();

            // 그리기
            draw();

            // 리페인트
            repaint();

            try {
                Thread.sleep(10);
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 충돌 감지 메소드
     */
    public void checkCollisions() {
        Rectangle ballRect = gameBall.getRect();
        Rectangle paddleRect = gamePaddle.getRect();

        for (int i = 0; i < powerUps.size(); i++) {
            Rectangle powerRect = powerUps.get(i).getRect();

            // 증가 패들 벽돌에 hit 시, 패들 크기 2배로 증가, 이후 다시 줄어듬
            if (paddleRect.intersects(powerRect)) {
                if (powerUps.get(i).getType() == PowerUp.WIDEPADDLE && !powerUps.get(i).getWasUsed()) {
                    gamePaddle.setWidth(gamePaddle.getWidth() * 2);
                    gamePaddle.setWidthTimer();
                    powerUps.get(i).setWasUsed(true);
                    gameHud.addScore(1000);     // 추가 점수 1000점 부여
                }
            }
        }

        if(ballRect.intersects(paddleRect)) {
            gameBall.setDY(-gameBall.getDY());

            if(gameBall.getX() < mousex + gamePaddle.getWidth() / 5) {  // 볼의 왼쪽 가장자리에 맞을 경우 왼쪽으로 위치 변화 증가
                gameBall.setDX(gameBall.getDX() - .5);
            }

            if(gameBall.getX() < mousex + gamePaddle.getWidth() && gameBall.getX() > mousex + gamePaddle.getWidth() / 5) { // 볼의 오른쪽 가장자리에 맞을 경우 오른쪽으로 위치 변화 증가
                gameBall.setDX(gameBall.getDX() + .5);
            }

        }

        int[][] gameMapArray = gameMap.getMapArray();
 
        A:
        for (int row = 0; row < gameMap.getMapArray().length; row++) {
            for (int col = 0; col < gameMap.getMapArray()[0].length; col++) {
                if (gameMap.getMapArray()[row][col] > 0) {
                    int brickx = col * gameMap.getBrickWidth() + gameMap.HOR_PAD;
                    int bricky = row * gameMap.getBrickHeight() + gameMap.VERT_PAD;
                    int brickWidth = gameMap.getBrickWidth();
                    int brickHeight = gameMap.getBrickHeight();

                    Rectangle brickRect = new Rectangle(brickx, bricky, brickWidth, brickHeight);

                    if (ballRect.intersects(brickRect)) {

                        if(gameMap.getMapArray()[row][col] == 1) {
                            brickSplosions.add(new BrickSplosion(brickx, bricky, gameMap));
                        }

                        if(gameMap.getMapArray()[row][col] > 3) {
                            powerUps.add(
                                new PowerUp(brickx, bricky, gameMap.getMapArray()[row][col],
                                    brickWidth, brickHeight));
                            gameMap.setBrick(row, col, 3);
                        } else {
                            gameMap.hitBrick(row, col);
                        }

                        gameBall.setDY(-gameBall.getDY());

                        if(gameMap.getMapArray()[row][col] != gameMap.getMapArray()[gameMap.getInfinitex()][gameMap.getInfinitey()]) {
                            gameHud.addScore(50); // 벽돌 hit 시 점수 증가, 남은 벽돌 수 감소
                        } else {
                            gameHud.addScore(-50); // 무한 벽돌 hit 시 점수 감소, 벽돌에 따라 점수가 다를 수 있음
                        }

                        break A;
                    }
                }
            }
        }
    }

    /**
     * 업데이트
     */
    public void update() {
        checkCollisions();
        gameBall.update();
        gamePaddle.update();

        for (PowerUp power : powerUps) {
            power.update();
        }

        for (int i = 0; i < brickSplosions.size(); i++) {
            brickSplosions.get(i).update();
            if (!brickSplosions.get(i).getIsActive()) {
                brickSplosions.remove(i);
            }
        }
    }

    /**
     * 그리기
     */
    public void draw() {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Main.WIDTH, Main.Height);

        gameMap.draw(g);
        gameBall.draw(g);
        gamePaddle.draw(g);
        gameHud.draw(g);

        drawPowerUps();

        if (gameMap.isThereAWin()) {
            drawWin();
            running = false;
        }

        if (gameBall.youLose()) {
            drawLoser();
            running = false;
        }

        for(BrickSplosion brickSplosion : brickSplosions) {
            brickSplosion.draw(g);
        }
    }

    /**
     * 승리 메소드
     */
    public void drawWin() {
        g.setColor(Color.BLUE);
        g.setFont(new Font("Courier New", Font.BOLD, 50));
        g.drawString("winner!!", 200, 200);
    }

    /**
     * 파워업 객체 그리기
     */
    public void drawPowerUps() {
        for (PowerUp power : powerUps) {
            power.draw(g);
        }
    }

    /**
     * 패배 메소드
     */
    public void drawLoser() {
        g.setColor(Color.RED);
        g.setFont(new Font("Courier New", Font.BOLD, 50));
        g.drawString("Loser..", 200, 200);
    }

    /**
     * @param g the <code>Graphics</code> context in which to paint
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(image, 0, 0, Main.WIDTH, Main.Height, null);
        g2.dispose();
    }

    /**
     * 개발자 정의 마우스 모션 리스너
     */
    private class MyMouseMotionListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        /**
         * @param e the event to be processed 마우스 움직임에 따라 패들 위치 제어
         */
        @Override
        public void mouseMoved(MouseEvent e) {
            mousex = e.getX();
            gamePaddle.mouseMoved(e.getX());
        }
    }

}
