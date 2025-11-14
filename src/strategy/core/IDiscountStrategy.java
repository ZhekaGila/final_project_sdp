package strategy.core;

public interface IDiscountStrategy {
    float applyDiscount(float total);
    String getDescription(float originalTotal);
}
