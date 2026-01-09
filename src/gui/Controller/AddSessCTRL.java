package gui.Controller;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import structure.UniSystem;
import util.Navigable;
import util.Navigator;

public class AddSessCTRL implements Navigable{

    //Data members
    private Navigator navigator;
    private UniSystem sys = UniSystem.getInstance();

    @FXML TextField subjectField;
    @FXML TextField locationField;
    @FXML TextField descriptionField;
    @FXML TextField timeField;
    @FXML TextField capacityField;

    @Override
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }
    
    public void createSession() {

        System.out.println("Session created");

        getInfo();

        // 2️⃣ Then navigate
        if (navigator != null) {
            navigator.navigateTo("/gui/view/MakeSess.fxml");
        } else {
            System.out.println("navigator is null");
        }
    }

    public void getInfo(){
        String subject = subjectField.getText();
        String location = locationField.getText();
        String desc = descriptionField.getText();
        String time = timeField.getText();
        int capacity = Integer.parseInt(capacityField.getText());
        sys.getSessionManager().createSession(capacity, "fahmi", subject, false, location,desc,time);
        sys.getSessionManager().printAllSess();
        System.out.println("get info used!");
    }


    

    

}