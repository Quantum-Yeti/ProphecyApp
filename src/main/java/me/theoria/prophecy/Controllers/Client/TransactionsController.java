package me.theoria.prophecy.Controllers.Client;

import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import me.theoria.prophecy.Models.Model;
import me.theoria.prophecy.Models.Transaction;
import me.theoria.prophecy.Views.TransactionCellFactory;

import java.net.URL;
import java.util.ResourceBundle;

// Initialize FXML listview for the transaction controller and container
public class TransactionsController implements Initializable {
    public ListView<Transaction> transactions_listview;


    // FXML function pointers
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initAllTransactionsL();
        transactions_listview.setItems(Model.getInstance().getAllTransactions());
        transactions_listview.setCellFactory(event -> new TransactionCellFactory());
    }

    /* If Transactions ListView is empty, display all transactions */
    private void initAllTransactionsL() {
        if (Model.getInstance().getAllTransactions().isEmpty()) {
            Model.getInstance().setAllTransactions();
        }
    }
}
