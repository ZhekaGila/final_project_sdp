package factory.concrete;

import factory.core.IPayment;
import model.User;

public class CardPayment implements IPayment {

    private String paymentSuccessMessage = "Payment process was succeed";
    private String paymentErrorMessage = "Not enough balance. Payment process was not succeed";

    @Override
    public void processPayment(User user, float price) {
        float balance = user.getWallet().getBalance();
        if (balance < price) {
            System.out.println(paymentErrorMessage);
        } else{
            user.getWallet().setBalance(balance - price);
            System.out.println(paymentSuccessMessage);
        }
    }
}
