package week01.day03.second.code;

import java.util.concurrent.ThreadLocalRandom;

public class Sender implements Runnable {
    Thread thread;
    final Pipe pipe;

    public Sender(Pipe pipe) {
        this.pipe = pipe;
        thread = new Thread(this);
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        thread.interrupt();
        try {
            thread.join();
        } catch (InterruptedException ignore) {
            Thread.currentThread().interrupt();
        }
    }

    public Thread.State getState() {
        return this.thread.getState();
    }

    @Override
    public void run() {
        // pipe를 통해서 데이터를 전송한다.
        // 전송에 성공하면 일정시간 기다린다.
        while (!Thread.interrupted()) {
            try {
                pipe.send(ThreadLocalRandom.current().nextInt());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
