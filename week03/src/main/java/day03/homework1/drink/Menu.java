package day03.homework1.drink;

public enum Menu implements Drink {

    ICEAMERICANO(5100, Type.HOT),
    HOTAMERICANO(4500, Type.HOT),
    ESSPRESSO(4000, Type.HOT),
    COLDBLUE(5500, Type.ICE);

    private final int price;
    private final Type type;

    Menu(int price, Type type) {
        this.price = price;
        this.type = type;
    }

    public int getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return "[" + this.name() + "] 가격: " + this.price;
    }

}
