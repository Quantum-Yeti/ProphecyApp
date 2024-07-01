package me.theoria.prophecy.Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Client {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty payeeAddress;
    private final ObjectProperty<Account> liquidAccount;
    private final ObjectProperty<Account> salesAccount;
    private final ObjectProperty<LocalDate> dateCreate;

    public Client(String fName, String lName, String pAddress, Account cAccount, Account sAccount, LocalDate date) {
        this.firstName = new SimpleStringProperty(this, "FirstName", fName);
        this.lastName = new SimpleStringProperty(this, "LastName", lName);
        this.payeeAddress = new SimpleStringProperty(this, "Payee Address", pAddress);
        this.liquidAccount = new SimpleObjectProperty<>(this, "Liquid Account", cAccount);
        this.salesAccount = new SimpleObjectProperty<>(this, "Sales Account", sAccount);
        this.dateCreate = new SimpleObjectProperty<>(this, "Date", date);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public StringProperty pAddressProperty() {
        return payeeAddress;
    }

    public ObjectProperty<Account> cAccountProperty() {
        return liquidAccount;
    }

    public ObjectProperty<Account> sAccountProperty() {
        return salesAccount;
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return dateCreate;
    }

}
