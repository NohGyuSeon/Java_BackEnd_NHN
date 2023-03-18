package day03.homework1.factory;


import day03.homework1.drink.Drink;

/**
 * 핫 메뉴는 개별 추상 클래스로 템플릿화 시킬 가능성
 */
public class HotFactory implements DrinkFactory {
    private String cupName;

    public HotFactory() {
        drinkFactories.add(this);
    }

    @Override
    public Drink create(Drink drink) throws InterruptedException {
        setCupName();
        hasCup();
        getDrink();

        return drink;
    }

    @Override
    public void setCupName() {
        this.cupName = "종이 컵";
    }

    @Override
    public void getDrink() throws InterruptedException {
        System.out.println("핫 음료를 받는 중입니다..");
        Thread.sleep(2000);
    }

    @Override
    public void hasCup() {
        System.out.println(this.cupName + "을 받으세요");
    }

}
