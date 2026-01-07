package gui.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class dashboardpage {

    @FXML private StackPane root;
    @FXML private VBox card;
    @FXML private StackPane contentPane;
    @FXML private Button button;
    private boolean has;
    

    public void remove(){
        has = false;
        MakeSessCTRL.setHas(has);
    }
}