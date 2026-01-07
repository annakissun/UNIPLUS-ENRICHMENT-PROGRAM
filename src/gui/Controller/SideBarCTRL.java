package gui.Controller;
import util.*;
import javafx.fxml.FXML;

public class SideBarCTRL {

    private Navigator navigator;

    //setter
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    @FXML
    private void button1() {
        if (navigator != null) navigator.navigateTo("/gui/view/dashboard.fxml");
    }

    @FXML
    private void button2() {
        if (navigator != null) navigator.navigateTo("/gui/view/Page2.fxml");
    }

    @FXML
    private void button3() {
        if (navigator != null) navigator.navigateTo("/gui/view/Page3.fxml");
    }
}