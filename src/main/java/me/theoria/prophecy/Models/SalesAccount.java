package me.theoria.prophecy.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SalesAccount extends Account{
    // The withdrawal limit from the saving account

    private final DoubleProperty withdrawalLimit;

    public SalesAccount(String owner, String accountNumber, double balance, double withdrawalLimit){
        super(owner, accountNumber, balance);
        this.withdrawalLimit = new SimpleDoubleProperty(this, "Withdrawal Limit", withdrawalLimit);
    }

    public DoubleProperty withdrawalLimitProperty(){return withdrawalLimit;}

    @Override
    public String toString() {
        return accountNumberProperty().get();
    }
}