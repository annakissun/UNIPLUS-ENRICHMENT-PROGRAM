package gui.Controller;


import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import structure.UniSystem;
import util.*;

public class AddSessCTRL implements Navigable{

    //Data members
    private Navigator navigator;
    private static UniSystem sys = UniSystem.getInstance();

    @FXML private TextField subjectField;
    @FXML private TextField locationField;
    @FXML private TextField descriptionField;
    @FXML private TextField timeField;
    @FXML private TextField capacityField;
    @FXML private CheckBox isPrivateBox;

    @Override
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }
    
    public void createSession() {

        getInfo();
        // 2️⃣ Then navigate
        if (navigator != null) {
            navigator.navigateTo("/gui/view/MakeSess.fxml");
        } else {
            System.out.println("navigator is null");
        }
    }

    public void getInfo() {

        String subject = subjectField.getText();
        String location = locationField.getText();
        String desc = descriptionField.getText();
        String time = timeField.getText();
        boolean isPrivate = isPrivateBox.isSelected();
        int capacity = Integer.parseInt(capacityField.getText());
        sys.getSessionManager().createSession(capacity, sys.getAuthService().getCurrentUser().getFullName(), subject, isPrivate, location,desc,time);
    }


}