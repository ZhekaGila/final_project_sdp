package observer.observers;

import builder.product.Product;
import model.User;
import observer.core.IProductObserver;
import observer.events.ProductEventType;

public class ProductUserObserver implements IProductObserver {

    private final User user;

    public ProductUserObserver(User user) {
        this.user = user;
    }

    public void getUpdateMessage(ProductEventType type, Product product) {
        switch (type) {
            case ADDED -> System.out.println("[User " + user.getName() + "] New product added: " + product);
            case REMOVED -> System.out.println("[User " + user.getName() + "] Product removed: " + product);
        }
    }

    @Override
    public void update(ProductEventType type, Product product) {
        getUpdateMessage(type, product);
    }
}