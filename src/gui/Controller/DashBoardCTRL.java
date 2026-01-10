package gui.Controller;

import java.util.LinkedList;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
//import model.Session;
import javafx.scene.layout.StackPane;
import structure.UniSystem;
import util.SessionCardRenderer;

public class DashBoardCTRL {

    @FXML private StackPane root;
    @FXML private VBox emptyBox;
    @FXML private VBox sessContainer;

    private final UniSystem sys = UniSystem.getInstance();

    @FXML
    public void initialize() {
        loadJoinedSessions();
    }

    private void loadJoinedSessions() {
    sessContainer.getChildren().clear(); // clear previous cards

    // Filter sessions that the current user has joined
    var joinedSessions = sys.getSessionManager().getSessions().stream().filter(s -> s.getStudents().contains(sys.getAuthService().getCurrentUser())).toList();

        if (joinedSessions.isEmpty()) {
            showEmpty();
        } else {
            hideEmpty();
            SessionCardRenderer.renderSessions(sessContainer, new LinkedList<>(joinedSessions), false, false,true, sys);
        }
    }

    private void showEmpty() {
        emptyBox.setVisible(true);
        emptyBox.setManaged(true);
        sessContainer.setVisible(false);
        sessContainer.setManaged(false);
    }

    private void hideEmpty() {
        emptyBox.setVisible(false);
        emptyBox.setManaged(false);
        sessContainer.setVisible(true);
        sessContainer.setManaged(true);
    }

    public void refresh() {
        loadJoinedSessions();
    }
}
