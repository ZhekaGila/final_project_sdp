package adapter;

import factory.core.IPayment;
import model.User;
import model.wallet.Wallet;

public class KaspiPaymentAdapter implements IPayment {

    private final KaspiBankService kaspiService;

    public KaspiPaymentAdapter() {
        this.kaspiService = new KaspiBankService();
    }

    @Override
    public boolean processPayment(User user, float amount) {
        float balance = user.getWallet().getBalance();
        boolean result = user.getWallet().withdraw(amount);

        if (!result) {
            return false;
        }

        return kaspiService.sendPayment(balance, amount);
    }
}
