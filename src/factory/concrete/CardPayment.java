package factory.concrete;

import factory.core.IPayment;
import model.User;

public class CardPayment implements IPayment {

    private String paymentSuccessMessage = "Payment process was succeed";
    private String paymentErrorMessage = "Not enough balance. Payment process was not succeed";

    @Override
    public boolean processPayment(User user, float price) {
        boolean result = user.getWallet().withdraw(price);

        if (!result) {
            System.out.println(paymentErrorMessage);
            return false;
        }

        System.out.println(paymentSuccessMessage);
        return true;
    }
}
