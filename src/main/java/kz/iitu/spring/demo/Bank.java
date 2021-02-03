package kz.iitu.spring.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public boolean validatePin(String pin){
        String regex = "^[0-9]{4}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(pin);
        return m.matches();
    }
    @Override
    public void changePin(long cardNumber, int newPin) {
        if (bank.containsKey(cardNumber) && validatePin(String.valueOf(newPin))){
            bank.get(cardNumber).setPin(newPin);
            System.out.println("You successfully changed your pin code");
        }else{
            System.out.println("Pin code must consist of 4 numbers");
        }
    }

    @Override
    public boolean authentication(long cardNumber, int pin) {
        if (bank.containsKey(cardNumber) && bank.get(cardNumber).getPin() == pin){
            return true;
        }
        return false;
    }


}
