import builder.product.*;
import builder.product.concrete.ComputerBuilder;
import builder.product.core.IProductBuilder;

import cart.Cart;
import facade.CheckoutFacade;

import facade.service.app.ShopServiceApp;
import model.User;
import model.promocode.*;
import observer.observers.ProductUserObserver;
import observer.subjects.ProductCatalog;
import strategy.concrete.*;
import strategy.core.IDiscountStrategy;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

import static facade.service.app.CreateUser.createUser;
import static facade.service.storage.ProductStorage.createProducts;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        IProductBuilder builder = new ComputerBuilder();
        List<Product> products=createProducts(builder);

        // Creating user
        User user = createUser(scanner);

        // Creating a catalog and adding observer
        ProductCatalog catalog = new ProductCatalog();
        System.out.println("Would you want to get notification about new products?[y/n]\n>");
        String answer = scanner.nextLine();

        if (answer.equals("y")){
            catalog.addObserver(new ProductUserObserver(user));
        }

        // Adding products and notify users
        for(Product product : products){
            catalog.addProduct(product);
        }

        // Creating cart and discounts with checkout
        Cart cart = user.getCart();
        IDiscountStrategy discountStrategy = new NoIDiscountStrategy();
        PromocodeStorage promocodeStorage = new PromocodeStorage();
        CheckoutFacade checkout = new CheckoutFacade(discountStrategy);

        ShopServiceApp shop = new ShopServiceApp(user, catalog, cart, checkout, promocodeStorage);

        while(shop.isRunning()){
            System.out.println("\n--- Computer Shop Menu ---");
            System.out.println("1. Show catalog");
            System.out.println("2. Add product to cart");
            System.out.println("3. View cart");
            System.out.println("4. Remove product from cart");
            System.out.println("5. Apply promocode");
            System.out.println("6. Show balance");
            System.out.println("7. Top up balance");
            System.out.println("8. Checkout");
            System.out.println("9. Exit");
            System.out.print("> ");

            int choice;

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                choice = 10;
            }

            scanner.nextLine(); // Cleaning

            shop.handleChoice(choice,scanner);
        }
    }
}

