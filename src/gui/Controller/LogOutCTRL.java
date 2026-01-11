package gui.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import structure.UniSystem;
import util.Navigable;
import util.Navigator;

public class LogOutCTRL implements Navigable{

    @FXML private StackPane root;
    private Navigator navigator;
    private UniSystem sys = UniSystem.getInstance();
    //setter
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    public void yes(){
        try {
            sys.getAuthService().logout();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/Login.fxml"));
            Parent loginRoot = loader.load();
            Stage stage = (Stage) this.root.getScene().getWindow();
            stage.getScene().setRoot(loginRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void no() {
        if (navigator != null) navigator.navigateTo("/gui/view/DashBoard.fxml");
    }
}