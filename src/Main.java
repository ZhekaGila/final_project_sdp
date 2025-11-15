import builder.product.*;
import builder.product.components.*;
import builder.product.concrete.ComputerBuilder;
import builder.product.core.IProductBuilder;

import facade.CheckoutFacade;

import model.User;
import model.promocode.Promocode;
import model.promocode.PromocodeType;
import model.wallet.Wallet;
import model.wallet.WalletType;
import observer.observers.ProductUserObserver;
import observer.subjects.ProductCatalog;
import strategy.concrete.*;
import strategy.core.IDiscountStrategy;

public class Main {
    public static void main(String[] args) {

        IProductBuilder builder = new ComputerBuilder();

        Product pc1 = builder.setName("Computer 1")
                .setCPU(new CPU("Intel Core i7-13700K", 8, 32, 170000))
                .setRAM(new RAM("Kingston Fury 32GB DDR5", 45000, 16))
                .setGPU(new GPU("NVIDIA RTX 4070", 350000, 8))
                .setStorage(new Storage("Samsung 990 Pro 1TB SSD", "SSD", 1024, 65000))
                .getComputer();

        Product pc2 = builder.setName("Computer 2")
                .setCPU(new CPU("Intel Core i3-50042", 100000, 4, 16))
                .setRAM(new RAM("Kingston Gay 32GB DDR5", 16, 75000))
                .setGPU(new GPU("NVIDIA GTX 2040", 8, 200000))
                .setStorage(new Storage("Xiaomi 50 500 GB HDD", "HDD", 512, 52000))
                .getComputer();

        System.out.println(pc1);
        System.out.println(pc2);

        User richUser = new User("Бэби Джин Богач бравл старс Мастер Туда Сюда Миллионер", new Wallet(15000000f, WalletType.CARD));
        User bitchUser = new User("Алихан", new Wallet(500000f, WalletType.PAYPAL));

        ProductCatalog catalog = new ProductCatalog();
        catalog.addObserver(new ProductUserObserver(richUser));
        catalog.addObserver(new ProductUserObserver(bitchUser));

        catalog.addNewProduct(pc1);


        richUser.getCart().addProduct(pc1);
        richUser.getCart().addProduct(pc2);

        System.out.println("\n" + richUser + "Added product to cart. Total now: " + richUser.getCart().getTotal());

        Promocode promocode = new Promocode("WELCOME10", PromocodeType.PERCENTAGE, 0.10f);
        IDiscountStrategy promocodeStrategy = new PromocodeStrategy(promocode);

        CheckoutFacade checkoutFacade = new CheckoutFacade(richUser, new NoIDiscountStrategy());

        checkoutFacade.setDiscountStrategy(new PercentageIDiscountStrategy(0.1f));
        checkoutFacade.checkout();

    }
}
