package gui.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Session;
import model.Student;
import structure.UniSystem;
import util.AlertShow;

public class PrivateSessCTRL {

    @FXML private TextField privateSessField;
    private final UniSystem sys = UniSystem.getInstance();

    @FXML
    public void JoinSess() {

        String code = privateSessField.getText().trim();
        if (code.isEmpty()) {
            AlertShow.showError("Invalid Code", "Please enter a join code");
            return;
        }
        for (Session s : sys.getSessionManager().getAllSessions()) {
            if (s.getJoinCode() == null) continue;  // ✅ skip public sessions safely
            // ✅ code matched
            if (s.getJoinCode().equalsIgnoreCase(code)) {
                boolean success = s.join((Student)sys.getAuthService().getCurrentUser());
                if (success) {
                    AlertShow.showInfo("Joined Session", "You joined the private session successfully!");
                } else {
                    AlertShow.showError("Join Failed", "You may already be in the session or it is full");
                }
                return; // ✅ stop searching
            }
        }
        AlertShow.showError("Invalid Code", "No session found with this join code");  // ❌ no matching code found
    }
}