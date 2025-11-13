package observer.subjects;

import builder.product.Product;
import observer.core.IProductObserver;
import observer.core.IProductSubject;
import java.util.ArrayList;
import java.util.List;

public class ObservableProduct implements IProductSubject {
    private Product product;
    private List<IProductObserver> observers = new ArrayList();

    public ObservableProduct(Product product) {
        this.product = product;
    }

    @Override
    public void addObserver(IProductObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IProductObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IProductObserver observer : observers) {
            observer.update(product);
        }
    }
}
