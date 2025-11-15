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
    private IDiscountStrategy IDiscountStrategy;

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
    public CheckoutFacade(User user, IDiscountStrategy IDiscountStrategy) {
        this.user = user;
        this.IDiscountStrategy = IDiscountStrategy;
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
        Cart cart = user.getCart();

        float originalTotal = cart.getTotal();
        float total = originalTotal;

        float total = calculateFinalPrice();
        System.out.println("Total price: " + total);
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
