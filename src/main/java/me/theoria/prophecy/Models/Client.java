package me.theoria.prophecy.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Client {

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty payeeAdress;
    private final ObjectProperty<Account> fungibleAccount;
    private final ObjectProperty<Account> salesAccount;
    private final ObjectProperty<LocalDate> dateCreated;
    private final IntegerProperty transactionLimit;
    private final DoubleProperty withdrawalLimit;

    public Client(String fName, String lName, String pAddress, Account cAccount, Account sAccount, LocalDate date){
        this.firstName = new SimpleStringProperty(this, "FirstName", fName);
        this.lastName = new SimpleStringProperty(this, "LastName", lName);
        this.payeeAdress = new SimpleStringProperty(this, "Payee Address", pAddress);
        this.fungibleAccount = new SimpleObjectProperty<>(this, "Fungible Account", cAccount);
        this.salesAccount =new SimpleObjectProperty<>(this, "Sales Account", sAccount);
        this.dateCreated =new SimpleObjectProperty<>(this, "Date", date);
        this.transactionLimit = new SimpleIntegerProperty(this, "Transaction Limit", 10);
        this.withdrawalLimit = new SimpleDoubleProperty(this, "Withdrawal Limit", 2000.00);
    }

    public StringProperty firstNameProperty(){return firstName;}
    public StringProperty lastNameProperty(){return lastName;}
    public StringProperty pAddressProperty(){return payeeAdress;}
    public ObjectProperty<Account> fungibleAccountProperty(){return fungibleAccount;}
    public ObjectProperty<Account> salesAccountProperty(){return salesAccount;}
    public ObjectProperty<LocalDate> dateProperty(){return dateCreated;}
    public IntegerProperty transactionLimitProperty(){return transactionLimit;}
    public DoubleProperty withdrawalLimitProperty(){return withdrawalLimit;}
}
