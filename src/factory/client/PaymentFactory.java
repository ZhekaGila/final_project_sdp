package factory.client;

import adapter.KaspiPaymentCreator;
import factory.core.PaymentCreator;
import factory.creators.CardPaymentCreator;
import factory.creators.PayPalPaymentCreator;
import model.wallet.WalletType;

public class PaymentFactory {

    public static PaymentCreator getCreator(WalletType type) {
        return switch (type) {
            case CARD -> new CardPaymentCreator();
            case PAYPAL -> new PayPalPaymentCreator();
            case KASPI -> new KaspiPaymentCreator();
            default -> throw new IllegalArgumentException("Unsupported wallet type for payment: " + type);
        };
    }
}