package me.theoria.prophecy.Controllers.Client;

import me.theoria.prophecy.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

// Declare JavaFX endpoints
public class AccountsController implements Initializable {
    public Label fu_acc_num;
    public Label fu_date;
    public Label fu_acc_bal;
    public Label sa_acc_num;
    public Label sa_acc_date;
    public Label sa_acc_bal;
    public TextField amount_to_sa;
    public Button trans_to_sa_btn;
    public TextField amount_to_fu;
    public Button trans_to_fu_btn;

    // Initialize sending of money
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bindData();
        trans_to_sa_btn.setOnAction(e -> {
            Model.getInstance().getDatabaseDriver().moveFundsOnAccount(Model.getInstance().getClient().pAddressProperty().get(), Double.parseDouble(amount_to_sa.getText()), "checking");
            updateUI();
            emptyFields();
        });
        trans_to_fu_btn.setOnAction(e -> {
            Model.getInstance().getDatabaseDriver().moveFundsOnAccount(Model.getInstance().getClient().pAddressProperty().get(), Double.parseDouble(amount_to_fu.getText()), "savings");
            updateUI();
            emptyFields();
        });
    }

    // Bind data to JavaFX
    public void bindData() {
        fu_acc_num.textProperty().bind(Model.getInstance().getClient().fungibleAccountProperty().get().accountNumberProperty());
        sa_acc_num.textProperty().bind(Model.getInstance().getClient().salesAccountProperty().get().accountNumberProperty());
        fu_acc_bal.textProperty().bind(Model.getInstance().getClient().fungibleAccountProperty().get().balanceProperty().asString());
        sa_acc_bal.textProperty().bind(Model.getInstance().getClient().salesAccountProperty().get().balanceProperty().asString());
        fu_date.textProperty().bind(Model.getInstance().getClient().dateProperty().asString());
        sa_acc_date.textProperty().bind(Model.getInstance().getClient().dateProperty().asString());
    }

    // Update the UI
    public void updateUI() {Model.getInstance().updateBalance();}

    // Empty fields after use
    public void emptyFields(){
        amount_to_fu.setText("");
        amount_to_sa.setText("");
    }
}

