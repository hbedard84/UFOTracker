package Controllers;

        import Models.UfoSighting;
        import Utilities.DatabaseUtility;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.chart.BarChart;
        import javafx.scene.chart.CategoryAxis;
        import javafx.scene.chart.NumberAxis;
        import javafx.scene.chart.XYChart;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;

        import java.lang.reflect.Array;
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

    private XYChart.Series ufoSeries;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ufoSeries = new XYChart.Series();
        ufoSeries.setName("UFO Sightings");
        xAxis.setLabel("Country");
        yAxis.setLabel("# of Reported UFO Sightings");

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
