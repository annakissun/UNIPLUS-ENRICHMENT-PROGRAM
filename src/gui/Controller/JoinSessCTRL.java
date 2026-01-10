package gui.Controller;

import java.util.LinkedList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import model.Session;
import structure.UniSystem;
import util.SessionCardRenderer;

public class JoinSessCTRL {

    @FXML private VBox sessContainer;
    @FXML private VBox emptyBox;

    private final UniSystem sys = UniSystem.getInstance();
    private final LinkedList<Session> publicSess = new LinkedList<>();

    @FXML
    public void initialize() {
        refreshSessions();
    }

    @FXML
    public void refreshSessions() {

        publicSess.clear(); // ✅ IMPORTANT

        // 1️⃣ Collect ONLY public sessions
        for (Session s : sys.getSessionManager().getSessions()) {
            if (s.getJoinCode() == null) {
                publicSess.add(s);
            }
        }

        // 2️⃣ If NO public sessions → show empty card
        if (publicSess.isEmpty()) {
            showEmpty();
            sessContainer.getChildren().clear();
            return;
        }

        // 3️⃣ Otherwise → show sessions
        hideEmpty();
        SessionCardRenderer.renderSessions(sessContainer,publicSess,false,true,  false, sys);
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
}