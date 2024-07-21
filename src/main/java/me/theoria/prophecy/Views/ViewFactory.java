package me.theoria.prophecy.Views;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import me.theoria.prophecy.Controllers.Admin.AdminController;
import me.theoria.prophecy.Controllers.Client.ClientController;

public class ViewFactory {
    //Account Selector
    private AccountType loginAccountType;

    //Client Views
    private AnchorPane dashboardView;
    private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;
    private AnchorPane accountsView;

    //Admin Views
    private final ObjectProperty<AdminMenuOptions> adminSelectedMenuItem;
    private AnchorPane transactionsView;
    private AnchorPane createClientView;
    private AnchorPane clientsView;
    private AnchorPane depositView;

    /* View Factory - ObjectProperties */
    public ViewFactory(){
        this.loginAccountType = AccountType.CLIENT;
        this.clientSelectedMenuItem = new SimpleObjectProperty<>();
        this.adminSelectedMenuItem = new SimpleObjectProperty<>();
    }

    /* Login Setter Getter */

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    /*Client Views Section */
    public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }


    public AnchorPane getDashboardView() {
        if (dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/Fxml/Client/Dashboard.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public AnchorPane getTransactionsView() {
        if (transactionsView == null){
            try {
                transactionsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Transactions.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return transactionsView;
    }

    public AnchorPane getAccountsView() {
        if (accountsView == null){
            try {
                accountsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Modeling.fxml")).load();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return accountsView;
    }

    public void showClientWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController = new ClientController();
        loader.setController(clientController);
        createStage(loader);
    }

    /* Admin Views Section */
    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem(){
        return adminSelectedMenuItem;
    }

    public AnchorPane getCreateClientView() {
        if(createClientView == null){
            try {
                createClientView = new FXMLLoader(getClass().getResource("/Fxml/Admin/CreateClient.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return createClientView;
    }

    public AnchorPane getClientsView() {
        if (clientsView == null){
            try {
                clientsView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Clients.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return clientsView;
    }

    public AnchorPane getDepositView() {
        if (depositView == null){
            try {
                depositView = new FXMLLoader(getClass().getResource("/Fxml/Admin/Deposit.fxml")).load();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return depositView;
    }

    public void showAdminWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController controller = new AdminController();
        loader.setController(controller);
        createStage(loader);
    }

    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        createStage(loader);

    }

    public void showMessagePop(String pAddress, String messageText) {
        StackPane pane = new StackPane();
        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.CENTER);
        Label sender = new Label(pAddress);
        Label message = new Label(messageText);
        vBox.getChildren().addAll(sender, message);
        pane.getChildren().add(vBox);
        Scene scene = new Scene(pane,300,100 );
        Stage stage = new Stage();
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/icon.png"))));
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Messages");
        stage.setScene(scene);
        stage.show();
    }

    /* Create JavaFX Stage */
    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e){
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/icon.png"))));
        stage.setResizable(false);
        stage.setTitle("Prophecy");
        stage.show();
    }

    /* Close JavaFX Stages */
    public void closeStage(Stage stage) {
        stage.close();
    }

}
