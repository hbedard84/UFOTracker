import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Views/graphUFOView.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("Views/style.css");
        stage.setTitle("UFO Tracker - Worldwide Sightings");
        // change default icon to ufo image
        stage.getIcons().add(new Image("Images/ufoicon.png"));
        stage.setScene(scene);
        stage.show();
    }
}