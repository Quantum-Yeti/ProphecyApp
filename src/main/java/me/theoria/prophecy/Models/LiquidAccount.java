package me.theoria.prophecy.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class LiquidAccount extends Account {

    private final IntegerProperty transactionLimit;

    public LiquidAccount(String owner, String accountNumber, double balance, int trlimit) {
        super(owner, accountNumber, balance);
        this.transactionLimit = new SimpleIntegerProperty(this, "Transaction Limit", trlimit);
    }

    public IntegerProperty transactionLimitProperty(){
        return transactionLimit;
    }

    @Override
    public String toString() {
        return accountNumberProperty().get();
    }

}
