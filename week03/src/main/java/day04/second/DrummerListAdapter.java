package day04.second;

import java.util.Iterator;

public class DrummerListAdapter implements Iterable<Drummer> {
    private DrummerList list;

    public DrummerListAdapter(DrummerList list) {
        this.list = list;
    }

    @Override
    public Iterator<Drummer> iterator() {
        return new DrummerEnumeratorAdapter((DrummerEnumerator) list.enumerator());
    }

}