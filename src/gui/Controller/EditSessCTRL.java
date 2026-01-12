package gui.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Session;
import structure.UniSystem;

public class EditSessCTRL {

    @FXML private TextField subjectField;
    @FXML private TextField locationField;
    @FXML private TextField descriptionField;
    @FXML private TextField timeField;
    @FXML private TextField capacityField;
    @FXML private CheckBox isPrivateBox;

    private Session session;
    private UniSystem sys;

    public void init(Session s) {
        this.session = s;
        this.sys = UniSystem.getInstance();

        String currentUser = sys.getAuthService().getCurrentUser().getUsername();

        if (!session.canEdit(currentUser)) {
            showError("You are not allowed to edit this session.");
            return;
        }

        loadSessionData();
    }

    private void loadSessionData() {
        subjectField.setText(session.getSubject());
        locationField.setText(session.getLocation());
        descriptionField.setText(session.getDescription());
        timeField.setText(session.getTime());
        capacityField.setText(String.valueOf(session.getCapacity()));
        isPrivateBox.setSelected(session.isPrivate());
    }

    @FXML
    private void createSession() {
        session.setSubject(subjectField.getText());
        session.setLocation(locationField.getText());
        session.setDescription(descriptionField.getText());
        session.setTime(timeField.getText());
        session.setCapacity(Integer.parseInt(capacityField.getText()));
        session.setPrivate(isPrivateBox.isSelected());

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Session updated successfully.");
        a.showAndWait();
    }

    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(msg);
        a.showAndWait();
    }
}
