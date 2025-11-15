package cart;

import builder.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private List<Product> products = new ArrayList<Product>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean removeProduct(Product product) {
        if(products.contains(product)) {
            products.remove(product);
            return true;
        }
        return false;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
