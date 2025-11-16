package facade.service.app;

import builder.product.Product;
import cart.Cart;
import facade.checkout.CheckoutFacade;
import model.User;
import model.promocode.Promocode;
import model.promocode.PromocodeStorage;
import observer.subjects.ProductCatalog;
import strategy.concrete.PromocodeStrategy;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ShopServiceApp {

    private final ProductCatalog catalog;
    private final Cart cart;
    private final User user;
    private final CheckoutFacade checkout;
    private final PromocodeStorage promocodeStorage;
    private boolean running = true;

    public ShopServiceApp(User user,
                          ProductCatalog catalog,
                          Cart cart,
                          CheckoutFacade checkout,
                          PromocodeStorage promocodeStorage) {
        this.user = user;
        this.catalog = catalog;
        this.cart = cart;
        this.checkout = checkout;
        this.promocodeStorage = promocodeStorage;
    }

    public void handleChoice(int choice,Scanner scanner) {
        switch (choice) {
            case 1 -> System.out.println(catalog.getProducts()); //show catalog
            case 2 -> addProductToCart(scanner);
            case 3 -> System.out.println(cart.getItems()); //view cart
            case 4 -> removeProductFromCart(scanner);
            case 5 -> applyPromocode(scanner);
            case 6 -> System.out.println("Your balance: " + user.getWallet().getBalance()); //view balance
            case 7 -> depositBalance(scanner);
            case 8 -> checkoutCart(scanner);
            case 9 -> running = false; //exit
            default -> System.out.println("Invalid option!");
        }

    }

    public boolean isRunning() {
        return running;
    }

    private void addProductToCart(Scanner scanner) {
        System.out.println(catalog.getProducts());
        System.out.println("Enter product name: ");
        String productName = scanner.nextLine();
        Product product = catalog.findProduct(productName);
        if (product!=null) {
            cart.addProduct(product);
            System.out.println("Product was added yo your cart!");
        } else{
            System.out.println("Product not found.");
        }
    }

    private void removeProductFromCart(Scanner scanner) {
        System.out.println(cart.getItems());
        System.out.println("Enter product name to remove it from cart: ");
        String productName = scanner.nextLine();
        Product product = cart.findProduct(productName);
        if (product!=null) {
            cart.removeProduct(product);
            System.out.println("Product was removed successfully!");
        } else{
            System.out.println("Product not found.");
        }
    }

    private void applyPromocode(Scanner scanner) {
        System.out.print("Enter promocode: ");
        String code = scanner.nextLine();

        if (checkout.isPromocodeApplied()) {
            System.out.println("You can use only one promocode per session.");
            return;
        }
        Promocode promocode = promocodeStorage.find(code);

        if (promocode == null) {
            System.out.println("Promocode not found.");
        }else if (promocode.isUsed()) {
            System.out.println("Promocode already used in this session.");
        }else {
            // Using strategy
            checkout.applyDiscountStrategy(new PromocodeStrategy(promocode));

            // Output the information about discount
            float oldPrice = cart.getTotal();
            float newPrice = checkout.calculateFinalPrice(user);
            float saved = oldPrice - newPrice;
            float percent = (saved / oldPrice) * 100;

            System.out.printf("Promocode applied: %s | Price: %.2f → %.2f (saved %.2f, %.0f%%)\n",
                    promocode.getCode(), oldPrice, newPrice, saved, percent);
        }
    }

    private void depositBalance(Scanner scanner) {
        System.out.println("How much do you want to deposit?");
        float deposit = 0;
        try {
            deposit = scanner.nextFloat();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number!");
        }
        if (deposit < 0) {
            System.out.println("Please enter a positive number!");
        } else{
            user.getWallet().deposit(deposit);
            System.out.println("Now your balance: " + user.getWallet().getBalance());
        }
    }

    private void checkoutCart(Scanner scanner) {
        System.out.println("Would you want to purchase products in your cart? [y/n]");
        if (scanner.nextLine().equals("y")) {
            boolean success = checkout.checkout(user);
            if(success){
                System.out.println("Order completed!");
                System.out.println("Your balance: " + user.getWallet().getBalance());
                for(Product item : cart.getItems()){
                    catalog.removeProduct(item);
                }
                cart.clear();
                checkout.clearDiscount();
            } else{
                System.out.println("Order is not completed.");
            }
        }
    }

}
