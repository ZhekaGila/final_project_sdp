package facade.checkout;

import cart.Cart;
import factory.client.PaymentFactory;
import factory.core.PaymentCreator;
import model.User;
import model.wallet.Wallet;
import strategy.concrete.NoIDiscountStrategy;
import strategy.core.IDiscountStrategy;

public class CheckoutFacade {

    private IDiscountStrategy discountStrategy;
    private boolean promocodeApplied = false;

    public CheckoutFacade(IDiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public boolean isPromocodeApplied() {
        return promocodeApplied;
    }

    public void applyDiscountStrategy(IDiscountStrategy strategy) {
        if (promocodeApplied) {
            System.out.println("You can use only one promocode.");
            return;
        }
        this.discountStrategy = strategy;
        promocodeApplied = true;
    }

    public float calculateFinalPrice(User user) {
        return discountStrategy.applyDiscount(user.getCart().getTotal());
    }

    public void clearDiscount(){
        this.discountStrategy = new NoIDiscountStrategy();
        promocodeApplied = false;
    }

    public boolean checkout(User user) {

        Wallet wallet = user.getWallet();
        Cart cart = user.getCart();

        float originalTotal = cart.getTotal();
        float total = originalTotal;

        System.out.println("Total price: " + total);
        if (discountStrategy != null) {
            total = discountStrategy.applyDiscount(originalTotal);
            System.out.println(discountStrategy.getDescription(originalTotal));
        }

        System.out.println("Final price: " + total);

        PaymentCreator creator = PaymentFactory.getCreator(wallet.getType());

        return creator.createPayment().processPayment(user, total);
    }
}
