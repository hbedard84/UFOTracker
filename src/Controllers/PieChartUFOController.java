package Controllers;

import Utilities.DatabaseUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PieChartUFOController implements Initializable {

    @FXML
    private PieChart shapePieChart;

    @FXML
    private Button btn_intlView;

    @FXML
    private Button btn_canada;

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

    @FXML
    void changeToCanadaView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/canadaUFOView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
        stage.setTitle("UFO Tracker - Canadian Sightings");
        // change default icon to ufo image
        stage.getIcons().add(new Image("/Images/ufo.png"));
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Getting the Piechart data from the database using a hashmap
        HashMap<String,Integer> shapeData = null;
        try {
            shapeData = DatabaseUtility.getShapeData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // iterate through the hashmap of shape/total pairs to add the data to the pie chart
        shapeData.forEach((k,v) -> shapePieChart.getData().add(new PieChart.Data(k,v)));

        //Setting where the display starts
        shapePieChart.setStartAngle(180);

        //setting the direction to arrange the data
        shapePieChart.setClockwise(true);

        //Defining the label line length
        shapePieChart.setLabelLineLength(15);

        //Making the labels visible
        shapePieChart.setLabelsVisible(true);



    }

}
