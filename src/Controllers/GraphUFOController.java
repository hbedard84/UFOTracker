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
        import javafx.scene.paint.Color;
        import javafx.stage.Stage;

        import java.io.IOException;
        import java.net.URL;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.ResourceBundle;
        import java.util.TreeMap;

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

    /***
     * This method is called when the btn_canada button is clicked, in order to switch scenes to the Canadian Sightings graph
     * @param event
     * @throws IOException
     */
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

    private XYChart.Series ufoSeries; //data series containing the worldwide sightings data

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ufoSeries = new XYChart.Series();
        ufoSeries.setName("UFO Sightings");

        //settings for the country (y)axis
        xAxis.setLabel("Country");
        xAxis.setTickLabelFill(Color.WHITE);

        //settings for the number(y) axis
        yAxis.setLabel("# of Reported Sightings");
        yAxis.setAutoRanging(false); //allows you to set a custom display range for the values
        yAxis.setUpperBound(400); //this zooms in the chart by only displaying data up to 400 so the smaller bars can be seen, since the default range is too large
        yAxis.setTickLabelFill(Color.WHITE);

        //creating a treemap of the worldwide data from the database
        TreeMap<String, Integer> chartData = new TreeMap<>();
        try {
            //returns the total number of sightings per country in a treemap<country,total>
            chartData = DatabaseUtility.getChartData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //populating the data for the barchart by iterating through the treemap of worldwide data retrieved from the database
        chartData.forEach((k,v) -> ufoSeries.getData().add(new XYChart.Data(k,v)));

        //add the data series to the chart
        barChart.getData().addAll(ufoSeries);
    }
}
