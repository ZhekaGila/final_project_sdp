package strategy.concrete;

import strategy.core.IDiscountStrategy;

public class NoIDiscountStrategy implements IDiscountStrategy {

    private String descriptionMessage = "No discount applied.";

    @Override
    public float applyDiscount(float total) {
        return total;
    }

    @Override
    public String getDescription(float originalTotal) {
        return descriptionMessage;
    }
}

