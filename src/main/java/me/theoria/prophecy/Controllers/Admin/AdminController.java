package me.theoria.prophecy.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import me.theoria.prophecy.Models.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    // Initialize FXML border pain for the administration parent box
    public BorderPane admin_parent;

    //
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case CLIENTS -> admin_parent.setCenter(Model.getInstance().getViewFactory().getClientsView());
                case DEPOSIT -> admin_parent.setCenter(Model.getInstance().getViewFactory().getDepositView());
                default -> admin_parent.setCenter(Model.getInstance().getViewFactory().getCreateClientView());
            }
        });
    }
}
