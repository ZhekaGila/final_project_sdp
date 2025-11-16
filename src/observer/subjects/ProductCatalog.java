package observer.subjects;

import builder.product.Product;
import observer.core.IProductObserver;
import observer.core.IProductSubject;
import observer.events.ProductEventType;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog implements IProductSubject {

    private List<Product> products = new ArrayList<Product>();
    private final List<IProductObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(IProductObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IProductObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(ProductEventType type, Product product) {
        for (IProductObserver observer : observers) {
            observer.update(type, product);
        }
    }

    public void addProduct(Product product) {
        products.add(product);
        notifyObservers(ProductEventType.ADDED, product);
    }

    public void removeProduct(Product product) {
        if(products.contains(product)) {
            products.remove(product);
            notifyObservers(ProductEventType.REMOVED, product);
        }
    }

    public Product findProduct(String productName) {
        for (Product product : products) {
            if(product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
