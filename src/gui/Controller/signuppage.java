package gui.Controller;

import util.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class signuppage {

    @FXML private StackPane root;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmField;
    @FXML private Button signupButton;
    @FXML private Hyperlink loginLink;
    @FXML private Rectangle bgRect;

    private AnimatedBackground ab;

    @FXML
    private void initialize() {
        // Animated background
        ab = new AnimatedBackground();
        ab.attach(bgRect);

        // Make background always fit the window
        bgRect.widthProperty().bind(root.widthProperty());
        bgRect.heightProperty().bind(root.heightProperty());
    }

    @FXML
    private void handleSignup() {
        try {
            // add actual signup logic here (validation, saving user, etc.)

            // Load the main application layout (Root.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/Root.fxml"));
            Parent root = loader.load();

            // Get current Stage
            Stage stage = (Stage) this.root.getScene().getWindow();

            // Replace the Scene root with the main app root
            stage.getScene().setRoot(root);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToLogin() {
        try {
            // Replace current root with login.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/login.fxml"));
            Parent loginRoot = loader.load();

            Stage stage = (Stage) this.root.getScene().getWindow();
            stage.getScene().setRoot(loginRoot);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
