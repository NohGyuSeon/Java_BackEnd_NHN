package week01.day03.second.test;

import week01.day03.second.code.Pipe;
import week01.day03.second.code.Receiver;
import week01.day03.second.code.Sender;

public class TestPipe {
    public static void main(String[] args) {
        Pipe pipe = new Pipe();
        Sender sender = new Sender(pipe);
        Receiver receiver = new Receiver(pipe);
        Thread senderThread = new Thread(sender);
        Thread receiverThread = new Thread(receiver);
        Thread.State senderState1;
        Thread.State senderState2;
        Thread.State receiverState1;
        Thread.State receiverState2;

        senderState1 = senderThread.getState();
        senderState2 = senderThread.getState();
        receiverState1 = receiverThread.getState();
        receiverState2 = receiverThread.getState();

        System.out.println("Sender: " + senderThread.getState() + "Receiver: " + receiverThread.getState());
        senderThread.start();
        receiverThread.start();

        while (!Thread.interrupted()) {
            try {
                senderState1 = senderThread.getState();
                receiverState1 = receiverThread.getState();

                if ((senderState1 != senderState2) || (receiverState1 != receiverState2)) {
                    System.out.println("Sender: " + senderThread.getState() + "Receiver: " + receiverThread.getState());
                    senderState2 = senderState1;
                    receiverState2 = receiverState1;
                }
                Thread.sleep(1000);
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
