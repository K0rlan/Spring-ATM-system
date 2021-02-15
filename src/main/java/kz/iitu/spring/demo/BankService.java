package kz.iitu.spring.demo;

import java.sql.Connection;
import java.sql.SQLException;

public interface BankService {
    void showMenu(Account account);
    Double checkCash();

    double withdraw(double cash);
    double topUp(double cash);
    void changePin(int pin);

    Bank getBank();

}
