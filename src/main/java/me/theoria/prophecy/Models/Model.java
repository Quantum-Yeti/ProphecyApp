package me.theoria.prophecy.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import me.theoria.prophecy.Views.ViewFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Model {

    private static Model model;
    private final ViewFactory viewFactory;
    private final DbDriver dbDriver;

    //Client Data
    private final Client client;
    private boolean clientLoginSuccessCheck;
    private final ObservableList<Transaction> latestTransactions;
    private final ObservableList<Transaction> allTransactions;

    //Admin Data
    private boolean adminLoginSuccessCheck;
    private final ObservableList<Client> clients;


    Model() {
        this.viewFactory = new ViewFactory();
        this.dbDriver = new DbDriver();

        // Client Data
        this.clientLoginSuccessCheck = false;
        this.client = new Client("", "", "", null, null, null);
        this.latestTransactions = FXCollections.observableArrayList();
        this.allTransactions = FXCollections.observableArrayList();

        // Admin Data
        this.adminLoginSuccessCheck = false;
        this.clients = FXCollections.observableArrayList();

    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DbDriver getDatabaseDriver() {
        return dbDriver;
    }

    /* Client Data Sections */

    // Client Login Getter - Setter - Evaluate
    public boolean getClientLoginSuccessCheck() {
        return this.clientLoginSuccessCheck;
    }

    public void setClientLoginSuccessCheck(boolean credentialCheck) {
        this.clientLoginSuccessCheck = credentialCheck;
    }

    public Client getClient() {
        return client;
    }

    public void evalClientCred(String pAddress, String password) {
        LiquidAccount liquidAccount;
        SalesAccount salesAccount;
        ResultSet resultSet = dbDriver.getClientData(pAddress, password);
        try {
            if (resultSet.isBeforeFirst()) {
                this.client.firstNameProperty().set(resultSet.getString("FirstName"));
                this.client.lastNameProperty().set(resultSet.getString("LastName"));
                this.client.pAddressProperty().set(resultSet.getString("PayeeAddress"));

                String[] dateS = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateS[0]), Integer.parseInt(dateS[1]), Integer.parseInt(dateS[2]));
                this.client.dateProperty().set(date);

                liquidAccount = getLiquidAccount(pAddress);
                salesAccount = getSalesAccount(pAddress);
                this.client.cAccountProperty().set(liquidAccount);
                this.client.sAccountProperty().set(salesAccount);

                this.clientLoginSuccessCheck = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void prepTransactions(ObservableList<Transaction> transactions, int limit) {
        ResultSet resultSet = dbDriver.getTransactions(this.client.pAddressProperty().get(), limit);
        try {
            while (resultSet.next()) {
                String sender = resultSet.getString("Sender");
                String receiver = resultSet.getString("Receiver");
                double amount = resultSet.getDouble("Amount");
                String[] dateS = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateS[0]), Integer.parseInt(dateS[1]), Integer.parseInt(dateS[2]));
                String message = resultSet.getString("Message");
                transactions.add(new Transaction(sender, receiver, amount, date, message));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* Transactions List Methods */
    public void setLatestTransactions() {
        prepTransactions(this.latestTransactions, 8);
    }

    public ObservableList<Transaction> getLatestTransactions() {
        return latestTransactions;
    }

    public void setAllTransactions() {
        prepTransactions(this.allTransactions, -1);
    }

    public ObservableList<Transaction> getAllTransactions() {
        return allTransactions;
    }


    /* Admin Data Section */

    public boolean getAdminLoginSuccessCheck() {
        return this.adminLoginSuccessCheck;
    }

    public void setAdminLoginSuccessCheck(boolean adminLoginSuccessCheck) {
        this.adminLoginSuccessCheck = adminLoginSuccessCheck;
    }

    public void evaluateAdminCred(String username, String password) {
        ResultSet resultSet = dbDriver.getAdminData(username, password);
        try {
            if (resultSet.isBeforeFirst()) {
                this.adminLoginSuccessCheck = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Client> getClients() {
        return clients;
    }

    public void setClients() {
        LiquidAccount liquidAccount;
        SalesAccount salesAccount;
        ResultSet resultSet = dbDriver.getAllClientData();
        try {
            while (resultSet.next()) {
                String fName = resultSet.getString("FirstName");
                String lName = resultSet.getString("LastName");
                String pAddress = resultSet.getString("PayeeAddress");
                String[] dateS = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateS[0]), Integer.parseInt(dateS[1]), Integer.parseInt(dateS[2]));
                liquidAccount = getLiquidAccount(pAddress);
                salesAccount = getSalesAccount(pAddress);
                clients.add(new Client(fName, lName, pAddress, liquidAccount, salesAccount, date));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Client> searchClient(String pAddress) {
        ObservableList<Client> searchResults = FXCollections.observableArrayList();
        ResultSet resultSet = dbDriver.searchClient(pAddress);
        try {
            LiquidAccount liquidAccount = getLiquidAccount(pAddress);
            SalesAccount salesAccount = getSalesAccount(pAddress);
            String fName = resultSet.getString("FirstName");
            String lName = resultSet.getString("LastName");
            String[] dateS = resultSet.getString("Date").split("-");
            LocalDate date = LocalDate.of(Integer.parseInt(dateS[0]), Integer.parseInt(dateS[1]), Integer.parseInt(dateS[2]));
            searchResults.add(new Client(fName, lName, pAddress, liquidAccount, salesAccount, date));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return searchResults;
    }


    public LiquidAccount getLiquidAccount(String pAddress) {
        LiquidAccount account = null;
        ResultSet resultSet = getDatabaseDriver().getLiquidAcctData(pAddress);
        try {
            String num = resultSet.getString("AccountNumber");
            int tLimit = (int) resultSet.getDouble("TransactionLimit");
            double balance = resultSet.getDouble("Balance");
            account = new LiquidAccount(pAddress, num, balance, tLimit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    public SalesAccount getSalesAccount(String pAddress) {
        SalesAccount account = null;
        ResultSet resultSet = getDatabaseDriver().getSalesAcctData(pAddress);
        try {
            String num = resultSet.getString("AccountNumber");
            double wLimit = (int) resultSet.getDouble("WithdrawalLimit");
            double balance = resultSet.getDouble("Balance");
            account = new SalesAccount(pAddress, num, balance, wLimit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

}




