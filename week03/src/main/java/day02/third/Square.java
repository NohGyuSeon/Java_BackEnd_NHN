package day02.third;

public class Square implements Calculable {
    private int length;

    public Square(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public Number getArea() {
        return length * length;
    }

}
