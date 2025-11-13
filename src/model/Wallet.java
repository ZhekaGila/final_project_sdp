package model;

public class Wallet {
    private float balance;

    private String descriptionMessage = "Wallet balance : " + balance ;

    public Wallet(float balance) {
        this.balance = balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
    public float getBalance() {
        return balance;
    }


    public String getDescription() {
        return descriptionMessage;
    }

}
