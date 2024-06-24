package me.theoria.prophecy.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CheckingAccount extends Account {

    private final IntegerProperty transactionLimit;

    public CheckingAccount(String owner, String accountNumber, double balance, int trlimit) {
        super(owner, accountNumber, balance);
        this.transactionLimit = new SimpleIntegerProperty(this, "Transaction Limit", trlimit);
    }

    public IntegerProperty transactionLimitProperty(){
        return transactionLimit;
    }

}
