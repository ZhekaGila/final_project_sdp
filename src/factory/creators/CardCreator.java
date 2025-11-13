package factory.creators;

import factory.core.IPayment;
import factory.core.PaymentCreator;
import factory.concrete.CardPayment;

public class CardCreator extends PaymentCreator {

    @Override
    public IPayment createPayment() {
        return new CardPayment();
    }
}
