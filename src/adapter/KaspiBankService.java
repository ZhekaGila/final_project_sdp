package adapter;

public class KaspiBankService {

    public boolean sendPayment(float balance, float money) {
        System.out.println("KaspiBank: paid " + money + " with balance " + balance);
        return true;
    }
}
