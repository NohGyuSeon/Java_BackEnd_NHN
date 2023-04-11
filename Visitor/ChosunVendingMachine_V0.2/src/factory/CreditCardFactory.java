package factory;

import framework.Payment;
import framework.PaymentFactory;
import payments.CreditCard;

public class CreditCardFactory extends PaymentFactory {
    @Override
    protected Payment create() {
        return new CreditCard();
    }
}
