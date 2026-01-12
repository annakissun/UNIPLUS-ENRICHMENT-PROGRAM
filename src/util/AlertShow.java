package util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import model.Student;
import structure.UniSystem;

public class AlertShow {

    /**
     * Show error dialog
     */
    public static void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /**
     * Show info dialog
     */
    public static void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static Optional<String> showInput(String title, String inputmessage){
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle(title);
    dialog.setHeaderText(null);
    dialog.setContentText(inputmessage);

    return dialog.showAndWait();
}


    public static void showVerify() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Verify Student");

        TextField tfMatrix = new TextField();
        tfMatrix.setPromptText("Matrix Number");

        TextField tfAge = new TextField();
        tfAge.setPromptText("Age");

        alert.getDialogPane().setContent(new VBox(10, tfMatrix, tfAge));

        // Show dialog and wait for OK or Cancel
        var result = alert.showAndWait();

        if (result.isEmpty() || result.get().getButtonData().isCancelButton()) {
            return; // user cancelled
        }

        // Validate input
        String matrixStr = tfMatrix.getText();
        String ageStr = tfAge.getText();

        if (matrixStr.isEmpty() || ageStr.isEmpty()) {
            showError("Invalid Input", "Matrix number and Age cannot be empty.");
            return;
        }

        try {
            int Matrix = Integer.parseInt(matrixStr);
            int age = Integer.parseInt(ageStr);

            Student s = (Student) UniSystem.getInstance().getAuthService().getCurrentUser();
            s.setMatrixNum(Matrix);
            s.setAge(age);

        } catch (NumberFormatException e) {
            showError("Invalid Input", "Matrix number and Age must be numbers.");
        }
    }

}