package strategy.concrete;

import model.promocode.Promocode;
import model.promocode.PromocodeType;
import strategy.core.IDiscountStrategy;

public class PromocodeStrategy implements IDiscountStrategy {

    private final Promocode promocode;

    public PromocodeStrategy(Promocode coupon) {
        this.promocode = coupon;
    }

    @Override
    public float applyDiscount(float total) {
        if (promocode.getType() == PromocodeType.PERCENTAGE) {
            return total * (1 - promocode.getValue());
        }
        // FIXED
        return Math.max(total - promocode.getValue(), 0);
    }

    @Override
    public String getDescription(float originalTotal) {
        float newPrice = applyDiscount(originalTotal);
        float saved = originalTotal - newPrice;

        if (promocode.getType() == PromocodeType.PERCENTAGE) {
            return String.format(
                    "Promocode '%s': %.0f%% | saved: %.2f",
                    promocode.getCode(),
                    promocode.getValue() * 100,
                    saved
            );
        }

        return String.format(
                "Promocode '%s': -%.2f | saved: %.2f",
                promocode.getCode(),
                promocode.getValue(),
                saved
        );
    }
}
