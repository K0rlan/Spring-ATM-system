package kz.iitu.spring.demo;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.Scanner;

public class ATM {
    static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

    private static final Scanner scanner = new Scanner(System.in);
    private static Bank bank = new Bank();
    private static Service accountService;

    public static void start(){
        context.scan("kz.iitu.spring.demo");
        context.refresh();
        accountService = context.getBean("bankService", Service.class);
        bank.setAccounts(accountService.getAccounts());
        startMenu();
    }
    public static void startMenu(){
        System.out.println("Enter card id:");
        int id = scanner.nextInt();
        System.out.println("Enter pin: ");
        int pin = scanner.nextInt();
        Account account = bank.checkPin(id, pin);
        if (account != null){
            while(true){
                menu(account);
            }
        }else{
            System.out.println("Wrong pin or card id");
            startMenu();
        }
        context.close();
    }

    private static void menu(Account account) {
        System.out.println("1. Top up\n" +
                "2. Withdrawal\n" +
                "3. Check balance\n" +
                "4. Change pin\n" +
                "5. Exit");
        int choice = scanner.nextInt();
        double amount = 0;
        switch (choice){
            case 1:
                System.out.println("Enter the sum: ");
                amount = scanner.nextDouble();
                if (bank.deposit(amount, account.getId())){
                    accountService.updateAccounts(account);
                }
                break;
            case 2:
                System.out.println("Enter the sum: ");
                amount = scanner.nextDouble();
                if (bank.withdrawal(amount, account.getId())){
                    accountService.updateAccounts(account);
                }
                break;
            case 3:
                bank.checkBalance(account.getId());
                break;
            case 4:
                if (changePin(account.getId())){
                    accountService.updateAccounts(account);
                }
                break;
            case 5:
                System.exit(0);
                break;
        }
    }

    private static boolean changePin(int id) {
        System.out.println("Enter your old pin:");
        int old = scanner.nextInt();
        System.out.println("Enter your new pin:");
        int newPin = scanner.nextInt();
        System.out.println("Repeat new pin:");
        int repeat = scanner.nextInt();
        if (newPin == repeat && bank.changePin(id, old, newPin)){
            System.out.println("Your pin was successfully changed!");
            return true;
        }else if(newPin == repeat && !bank.changePin(id, old, newPin)){
            System.out.println("Your old id incorrect. Please, try again!");
            changePin(id);
        }else{
            System.out.println("Password doesn't match. Please, try again!");
            changePin(id);
        }
        return false;
    }
}
