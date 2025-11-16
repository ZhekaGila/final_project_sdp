package facade.service.app;

import model.User;
import model.wallet.Wallet;
import model.wallet.WalletType;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateUser {

    public static User createUser(Scanner scanner) {
        // Ask for name
        System.out.println("Enter your name please\n>");
        String name =scanner.nextLine();

        // Ask for type of payment
        System.out.println("Choose type of wallet: \n1.Card \n2.Paypal \n3.Kaspi\n>");
        int walletType = 0;
        try {
            walletType = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number!");
        }
        scanner.nextLine();

        // Ask for balance
        System.out.println("Enter your balance:\n>");
        int balance=0;
        try {
             balance = scanner.nextInt();
             if (balance< 0) {
                 throw new InputMismatchException();
             }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number!");
        }
        scanner.nextLine();

        Wallet wallet = null;
        switch (walletType){
            case 1-> wallet = new Wallet(balance, WalletType.CARD);
            case 2-> wallet = new Wallet(balance, WalletType.PAYPAL);
            case 3-> wallet = new Wallet(balance, WalletType.KASPI);
            default -> System.out.println("Invalid wallet type");
        }

        // Creating user
        return new User(name, wallet);
    }

}
