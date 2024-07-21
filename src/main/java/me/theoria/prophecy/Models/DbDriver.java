package me.theoria.prophecy.Models;

import java.sql.*;
import java.time.LocalDate;

public class DbDriver {

    // Initialize database connection
    private Connection connect;

    // Direct database access
    public DbDriver() {
        try {
            this.connect = DriverManager.getConnection("jdbc:sqlite:prophecy.db");
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    /* Client Credential */
    // Parse and query client credentials from the database
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

    // Access account limits per user account address
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

    // Return Sales Account Balance
    public double getSalesBalance (String pAddress) {
        Statement statement;
        ResultSet resultSet;
        double balance = 0;
        try {
            statement = this.connect.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SalesAccounts WHERE Owner='"+pAddress+"';");
            balance = resultSet.getDouble("Balance");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    // Add or subtract from the balance
    public void updateBalance(String pAddress, double amount, String operand) {
        Statement statement;
        ResultSet resultSet;
        try {
            statement = this.connect.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM SalesAccounts WHERE  Owner='"+pAddress+"';");
            double newBalance;
            if (operand.equals("ADD")) {
                newBalance = resultSet.getDouble("Balance") + amount;
                statement.executeUpdate("UPDATE SalesAccounts SET Balance="+newBalance+" WHERE Owner='"+pAddress+"';");
            } else {
                if (resultSet.getDouble("Balance") >= amount) {
                    newBalance = resultSet.getDouble("Balance") - amount;
                    statement.executeUpdate("UPDATE SalesAccounts SET Balance="+newBalance+" WHERE Owner='"+pAddress+"';");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Record a new transaction
    public void newTransact(String sender, String receiver, double amount, String message) {
        Statement statement;
        try {
            statement = this.connect.createStatement();
            LocalDate date = LocalDate.now();
            statement.executeUpdate("INSERT INTO Transactions(Sender, Receiver, Amount, Date, Message) " +
                    "VALUES ('"+sender+"', '"+receiver+"', '"+amount+"', '"+date+"', '"+message+"');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update deposit to sales account
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

    /* Account Creation Methods */

    //
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

    // Result set for SQL statement to return liquid account data
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

    // Result set for SQL statement to return sales account data
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
