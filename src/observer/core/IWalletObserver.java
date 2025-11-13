package observer.core;

import model.Wallet;

public interface IWalletObserver {
    void update(Wallet wallet);
}
