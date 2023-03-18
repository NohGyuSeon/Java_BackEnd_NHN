package day03.homework1.factory;


import day03.homework1.drink.Drink;

public class IceFactory implements DrinkFactory {
    private String cupName;

    public IceFactory() {
        drinkFactories.add(this);
    }

    @Override
    public final Drink create(Drink drink) throws InterruptedException {
        setCupName();
        hasCup();
        getIce();
        getDrink();

        return drink;
    }

    @Override
    public void setCupName() {
        this.cupName = "플라스틱 컵";
    }

    @Override
    public void hasCup() {
        System.out.println(this.cupName + "을 받으세요");
    }
    /**
     * 아이스를 받는 절차에 대한 차별성 -> 팩토리로 책임을 구현 가능
     */
    public void getIce() throws InterruptedException {
        System.out.println("얼음을 받는 중입니다..");
        Thread.sleep(1000);
    }

    @Override
    public void getDrink() throws InterruptedException {
        System.out.println("아이스 음료를 받는 중입니다..");
        Thread.sleep(2000);
    }

}
