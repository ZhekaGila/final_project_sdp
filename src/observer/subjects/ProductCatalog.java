package observer.subjects;

import builder.product.Product;
import observer.core.IProductObserver;
import observer.core.IProductSubject;

import java.util.ArrayList;
import java.util.List;

public class ProductCatalog implements IProductSubject {

    private final List<IProductObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(IProductObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IProductObserver observer) {
        observers.remove(observer);
    }

    public void addNewProduct(Product newProduct) {
        notifyObservers(newProduct);
    }

    @Override
    public void notifyObservers(Product product) {
        for (IProductObserver observer : observers) {
            observer.update(product);
        }
    }


}
