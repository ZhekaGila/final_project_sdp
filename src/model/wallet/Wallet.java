package model.wallet;

public class Wallet {

    private float balance;
    private WalletType type;
    private String descriptionMessageTemplate = "Wallet Type: %s | Balance: %.2f";

    public Wallet(float balance, WalletType type) {
        this.balance = balance;
        this.type = type;
    }

    public void deposit(float amount) {
        balance += amount;
    }

    public boolean withdraw(float amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setType(WalletType type) {
        this.type = type;
    }

    public float getBalance() {
        return balance;
    }

    public WalletType getType() {
        return this.type;
    }

    public String getDescription() {
        return String.format(descriptionMessageTemplate, type.name(), balance);
    }
}
