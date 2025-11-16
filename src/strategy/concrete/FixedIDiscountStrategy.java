package strategy.concrete;

import strategy.core.IDiscountStrategy;

public class FixedIDiscountStrategy implements IDiscountStrategy {

    private final float discount;
    private String descriptionMessageTemplate = "Fixed discount: -%.2f | saved: %.2f";

    public FixedIDiscountStrategy(float discount) {
        this.discount = discount;
    }

    @Override
    public float applyDiscount(float total) {
        float discounted = total - discount;
        return Math.max(discounted, 0);
    }

    @Override
    public String getDescription(float originalTotal) {
        float newPrice = applyDiscount(originalTotal);
        float saved = originalTotal - newPrice;
        return String.format(descriptionMessageTemplate, discount, saved);
    }
}
