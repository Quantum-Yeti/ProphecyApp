package me.theoria.prophecy.Models;

import java.sql.*;
import java.time.LocalDate;

public class DbDriver {


    private Connection connect;

    public DbDriver() {
        try {
            this.connect = DriverManager.getConnection("jdbc:sqlite:prophecy.db");
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    /* Client Credential */

    public ResultSet getClientData(String pAddress, String password) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.connect.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Clients WHERE PayeeAddress='"+pAddress+"' AND Password='"+password+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getTransactions(String pAddress, int limit) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.connect.createStatement();
            resultSet = statement.executeQuery("SELECT  * FROM  Transactions WHERE Sender='"+pAddress+"' OR Receiver='"+pAddress+"' LIMIT  "+limit+";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet searchClient(String pAddress) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.connect.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Clients WHERE PayeeAddress='"+pAddress+"';");
        } catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public void depositSales(String pAddress, double amount){
        Statement statement;
        try {
            statement = this.connect.createStatement();
            statement.executeUpdate("UPDATE SalesAccounts SET Balance="+amount+" WHERE  Owner='"+pAddress+"';");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


    /* Admin Credential */

    public ResultSet getAdminData(String username, String password) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.connect.createStatement();
            resultSet = statement.executeQuery("SELECT  * FROM Admins WHERE Username='"+username+"' AND Password='"+password+"';");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void createClient(String fName, String lName, String pAddress, String password, LocalDate date) {
        Statement statement;
        try {
            statement = this.connect.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "Clients(FirstName, LastName, PayeeAddress, Password, Date)" +
                    "VALUES ('"+fName+"', '"+lName+"', '"+pAddress+"', '"+password+"', '"+date.toString()+"');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /* Create Clients from Admin Menu */

    public void createLiquidAccount(String owner, String number, double tLimit, double balance) {
        Statement statement;
        try {
            statement = this.connect.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "LiquidAccounts (Owner, AccountNumber, TransactionLimit, Balance)" +
                    "VALUES ('"+owner+"', '"+number+"', "+tLimit+", "+balance+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createSalesAccount(String owner, String number, double wLimit, double balance) {
        Statement statement;
        try {
            statement = this.connect.createStatement();
            statement.executeUpdate("INSERT INTO " +
                    "SalesAccounts (Owner, AccountNumber, WithdrawalLimit, Balance)" +
                    "VALUES ('"+owner+"', '"+number+"', "+wLimit+", "+balance+")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAllClientData() {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.connect.createStatement();
            resultSet = statement.executeQuery("SELECT  * FROM Clients;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    /* Account Creation Methods */

    public int getLastClientCred() {
        Statement statement;
        ResultSet resultSet;
        int cred = 0;
        try {
            statement = this.connect.createStatement();
            resultSet = statement.executeQuery("SELECT  * FROM sqlite_sequence WHERE name='Clients';");
            cred = resultSet.getInt("seq");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cred;
    }

    /* Account Listing Methods */

    public ResultSet getLiquidAcctData(String pAddress) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.connect.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM LiquidAccounts WHERE Owner='"+pAddress+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getSalesAcctData(String pAddress) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            statement = this.connect.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SalesAccounts WHERE Owner='"+pAddress+"';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

}
