package adapter;

import factory.core.IPayment;
import factory.core.PaymentCreator;

public class KaspiPaymentCreator extends PaymentCreator {

    @Override
    public IPayment createPayment() {
        return new KaspiPaymentAdapter();
    }
}
