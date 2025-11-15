package model.promocode;

public class Promocode {

    private final String code;
    private final PromocodeType type; // FIXED или PERCENTAGE
    private final float value;

    private boolean used = false;

    public Promocode(String code, PromocodeType type, float value) {
        this.code = code;
        this.type = type;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public PromocodeType getType() {
        return type;
    }

    public float getValue() {
        return value;
    }

    public boolean isUsed() {
        return used;
    }

    public void markUsed() {
        this.used = true;
    }
}
