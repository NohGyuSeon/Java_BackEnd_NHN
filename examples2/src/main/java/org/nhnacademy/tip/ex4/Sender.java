package org.nhnacademy.tip.ex4;

import java.util.concurrent.ThreadLocalRandom;

public class Sender implements Runnable {
    Thread thread;
    Pipe pipe;

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
        }
    }

    public Thread.State getState() {
        return  thread.getState();
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            try {
                pipe.send(ThreadLocalRandom.current().nextInt());
                Thread.sleep(ThreadLocalRandom.current().nextLong(1000, 5000));
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
