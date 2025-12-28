
package gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Start with login screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(loader.load());

        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        stage.setWidth(screen.getWidth() * 0.85);
        stage.setHeight(screen.getHeight() * 0.85);
        stage.centerOnScreen();

        stage.setTitle("Login & Signup UI");
        stage.setScene(scene);
        stage.show();
    }

    

    public static void main(String[] args) {
        launch(args);
    }
}