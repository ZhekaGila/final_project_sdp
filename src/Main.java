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

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        IProductBuilder builder = new ComputerBuilder();
        // 1
        Product officePC = builder.setName("Office PC")
                .setCPU(new CPU("Intel Core i3-12100F", 4, 8, 55000))
                .setRAM(new RAM("Kingston Fury 8GB DDR4", 8, 15000))
                .setGPU(new GPU("Integrated UHD 730", 0, 0))
                .setStorage(new Storage("Kingston A400 240GB SSD", "SSD", 240, 12000))
                .getComputer();

        // 2
        Product midGaming = builder.setName("Mid Gaming PC")
                .setCPU(new CPU("AMD Ryzen 5 5600", 6, 12, 60000))
                .setRAM(new RAM("Corsair Vengeance 16GB DDR4", 16, 28000))
                .setGPU(new GPU("NVIDIA GTX 1660 Super", 6, 160000))
                .setStorage(new Storage("Samsung 870 EVO 500GB", "SSD", 500, 25000))
                .getComputer();

        // 3
        Product highEnd = builder.setName("High-End Gaming PC")
                .setCPU(new CPU("Intel Core i9-13900K", 24, 64, 220000))
                .setRAM(new RAM("G.Skill TridentZ 32GB DDR5", 32, 75000))
                .setGPU(new GPU("NVIDIA RTX 4090", 16, 950000))
                .setStorage(new Storage("Samsung 990 Pro 2TB", "SSD", 2000, 90000))
                .getComputer();

        // 4
        Product workstation = builder.setName("Rendering Workstation")
                .setCPU(new CPU("AMD Threadripper 3960X", 24, 64, 550000))
                .setRAM(new RAM("Corsair 64GB DDR4", 64, 120000))
                .setGPU(new GPU("NVIDIA RTX 4080 Super", 16, 750000))
                .setStorage(new Storage("WD Black SN850 2TB NVMe", "SSD", 2000, 110000))
                .getComputer();

        // 5
        Product miniPC = builder.setName("Mini PC")
                .setCPU(new CPU("AMD Ryzen 3 5300G", 4, 8, 35000))
                .setRAM(new RAM("Patriot Viper 8GB DDR4", 8, 14000))
                .setGPU(new GPU("Integrated Vega 6", 0, 0))
                .setStorage(new Storage("Crucial MX500 500GB", "SSD", 500, 22000))
                .getComputer();


        //просим ввести имя, баланс и тип карты
        System.out.println("Enter your name please\n>");
        String name =scanner.nextLine();

        System.out.println("Choose type of wallet: \n1.Card \n2.Paypal \n3.Kaspi\n>");
        int walletType = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter your balance:\n>");
        int balance = scanner.nextInt();
        scanner.nextLine();

        Wallet wallet = null;
        switch (walletType){
            case 1-> wallet = new Wallet(balance, WalletType.CARD);
            case 2-> wallet = new Wallet(balance, WalletType.PAYPAL);
            case 3-> wallet = new Wallet(balance, WalletType.KASPI);
            default -> System.out.println("Invalid wallet type");
        }

        // Создаём пользователя
        User user = new User(name, wallet);

        // Создаём каталог и добавляем наблюдателя
        ProductCatalog catalog = new ProductCatalog();
        System.out.println("Would you want to get notification about new products?[y/n]\n>");
        String answer = scanner.nextLine();

        if (answer.equals("y")){
            catalog.addObserver(new ProductUserObserver(user));
        }

        //добавляем и уведомляется
        catalog.addProduct(officePC);
        catalog.addProduct(midGaming);
        catalog.addProduct(highEnd);
        catalog.addProduct(workstation);
        catalog.addProduct(miniPC);


        Cart cart = user.getCart();
        IDiscountStrategy discountStrategy = new NoIDiscountStrategy();
        PromocodeStorage promocodeStorage = new PromocodeStorage();
        CheckoutFacade checkout = new CheckoutFacade(discountStrategy);

        boolean running = true;
        while(running){
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
                choice = 10;        //или любое значение по умолчанию
            }

            scanner.nextLine(); //очищаем

            switch (choice) {
                case 1 -> {
                    System.out.println(catalog.getProducts());
                }
                case 2 -> {
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
                case 3 -> {
                    System.out.println(cart.getItems());
                }
                case 4 -> {
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
                case 5 -> {
                    System.out.print("Enter promocode: ");
                    String code = scanner.nextLine();

                    if (checkout.isPromocodeApplied()) {
                        System.out.println("You can use only one promocode per session.");
                        break;
                    }
                    Promocode promocode = promocodeStorage.find(code);

                    if (promocode == null) {
                        System.out.println("Promocode not found.");
                    }else if (promocode.isUsed()) {
                        System.out.println("Promocode already used in this session.");
                    }
                    else {
                        //применяем стратегию
                        checkout.applyDiscountStrategy(new PromocodeStrategy(promocode));

                        //выводим информацию о скидке
                        float oldPrice = cart.getTotal();
                        float newPrice = checkout.calculateFinalPrice(user);
                        float saved = oldPrice - newPrice;
                        float percent = (saved / oldPrice) * 100;

                        System.out.printf("Promocode applied: %s | Price: %.2f → %.2f (saved %.2f, %.0f%%)\n",
                                promocode.getCode(), oldPrice, newPrice, saved, percent);
                    }
                }
                case 6 -> {
                    System.out.println("Your balance: " + user.getWallet().getBalance() );
                }
                case 7 -> {
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
                case 8 ->{
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
                case 9 -> {
                    System.out.println("Thank you!!! Good bye");
                    running = false;
                }
                case 10 -> {
                    System.out.println("Please enter a valid number!");
                }
                default -> System.out.println("Invalid option!");
            }
        }
    }
}

