package gui.Controller;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import structure.*;
import util.*;

public class SignUpCTRL {

    @FXML private StackPane root;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmField;
    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private Button signupButton;
    @FXML private Hyperlink loginLink;
    @FXML private Rectangle bgRect;

    private AnimatedBackground ab;
    private static UniSystem sys = UniSystem.getInstance();

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
    private void signUp() {
        try {

            String fullName = fullNameField.getText().trim();
            String userName = usernameField.getText().trim();
            String email = emailField.getText().trim();
            String password = passwordField.getText().trim();
            String role = "Student";
            boolean success = sys.getAuthService().signup(fullName, email, userName, password, role);

            if (success) {

                AlertShow.showInfo("Sign Up Successfull! ", "Your Account have been created!");
                // add actual signup logic here (validation, saving user, etc.)

                // Load the main application layout (Root.fxml)
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/Login.fxml"));
                Parent root = loader.load();

                // Get current Stage
                Stage stage = (Stage) this.root.getScene().getWindow();

                // Replace the Scene root with the main app root
                stage.getScene().setRoot(root);
            } else {
                AlertShow.showError("Can't Sign", "Please complete all the required data");
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/Login.fxml"));
            Parent loginRoot = loader.load();

            Stage stage = (Stage) this.root.getScene().getWindow();
            stage.getScene().setRoot(loginRoot);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
