package model.promocode;

import java.util.HashMap;
import java.util.Map;

public class PromocodeStorage {

    private final Map<String, Promocode> promocodes = new HashMap<>();

    public PromocodeStorage() {
        // default promocodes
        promocodes.put("WELCOME10", new Promocode("WELCOME10", PromocodeType.PERCENTAGE, 0.10f));
        promocodes.put("SAVE100", new Promocode("SAVE100", PromocodeType.FIXED, 100f));
    }

    public Promocode find(String code) {
        return promocodes.get(code.toUpperCase());
    }
}
