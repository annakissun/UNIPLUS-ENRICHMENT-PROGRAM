package gui.Controller;

import javafx.fxml.FXML;
import util.Navigable;
import util.Navigator;

public class NewBar implements Navigable {
    private Navigator navigator;

    @FXML
    private void GoToDashboard() {
        if (navigator != null) navigator.navigateTo("/gui/view/dashboard.fxml");
    }
    
    @FXML
    private void GoToJoinSession() {
        if (navigator != null) navigator.navigateTo("/gui/view/JoinSess.fxml");
    }
    
    @FXML
    private void GoToPrivateSession() {
        if (navigator != null) navigator.navigateTo("/gui/view/PrivateSess.fxml");
    }
    
    @FXML
    private void GoToCreateSession() {
        if (navigator != null) navigator.navigateTo("/gui/view/MakeSess.fxml");
    }
    
    @FXML
    private void GoToLogout() {
        if (navigator != null) navigator.navigateTo("/gui/view/Logout.fxml");
    }

    @Override
    //setter
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

}
