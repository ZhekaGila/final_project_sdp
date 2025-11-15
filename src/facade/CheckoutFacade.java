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
    private IDiscountStrategy discountStrategy;

    private String successMessage = "Order completed!";
    private String failureMessage = "Order is not completed!";
    private boolean promocodeApplied = false;


    public CheckoutFacade(User user, Cart cart, IDiscountStrategy discountStrategy) {
        this.user = user;
        this.cart = cart;
        this.discountStrategy = discountStrategy;
    }

    public boolean isPromocodeApplied() {
        return promocodeApplied;
    }

    public void applyDiscountStrategy(IDiscountStrategy strategy) {
        if (promocodeApplied) {
            System.out.println("You can use only one promocode per session.");
            return;
        }
        this.discountStrategy = strategy;
        promocodeApplied = true;
    }

    public float calculateFinalPrice() {
        return discountStrategy.applyDiscount(cart.getTotal());
    }

    public void checkout() {

        Wallet wallet = user.getWallet();

        float total = calculateFinalPrice();
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
