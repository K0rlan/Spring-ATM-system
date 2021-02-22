package kz.iitu.spring.demo;

import java.sql.Connection;
import java.sql.SQLException;

public interface BankService {
    boolean withdrawal(double sum, int id);
    boolean deposit(double sum, int id);
    void checkBalance(int cash);
    boolean changePin(int id, int pin, int newPin);
}

