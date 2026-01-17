package gui.Controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import structure.UniSystem;
import util.*;

public class MakeSessCTRL implements Navigable {

    @FXML private VBox emptyCard;
    @FXML private VBox sessionContainer;

    private Navigator navigator;
    private final UniSystem sys = UniSystem.getInstance();

    @FXML
    private void initialize() {
        loadSessions();
    }

    public void refresh(){
        loadSessions();
    }

    @FXML
    private void AddNewSess() {
        if (navigator != null) {
            navigator.navigateTo("/gui/view/AddSess.fxml");
        }
    }

    @Override
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }


    private void loadSessions() {
        if (sys.getSessionManager().getAllSessions().isEmpty()) {
            showEmpty();
            sessionContainer.getChildren().clear();
            return;
        }

        hideEmpty();
        SessionCardRenderer.renderSessions(sessionContainer, sys.getSessionManager().getAllSessions(), true, false,false, sys);// ✅ editable (delete button shown)
    }

    private void showEmpty() {
        emptyCard.setVisible(true);
        emptyCard.setManaged(true);
    }

    private void hideEmpty() {
        emptyCard.setVisible(false);
        emptyCard.setManaged(false);
    }

}
