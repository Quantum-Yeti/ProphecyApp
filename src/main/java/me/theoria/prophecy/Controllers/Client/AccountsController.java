package me.theoria.prophecy.Controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountsController implements Initializable {
    public Label liq_acc_num;
    public Label transaction_limit;
    public Label liq_acc_date;
    public Label liq_acc_bal;
    public Label sales_acc_num;
    public Label withdrawal_limit;
    public Label sales_acc_date;
    public Label sales_acc_bal;
    public TextField amount_to_sales;
    public Button trans_to_sales_btn;
    public TextField amount_to_ch;
    public Button trans_to_ch_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

}
