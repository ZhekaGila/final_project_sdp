package observer.observers;

import model.User;
import model.Wallet;
import observer.core.IWalletObserver;

public class WalletUserObserver implements IWalletObserver{

    private User user;

    public WalletUserObserver(User user){
        this.user=user;
    }

    private String getUpdateMessage(Wallet wallet) {
        return "User " + user.getName() + " received a notification: the balance has changed to: " + wallet.getBalance();
    }

    @Override
    public void update(Wallet wallet){
        String message = getUpdateMessage(wallet);
        System.out.println(message);
    }

}
