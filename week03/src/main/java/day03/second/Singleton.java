package day03.second;

/**
 * Singleton
 */
public class Singleton {
    static Singleton singleton;
    private int number;

    private Singleton() {}

    public static synchronized Singleton getSingleton() {
        if (singleton != null) {
            return singleton;
        }
        singleton = new Singleton();
        return singleton;
    }

    public synchronized int getNumber() {
        return this.number++;
    }
    
}