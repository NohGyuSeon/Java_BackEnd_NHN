package factory;

import framework.Payment;
import framework.PaymentFactory;
import payments.Payco;

public class PaycoFactory extends PaymentFactory {
    @Override
    protected Payment create() {
        return new Payco();
    }
}
