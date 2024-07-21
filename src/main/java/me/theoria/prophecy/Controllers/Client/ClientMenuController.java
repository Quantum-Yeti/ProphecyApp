package me.theoria.prophecy.Controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import me.theoria.prophecy.Models.Model;
import me.theoria.prophecy.Views.ClientMenuOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button transaction_btn;
    public Button modeling_btn;
    public Button profile_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        addListeners();
    }

    private void addListeners() {
        dashboard_btn.setOnAction(event -> onDashboard());
        transaction_btn.setOnAction(event -> onTransactions());
        modeling_btn.setOnAction(event -> onAccounts());
        logout_btn.setOnAction(event -> onLogout());
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
    }

    private void onTransactions() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.TRANSACTIONS);
    }

    private void onAccounts() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.ACCOUNTS);
    }

    private void onLogout() {
        // Retrieve stage
        Stage stage = (Stage) dashboard_btn.getScene().getWindow();
        // Close the stage
        Model.getInstance().getViewFactory().closeStage(stage);
        // Return primary stage
        Model.getInstance().getViewFactory().showLoginWindow();
        // Set Re-evaluation of credentials to false
        Model.getInstance().setClientLoginSuccessCheck(false);
    }


}
