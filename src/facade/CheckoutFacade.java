package facade;

import cart.Cart;
import factory.client.PaymentFactory;
import factory.core.PaymentCreator;
import model.User;
import model.wallet.Wallet;
import strategy.core.IDiscountStrategy;

public class CheckoutFacade {

    private final User user;
    private final Cart cart;
    private IDiscountStrategy IDiscountStrategy;

    private String successMessage = "Order completed!";
    private String failureMessage = "Order is not completed!";

    public CheckoutFacade(User user, Cart cart, IDiscountStrategy IDiscountStrategy) {
        this.user = user;
        this.cart = cart;
        this.IDiscountStrategy = IDiscountStrategy;
    }

    public void setDiscountStrategy(IDiscountStrategy strategy) {
        this.IDiscountStrategy = strategy;
    }

    public void checkout() {

        Wallet wallet = user.getWallet();

        float total = cart.getTotal();
        total = IDiscountStrategy.applyDiscount(total);

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
