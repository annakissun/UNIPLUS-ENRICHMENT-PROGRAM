package gui.Controller;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import structure.UniSystem;
import util.*;

public class LoginCTRL {
    //Data members
    @FXML private StackPane root;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Hyperlink signupLink;
    @FXML private Rectangle bgRect;

    private AnimatedBackground ab;
    private static UniSystem sys = UniSystem.getInstance();

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
            boolean success = sys.getAuthService().login(usernameField.getText(), passwordField.getText());
            if (success) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/Root.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) this.root.getScene().getWindow();
            stage.getScene().setRoot(root);
        } else {
            AlertShow.showError("Login failed!", "Enter all the information needed!!");
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToSignup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/SignUp.fxml"));
            Parent loginRoot = loader.load();
            Stage stage = (Stage) this.root.getScene().getWindow();
            stage.getScene().setRoot(loginRoot);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}