package kz.iitu.spring.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter card number(1111): ");
        long cardNumber = scanner.nextLong();
        System.out.println("Enter pin(1111): ");
        int pin = scanner.nextInt();

        atm.atm(cardNumber, pin);
    }
}
