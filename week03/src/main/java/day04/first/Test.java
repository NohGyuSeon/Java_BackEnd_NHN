package day04.first;

public class Test {
    public static void printEnumerable(Enumerable enumerable) {
        ListEnumerator list = (ListEnumerator) enumerable.iterator();
        while (list.hasMoreElement()) {
            System.out.println(list.current());
        }
    }
    public static void main(String[] args) {
        GuitaristList list = new GuitaristList();

        Guitarist jimiHendrix = new Guitarist.Builder("jimi").genre("rock")
            .guitarType("stratocaster").build();
        list.add(jimiHendrix);

        Guitarist kurtCobain = new Guitarist.Builder("kurt").genre("alternative rock")
            .guitarType("mustang").team("nirvana").build();
        list.add(kurtCobain);

        ListEnumerator listEnumerator = (ListEnumerator) list.iterator();
        while (listEnumerator.hasMoreElement()) {
            System.out.println(listEnumerator.current());
        }

        // printEnumerable(list);
    }
}
