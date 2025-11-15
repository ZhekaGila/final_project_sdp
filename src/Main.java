import builder.product.*;
import builder.product.components.*;
import builder.product.concrete.ComputerBuilder;
import builder.product.core.IProductBuilder;

import cart.Cart;
import facade.CheckoutFacade;

import model.User;
import model.promocode.*;
import model.wallet.Wallet;
import model.wallet.WalletType;
import observer.observers.ProductUserObserver;
import observer.subjects.ProductCatalog;
import strategy.concrete.*;
import strategy.core.IDiscountStrategy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Создаём пользователя
        User currentUser = new User("Alice", new Wallet(1000000, WalletType.CARD));

        // Создаём каталог и добавляем наблюдателя
        ProductCatalog catalog = new ProductCatalog();
        catalog.addObserver(new ProductUserObserver(currentUser));

        // Строим продукты
        IProductBuilder builder = new ComputerBuilder();

        Product pc1 = builder
                .setCPU(new CPU("Intel Core i7-13700K", 8, 32, 170000))
                .setRAM(new RAM("Kingston Fury 32GB DDR5", 45000, 16))
                .setGPU(new GPU("NVIDIA RTX 4070", 350000, 8))
                .setStorage(new Storage("Samsung 990 Pro 1TB SSD", "SSD", 1024, 65000))
                .getComputer();

        Product pc2 = builder
                .setCPU(new CPU("Intel Core i3-50042", 4, 16, 100000))
                .setRAM(new RAM("Kingston Gay 32GB DDR5", 16, 75000))
                .setGPU(new GPU("NVIDIA GTX 2040", 8, 200000))
                .setStorage(new Storage("Xiaomi 50 500 GB HDD", "HDD", 512, 52000))
                .getComputer();

        // Добавляем продукты в каталог
        catalog.addNewProduct(pc1);
        catalog.addNewProduct(pc2);

        Cart cart = currentUser.getCart();
        IDiscountStrategy discountStrategy = new NoIDiscountStrategy();
        CheckoutFacade checkout = new CheckoutFacade(currentUser, cart, discountStrategy);

        PromocodeStorage promocodeStorage = new PromocodeStorage();

        boolean running = true;

        while (running) {
            System.out.println("\n--- Computer Shop Menu ---");
            System.out.println("1. Show catalog");
            System.out.println("2. Add product to cart");
            System.out.println("3. View cart");
            System.out.println("4. Apply promocode");
            System.out.println("5. Checkout");
            System.out.println("6. Exit");
            System.out.print("> ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("Catalog:");
                    System.out.println("1. " + pc1);
                    System.out.println("2. " + pc2);
                }
                case 2 -> {
                    System.out.println("Which product to add? (1 or 2)");
                    int prodChoice = scanner.nextInt();
                    if (prodChoice == 1) cart.addProduct(pc1);
                    else if (prodChoice == 2) cart.addProduct(pc2);
                    System.out.println("Product added!");
                }
                case 3 -> {
                    System.out.println("Your cart:");
                    for (Product p : cart.getItems()) {
                        System.out.println(p);
                    }
                    System.out.println("Total: " + cart.getTotal());
                }
                case 4 -> {
                    System.out.print("Enter promocode: ");
                    String code = scanner.next();

                    if (checkout.isPromocodeApplied()) {
                        System.out.println("You can use only one promocode per session.");
                        break; // выходим из case, не применяем скидку повторно
                    }

                    Promocode promocode = promocodeStorage.find(code);

                    if (promocode == null) {
                        System.out.println("Promocode not found.");
                    }else if (promocode.isUsed()) {
                        System.out.println("Promocode already used in this session.");
                    }
                    else {
                        // применяем стратегию
                        checkout.applyDiscountStrategy(new PromocodeStrategy(promocode));

                        // выводим информацию о скидке
                        float oldPrice = cart.getTotal();
                        float newPrice = checkout.calculateFinalPrice();
                        float saved = oldPrice - newPrice;
                        float percent = (saved / oldPrice) * 100;

                        System.out.printf("✔ Promocode applied: %s | Price: %.2f → %.2f (saved %.2f, %.0f%%)\n",
                                promocode.getCode(), oldPrice, newPrice, saved, percent);
                    }
                }


                case 5 -> checkout.checkout();
                case 6 -> {
                    running = false;
                    System.out.println("Bye!");
                }
                default -> System.out.println("Invalid option!");
            }
        }

        scanner.close();
    }
}
