package gui;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class signuppage {
    
    @FXML private BorderPane root;
    @FXML private VBox card;
    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button signupButton;
    
    private Rectangle gradientRect;
    private double t = 0;
    
    @FXML
    private void initialize() {
        setupAnimatedBackground();
    }
    
    private void setupAnimatedBackground() {
        gradientRect = new Rectangle();
        gradientRect.widthProperty().bind(root.widthProperty());
        gradientRect.heightProperty().bind(root.heightProperty());
        
        StackPane stack = (StackPane) root.getCenter();
        stack.getChildren().add(0, gradientRect);
        
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
    private void handleSignup() {
        if (validateForm()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sign Up Successful");
            alert.setHeaderText("Account Created");
            alert.setContentText("Welcome, " + fullNameField.getText() + "!");
            alert.showAndWait();
            
            // After successful signup, go to login
            goToLogin();
        }
    }
    
    @FXML
    private void goToLogin() {
        try {
            // Get current stage
            Stage stage = (Stage) root.getScene().getWindow();
            
            // Load login FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Scene scene = new Scene(loader.load());
            
            // Apply same window size
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private boolean validateForm() {
        StringBuilder errors = new StringBuilder();
        
        if (fullNameField.getText().isEmpty()) {
            errors.append("• Full Name is required\n");
        }
        
        if (emailField.getText().isEmpty() || !emailField.getText().contains("@")) {
            errors.append("• Valid email is required\n");
        }
        
        if (usernameField.getText().isEmpty()) {
            errors.append("• Username is required\n");
        }
        
        if (passwordField.getText().isEmpty()) {
            errors.append("• Password is required\n");
        } else if (passwordField.getText().length() < 6) {
            errors.append("• Password must be at least 6 characters\n");
        }
        
        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            errors.append("• Passwords do not match\n");
        }
        
        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Please fix the following errors nigga:");
            alert.setContentText(errors.toString());
            alert.showAndWait();
            return false;
        }
        
        return true;
    }
}