package framework;

public abstract class PaymentFactory {
    public final Payment createPayment() {
        return create();
    }

    protected abstract Payment create();
}
