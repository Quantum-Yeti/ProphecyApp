package me.theoria.prophecy.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import me.theoria.prophecy.Models.Model;
import me.theoria.prophecy.Views.AdminMenuOptions;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    // FXML labels and pointers
    public Button create_client_btn;
    public Button clients_btn;
    public Button adminsum_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    // Adding listeners for button events for admin window
    private void addListeners() {
        create_client_btn.setOnAction(event -> onCreateClient());
        clients_btn.setOnAction(event -> onClients());
        adminsum_btn.setOnAction(event -> onDeposit());
        logout_btn.setOnAction(event -> onLogout());
    }

    private void onCreateClient() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CREATE_CLIENT);
    }

    private void onClients() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CLIENTS);
    }

    private void onDeposit() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.SUMMARY);
    }

    private void onLogout() {
        // Retrieve stage
        Stage stage = (Stage) clients_btn.getScene().getWindow();
        // Close the stage
        Model.getInstance().getViewFactory().closeStage(stage);
        // Return primary stage
        Model.getInstance().getViewFactory().showLoginWindow();
        // Set Re-evaluation of credentials to false
        Model.getInstance().setAdminLoginSuccessCheck(false);
    }

}
