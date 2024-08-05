package me.theoria.prophecy.Controllers.Client;

import me.theoria.prophecy.Models.Model;
import me.theoria.prophecy.Models.Transaction;
import me.theoria.prophecy.Views.TransactionCellFactory;
import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

// Declare DashBoard Controller JavaFX Components
public class DashboardController implements Initializable {
    public Label login_date;
    public Text user_name;
    public Label fungible_balance;
    public Label fungible_acc_num;
    public Label sales_bal;
    public Label sales_acc_num;
    public Label income_lbl;
    public Label expense_lbl;
    public ListView transaction_listview;
    public TextField payee_fld;
    public TextField amount_fld;
    public TextArea message_fld;
    public Button send_money_btn;


    // Initialize actions for Dashboard selections and buttons
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindData();
        initLatestTransactionsList();
        transaction_listview.setItems(Model.getInstance().getLatestTransactions());
        transaction_listview.setCellFactory(e->new TransactionCellFactory());
        send_money_btn.setOnAction(e->{
            onSendMoney();
            accountSummary();
        });
        accountSummary();
    }

    // Bind JavaFX components to data
    private void bindData(){
        user_name.textProperty().bind(Bindings.concat("Hi, ").concat(Model.getInstance().getClient().firstNameProperty()));
        login_date.setText("Today, " + LocalDate.now());
        fungible_balance.textProperty().bind(Model.getInstance().getClient().fungibleAccountProperty().get().balanceProperty().asString());
        fungible_acc_num.textProperty().bind(Model.getInstance().getClient().fungibleAccountProperty().get().accountNumberProperty());
        sales_bal.textProperty().bind(Model.getInstance().getClient().salesAccountProperty().get().balanceProperty().asString());
        sales_acc_num.textProperty().bind(Model.getInstance().getClient().salesAccountProperty().get().accountNumberProperty());
    }

    // Initialize latest transactions
    private void initLatestTransactionsList(){
        if(Model.getInstance().getLatestTransactions().isEmpty()){
            Model.getInstance().setLatestTransactions();
        }else {
            Model.getInstance().setLatestTransactions();
        }
    }

    // Sending money between fungible and sales accounts
    private void onSendMoney(){
        String receiver = payee_fld.getText();
        double amount = Double.parseDouble(amount_fld.getText());
        String message = message_fld.getText();
        String sender = Model.getInstance().getClient().pAddressProperty().get();
        ResultSet resultSet = Model.getInstance().getDatabaseDriver().searchClient(receiver);
        try{
            if(resultSet.isBeforeFirst()){
                Model.getInstance().getDatabaseDriver().updateBalance(receiver, amount, "ADD");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        // Subtract From Sender Savings Account
        Model.getInstance().getDatabaseDriver().updateBalance(sender, amount, "SUB");
        // UPDATE The Savings Account BALANCE in the client object
        Model.getInstance().getClient().salesAccountProperty().get().setBalance(Model.getInstance().getDatabaseDriver().getSalesAccountBalance(sender));
        // Record NEW Transaction
        Model.getInstance().getDatabaseDriver().newTransaction(sender, receiver, amount, message);
        // Add the new transaction to the list
        Model.getInstance().addTransaction(new Transaction(sender, receiver, amount, LocalDate.now(), message));
        // Clear the FIELDS
        payee_fld.setText("");
        amount_fld.setText("");
        message_fld.setText("");
    }

    // Method calculates all expenses and income
    private void accountSummary(){
        double income = 0;
        double expenses = 0;
        if(Model.getInstance().getAllTransactions().isEmpty()){
            Model.getInstance().setAllTransactions();
        }else {
            Model.getInstance().updateTransactions();
        }
        for(Transaction transaction: Model.getInstance().getAllTransactions()){
            if (transaction.senderProperty().get().equals(Model.getInstance().getClient().pAddressProperty().get())){
                expenses = expenses + transaction.amountProperty().get();
            }else{
                income = income + transaction.amountProperty().get();
            }
        }
        income_lbl.setText("+ $"+ income);
        expense_lbl.setText("- $" + expenses);
    }
}
