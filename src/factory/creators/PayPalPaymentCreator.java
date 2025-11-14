package factory.creators;

import factory.core.IPayment;
import factory.core.PaymentCreator;
import factory.concrete.PayPalPayment;

public class PayPalPaymentCreator extends PaymentCreator {

    @Override
    public IPayment createPayment() {
        return new PayPalPayment();
    }
}
