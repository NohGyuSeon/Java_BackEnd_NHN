package day04.second;

import java.util.Iterator;

public class DrummerEnumeratorAdapter implements Iterator<Drummer> {
    private DrummerEnumerator drummerEnumerator;

    public DrummerEnumeratorAdapter(DrummerEnumerator drummerEnumerator) {
        this.drummerEnumerator = drummerEnumerator;
    }

    @Override
    public boolean hasNext() {
        return drummerEnumerator.hasMoreElement();
    }

    @Override
    public Drummer next() {
        return (Drummer) drummerEnumerator.current();
    }
}
