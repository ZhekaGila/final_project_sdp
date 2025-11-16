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

    public boolean addToCart(ProductCatalog catalog, Product product) {
        if (catalog.getProducts().contains(product)) {
            cart.addProduct(product);
            return true;
        }
        return false;
    }

    public boolean removeFromCart(Product product) {
        if (cart.exists(product)) {
            cart.removeProduct(product);
            return true;
        }
        return false;
    }

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
