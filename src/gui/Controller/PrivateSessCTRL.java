package gui.Controller;

import java.lang.reflect.Field;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrivateSessCTRL {

    @FXML Field privateSessionField;
    @FXML Button joinBtn;


    public void joinBtn(){
        System.out.println("joinBtn clicked!");
    }
    public void JoinSess(){System.out.println("#JoinSess");}
}
