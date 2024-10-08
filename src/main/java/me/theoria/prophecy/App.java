package me.theoria.prophecy;

import me.theoria.prophecy.Models.Model;
import me.theoria.prophecy.Views.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage){
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
