package gui.Controller;

import java.util.LinkedList;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import model.*;
import javafx.scene.layout.StackPane;
import structure.UniSystem;
import util.AlertShow;
import util.SessionCardRenderer;

public class DashBoardCTRL {

    @FXML private StackPane root;
    @FXML private VBox emptyBox;
    @FXML private VBox sessContainer;

    private final UniSystem sys = UniSystem.getInstance();
    private User currentUser = sys.getAuthService().getCurrentUser();

    public void verifyStud() {
        if (sys.getAuthService().getCurrentUser() instanceof Student s && !s.isProfileComplete()) {
        Platform.runLater(() -> AlertShow.showVerify());
    }

    }

    @FXML
    public void initialize() {
        loadJoinedSessions();
        verifyStud();
    }

   private void loadJoinedSessions() {
    sessContainer.getChildren().clear();
    // Filter sessions where the current student has joined
    
        var joinedSessions = sys.getSessionManager().getAllSessions().stream().filter(s -> 
            s.getJoinedListStudents().stream().anyMatch(stud -> stud.getUsername().equals(currentUser.getUsername()))).toList();

    if (joinedSessions.isEmpty()) {
        showEmpty();
    } else {
        hideEmpty();
        SessionCardRenderer.renderSessions(sessContainer,new LinkedList<>(joinedSessions),false, false, true,sys);
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
