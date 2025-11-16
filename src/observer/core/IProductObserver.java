package observer.core;


import builder.product.Product;
import observer.events.ProductEventType;

public interface IProductObserver {
    void update(ProductEventType type, Product product);
}
