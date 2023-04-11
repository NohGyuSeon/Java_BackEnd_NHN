import org.nhnacademy.tip.ex4.Pipe;
import org.nhnacademy.tip.ex4.Receiver;
import org.nhnacademy.tip.ex4.Sender;

public class TestEx4 {
    public static void main(String[] args) throws InterruptedException {
        Pipe pipe = new Pipe();
        Sender sender = new Sender(pipe);
        Receiver receiver = new Receiver(pipe);

        Thread.State senderState1;
        Thread.State senderState2;
        Thread.State receiverState1;
        Thread.State receiverState2;

        System.out.println("Sender : " + sender.getState() + ", Receiver : " + receiver.getState());
        sender.start();
        receiver.start();
        senderState1 = sender.getState();
        senderState2 = sender.getState();
        receiverState1 = receiver.getState();
        receiverState2 = receiver.getState();
        for(int i = 0; i < 10; i++) {
           try {
               senderState1 = sender.getState();
               receiverState1 = receiver.getState();
               if ((senderState1 != senderState2) || (receiverState1 != receiverState2)) {
                   System.out.println("Sender : " + sender.getState() + ", Receiver : " + receiver.getState());
                   senderState2 = senderState1;
                   receiverState2 = receiverState1;
               }

               Thread.sleep(1000);
           } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
           }
        }

        sender.stop();
        receiver.stop();

        System.out.println("Sender : " + sender.getState() + ", Receiver : " + receiver.getState());

    }
}
