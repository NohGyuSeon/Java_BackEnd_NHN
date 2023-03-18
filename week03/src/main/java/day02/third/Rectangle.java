package day02.third;

public class Rectangle implements Calculable {
    private int witdh;
    private int height;

    public Rectangle(int witdh, int height) {
        this.witdh = witdh;
        this.height = height;
    }

    public int getWitdh() {
        return witdh;
    }

    public int getHeight() {
        return height;
    }

    public void setWitdh(int witdh) {
        this.witdh = witdh;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public Number getArea() {
        return this.witdh * this.height;
    }
}