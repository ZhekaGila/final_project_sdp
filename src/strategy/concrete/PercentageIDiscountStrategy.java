package strategy.concrete;

import strategy.core.IDiscountStrategy;

public class PercentageIDiscountStrategy implements IDiscountStrategy {

    private final float percent;
    private String descriptionMessageTemplate = "Percentage discount: %.0f%% | saved: %.2f";

    public PercentageIDiscountStrategy(float percent) {
        this.percent = percent;
    }

    @Override
    public float applyDiscount(float total) {
        return total * (1 - percent);
    }

    @Override
    public String getDescription(float originalTotal) {
        float newPrice = applyDiscount(originalTotal);
        float saved = originalTotal - newPrice;
        return String.format(descriptionMessageTemplate, percent * 100, saved);
    }
}
