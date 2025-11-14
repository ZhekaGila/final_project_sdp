package cart;

import builder.product.Product;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<Product> items = new ArrayList<>();

    public void addProduct(Product product) {
        items.add(product);
    }

    public void removeProduct(Product product) {
        items.remove(product);
    }

    public List<Product> getItems() {
        return items;
    }

    public float getTotal() {
        float total = 0;
        for (Product computer : items) {
            total += computer.getPrice();
        }
        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
}
