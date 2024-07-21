module me.theoria.prophecy {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires jdk.jfr;


    opens me.theoria.prophecy to javafx.fxml;
    exports me.theoria.prophecy;
    exports me.theoria.prophecy.Controllers;
    exports me.theoria.prophecy.Controllers.Admin;
    exports me.theoria.prophecy.Controllers.Client;
    exports me.theoria.prophecy.Models;
    exports me.theoria.prophecy.Views;

}