package model;

import builder.product.Product;
import cart.Cart;
import model.wallet.Wallet;
import observer.subjects.ProductCatalog;

public class User {

    private String name;
    private Wallet wallet;
    private Cart cart;

    public User(String name, Wallet wallet) {
        this.name = name;
        this.wallet = wallet;
        this.cart = new Cart();
    }

    public void setName(String name) {this.name = name;}
    public void setCart(Cart cart) {this.cart = cart;}
    public void setWallet(Wallet wallet) {this.wallet = wallet;}

    public Wallet getWallet() {
        return wallet;
    }

    public String getName() {
        return name;
    }

    public Cart getCart() {
        return cart;
    }
}
