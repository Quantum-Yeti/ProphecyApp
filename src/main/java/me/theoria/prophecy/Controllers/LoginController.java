package me.theoria.prophecy.Controllers;

import me.theoria.prophecy.Models.Model;
import me.theoria.prophecy.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

// Declare JavaFx End Points
public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector;
    public Label payee_adress_lbl;
    public TextField payee_adress_fld;
    public TextField password_fld;
    public Button loggin_btn;
    public Label error_lbl;


    // Initialize actions
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT, AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(o->setAcc_selector());
        loggin_btn.setOnAction(e-> onLogin());

    }

    /* Logging In */
    private void onLogin(){
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        if(Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.CLIENT){
            // Evaluate Client Login Credentials
            Model.getInstance().evaluateClientCredentials(payee_adress_fld.getText(), password_fld.getText());
            if(Model.getInstance().getClientLoginSuccessFlag()){
                Model.getInstance().getViewFactory().showClientWindow();
                // Close the login stage
                Model.getInstance().getViewFactory().closeStage(stage);
            }else{
                payee_adress_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No Such Login Credentials");
            }
        }else{
            // Check Admin Login Credentials
            Model.getInstance().evaluateAdminCredentials(payee_adress_fld.getText(), password_fld.getText());
            if(Model.getInstance().getAdminLoginSuccessFlag()){
                Model.getInstance().getViewFactory().showAdminWindow();
                // Close the Login Stage
                Model.getInstance().getViewFactory().closeStage(stage);
            }else {
                payee_adress_fld.setText("");
                password_fld.setText("");
                error_lbl.setText("No Such Login Credentials");
            }
        }
    }

    // Set the account type and selection
    private void setAcc_selector(){
        Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue());
        // Change Payee Address label accordingly
        if(acc_selector.getValue() == AccountType.ADMIN){
            payee_adress_lbl.setText("Username:");
        }else {
            payee_adress_lbl.setText("Payee Address:");
        }
    }
}

