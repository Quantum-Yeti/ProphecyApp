package me.theoria.prophecy.Controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
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
    public ListView transaction_listview;
    public TextField payee_fld;
    public TextField amount_fld;
    public TextArea message_fld;
    public Button send_money_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
