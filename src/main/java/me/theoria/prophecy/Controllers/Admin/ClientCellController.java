package me.theoria.prophecy.Controllers.Admin;

import me.theoria.prophecy.Models.Client;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientCellController implements Initializable {

    public Label fname_lbl;
    public Label lname_lbl;
    public Label pAdress_lbl;
    public Label ch_acc_lbl;
    public Label sv_acc_lbl;
    public Label date_lbl;
    public Button delete_btn;

    private final Client client;

    public ClientCellController(Client client){
        this.client = client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fname_lbl.textProperty().bind(client.firstNameProperty());
        lname_lbl.textProperty().bind(client.lastNameProperty());
        pAdress_lbl.textProperty().bind(client.pAddressProperty());
        ch_acc_lbl.textProperty().bind(client.fungibleAccountProperty().asString());
        sv_acc_lbl.textProperty().bind(client.salesAccountProperty().asString());
        date_lbl.textProperty().bind(client.dateProperty().asString());
    }
}
