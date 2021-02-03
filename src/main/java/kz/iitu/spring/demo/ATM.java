package kz.iitu.spring.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class ATM {
    Scanner scanner = new Scanner(System.in);
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    Bank bank = context.getBean("bankDatabase", Bank.class);
    public void atm(long cardNumber, int pin){
        if (bank.authentication(cardNumber, pin))
            showMenu(cardNumber);
        else
            System.out.println("You need to authenticate!!!");
    }
    public void showMenu(long cardNumber){
        double amount;
        String ans = "y";
        while(ans.equals("y")) {
            System.out.println("1. Check cash\n2. Withdraw\n3. Top up");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Your cash: " + bank.checkCash(cardNumber));
                    break;
                case 2:
                    System.out.println("Enter amount of money: ");
                    amount = scanner.nextDouble();
                    System.out.println("Your cash: " + bank.withdraw(amount, cardNumber));
                    break;
                case 3:
                    System.out.println("Enter amount of money: ");
                    amount = scanner.nextDouble();
                    System.out.println("Your cash: " + bank.topUp(amount, cardNumber));
                    break;
                default:
                    System.out.println("Wrong option");

            }
            System.out.println("Do you want to continue? y/n");
            ans = scanner.next();
        }
    }

}
