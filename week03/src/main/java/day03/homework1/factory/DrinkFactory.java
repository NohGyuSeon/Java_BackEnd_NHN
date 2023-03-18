package day03.homework1.factory;


import day03.homework1.drink.Drink;
import java.util.ArrayList;
import java.util.List;

/**
 * 팩토리 메소드 패턴 적용
 */
public interface DrinkFactory {
    List<DrinkFactory> drinkFactories = new ArrayList<>();
    Drink create(Drink drink) throws InterruptedException;
    void setCupName();
    void getDrink() throws InterruptedException;
    void hasCup();

}
