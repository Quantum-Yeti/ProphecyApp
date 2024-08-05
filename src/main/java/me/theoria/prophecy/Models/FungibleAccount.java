package me.theoria.prophecy.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class FungibleAccount extends Account{
    // The number of transactions that client  can do per day
    private final IntegerProperty transactionLimit;

    public FungibleAccount(String owner, String accountNumber, double balance, int transactionLimit){
        super(owner, accountNumber, balance);
        this.transactionLimit = new SimpleIntegerProperty(this, "Transaction Limit", transactionLimit);
    }

    public IntegerProperty transactionLimitProperty(){return transactionLimit;}

    @Override
    public String toString(){
        return accountNumberProperty().get();
    }
}

