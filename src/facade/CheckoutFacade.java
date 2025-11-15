package facade;

import cart.Cart;
import factory.client.PaymentFactory;
import factory.core.PaymentCreator;
import model.User;
import model.wallet.Wallet;
import strategy.core.IDiscountStrategy;

public class CheckoutFacade {
    private final User user;
    private IDiscountStrategy IDiscountStrategy;

    private String successMessage = "Order completed!";
    private String failureMessage = "Order is not completed!";

    public CheckoutFacade(User user, IDiscountStrategy IDiscountStrategy) {
        this.user = user;
        this.IDiscountStrategy = IDiscountStrategy;
    }

    public void setDiscountStrategy(IDiscountStrategy strategy) {
        this.IDiscountStrategy = strategy;
    }

    public void checkout() {

        Wallet wallet = user.getWallet();
        Cart cart = user.getCart();

        float originalTotal = cart.getTotal();
        float total = originalTotal;

        if (IDiscountStrategy != null) {
            total = IDiscountStrategy.applyDiscount(originalTotal);
            System.out.println(IDiscountStrategy.getDescription(originalTotal));
        }

        System.out.println("Final price: " + total);

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
