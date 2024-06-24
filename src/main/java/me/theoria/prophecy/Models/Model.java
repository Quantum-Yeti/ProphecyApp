package me.theoria.prophecy.Models;

import me.theoria.prophecy.Views.ViewFactory;

public class Model {

    private static Model model;
    private final ViewFactory viewFactory;
    private final DbDriver dbDriver;

    //Client Data
    private Client client;
    private boolean clientLoginSuccessFlag;
    //Admin Data

    private Model() {
        this.viewFactory = new ViewFactory();
        this.dbDriver = new DbDriver();
    }

    public static synchronized Model getInstance() {
        if (model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DbDriver getDatabaseDriver() {
        return dbDriver;
    }

}
