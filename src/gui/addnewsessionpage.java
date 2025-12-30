package gui;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
import javafx.animation.TranslateTransition;

public class addnewsessionpage {
    
    @FXML private BorderPane root;
    @FXML private VBox sidePanel;
    @FXML private StackPane contentPane;
    
    // TextFields for session form
    @FXML private TextField subjectField;
    @FXML private TextField locationField;
    @FXML private TextField descriptionField;
    @FXML private TextField timeField;
    @FXML private TextField capacityField;

    
    private Rectangle gradientRect;
    private double t = 0;
    private boolean panelVisible = true;
    
    @FXML
    private void initialize() {
        // Setup animated background
        setupAnimatedBackground();
        
        // Set proper field IDs based on FXML
        // Since FXML uses different IDs, we need to find them
        setupFieldReferences();
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
    
    private void setupFieldReferences() {
        // Find text fields by their IDs from FXML
        if (subjectField == null) {
            // Try to find by looking up
            subjectField = (TextField) root.lookup("#usernameField");
        }
        if (locationField == null) {
            locationField = (TextField) root.lookup("#usernameField1");
        }
        if (descriptionField == null) {
            descriptionField = (TextField) root.lookup("#usernameField11");
        }
        if (timeField == null) {
            timeField = (TextField) root.lookup("#usernameField111");
        }
        if (capacityField == null) {
            timeField = (TextField) root.lookup("#usernameField1111");
        }
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
    
    @FXML
    private void createSession() {
        System.out.println("Create Session button clicked");
        
        // Get values from fields
        String subject = subjectField != null ? subjectField.getText() : "";
        String location = locationField != null ? locationField.getText() : "";
        String description = descriptionField != null ? descriptionField.getText() : "";
        String time = timeField != null ? timeField.getText() : "";
        
        // Validation
        if (subject.isEmpty() || location.isEmpty() || time.isEmpty()) {
            showError("Validation Error", "Please fill in all required fields (Subject, Location, Time)");
            return;
        }
        
        // Here you would save the session to database or data structure
        System.out.println("Creating new session:");
        System.out.println("Subject: " + subject);
        System.out.println("Location: " + location);
        System.out.println("Description: " + description);
        System.out.println("Time: " + time);
        
        // Show success message
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Success");
        successAlert.setHeaderText("Session Created");
        successAlert.setContentText("New session has been created successfully!");
        successAlert.showAndWait();
        
        // Navigate back to makenewsession.fxml after creation
        try {
            Stage stage = (Stage) root.getScene().getWindow();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("makenewsession.fxml"));
            Scene scene = new Scene(loader.load());
            
            stage.setScene(scene);
            stage.centerOnScreen();
            
        } catch (Exception e) {
            e.printStackTrace();
            showError("Navigation Error", "Cannot return to session list");
        }
    }
    
    // Navigation methods
    @FXML
    private void handleDashboard() {
        navigateTo("dashboard.fxml");
    }
    
    @FXML
    private void handleRegisterClass() {
        navigateTo("joinsession.fxml");
        // TODO: Implement register class
    }
    
    @FXML
    private void handleJoinClass() {
        navigateTo("joinprivatesession.fxml");
    }
    
    @FXML
    private void handleJoinSession() {
        System.out.println("Join Session clicked");
        // TODO: Implement join session
    }
    
    @FXML
    private void handleMakeClass() {
        navigateTo("makenewsession.fxml");
    }
    
    @FXML
    private void handleLogout() {
        navigateTo("logoutt.fxml");
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