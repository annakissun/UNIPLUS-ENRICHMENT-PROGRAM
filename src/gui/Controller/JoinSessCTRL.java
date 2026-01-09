package gui.Controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import structure.UniSystem;
import util.SessionCardRenderer;

public class JoinSessCTRL {

    @FXML
    private VBox sessContainer; // 🚫 NO new VBox()
    @FXML private VBox emptyBox;

    private final UniSystem sys = UniSystem.getInstance();

    @FXML
    public void initialize() {
        refreshSessions();
    }

    @FXML public void refreshSessions() {
    boolean empty = sys.getSessionManager().getSessions().isEmpty();

    emptyBox.setVisible(empty);
    emptyBox.setManaged(empty);

    sessContainer.setVisible(!empty);
    sessContainer.setManaged(!empty);

    if (!empty) {
        SessionCardRenderer.renderSessions(sessContainer, sys.getSessionManager().getSessions(), false, true, sys);// ✅ editable (delete button shown)
    }
}

}