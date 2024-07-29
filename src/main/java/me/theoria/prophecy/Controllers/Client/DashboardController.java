package me.theoria.prophecy.Controllers.Client;

import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import me.theoria.prophecy.Models.Model;
import me.theoria.prophecy.Models.Transaction;
import me.theoria.prophecy.Views.TransactionCellFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

// FXML labels and pointers
public class DashboardController implements Initializable {
    public Text user_name;
    public Label login_date;
    public Label liquid_bal;
    public Label liquid_acc_num;
    public Label sales_bal;
    public Label sales_acc_num;
    public Label income_lbl;
    public Label expense_lbl;
    public ListView<Transaction> transaction_listview;
    public TextField payee_fld;
    public TextField amount_fld;
    public TextArea message_fld;
    public Button send_money_btn;

    // Initialize functions on button clicks
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindData();
        initLatestTransactions();
        transaction_listview.setItems(Model.getInstance().getLatestTransactions());
        transaction_listview.setCellFactory(event -> new TransactionCellFactory());
        send_money_btn.setOnAction(event -> onSendTrans());
        accountSummary();
    }

    /* Bind data to display user information and populate accounts on primary client window */
    private void bindData() {
        user_name.textProperty().bind(Bindings.concat("Hello, ").concat(Model.getInstance().getClient().firstNameProperty()));
        login_date.setText("Today is: " + LocalDate.now());
        liquid_bal.textProperty().bind(Model.getInstance().getClient().cAccountProperty().get().balanceProperty().asString());
        liquid_acc_num.textProperty().bind(Model.getInstance().getClient().cAccountProperty().get().accountNumberProperty());
        sales_bal.textProperty().bind(Model.getInstance().getClient().sAccountProperty().get().balanceProperty().asString());
        sales_acc_num.textProperty().bind(Model.getInstance().getClient().sAccountProperty().get().accountNumberProperty());
    }

    // Initialize the latest transactions
    private void initLatestTransactions() {
        if (Model.getInstance().getLatestTransactions().isEmpty()) {
            Model.getInstance().setLatestTransactions();
        }
    }

    // Method to send transactions
    private void onSendTrans() {
        String receiver = payee_fld.getText();
        double amount = Double.parseDouble(amount_fld.getText());
        String message = message_fld.getText();;
        String sender = Model.getInstance().getClient().pAddressProperty().get();
        ResultSet resultSet = Model.getInstance().getDatabaseDriver().searchClient(receiver);
        try {
            if (resultSet.isBeforeFirst()) {
                Model.getInstance().getDatabaseDriver().updateBalance(receiver, amount, "ADD");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Subtract from sender sales
        Model.getInstance().getDatabaseDriver().updateBalance(sender, amount, "SUB");
        // Update sales account
        Model.getInstance().getClient().sAccountProperty().get().setBalance(Model.getInstance().getDatabaseDriver().getSalesBalance(sender));
        // Record the new transaction
        Model.getInstance().getDatabaseDriver().newTransact(sender, receiver, amount, message);
        // Clear all fields
        payee_fld.setText("");
        amount_fld.setText("");
        message_fld.setText("");

    }

    private void accountSummary() {
        double income = 0.00;
        double expenses = 0.00;
        if (Model.getInstance().getAllTransactions().isEmpty()){
            Model.getInstance().setAllTransactions();
        }
        for (Transaction transaction: Model.getInstance().getAllTransactions()) {
            if (transaction.senderProperty().get().equals(Model.getInstance().getClient().pAddressProperty().get())){
                expenses += transaction.amountProperty().get();
            }
            if (transaction.receiverProperty().get().equals(Model.getInstance().getClient().pAddressProperty().get())){
                income += transaction.amountProperty().get();
            }
        }
        this.income_lbl.setText("+ $"+String.valueOf(income));
        this.expense_lbl.setText("- $"+String.valueOf(expenses));
    }

}
