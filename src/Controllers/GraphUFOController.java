package Controllers;

        import Models.UfoSighting;
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
        HashMap<String, Integer> chartData = new HashMap<>();


       // ufoSeries.getData().add(new XYChart.Data(xData,yData));

    }
}
