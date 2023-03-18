package day04.second;

public class Test {
    public static void printList(Enumerator enumerator) {
        while (enumerator.hasMoreElement()) {
            System.out.println(enumerator.current());
        }
    }

    public static void main(String[] args) {
        DrummerList list = new DrummerList(3);
        Drummer d = new Drummer.Builder(1, "John")
            .numberOfBass(1)
            .countOfSymbol(6)
            .teamName("Led Zeppelin")
            .build();

        Drummer d2 = new Drummer.Builder(2, "Lars Ulrich")
            .numberOfBass(2)
            .countOfSymbol(13)
            .teamName("Metallica")
            .build();

        Drummer d3 = new Drummer.Builder(3, "Karl Palmer")
            .numberOfBass(1)
            .countOfSymbol(13)
            .teamName("ELP")
            .build();

        list.add(d);
        list.add(d2);
        list.add(d3);

        // printList(list.enumerator());
        DrummerListAdapter adapter = new DrummerListAdapter(list);
        for (Drummer drummer : adapter) {
            System.out.println(drummer);
        }
    }
}
