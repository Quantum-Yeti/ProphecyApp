package me.theoria.prophecy.Controllers.Client;

import javafx.beans.binding.Bindings;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import me.theoria.prophecy.Models.Model;
import me.theoria.prophecy.Models.Transaction;
import me.theoria.prophecy.Views.TransactionCellFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public Text user_name;
    public Label login_date;
    public Label liquid_bal;
    public Label liquid_acc_num;
    public Label sales_bal;
    public Label sales_acc_num;
    public Label sales_lbl;
    public Label expense_lbl;
    public ListView<Transaction> transaction_listview;
    public TextField payee_fld;
    public TextField amount_fld;
    public TextArea message_fld;
    public Button send_money_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindData();
        initLatestTransactions();
        transaction_listview.setItems(Model.getInstance().getLatestTransactions());
        transaction_listview.setCellFactory(event -> new TransactionCellFactory());
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

    private void initLatestTransactions() {
        if (Model.getInstance().getLatestTransactions().isEmpty()) {
            Model.getInstance().setLatestTransactions();
        }
    }

}
