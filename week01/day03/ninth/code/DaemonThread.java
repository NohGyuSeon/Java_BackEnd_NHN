package week01.day03.ninth.code;

public class DaemonThread {

    public static void showStatusMessage(int size) {
        Thread thread = Thread.currentThread();
        for (int i = 0; i < size; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(i + " : 이름 === " + thread.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        Thread firstThread = new Thread("fi" ) {
            @Override
            public void run() {
                showStatusMessage(5);
            }
        };

        Thread secondThread = new Thread("se" ) {
            @Override
            public void run() {
                showStatusMessage(3);
            }
        };

        firstThread.setDaemon(true);
        secondThread.setDaemon(true);

        firstThread.start();
        secondThread.start();

        showStatusMessage(1);
    }

}
