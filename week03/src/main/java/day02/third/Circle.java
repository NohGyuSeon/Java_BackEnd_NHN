package day02.third;

public class Circle implements Calculable{
    double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public Number getArea() {
        return 2 * radius * Math.PI;
    }
}
