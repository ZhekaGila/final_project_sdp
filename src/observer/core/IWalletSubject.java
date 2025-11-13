package observer.core;

public interface IWalletSubject {
    void addObserver(IWalletObserver observer);
    void removeObserver(IWalletObserver observer);
    void notifyObservers();
}
