package factory.concrete;

import factory.core.IPayment;
import model.User;

public class CardPayment implements IPayment {

    @Override
    public void processPayment(User user, float price) {
        float balance = user.getWallet().getBalance();
        if (balance < price) {
            System.out.println("Not enough balance. Payment process was not succeed");
        } else{
            user.getWallet().setBalance(balance - price);
            System.out.println("Payment process was succeed");
        }
    }
}
