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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

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

    @FXML
    void changeToAllCountryView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/graphUFOView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
        stage.setTitle("UFO Tracker - Worldwide Sightings");
        // change default icon to ufo image
        stage.getIcons().add(new Image("/Images/ufo.png"));
        stage.setScene(scene);
        stage.show();
    }

    @FXML void changeToShapesView(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/Views/pieChartUFOView.fxml"));
        Scene scene3 = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
        stage.setTitle("UFO Tracker - Proportions of Reported Shapes");
        // change default icon to ufo image
        stage.getIcons().add(new Image("/Images/ufo.png"));
        stage.setScene(scene3);
        stage.show();
    }

    private XYChart.Series canadaSeries;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        canadaSeries = new XYChart.Series();
        canadaSeries.setName("Canadian UFO Sightings");
        xAxis.setLabel("Province");
        yAxis.setLabel("# of Reported UFO Sightings");

        HashMap<String, Integer> chartData = new HashMap<>();
        try {
            chartData = DatabaseUtility.getCanadaData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        chartData.forEach((k, v) -> canadaSeries.getData().add(new XYChart.Data(k, v)));

        canadaBarChart.getData().addAll(canadaSeries);
    }

}
