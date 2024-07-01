package me.theoria.prophecy.Controllers;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import me.theoria.prophecy.Models.Model;
import me.theoria.prophecy.Views.AccountType;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector;
    public Label payee_address_lbl;
    public TextField payee_address_fid;
    public PasswordField password_fld;
    public Button login_btn;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT, AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());
        acc_selector.valueProperty().addListener(observable -> setAcc_selector());
        login_btn.setOnAction(event -> onLogin());
    }

    private void onLogin() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();

        if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.CLIENT){

            // Evaluation of Client Login Credentials
            Model.getInstance().evalClientCred(payee_address_fid.getText(), password_fld.getText());

            if (Model.getInstance().getClientLoginSuccessCheck()){
                Model.getInstance().getViewFactory().showClientWindow();

                // Close the GUI Stage
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                payee_address_fid.setText("");
                password_fld.setText("");
                error_lbl.setText("Invalid credentials! Please try again!");
            }
        } else {
            // Check-Evaluate Admin Credentials
            Model.getInstance().evaluateAdminCred(payee_address_fid.getText(), password_fld.getText());
            if (Model.getInstance().getAdminLoginSuccessCheck()) {
                Model.getInstance().getViewFactory().showAdminWindow();
                // Close the login GUI Stage
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                payee_address_fid.setText("");
                password_fld.setText("");
                error_lbl.setText("Invalid credentials! Try again!");
            }
        }
    }

    private void setAcc_selector() {
        Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue());

        //This changes login from an account address to an administrator username
        if (acc_selector.getValue() == AccountType.ADMIN) {
            payee_address_lbl.setText("Admin Username: ");
        } else {
            payee_address_lbl.setText("(@Username): ");
        }
    }

}
