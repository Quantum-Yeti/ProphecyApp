package me.theoria.prophecy.Controllers.Client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import me.theoria.prophecy.Models.Model;
import me.theoria.prophecy.Views.ViewFactory;

import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.chart.XYChart.*;
import static me.theoria.prophecy.Controllers.Client.LinearRegression.predictForValue;


public class ClientSummaryController implements Initializable {

    @FXML
    public BarChart<String, Double> barChart;
    public LineChart<String, Double> lineChart;
    public TextArea linearOutput;
    public Button linOutput;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addListeners();
        linOutput.setOnAction(event -> {
            onLinear();
            System.out.println(predictForValue(13));
        });

        XYChart.Series<String, Double> series1 = new Series<>();
        series1.setName("Fungible");
        series1.getData().add(new XYChart.Data<>("@bMsupply1", 1194.97));
        series1.getData().add(new XYChart.Data<>("@dSdvendor2", 1000.00));
        series1.getData().add(new XYChart.Data<>("@dWinters3", 1000.00));
        series1.getData().add(new XYChart.Data<>("@sAnderson4", 756.42));
        series1.getData().add(new XYChart.Data<>("@sEasonal5", 2045.77));
        series1.getData().add(new XYChart.Data<>("@jAmesretail6", 4020.31));
        series1.getData().add(new XYChart.Data<>("@pHdpharm7", 2500.00));
        series1.getData().add(new XYChart.Data<>("@mElindavine8", 2311.56));
        series1.getData().add(new XYChart.Data<>("@kAylascookies9", 6000.00));

        XYChart.Series<String, Double> series2 = new Series<>();
        series2.setName("Sales");
        series2.getData().add(new XYChart.Data<>("@bMsupply1", 13337.68));
        series2.getData().add(new XYChart.Data<>("@dSdvendor2", 27000.00));
        series2.getData().add(new XYChart.Data<>("@dWinters3", 14000.00));
        series2.getData().add(new XYChart.Data<>("@sAnderson4", 6210.05));
        series2.getData().add(new XYChart.Data<>("@sEasonal5", 16992.04));
        series2.getData().add(new XYChart.Data<>("@jAmesretail6", 40500.00));
        series2.getData().add(new XYChart.Data<>("@pHdpharm7", 26500.00));
        series2.getData().add(new XYChart.Data<>("@mElindavine8", 1411.06));
        series2.getData().add(new XYChart.Data<>("@kAylascookies9", 27200.00));

        XYChart.Series<String, Double> series6 = new Series<>();
        series6.setName("Expenses");
        series6.getData().add(new XYChart.Data<>("@bMsupply1", 100.00));
        series6.getData().add(new XYChart.Data<>("@dSdvendor2", 50.00));
        series6.getData().add(new XYChart.Data<>("@dWinters3", 600.00));
        series6.getData().add(new XYChart.Data<>("@sAnderson4", 300.00));
        series6.getData().add(new XYChart.Data<>("@sEasonal5", 10.00));
        series6.getData().add(new XYChart.Data<>("@jAmesretail6", 900.00));
        series6.getData().add(new XYChart.Data<>("@pHdpharm7", 10000.00));
        series6.getData().add(new XYChart.Data<>("@mElindavine8", 50.00));
        series6.getData().add(new XYChart.Data<>("@kAylascookies9", 100.00));
        barChart.getData().addAll(series1, series2, series6);


        XYChart.Series<String, Double> series3 = new Series<>();
        series3.setName("Fungible");
        series3.getData().add(new XYChart.Data<>("@bMsupply1", 1194.97));
        series3.getData().add(new XYChart.Data<>("@dSdvendor2", 1000.00));
        series3.getData().add(new XYChart.Data<>("@dWinters3", 1000.00));
        series3.getData().add(new XYChart.Data<>("@sAnderson4", 756.42));
        series3.getData().add(new XYChart.Data<>("@sEasonal5", 2045.77));
        series3.getData().add(new XYChart.Data<>("@jAmesretail6", 4020.31));
        series3.getData().add(new XYChart.Data<>("@pHdpharm7", 2500.00));
        series3.getData().add(new XYChart.Data<>("@mElindavine8", 2311.56));
        series3.getData().add(new XYChart.Data<>("@kAylascookies9", 6000.00));

        XYChart.Series<String, Double> series4 = new Series<>();
        series4.setName("Sales");
        series4.getData().add(new XYChart.Data<>("@bMsupply1", 13337.68));
        series4.getData().add(new XYChart.Data<>("@dSdvendor2", 27000.00));
        series4.getData().add(new XYChart.Data<>("@dWinters3", 14000.00));
        series4.getData().add(new XYChart.Data<>("@sAnderson4", 6210.05));
        series4.getData().add(new XYChart.Data<>("@sEasonal5", 16992.04));
        series4.getData().add(new XYChart.Data<>("@jAmesretail6", 40500.00));
        series4.getData().add(new XYChart.Data<>("@pHdpharm7", 26500.00));
        series4.getData().add(new XYChart.Data<>("@mElindavine8", 1411.06));
        series4.getData().add(new XYChart.Data<>("@kAylascookies9", 27200.00));

        XYChart.Series<String, Double> series5 = new Series<>();
        series5.setName("Expenses");
        series5.getData().add(new XYChart.Data<>("@bMsupply1", 100.00));
        series5.getData().add(new XYChart.Data<>("@dSdvendor2", 50.00));
        series5.getData().add(new XYChart.Data<>("@dWinters3", 600.00));
        series5.getData().add(new XYChart.Data<>("@sAnderson4", 300.00));
        series5.getData().add(new XYChart.Data<>("@sEasonal5", 10.00));
        series5.getData().add(new XYChart.Data<>("@jAmesretail6", 900.00));
        series5.getData().add(new XYChart.Data<>("@pHdpharm7", 10000.00));
        series5.getData().add(new XYChart.Data<>("@mElindavine8", 50.00));
        series5.getData().add(new XYChart.Data<>("@kAylascookies9", 100.00));
        lineChart.getData().addAll(series3, series4, series5);





    }

    private void onLinear () {
        Model.getInstance().getViewFactory().showLinearWindow();
    }

    private void addListeners() {
        linOutput.setOnAction(e -> onLinear());
    }



}




