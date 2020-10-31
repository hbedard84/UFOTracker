package Controllers;

        import Models.UfoSighting;
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
        import javafx.scene.control.Label;
        import javafx.scene.image.Image;
        import javafx.stage.Stage;

        import java.io.IOException;
        import java.net.URL;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.ResourceBundle;

public class GraphUFOController implements Initializable {

    @FXML
    private BarChart<?, ?> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private Button btn_canada;

    @FXML
    private Button btn_pieChart;

    @FXML
    private Label titleLabel;

    @FXML
    void changeToCanadaView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Views/canadaUFOView.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
        stage.setTitle("UFO Tracker - Canadian Sightings");
        // change default icon to ufo image
        stage.getIcons().add(new Image("/Images/ufoicon.png"));
        stage.setScene(scene);
        stage.show();
    }

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

    private XYChart.Series ufoSeries;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ufoSeries = new XYChart.Series();
        ufoSeries.setName("UFO Sightings");
        xAxis.setLabel("Country");
        yAxis.setLabel("# of Reported UFO Sightings");
        yAxis.setAutoRanging(false); //allows you to set a custom display range for the values
        yAxis.setUpperBound(500); //this zooms in the chart so you can see the smaller bars better when the range is too lange

        HashMap<String, Integer> chartData = new HashMap<>();
        try {
            chartData = DatabaseUtility.getChartData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        chartData.forEach((k,v) -> ufoSeries.getData().add(new XYChart.Data(k,v)));

        barChart.getData().addAll(ufoSeries);


        /*
        //get total sightings count each for canada, us and international from database and add to hashmap
        HashMap<String, Integer> chartData = new HashMap<>();
        try {
            chartData.put("Canada", DatabaseUtility.getCountryTotal("ca"));
            chartData.put("USA", DatabaseUtility.getCountryTotal("us"));
            chartData.put("International", DatabaseUtility.getCountryTotal("intl"));
        } catch (SQLException throwables) {
                throwables.printStackTrace();
        }
        //for each country/country total pair in the hash map, add the data to the ufo series
        chartData.forEach((k,v) -> ufoSeries.getData().add(new XYChart.Data(k,v)));

        //add data to the chart
        barChart.getData().addAll(ufoSeries);




       // ufoSeries.getData().add(new XYChart.Data(xData,yData)); */

    }
}
