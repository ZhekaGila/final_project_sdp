package observer.core;


import builder.product.Product;

public interface IProductObserver {
    void update(Product product);
}
