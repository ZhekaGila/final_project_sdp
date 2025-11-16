package facade;

import cart.Cart;
import factory.client.PaymentFactory;
import factory.core.PaymentCreator;
import model.User;
import model.wallet.Wallet;
import strategy.concrete.NoIDiscountStrategy;
import strategy.core.IDiscountStrategy;

public class CheckoutFacade {

    private IDiscountStrategy discountStrategy;

    public CheckoutFacade() {
        this.discountStrategy = new NoIDiscountStrategy();
    }

    public CheckoutFacade(IDiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy != null
                ? discountStrategy
                : new NoIDiscountStrategy();
    }

    public boolean isPromocodeApplied() {
        return !(discountStrategy instanceof NoIDiscountStrategy);
    }

    public void applyDiscountStrategy(IDiscountStrategy strategy) {
        if (isPromocodeApplied()) {
            throw new IllegalStateException("Only one promocode can be applied.");
        }
        this.discountStrategy = strategy;
    }

    public void clearDiscount() {
        this.discountStrategy = new NoIDiscountStrategy();
    }

    public float calculateFinalPrice(User user) {
        float original = user.getCart().getTotal();
        return discountStrategy.applyDiscount(original);
    }

    public boolean checkout(User user) {

        Cart cart = user.getCart();
        Wallet wallet = user.getWallet();

        float original = cart.getTotal();
        float finalPrice = calculateFinalPrice(user);

        logPricingInfo(original, finalPrice);

        PaymentCreator creator = PaymentFactory.getCreator(wallet.getType());

        return creator.createPayment().processPayment(user, finalPrice);
    }

    private void logPricingInfo(float original, float finalPrice) {
        System.out.println("Total price: " + original);
        System.out.println(discountStrategy.getDescription(original));
        System.out.println("Final price: " + finalPrice);
    }
}
