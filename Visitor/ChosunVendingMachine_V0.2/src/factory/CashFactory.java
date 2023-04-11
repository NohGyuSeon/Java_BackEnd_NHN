package factory;

import framework.Payment;
import framework.PaymentFactory;
import payments.Cash;

public class CashFactory extends PaymentFactory {
    @Override
    protected Payment create() {
        return new Cash();
    }
}
