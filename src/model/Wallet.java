package model;

public class Wallet {
    private float balance;
    private WalletType type;

    private String descriptionMessageTemplate = "Wallet Type: %s | Balance: %.2f";

    public Wallet(float balance, WalletType type) {
        this.balance = balance;
        this.type = type;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
    public float getBalance() {
        return balance;
    }

    public WalletType getType() {
        return this.type;
    }

    public void setType(WalletType type) {}

    public String getDescription() {
        return String.format(descriptionMessageTemplate, type.name(), balance);
    }

}
