package adapter;

import factory.core.IPayment;
import model.User;

public class KaspiPaymentAdapter implements IPayment {

    private KaspiBankService kaspiService;

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

    public KaspiBankService getKaspiService() {
        return kaspiService;
    }

    public void setKaspiService(KaspiBankService kaspiService) {
        this.kaspiService = kaspiService;
    }
}
