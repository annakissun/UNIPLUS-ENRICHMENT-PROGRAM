package gui;


import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import structure.UniSystem;

public class loginpage {
    
    @FXML private BorderPane root;
    @FXML private VBox card;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Hyperlink signupLink;
    
    private UniSystem system = UniSystem.getInstance();
    
    private Rectangle gradientRect;
    private double t = 0;
    
    @FXML
    private void initialize() {
        card.setPrefSize(400, 250);
        
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
    private void handleLogin() {
        try {
            String username = usernameField.getText();
            String password = passwordField.getText();
            boolean success = system.getAuthService().login(username, password);

            if (success) {
                Stage stage = (Stage) root.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                Scene scene = new Scene(loader.load());
                stage.setScene(scene);
                stage.centerOnScreen();
            } else {
                System.out.println("Invalid username or password lol");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    // ADD THIS METHOD - Navigation to Signup Page
    @FXML
    private void goToSignup() {
        try {
            // Get current stage
            Stage stage = (Stage) root.getScene().getWindow();
            
            // Load signup FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
            Scene scene = new Scene(loader.load());
            
            // Apply same window size
            stage.setScene(scene);
            stage.centerOnScreen();        
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Cannot load signup page");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void handleSignUp(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean success = system.getAuthService().signup(username, password,"Guest");
        if (success) {
            System.out.println("yes");
        }
    }
}