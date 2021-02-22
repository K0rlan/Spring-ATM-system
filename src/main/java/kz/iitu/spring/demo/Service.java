package kz.iitu.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@PropertySource("application.properties")
@Component("bankService")
public class Service {

    @Value("${bankService.dbUrl}")
    private String dbUrl;

    @Value("${bankService.dbUsername}")
    private String dbUsername;

    @Value("${bankService.dbPassword}")
    private String dbPassword;

    Connection connection;
    Statement statement;
    private List<Account> accounts = new ArrayList<>();

    @Autowired
    public Service() {
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void updateAccounts(Account account) {
        try {
            String query = "UPDATE accounts SET pin = '" + account.getPin() + "', cash = '" + account.getCash() +"'  WHERE id = " + account.getId();
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    @PostConstruct
    public void init() throws SQLException {
        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            statement = connection.createStatement();
            String queryString = "SELECT * FROM accounts";
            ResultSet rs = statement.executeQuery(queryString);
            while (rs.next()){
                Account account = new Account(rs.getInt(1), rs.getInt(2), rs.getInt(3));
                accounts.add(account);
            }
            System.out.println("UserService.createDbConnection");
            System.out.println(accounts);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
    }

    @PreDestroy
    public void destroyCustom() throws SQLException {
        connection.close();
    }
}
