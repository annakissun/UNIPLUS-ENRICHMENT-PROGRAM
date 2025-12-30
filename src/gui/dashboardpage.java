package gui;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class dashboardpage {
    
    @FXML private BorderPane root;
    @FXML private VBox sidePanel;
    @FXML private StackPane contentPane;
    
    private Rectangle gradientRect;
    private double t = 0;
    private boolean panelVisible = true;
    
    @FXML
    private void initialize() {
        // Setup animated background
        setupAnimatedBackground();
    }
    
    private void setupAnimatedBackground() {
        gradientRect = new Rectangle();
        gradientRect.widthProperty().bind(root.widthProperty());
        gradientRect.heightProperty().bind(root.heightProperty());
        
        contentPane.getChildren().add(0, gradientRect);
        
        // Animated gradient timer
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                t += 0.000575;
                if (t > 1) t = 0;
                
                double fade = (Math.sin(t * 2 * Math.PI) + 1) / 2;
                
                Color c1 = lerp(Color.web("#8DB9FF"), Color.web("#D3C2FF"), fade);
                Color c2 = lerp(Color.web("#D3C2FF"), Color.web("#FFC1E8"), fade);
                Color c3 = lerp(Color.web("#FFC1E8"), Color.web("#8DB9FF"), fade);
                
                LinearGradient gradient = new LinearGradient(
                    0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                    new Stop(0.0, c1),
                    new Stop(0.5, c2),
                    new Stop(1.0, c3)
                );
                
                gradientRect.setFill(gradient);
            }
        };
        timer.start();
    }
    
    private Color lerp(Color a, Color b, double t) {
        double r = a.getRed() * (1 - t) + b.getRed() * t;
        double g = a.getGreen() * (1 - t) + b.getGreen() * t;
        double bVal = a.getBlue() * (1 - t) + b.getBlue() * t;
        return new Color(r, g, bVal, 1.0);
    }
    
    @FXML
    private void togglePanel() {
        TranslateTransition transition = new TranslateTransition(Duration.millis(300), sidePanel);
        
        if (panelVisible) {
            // Hide panel
            transition.setToX(-sidePanel.getWidth());
            transition.setOnFinished(e -> {
                root.setLeft(null);
                ((Button) root.getTop().lookup("#toggleButton")).setText("Show");
            });
        } else {
            // Show panel
            root.setLeft(sidePanel);
            sidePanel.setTranslateX(-sidePanel.getWidth());
            transition.setToX(0);
            ((Button) root.getTop().lookup("#toggleButton")).setText("Hide");
        }
        
        transition.play();
        panelVisible = !panelVisible;
    }
    
    // Navigation methods
    @FXML
    private void handleDashboard() {
        // Already on dashboard, do nothing
        System.out.println("Already on Dashboard");
    }
    
    @FXML
    private void handleRegisterClass() {
         navigateTo("joinsession.fxml");
        // TODO: Create registerclass.fxml and implement this
    }
    
    @FXML
    private void handleJoinClass() {
        navigateTo("joinprivatesession.fxml");
    }
    
    @FXML
    private void handleJoinSession() {
        System.out.println("Join Session clicked");
        // TODO: Create joinsession.fxml and implement this
    }

    @FXML
    private void handleLogout() {
        navigateTo("logoutt.fxml");
    }
    
    @FXML
    private void handleMakeClass() {
        navigateTo("makenewsession.fxml");
    }
    
    private void navigateTo(String fxmlFile) {
        try {
            Stage stage = (Stage) root.getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(loader.load());
            
            stage.setScene(scene);
            stage.centerOnScreen();
            
        } catch (Exception e) {
            e.printStackTrace();
            showError("Navigation Error", "Cannot load " + fxmlFile);
        }
    }
    
    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}