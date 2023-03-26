import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestEx6_1 {
    public static class WorkerThread implements Runnable {

        private String message;

        public WorkerThread(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()
                + " (작업 스레드) 시작: " + message);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()
                + " (작업 스레드) 종료: " + message);
        }
    }
    public static void main(String[] args) {
        // ThreadPool 생성
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
		    executor.execute(new WorkerThread("Thread 수업중입니다."));
        }

        // 모든 작업이 완료될 때까지 대기
        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        System.out.println("모든 작업이 완료되었습니다.");
    }
}