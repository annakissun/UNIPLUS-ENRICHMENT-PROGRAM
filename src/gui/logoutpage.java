package gui;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class logoutpage {
    
    @FXML private BorderPane root;
    @FXML private StackPane centerPane;
    
    private Rectangle gradientRect;
    private double t = 0;
    
    @FXML
    private void initialize() {
        // Create animated background
        setupAnimatedBackground();
    }
    
    private void setupAnimatedBackground() {
        gradientRect = new Rectangle();
        gradientRect.widthProperty().bind(root.widthProperty());
        gradientRect.heightProperty().bind(root.heightProperty());
        
        // Add gradient background behind the card
        StackPane backgroundPane = (StackPane) root.getCenter();
        backgroundPane.getChildren().add(0, gradientRect);
        
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
    private void handleYes() {
        System.out.println("User confirmed logout");
        
        try {
            // Navigate to login page atau shutdown
            Stage stage = (Stage) root.getScene().getWindow();
            
            // Option 1: Close application
            stage.close();
            
            // Option 2: Go back to login page
            /*
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.centerOnScreen();
            */
            
        } catch (Exception e) {
            e.printStackTrace();
            showError("Logout failed", e.getMessage());
        }
    }
    
    @FXML
    private void handleNo() {
        System.out.println("User cancelled logout");
        
        try {
            // Go back to dashboard
            Stage stage = (Stage) root.getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.centerOnScreen();
            
        } catch (Exception e) {
            e.printStackTrace();
            showError("Navigation failed", e.getMessage());
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