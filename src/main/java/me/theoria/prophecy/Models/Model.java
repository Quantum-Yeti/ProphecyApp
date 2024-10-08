package me.theoria.prophecy.Models;

import me.theoria.prophecy.Views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    // Client Data Section
    private Client client;
    private boolean clientLoginSuccessFlag;
    private final ObservableList<Transaction> latestTransactions;
    private final ObservableList<Transaction> allTransactions;
    // Admin Data Section
    private boolean adminLoginSuccessFlag;
    private final ObservableList<Client> clients;

    private Model(){
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        // Client Data Section
        this.clientLoginSuccessFlag = false;
        this.client = new Client("", "", "", null, null, null);
        this.latestTransactions = FXCollections.observableArrayList();
        this.allTransactions = FXCollections.observableArrayList();
        // Admin Data Section
        this.adminLoginSuccessFlag = false;
        this.clients = FXCollections.observableArrayList();
    }

    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver(){return databaseDriver;}

    /*
     * Client Method Section
     * */
    public boolean getClientLoginSuccessFlag(){return this.clientLoginSuccessFlag;}

    public void setClientLoginSuccessFlag(boolean flag){this.clientLoginSuccessFlag = flag;}

    public Client getClient() {return client;}

    public void evaluateClientCredentials(String pAddress, String password){
        FungibleAccount fungibleAccount;
        SalesAccount salesAccount;
        ResultSet resultSet = databaseDriver.getClientData(pAddress, password);
        try{
            if(resultSet.isBeforeFirst()){
                this.client.firstNameProperty().set(resultSet.getString("FirstName"));
                this.client.lastNameProperty().set(resultSet.getString("LastName"));
                this.client.pAddressProperty().set(resultSet.getString("PayeeAddress"));
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                this.client.dateProperty().set(date);
                fungibleAccount = getFungibleAccount(pAddress);
                salesAccount = getSalesAccount(pAddress);
                this.client.fungibleAccountProperty().set(fungibleAccount);
                this.client.salesAccountProperty().set(salesAccount);
                this.clientLoginSuccessFlag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void prepareTransactions(ObservableList<Transaction> transactions, int limit) {
        ResultSet resultSet = databaseDriver.getTransactions(this.client.pAddressProperty().get(), limit);
        try {
            while (resultSet.next()) {
                String sender = resultSet.getString("Sender");
                String receiver = resultSet.getString("Receiver");
                double amount = resultSet.getDouble("Amount");
                String dateStr = resultSet.getString("Date");
                String message = resultSet.getString("Message");

                // Getting Date From TimeLine
                LocalDate date = null;
                try {
                    date = LocalDate.parse(dateStr);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error DATE: " + dateStr);
                }

                transactions.add(new Transaction(sender, receiver, amount, date, message));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Request ERROR: " + e.getMessage());
        }
    }

    public void updateTransactions(){
        allTransactions.clear();
        prepareTransactions(allTransactions, -1);
    }

    public void addTransaction(Transaction transaction) {
        // Додаємо нову транзакцію
        latestTransactions.add(0, transaction);

        // Перевіряємо розмір списку
        if (latestTransactions.size() > 4) {
            latestTransactions.remove(latestTransactions.size() - 1);
        }
    }

    public void setLatestTransactions() {
        prepareTransactions(this.latestTransactions, 4);
    }

    public ObservableList<Transaction> getLatestTransactions(){
        return latestTransactions;
    }

    public void setAllTransactions(){
        prepareTransactions(this.allTransactions, -1);
    }

    public ObservableList<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public void updateBalance(){
        Model.getInstance().getClient().salesAccountProperty().get().setBalance(Model.getInstance().getDatabaseDriver().getSalesAccountBalance(Model.getInstance().getClient().pAddressProperty().get()));
        Model.getInstance().getClient().fungibleAccountProperty().get().setBalance(Model.getInstance().getDatabaseDriver().getFungibleAccountBalance(Model.getInstance().getClient().pAddressProperty().get()));
    }

    /*
     * Admin Method Section
     * */

    public boolean getAdminLoginSuccessFlag(){return this.adminLoginSuccessFlag;}

    public void setAdminLoginSuccessFlag(boolean adminLoginSuccessFlag) {this.adminLoginSuccessFlag = adminLoginSuccessFlag;}

    public void evaluateAdminCredentials(String username, String password){
        ResultSet resultSet = databaseDriver.getAdminData(username, password);
        try{
            if(resultSet.isBeforeFirst()){
                this.adminLoginSuccessFlag = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<Client> getClients() {
        return clients;
    }

    public void setClients(){
        FungibleAccount fungibleAccount;
        SalesAccount salesAccount;
        ResultSet resultSet = databaseDriver.getAllClientsData();
        try{
            while(resultSet.next()) {
                String fName = resultSet.getString("FirstName");
                String lName = resultSet.getString("LastName");
                String pAddress = resultSet.getString("PayeeAddress");
                String[] dateParts = resultSet.getString("Date").split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                fungibleAccount = getFungibleAccount(pAddress);
                salesAccount = getSalesAccount(pAddress);
                clients.add(new Client(fName, lName, pAddress, fungibleAccount, salesAccount, date));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Client> searchClient(String pAddress){
        ObservableList<Client> searchResults = FXCollections.observableArrayList();
        ResultSet resultSet = databaseDriver.searchClient(pAddress);
        try {
            FungibleAccount fungibleAccount = getFungibleAccount(pAddress);
            SalesAccount salesAccount = getSalesAccount(pAddress);
            String fName = resultSet.getString("FirstName");
            String lName = resultSet.getString("LastName");
            String[] dateParts = resultSet.getString("Date").split("-");
            LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
            searchResults.add(new Client(fName, lName, pAddress, fungibleAccount, salesAccount, date));
        }catch (Exception e){
            e.printStackTrace();
        }
        return searchResults;
    }

    /*
     * Utility Methods Section
     */
    public FungibleAccount getFungibleAccount(String pAddress){
        FungibleAccount account = null;
        ResultSet resultSet = databaseDriver.getFungibleAccountData(pAddress);
        try{
            String num = resultSet.getString("AccountNumber");
            int tLimit = (int)resultSet.getDouble("TransactionLimit");
            double balance = resultSet.getDouble("Balance");
            account = new FungibleAccount(pAddress, num,balance, tLimit);
        }catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }

    public SalesAccount getSalesAccount(String pAddress){
        SalesAccount account = null;
        ResultSet resultSet = databaseDriver.getSalesAccountData(pAddress);
        try{
            String num = resultSet.getString("AccountNumber");
            double wLimit = resultSet.getDouble("WithdrawalLimit");
            double balance = resultSet.getDouble("Balance");
            account = new SalesAccount(pAddress, num,balance, wLimit);
        }catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }








}





