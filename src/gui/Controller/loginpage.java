package gui.Controller;

import util.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class loginpage {

    @FXML private StackPane root;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Hyperlink signupLink;
    @FXML private Rectangle bgRect;

    private AnimatedBackground ab;

    @FXML
    private void initialize() {

         // 🔥 ONE LINE reusable animated background
        ab = new AnimatedBackground();
        ab.attach(bgRect);

        bgRect.widthProperty().bind(root.widthProperty());
        bgRect.heightProperty().bind(root.heightProperty());
    }

   @FXML
    private void handleLogin() {
        try {
            // Create an FXMLLoader for Root.fxml (the main app layout)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/Root.fxml"));
            // Load Root.fxml and create its UI node hierarchy
            Parent root = loader.load();
            // Get the current Stage via the login page's root node
            Stage stage = (Stage) this.root.getScene().getWindow();
            // Replace the Scene's root with the main application root
            stage.getScene().setRoot(root);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToSignup() {
        try {
            // Replace current root with login.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/signup.fxml"));
            Parent loginRoot = loader.load();

            Stage stage = (Stage) this.root.getScene().getWindow();
            stage.getScene().setRoot(loginRoot);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}