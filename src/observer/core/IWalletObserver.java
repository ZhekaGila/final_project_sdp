package observer.core;

import model.wallet.Wallet;

public interface IWalletObserver {
    void update(Wallet wallet);
}
