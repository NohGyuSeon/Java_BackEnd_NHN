package day04.second;

public class DrummerList implements Enumerable {
    private Drummer[] list;
    private int index;

    public DrummerList(int size) {
        this.list = new Drummer[size];
        this.index = 0;
    }

    public void add(Drummer drummer) {
        if (this.index >= this.list.length) {
            System.out.println("Lists full");
        } else {
            list[index++] = drummer;
        }
    }

    public Drummer get(int index) {
        return this.list[index];
    }

    public int size() {
        return this.index;
    }

    @Override
    public Enumerator enumerator() {
        return new DrummerEnumerator(this);
    }
}