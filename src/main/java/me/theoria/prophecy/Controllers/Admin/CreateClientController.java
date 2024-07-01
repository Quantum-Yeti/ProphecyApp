package me.theoria.prophecy.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import me.theoria.prophecy.Models.Model;

import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;

public class CreateClientController implements Initializable {
    public TextField fName_fld;
    public TextField lName_fld;
    public TextField password_fld;
    public CheckBox pAddress_box;
    public Label pAddress_lbl;
    public CheckBox liq_acc_box;
    public TextField liq_amount_fld;
    public CheckBox sales_acc_box;
    public TextField sales_amount_fld;
    public Button create_client_btn;
    public Label error_lbl;

    private String payeeAddress;
    private boolean createLiquidAccountCheck = false;
    private boolean createSalesAccountCheck = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_client_btn.setOnAction(event -> createClient());
        pAddress_box.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) {
                payeeAddress = createPayeeAddress();
                onCreatePayeeAddress();
            }
        });

        liq_acc_box.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            createLiquidAccountCheck = true;
        });

        sales_acc_box.selectedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) {
                createSalesAccountCheck = true;
            }
        });

    }

    private void createClient() {
        if (createLiquidAccountCheck) {
            createAccount("Liquid");
        }

        if (createSalesAccountCheck) {
            createAccount("Sales");
        }

        String fName = fName_fld.getText();
        String lName = lName_fld.getText();
        String password = password_fld.getText();

        Model.getInstance().getDatabaseDriver().createClient(fName, lName, payeeAddress, password, LocalDate.now());
        error_lbl.setStyle("-fx-text-fill: blue; -fx-font-size: 1.3em; -fx-font-weight: bold");
        error_lbl.setText("Client Created");
        emptyFields();
    }



    private void createAccount(String accountType) {
        double balance = Double.parseDouble(liq_amount_fld.getText());

        // Account Number Generation
        String firstVar = "5675";
        String secondVar = Integer.toString(new Random().nextInt(9999) + 1000);
        String accountNumber = firstVar + " " + secondVar;

        if (accountType.equals("Checking")) {
            Model.getInstance().getDatabaseDriver().createLiquidAccount(payeeAddress, accountNumber, 10, balance);
        } else {
            Model.getInstance().getDatabaseDriver().createSalesAccount(payeeAddress, accountNumber, 200, balance);
        }

    }

    private void onCreatePayeeAddress() {
        if (fName_fld.getText() != null & lName_fld.getText() != null) {
            pAddress_lbl.setText(payeeAddress);
        }
    }

    private String createPayeeAddress() {
        int cred = Model.getInstance().getDatabaseDriver().getLastClientCred() + 1;
        char fChar = Character.toLowerCase(fName_fld.getText().charAt(0));
        return "@"+fChar+lName_fld.getText()+cred;
    }


    private void emptyFields() {
        fName_fld.setText("");
        lName_fld.setText("");
        password_fld.setText("");
        pAddress_box.setSelected(false);
        pAddress_lbl.setText("");
        liq_acc_box.setSelected(false);
        liq_amount_fld.setText("");
        sales_acc_box.setSelected(false);
        sales_amount_fld.setText("");
    }




}


