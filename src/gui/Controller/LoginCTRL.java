package gui.Controller;

import util.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Session;
import model.Student;
import structure.UniSystem;

public class LoginCTRL {

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
                // Create an FXMLLoader for Root.fxml (the main app layout)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/Root.fxml"));
            // Load Root.fxml and create its UI node hierarchy
            Parent root = loader.load();
            // Get the current Stage via the login page's root node
            Stage stage = (Stage) this.root.getScene().getWindow();
            // Replace the Scene's root with the main application root
            stage.getScene().setRoot(root);
            System.out.println("login success");
            System.out.println(sys.getAuthService().getCurrentUser().getUsername() + "is logged in " +sys.getAuthService().getCurrentUser().getPassword());
            System.out.println("creating session with 30 capacity");
            Session s = sys.getSessionManager().createSession(30, sys.getAuthService().getCurrentUser().getFullName(), "ITT270", true, "LT5", "Late is dead", "12:00");
            sys.getSessionManager().setCurrentSession(s);
            System.out.println("the current session is : " + s);
            System.out.println("Printing all session 1 ");
            sys.getSessionManager().printAllSess();
            System.out.println();
            System.out.println("Adding harris to this session");
            Student h = new Student("Harris", "harris@gmail.com", "HarrisJM", "12345", "Student", "Muhammad Harris Haikal", 20, 202444890);
            sys.getSessionManager().getCurrentSession().addStudent(h);
            sys.getStudentManager().addStudent(h);
            System.out.println("printing all session 2");
            sys.getSessionManager().printAllSess();
            System.out.println("not adding nurul to current session ");
            for (Student st : sys.getStudentManager().getStudents()) {
                if (!st.getFullName().equals("Nurul Aina")) {
                    sys.getSessionManager().getCurrentSession().addStudent(st);
                }
                
            }
            System.out.println("printin all session 3");
            sys.getSessionManager().printAllSess();
            for (Student st : sys.getStudentManager().getStudents()) {
                if (st.getFullName().equalsIgnoreCase("Harris")) {
                    System.out.println(st.getFullName() + "removed from the system!");
                    sys.getSessionManager().getCurrentSession().removeStudent(st);
                } else {System.out.println("Cant remove harris from system (student manager)");}
                
            }

            sys.getSessionManager().printAllSess();
            
        } else {System.out.println("failed");}
            
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