package kz.iitu.spring.demo;

import org.springframework.stereotype.Component;

@Component
public class Account {

    private int cardNumber;
    private int pin;
    private String name;
    private double cash;

    public Account() {
    }

    public Account(int cardNumber, int pin, String name, double cash) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.name = name;
        this.cash = cash;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }
}
