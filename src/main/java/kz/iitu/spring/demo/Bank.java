package kz.iitu.spring.demo;

import org.omg.PortableInterceptor.ACTIVE;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
public class Bank {
    private List<Account> accounts;

    public Bank() { }

    public Bank(List<Account> accounts, Double fee) {
        this.accounts = accounts;

    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account){
        this.accounts.add(account);
    }

}
