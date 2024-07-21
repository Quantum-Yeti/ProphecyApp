package me.theoria.prophecy.Controllers.Client;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import me.theoria.prophecy.Models.Model;
import me.theoria.prophecy.Models.Transaction;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {

    // FXML labels and pointers
    public FontAwesomeIconView in_icon;
    public FontAwesomeIconView out_icon;
    public Label trans_date_lbl;
    public Label sender_lbl;
    public Label receiver_lbl;
    public Button message_btn;
    public Label amount_lbl;

    // Implement transaction
    private final Transaction transaction;

    public TransactionCellController(Transaction transaction){
        this.transaction = transaction;
    }

    // Bind properties to fxml properties
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Bind properties to data - sender/receiver transaction list
        sender_lbl.textProperty().bind(transaction.senderProperty());
        receiver_lbl.textProperty().bind(transaction.receiverProperty());
        amount_lbl.textProperty().bind(transaction.amountProperty().asString());
        trans_date_lbl.textProperty().bind(transaction.dateProperty().asString());
        message_btn.setOnAction(event -> Model.getInstance().getViewFactory().showMessagePop(transaction.senderProperty().get(), transaction.messageProperty().get()));
        transIcons();
    }

    // Method to color encode send and receive transaction arrows
    private void transIcons() {
        if (transaction.senderProperty().get().equals(Model.getInstance().getClient().pAddressProperty().get())){
            in_icon.setFill(Color.rgb(240, 240, 240));
            out_icon.setFill(Color.RED);
        } else {
            in_icon.setFill(Color.BLUE);
            out_icon.setFill(Color.rgb(240, 240, 240));
        }
    }


}
