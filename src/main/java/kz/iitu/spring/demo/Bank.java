package kz.iitu.spring.demo;

import org.omg.PortableInterceptor.ACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class Bank {
    @Autowired
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
