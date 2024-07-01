package me.theoria.prophecy.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import me.theoria.prophecy.Models.Model;
import me.theoria.prophecy.Views.ClientCellFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientsController implements Initializable {
    public ListView clientsListview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        initData();
        clientsListview.setItems(Model.getInstance().getClients());
        clientsListview.setCellFactory(c -> new ClientCellFactory());
    }

    private void initData() {
        if (Model.getInstance().getClients().isEmpty()) {
            Model.getInstance().setClients();
        }
    }
}
