package day04.first;

import java.util.Iterator;

public class GuitaristList implements Enumerable {
    private Guitarist[] guitarists;
    private int cursor;

    public GuitaristList() {
        guitarists = new Guitarist[10];
        this.cursor = 0;
    }

    public void add(Guitarist guitarist) {
        if (cursor < guitarists.length) {
            guitarists[cursor++] = guitarist;
        }
    }

    @Override
    public Object iterator() {
        return new ListEnumerator(guitarists);
    }
}
