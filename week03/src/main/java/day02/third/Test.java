package day02.third;

import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Test {
    public static void main(String[] args) {
        Rectangle rec = new Rectangle(5, 10);
        Square square = new Square(4);

        System.out.println(AreaCalculator.sumOfShapes(rec, square));
    }
}