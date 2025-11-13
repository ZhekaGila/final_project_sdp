package observer.subjects;

import model.Wallet;
import observer.core.IWalletObserver;
import observer.core.IWalletSubject;

public class ObservableWallet implements IWalletSubject {
    private Wallet wallet;
    private IWalletObserver observer;

    public ObservableWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    @Override
    public void addObserver(IWalletObserver observer) {
        this.observer = observer;
    }

    @Override
    public void removeObserver(IWalletObserver observer) {
        if(this.observer == observer){
            this.observer = null;
        }
    }

    @Override
    public void notifyObservers() {
        if(observer != null){
            observer.update(wallet);
        }
    }
}
