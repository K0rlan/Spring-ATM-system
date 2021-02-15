package kz.iitu.spring.demo;

import java.sql.*;
import java.util.Scanner;

public class ATM implements BankService{
    private Account account;
    private Bank bank;
    Connection connection;
    Scanner scanner = new Scanner(System.in);

    public ATM(Bank bank) {
        this.bank = bank;
    }
    Statement statement;

    @Override
    public void showMenu(Account account){
        this.account = account;
        double amount;
        String ans = "y";
        while(ans.equals("y")) {
            System.out.println("1. Check cash\n2. Withdraw\n3. Top up\n4. Change pin code");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Your cash: " + checkCash());
                    break;
                case 2:
                    System.out.println("Enter amount of money: ");
                    amount = scanner.nextDouble();
                    System.out.println("Your cash: " + withdraw(amount));
                    break;
                case 3:
                    System.out.println("Enter amount of money: ");
                    amount = scanner.nextDouble();
                    System.out.println("Your cash: " + topUp(amount));
                    break;
                case 4:
                    System.out.println("Enter new pin code: ");
                    int pin = scanner.nextInt();
                    changePin(pin);
                    break;
                default:
                    System.out.println("Wrong option");

            }
            System.out.println("Do you want to continue? y/n");
            ans = scanner.next();
        }
    }

    @Override
    public Double checkCash() {
        return this.account.getCash();
    }

    @Override
    public double withdraw(double cash) {
        if (cash > account.getCash()) {
            System.out.println("You don't have enough money.");
            System.out.println("Your cash: " + checkCash());
        }
        else {
            double total = account.getCash() - cash;
            this.account.setCash(total);
            try {
                String query = "UPDATE accounts SET cash = '" + total + "' WHERE cardNumber = " + account.getCardNumber();
                statement = connection.createStatement();
                statement.executeUpdate(query);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return checkCash();
    }

    @Override
    public double topUp(double cash) {
        try {
            Double total = account.getCash() + cash;
            String query = "UPDATE accounts SET cash = '" + total + "' WHERE cardNumber = " + account.getCardNumber();
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println(e);
        }
        this.account.setCash(this.account.getCash() + cash);
        return checkCash();
    }

    @Override
    public void changePin(int pin) {
        try {
            String query = "UPDATE accounts SET pin = '" + pin + "' WHERE cardNumber = " + account.getCardNumber();
            statement = connection.createStatement();
            statement.executeUpdate(query);
            this.account.setPin(pin);
        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println("You successfully changed your pin code: " + this.account.getPin());
    }


    public void init() throws SQLException {
        Connection connection = this.create_DBCon();
        ResultSet set = null;
        String query = "SELECT * FROM accounts";
        statement = connection.createStatement();
        set = statement.executeQuery(query);
        while (set.next()){
            Account account = new Account(set.getInt(1), set.getInt(2),
                    set.getString(3), set.getDouble(4));
            bank.getAccounts().add(account);
        }
    }


    public Connection create_DBCon() {

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/atm", "postgres", "1234");
            if (connection != null) {
                System.out.println("Connected");
            }
            else {
                System.out.println("Connection failed");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return connection;
    }

    @Override
    public Bank getBank() {
        return this.bank;
    }

    public void destroy() {
        try {
            connection.close();
            if (connection != null) {
                System.out.println("Connection closed");
            }
            else {
                System.out.println("Error closing connection");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}