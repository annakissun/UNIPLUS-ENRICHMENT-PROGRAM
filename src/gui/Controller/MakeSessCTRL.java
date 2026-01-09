package gui.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import structure.UniSystem;
import util.Navigable;
import util.Navigator;
import util.SessionCardRenderer;

public class MakeSessCTRL implements Navigable {

    @FXML private VBox emptyCard;
    @FXML private VBox sessionContainer;
    @FXML private Button addmoreBtn;

    private Navigator navigator;
    private final UniSystem system = UniSystem.getInstance();

    @FXML
    private void initialize() {
        loadSessions();
    }
    public void refresh(){
        loadSessions();
    }
    @FXML
    private void handleAddNewSession() {
        if (navigator != null) {
            navigator.navigateTo("/gui/view/AddSess.fxml");
        }
    }

    public void addMore(){handleAddNewSession();}

    @Override
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    /* ===============================
       CORE LOGIC
       =============================== */

    private void loadSessions() {
        if (system.getSessionManager().getSessions().isEmpty()) {
            showEmpty();
            sessionContainer.getChildren().clear();
            return;
        }

        hideEmpty();

        SessionCardRenderer.renderSessions(sessionContainer, system.getSessionManager().getSessions(), true, false, system);// ✅ editable (delete button shown)
        
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
