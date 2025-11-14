package model;

import cart.Cart;
import model.wallet.Wallet;

public class User {
    private String name;
    private Wallet wallet;
    private Cart cart;


    public User(String name, Wallet wallet) {
        this.name = name;
        this.wallet = wallet;
        this.cart = new Cart();
    }

    public Wallet getWallet() {
        return wallet;
    }

    public String getName() {
        return name;
    }

    public Cart getCart() {return cart;}

}
