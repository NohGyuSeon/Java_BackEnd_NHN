package day03.homework1.framework;
import day03.homework1.drink.Drink;
import java.rmi.NoSuchObjectException;

/**
 * 자판기 판매방식 타입
 */
public interface VendingMachine {
    void addDrink(int number);
//    void createDrink(Drink drink) throws InterruptedException;
    void removeDrink(Drink drink);
    void clearDrink();
    void supplyDrink();
    void getInventoryList();
    int getInventorySize();
    void setInventorySize();
    void getMenuList();
    void doSales() throws NoSuchObjectException;

}
