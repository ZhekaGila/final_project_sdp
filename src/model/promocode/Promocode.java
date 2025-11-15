package model.promocode;

public class Promocode {

    private final String code;
    private final PromocodeType type;
    private final float value;

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
}
