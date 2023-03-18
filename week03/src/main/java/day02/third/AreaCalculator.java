package day02.third;

public class AreaCalculator {
    public static Number sumOfShapes(Calculable a, Calculable b) {
        return a.getArea().doubleValue() + b.getArea().doubleValue();
    }
}
