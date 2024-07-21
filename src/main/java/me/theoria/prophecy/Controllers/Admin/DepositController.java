package me.theoria.prophecy.Controllers.Admin;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import me.theoria.prophecy.Models.Client;
import me.theoria.prophecy.Models.Model;
import me.theoria.prophecy.Views.ClientCellFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DepositController implements Initializable {
    // FXML labels and pointers
    public TextField pAddress_fld;
    public Button search_btn;
    public ListView<Client> result_listview;
    public TextField amount_fld;
    public Button deposit_btn;

    private Client client;

    // Set actions for buttons
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search_btn.setOnAction(event -> onClientSearch());
        deposit_btn.setOnAction(event -> onDeposit());
    }

    // Method to return list of clients
    private void onClientSearch() {
        ObservableList<Client> searchResults = Model.getInstance().searchClient(pAddress_fld.getText());
        result_listview.setItems(searchResults);
        result_listview.setCellFactory(e -> new ClientCellFactory());
        client = searchResults.get(0);
    }

    // Method to deposit money into sales account and return new balance
    private void onDeposit() {
        double amount = Double.parseDouble(amount_fld.getText());
        double newBalance = amount + client.sAccountProperty().get().balanceProperty().get();
        if (amount_fld.getText() != null) {
            Model.getInstance().getDatabaseDriver().depositSales(client.pAddressProperty().get(), newBalance);
        }
        emptyFields();
    }

    // Empty all fields after use
    private void emptyFields() {
        pAddress_fld.setText("");
        amount_fld.setText("");
    }

}
