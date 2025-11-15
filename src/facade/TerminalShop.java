package facade;

import builder.product.*;
import builder.product.components.*;
import builder.product.concrete.ComputerBuilder;
import builder.product.core.IProductBuilder;

import cart.Cart;
import model.User;
import model.wallet.Wallet;
import model.wallet.WalletType;
import observer.observers.ProductUserObserver;
import observer.subjects.ProductCatalog;
import strategy.concrete.*;
import strategy.core.IDiscountStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalShop {

    // Главный каталог и список всех пользователей
    private static ProductCatalog catalog = new ProductCatalog();
    private static List<User> users = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Создаём admin
        User admin = new User("admin", new Wallet(Float.MAX_VALUE, WalletType.CARD));
        users.add(admin);

        System.out.println("Welcome to the Terminal Computer Shop!");

        while (true) {
            System.out.println("\n1. Register as new user\n2. Login\n3. Exit");
            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> registerUser(scanner);
                case 2 -> {
                    User currentUser = login(scanner);
                    if (currentUser != null) {
                        if (currentUser == admin) {
                            adminMenu(scanner, currentUser);
                        } else {
                            userMenu(scanner, currentUser);
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Bye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String name = scanner.next();
        System.out.print("Initial balance: ");
        float balance = readFloat(scanner);
        User user = new User(name, new Wallet(balance, WalletType.CARD));
        users.add(user);
        catalog.addObserver(new ProductUserObserver(user)); // подписка на уведомления
        System.out.println("User registered successfully!");
    }

    private static User login(Scanner scanner) {
        System.out.print("Enter username: ");
        String login = scanner.next();
        return users.stream().filter(u -> u.getName().equals(login)).findFirst().orElseGet(() -> {
            System.out.println("User not found!");
            return null;
        });
    }

    private static void adminMenu(Scanner scanner, User admin) {
        while (true) {
            System.out.println("\nAdmin Menu: 1. Add product 2. View catalog 3. Logout");
            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> addProduct(scanner);
                case 2 -> showCatalog();
                case 3 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void userMenu(Scanner scanner, User user) {
        Cart cart = user.getCart();
        IDiscountStrategy discountStrategy = new NoIDiscountStrategy();
        CheckoutFacade checkoutFacade = new CheckoutFacade(user, cart, discountStrategy);

        while (true) {
            System.out.println("\nUser Menu: 1. View catalog 2. Add to cart 3. View cart 4. Apply discount 5. Checkout 6. Logout");
            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> showCatalog();
                case 2 -> addToCart(scanner, user);
                case 3 -> {
                    System.out.println("Cart total: " + cart.getTotal());
                    System.out.println("Products in cart:");
                    for (Product p : cart.getItems()) {
                        System.out.println(p);
                    }
                }
                case 4 -> {
                    System.out.println("Select discount strategy:\n1. No discount\n2. 10% discount\n3. Fixed discount 5000");
                    int discountChoice = readInt(scanner);
                    switch (discountChoice) {
                        case 1 -> checkoutFacade.setDiscountStrategy(new NoIDiscountStrategy());
                        case 2 -> checkoutFacade.setDiscountStrategy(new PercentageIDiscountStrategy(0.1f));
                        case 3 -> checkoutFacade.setDiscountStrategy(new FixedIDiscountStrategy(5000));
                        default -> System.out.println("Invalid option. No discount applied.");
                    }
                }
                case 5 -> checkoutFacade.checkout();
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void addProduct(Scanner scanner) {
        IProductBuilder builder = new ComputerBuilder();

        System.out.print("Enter CPU name: ");
        String cpuName = scanner.next();
        System.out.print("Enter CPU cores: ");
        int cores = readInt(scanner);
        System.out.print("Enter CPU threads: ");
        int threads = readInt(scanner);
        System.out.print("Enter CPU price: ");
        float cpuPrice = readFloat(scanner);
        CPU cpu = new CPU(cpuName, cores, threads, cpuPrice);

        System.out.print("Enter RAM name: ");
        String ramName = scanner.next();
        System.out.print("Enter RAM size GB: ");
        int ramSize = readInt(scanner);
        System.out.print("Enter RAM price: ");
        float ramPrice = readFloat(scanner);
        RAM ram = new RAM(ramName, ramSize,ramPrice);

        System.out.print("Enter GPU name: ");
        String gpuName = scanner.next();
        System.out.print("Enter GPU memory GB: ");
        int gpuMemory = readInt(scanner);
        System.out.print("Enter GPU price: ");
        float gpuPrice = readFloat(scanner);
        GPU gpu = new GPU(gpuName, gpuMemory,gpuPrice);

        System.out.print("Enter Storage name: ");
        String storageName = scanner.next();
        System.out.print("Enter Storage type (SSD/HDD): ");
        String type = scanner.next();
        System.out.print("Enter Storage size GB: ");
        int storageSize = readInt(scanner);
        System.out.print("Enter Storage price: ");
        float storagePrice = readFloat(scanner);
        Storage storage = new Storage(storageName, type, storageSize, storagePrice);

        Product product = builder.setCPU(cpu).setRAM(ram).setGPU(gpu).setStorage(storage).getComputer();
        catalog.addNewProduct(product);
        System.out.println("Product added to catalog!");
    }

    private static void showCatalog() {
        System.out.println("\n--- Product Catalog ---");
        for (Product p : catalog.getProducts()) { // дополним ProductCatalog методом getProducts()
            System.out.println(p);
        }
    }

    private static void addToCart(Scanner scanner, User user) {
        List<Product> products = catalog.getProducts();
        if (products.isEmpty()) {
            System.out.println("Catalog is empty.");
            return;
        }
        System.out.println("Select product to add:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i).getCPU().getDescription() + " | Price: " + products.get(i).getPrice());
        }
        int choice = readInt(scanner) - 1;
        if (choice >= 0 && choice < products.size()) {
            user.getCart().addProduct(products.get(choice));
            System.out.println("Product added to cart.");
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private static int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid integer.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static float readFloat(Scanner scanner) {
        while (!scanner.hasNextFloat()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
        }
        return scanner.nextFloat();
    }
}
