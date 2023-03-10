package practice.week01.prac08;

public class MySemaphore {
    private int criticalSectionLimits;

    public MySemaphore(int criticalSectionLimits) {
        this.criticalSectionLimits = criticalSectionLimits;
    }

    public synchronized void acquire() throws IllegalMonitorStateException, InterruptedException {
        if (--criticalSectionLimits < 0) {
            wait();
        }
    }

    public synchronized void release() {
        if (criticalSectionLimits++ <= 0) {
            notify();
        }
    }

}
