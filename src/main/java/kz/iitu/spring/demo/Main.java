package kz.iitu.spring.demo;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("kz.iitu.spring.demo");
        context.refresh();
        BankService bankService = context.getBean("bankService", ATM.class);
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);

        Account randomClient = bankService.getBank().getAccounts().get(rand.nextInt(bankService.getBank().getAccounts().size()));
        System.out.println("Enter pin:");
        int pin = scanner.nextInt();
        if ((pin == randomClient.getPin())) {
            bankService.showMenu(randomClient);
        } else {
            System.out.println("You need to authenticate!!!");
        }
        ((AnnotationConfigApplicationContext) context).close();
    }
}
