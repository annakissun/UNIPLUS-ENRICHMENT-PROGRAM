package gui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class BackgroundAnimator extends Application {
    
    // Variables untuk animasi
    private Rectangle backgroundRect;
    private double t = 0;
    
    @Override
    public void start(Stage stage) {
        // Create background rectangle
        backgroundRect = new Rectangle();
        backgroundRect.setWidth(800);
        backgroundRect.setHeight(600);
        
        // Root container - HANYA background saja
        StackPane root = new StackPane();
        root.getChildren().add(backgroundRect);
        
        // Create scene
        Scene scene = new Scene(root, 800, 600);
        
        // Bind background size to scene
        backgroundRect.widthProperty().bind(scene.widthProperty());
        backgroundRect.heightProperty().bind(scene.heightProperty());
        
        // Start animation
        startBackgroundAnimation();
        
        // Setup stage
        stage.setTitle("Background Animation - Full Gradient");
        stage.setScene(scene);
        stage.show();
    }
    
    private void startBackgroundAnimation() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Update time
                t += 0.000575;
                if (t > 1) t = 0;
                
                // Calculate fade value
                double fade = (Math.sin(t * 2 * Math.PI) + 1) / 2;
                
                // Create gradient colors
                Color c1 = lerp(Color.web("#8DB9FF"), Color.web("#D3C2FF"), fade);
                Color c2 = lerp(Color.web("#D3C2FF"), Color.web("#FFC1E8"), fade);
                Color c3 = lerp(Color.web("#FFC1E8"), Color.web("#8DB9FF"), fade);
                
                // Create and set gradient
                LinearGradient gradient = new LinearGradient(
                    0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                    new Stop(0.0, c1),
                    new Stop(0.5, c2),
                    new Stop(1.0, c3)
                );
                
                backgroundRect.setFill(gradient);
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
    
    public static void main(String[] args) {
        launch(args);
    }
}