package me.theoria.prophecy.Models;

import java.sql.*;

public class DbDriver {

    private Connection connect;

    public DbDriver() {
        try {
            this.connect = DriverManager.getConnection("jdbc:sqlite:prophecy.db");
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    //Client

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


    //Admin


}
