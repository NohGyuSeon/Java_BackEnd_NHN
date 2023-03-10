package week01.day03.homework1.code;

/**
 * 세마포어 클래스
 */
public class MySemaphore {

    /**
     * 임계 영역 제어 정수형 변수
     */
    private int criticalSectionLimits;

    /**
     * @param criticalSectionLimits 세마포어 count 제어 변수
     */
    public MySemaphore(int criticalSectionLimits) {
        this.criticalSectionLimits = criticalSectionLimits;
    }

    /**
     * @throws InterruptedException 인터럽트를 통한 스레드 종료 예외
     */
    public synchronized void acquire() throws IllegalMonitorStateException, InterruptedException {
        if (--criticalSectionLimits < 0) {
            wait();
        }
    }

    /**
     * @throws InterruptedException 인터럽트를 통한 스레드 종료 예외
     */
    public synchronized void release() throws IllegalMonitorStateException {
        if (criticalSectionLimits++ <= 0) {
            notify();
        }
    }
}
