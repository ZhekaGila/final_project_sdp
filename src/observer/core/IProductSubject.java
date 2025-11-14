package observer.core;

import builder.product.Product;

public interface IProductSubject {
    void addObserver(IProductObserver observer);
    void removeObserver(IProductObserver observer);
    void notifyObservers(Product product);
}
