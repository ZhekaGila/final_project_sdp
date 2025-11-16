package observer.core;

import builder.product.Product;
import observer.events.ProductEventType;

public interface IProductSubject {
    void addObserver(IProductObserver observer);
    void removeObserver(IProductObserver observer);
    void notifyObservers(ProductEventType type, Product product);
}
