package vending;

/**
 * Use Template method Pattern
 */
public abstract class VendingMachine {
    int cost;
    
    protected void setCost(int cost) {
        this.cost = cost;
    }
    
    public void vend() {
        selectProduct();
        pay(cost);
        getProduct();
    }

    abstract void selectProduct();
    abstract void getProduct();
    
    void pay(int cost) {
        selectPayment();
        calulate(cost);
        closePay();
    }
    
    abstract void selectPayment();
    abstract void calulate(int inputMoney);
    abstract void closePay();
}
