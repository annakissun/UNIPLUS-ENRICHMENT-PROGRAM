package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import structure.UniSystem;

public class App extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
         //stage = new Stage();
        stage.setTitle("Fahmi app");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/Login.fxml"));
        Rectangle2D r = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(loader.load());
        stage.setWidth(r.getWidth() * 0.85);
        stage.setHeight(r.getHeight() * 0.85);
        stage.setScene(scene);
        stage.show();
        stage.centerOnScreen();
    }
    
    public static void main(String[] args) throws Exception {
        UniSystem.getInstance();
        launch(args);
    }
}
