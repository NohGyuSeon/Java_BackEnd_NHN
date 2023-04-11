package exam2;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

/**
 * Java Collection Framework에서 제공하는 클래스 중에 Red-Black Tree를 사용하는 클래스를 사용하여 아래 문제의 풀이를 작성하세요.
 * TreeSet과 TreeMap 중에서, Indexing이 좀 더 용이한 TreeMap을 사용하여 풀이.
 */
public class RedBlackTree {
    //TODO#1 정수를 저장하는 해당 클래스 인스턴스를 작성하세요.
    private final TreeMap<Integer, Integer> tree = new TreeMap<>();
    private final Random random = new Random();

    public void addRandomInt() {
        //TODO#2 객체에 30개의 무작위 정수를 추가하세요.
        // 범위는 1부터 30까지로 지정
        System.out.println("객체에 30개의 무작위 정수를 추가");
        for (int i = 0; i < 30; i++) {
            tree.put(i, random.nextInt(30));
        }
        System.out.println();
    }

    //TODO#3 삽입된 무작위 정수들을 삽입 순서대로 출력하세요.
    public void printRandomValue() {
        System.out.println("삽입된 무작위 정수들을 삽입 순서대로 출력");
        for (int i = 0; i < tree.size(); i++) {
            System.out.print(tree.get(i) + " ");
        }
        System.out.println('\n');
    }

    //TODO#4 삽입된 무작위 정수들을 내림차순으로 정렬해서 출력하세요.
    public void printOrderedValue() {
        List<Entry<Integer, Integer>> entryList = new LinkedList<>(tree.entrySet());
        entryList.sort(((o1, o2) -> tree.get(o2.getKey()) - tree.get(o1.getKey())));

        System.out.println("삽입된 무작위 정수들을 내림차순으로 정렬해서 출력");
        for (Map.Entry<Integer, Integer> entry : entryList) {
            System.out.print(entry.getValue() + " ");
        }
        System.out.println('\n');
    }

    //TODO#5 삽입된 무작위 정수들의 합을 구하는 메소드를 작성하고, 실행해서 출력하는 코드를 작성하세요.
    public void printSumOfRandomValue() {
        int sum = 0;

        for (int i = 0; i < tree.size(); i++) {
            sum += tree.get(i);
        }
        System.out.println("삽입된 무작위 정수들의 합을 구해서 출력");
        System.out.println(sum + '\n');
    }
}

class Test {
    public static void main(String[] args) {
        RedBlackTree redBlackTree = new RedBlackTree();
        try {
            redBlackTree.addRandomInt();
            redBlackTree.printRandomValue();
            redBlackTree.printOrderedValue();
            redBlackTree.printSumOfRandomValue();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
