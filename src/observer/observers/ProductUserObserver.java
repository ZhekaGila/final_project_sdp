package observer.observers;

import builder.product.Product;
import model.User;
import observer.core.IProductObserver;

public class ProductUserObserver implements IProductObserver {

    private final User user;

    public ProductUserObserver(User user) {
        this.user = user;
    }

    private String getUpdateMessage(Product product) {
        return "[User " + user.getName() + "] New product added: " + product.toString();
    }

    @Override
    public void update(Product product) {
        System.out.println(getUpdateMessage(product));
    }
}