import builder.product.*;
import builder.product.components.*;
import builder.product.concrete.ComputerBuilder;
import builder.product.core.IProductBuilder;
import factory.core.IPayment;
import factory.core.PaymentCreator;
import factory.creators.PayPalPaymentCreator;
import factory.client.PaymentFactory;
import model.User;
import model.Wallet;
import model.WalletType;

public class Main {
    public static void main(String[] args) {

        IProductBuilder builder = new ComputerBuilder();

        Product pc = builder.setCPU(new CPU("Intel Core i7-13700K", 170000, 8, 32))
            .setRAM(new RAM("Kingston Fury 32GB DDR5", 45000, 16))
            .setGPU(new GPU("NVIDIA RTX 4070", 350000, 8))
            .setStorage(new Storage("Samsung 990 Pro 1TB SSD", 65000, "SSD", 512))
            .getComputer();

        System.out.println(pc);

        User user1 = new User("Aizada", new Wallet(500000f, WalletType.PAYPAL));

        WalletType userPaymentType = user1.getWallet().getType();

        PaymentCreator creator = PaymentFactory.getCreator(userPaymentType);

        IPayment payment = creator.createPayment();

        payment.processPayment(user1, pc.getPrice());

    }
}