package facade;

import cart.Cart;
import factory.client.PaymentFactory;
import factory.core.PaymentCreator;
import model.User;
import model.wallet.Wallet;
import model.wallet.WalletType;

public class CheckoutFacade {

    private final User user;
    private final Cart cart;

    private String successMessage = "Order completed!";
    private String failureMessage = "Order is not completed!";

    public CheckoutFacade(User user, Cart cart) {
        this.user = user;
        this.cart = cart;
    }

    public void checkout() {

        Wallet wallet = user.getWallet();

        float total = cart.getTotal();

        System.out.println("Total price: " + total);

        PaymentCreator creator = PaymentFactory.getCreator(wallet.getType());

        boolean success = creator.createPayment().processPayment(user, total);

        if (success) {
            System.out.println(successMessage);
            cart.clear();
        } else {
            System.out.println(failureMessage);
        }
    }
}
