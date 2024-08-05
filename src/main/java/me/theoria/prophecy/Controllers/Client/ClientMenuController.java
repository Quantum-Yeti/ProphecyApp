package me.theoria.prophecy.Controllers.Client;

import me.theoria.prophecy.Models.Model;
import me.theoria.prophecy.Views.ClientMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

// Declare JavaFX components
public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button transaction_btn;
    public Button accounts_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;

    // Add listeners to JavaFX components
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners(){
        dashboard_btn.setOnAction(e->onDashboard());
        transaction_btn.setOnAction(e->onTransactions());
        accounts_btn.setOnAction(e->onAccounts());
        profile_btn.setOnAction(e->onProfile());
        report_btn.setOnAction(e->onReport());
        logout_btn.setOnAction(e->onLogout());
    }

    private void onTransactions(){ Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.TRANSACTION);}

    private void onAccounts(){ Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.ACCOUNTS);}

    private void onDashboard(){ Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);}

    private void onProfile(){Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.SUMMARY);}

    private void onReport(){Model.getInstance().getViewFactory().showReportWindow();}

    private void onLogout(){
        // Get Stage
        Stage stage = (Stage) dashboard_btn.getScene().getWindow();
        // Close the client
        Model.getInstance().getViewFactory().closeStage(stage);
        // Show Login Window
        Model.getInstance().getViewFactory().showLoginWindow();
        // Set Client Login Success Flag to False
        Model.getInstance().setClientLoginSuccessFlag(false);
    }
}

