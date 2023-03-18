package day04.first;

public class ListEnumerator implements Enumerator {
    private Guitarist[] guitarists;
    private int cursor;

    public ListEnumerator(Guitarist[] guitarists) {
        this.guitarists = guitarists;
        this.cursor = 0;
    }

    @Override
    public boolean hasMoreElement() {
        return this.cursor < this.guitarists.length && guitarists[cursor] != null;
    }

    @Override
    public Object current() {
        return guitarists[cursor++];
    }

}
