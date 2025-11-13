package observer.core;

public interface IProductSubject {
    void addObserver(IProductObserver observer);
    void removeObserver(IProductObserver observer);
    void notifyObservers();
}
