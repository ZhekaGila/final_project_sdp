package factory.client;

import factory.core.PaymentCreator;
import factory.creators.CardPaymentCreator;
import factory.creators.PayPalPaymentCreator;
import model.wallet.WalletType;

public class PaymentFactory {

    public static PaymentCreator getCreator(WalletType type) {
        switch (type) {
            case CARD:
                return new CardPaymentCreator();
            case PAYPAL:
                return new PayPalPaymentCreator();
            default:
                throw new IllegalArgumentException("Unsupported wallet type for payment: " + type);
        }
    }
}