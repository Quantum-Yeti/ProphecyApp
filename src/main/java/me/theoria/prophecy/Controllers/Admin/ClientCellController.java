package me.theoria.prophecy.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import me.theoria.prophecy.Models.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {
    public Label fName_lbl;
    public Label lName_lbl;
    public Label pAddress_lbl;
    public Label liq_acc_lbl;
    public Label sales_acc_lbl;
    public Label date_lbl;
    public Button delete_btn;

    private final Client client;

    public ClientCellController(Client client){
        this.client = client;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Bind Properties
        fName_lbl.textProperty().bind(client.firstNameProperty());
        lName_lbl.textProperty().bind(client.lastNameProperty());
        pAddress_lbl.textProperty().bind(client.pAddressProperty());
        liq_acc_lbl.textProperty().bind(client.cAccountProperty().asString());
        sales_acc_lbl.textProperty().bind(client.sAccountProperty().asString());
        date_lbl.textProperty().bind(client.dateProperty().asString());
    }
}
