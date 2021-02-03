package kz.iitu.spring.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bank implements IBankService{
    private HashMap<Long, Account> bank = new HashMap<>();

    public void setBank(HashMap<Long, Account> bank) {
        this.bank = bank;
    }

    @Override
    public double withdraw(double amount, long cardNumber) {
        if (bank.containsKey(cardNumber)) {
            if (bank.get(cardNumber).getCash() < amount){
                System.out.println("You don't have enough funds in your account...");
            }else {
                bank.get(cardNumber).setCash(bank.get(cardNumber).getCash() - amount);
                System.out.println("You have successfully withdrawn " + amount + " from the card");
            }
        }
        return bank.get(cardNumber).getCash();
    }

    @Override
    public double topUp(double amount, long cardNumber) {
        if (bank.containsKey(cardNumber)){
            bank.get(cardNumber).setCash(bank.get(cardNumber).getCash() + amount);
            System.out.println("You have successfully top up " + amount + " to the card");
        }
        return bank.get(cardNumber).getCash();
    }

    @Override
    public double checkCash(long cardNumber) {
        return bank.get(cardNumber).getCash();
    }

    @Override
    public boolean authentication(long cardNumber, int pin) {
        if (bank.containsKey(cardNumber) && bank.get(cardNumber).getPin() == pin){
            return true;
        }
        return false;
    }


}
