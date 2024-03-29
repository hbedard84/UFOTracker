package Controllers;

import Utilities.DatabaseUtility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.TreeMap;

public class CanadaUFOController implements Initializable {

    @FXML
    private BarChart<?, ?> canadaBarChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button btn_intlView;

    @FXML
    private Button btn_pieChart;

    /***
     * This method is called when the btn_intlView button is clicked, in order to switch scenes to the Worldwide Sightings graph
     * @param event
     * @throws IOException
     */
    @FXML
    void changeToAllCountryView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/graphUFOView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
        stage.setTitle("UFO Tracker - Worldwide Sightings");
        stage.getIcons().add(new Image("/Images/ufoicon.png"));  // changes default icon to ufo image
        stage.setScene(scene);
        stage.show();
    }

    /***
     * This method is called when the btn_pieChart button is clicked, in order to switch scenes to the Shapes piechart
     * @param event
     * @throws IOException
     */
    @FXML void changeToShapesView(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/Views/pieChartUFOView.fxml"));
        Scene scene3 = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
        stage.setTitle("UFO Tracker - Reported Shapes");
        // change default icon to ufo image
        stage.getIcons().add(new Image("/Images/ufoicon.png"));
        stage.setScene(scene3);
        stage.show();
    }

    private XYChart.Series canadaSeries;  //data series containing the canadian sightings data

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        canadaSeries = new XYChart.Series();
        canadaSeries.setName("Canadian UFO Sightings");

        //the settings for the category(x) axis
        xAxis.setLabel("Province");
        xAxis.setTickLabelFill(Color.WHITE);

        //settings for the number(y) axis
        yAxis.setLabel("# of Reported Sightings");
        yAxis.setTickLabelFill(Color.WHITE);

        //creating a treemap of the canadian data from the database
        TreeMap<String, Integer> chartData = new TreeMap<>();
        try {
            //returns the total number of sightings per province in a treemap<province,total>
            chartData = DatabaseUtility.getCanadaData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //populating the data for the barchart by iterating through the treemap of canadian data retrieved from the database
        chartData.forEach((k, v) -> canadaSeries.getData().add(new XYChart.Data(k, v)));

        //add the data series to the chart
        canadaBarChart.getData().addAll(canadaSeries);
    }

}
