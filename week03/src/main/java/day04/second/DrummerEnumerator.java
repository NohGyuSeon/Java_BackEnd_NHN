package day04.second;

public class DrummerEnumerator implements Enumerator {
    private DrummerList list;
    private int index;

    public DrummerEnumerator(DrummerList list) {
        this.list = list;
        this.index = 0;
    }

    @Override
    public boolean hasMoreElement() {
        if (this.index >= list.size()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object current() {
        return list.get(index++);
    }

}
