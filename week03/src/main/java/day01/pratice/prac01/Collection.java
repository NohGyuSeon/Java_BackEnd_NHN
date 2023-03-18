package day01.pratice.prac01;

import java.util.ArrayList;
import java.util.List;

public class Collection {
    List<String> list = new ArrayList<>();

    public void add(String value) {
        this.list.add(value);
    }

    public void remove(String value) {
        this.list.remove(value);
    }

}
