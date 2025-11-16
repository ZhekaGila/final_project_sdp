package cart;

import builder.product.Product;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Product> items = new ArrayList<>();

    public void addProduct(Product product) {
        items.add(product);
    }

    public void removeProduct(Product product) {
        items.remove(product);
    }

    public boolean exists(Product product) {
        if (items.contains(product)) {
            return true;
        }
        return false;
    }

    public Product findProduct(String productName){
        for (Product product : items) {
            if(product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
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

    public void setItems(List<Product> items) {
        this.items = items;
    }
    public List<Product> getItems() {
        return items;
    }
}
