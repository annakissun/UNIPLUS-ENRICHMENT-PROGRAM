package gui.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import util.Navigable;
import util.Navigator;

public class LogOutCTRL implements Navigable{
    //@FXML Button noBtn;
    //@FXML Button yesBtn;
    @FXML private StackPane root;

    private Navigator navigator;
    //setter
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    public void yes(){
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
    public void no() {
        if (navigator != null) navigator.navigateTo("/gui/view/dashboard.fxml");
        else {System.out.println("navigator is null");}
    }
}