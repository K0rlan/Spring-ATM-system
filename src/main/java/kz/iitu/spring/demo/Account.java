package kz.iitu.spring.demo;

public class Account {
    private int pin;
    private double cash;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public int getPin() {
        return pin;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "pin=" + pin +
                ", cash=" + cash +
                ", name='" + name + '\'' +
                '}';
    }
}
