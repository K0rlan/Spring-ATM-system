package kz.iitu.spring.demo;

public interface IBankService {
    double withdraw(double amount, long cardNumber);
    double topUp(double amount, long cardNumber);
    double checkCash(long cardNumber);
    boolean authentication(long cardNumber, int pin);
}
