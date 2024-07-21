package me.theoria.prophecy;

import javafx.application.Application;

import javafx.stage.Stage;
import me.theoria.prophecy.Models.Model;
import me.theoria.prophecy.Views.ViewFactory;

// Boot initial FXML login window stage
public class App extends Application {
    @Override
    public void start(Stage stage) {

        //Testing splash screen/login

        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/Login.fxml"));
        //Scene scene = new Scene(fxmlLoader.load());
        //stage.setScene(scene);
        //stage.show();

        Model.getInstance().getViewFactory().showLoginWindow();


    }
}
