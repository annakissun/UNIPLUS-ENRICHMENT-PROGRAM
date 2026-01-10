package util;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Session;
import structure.UniSystem;
import java.util.LinkedList;

public final class SessionCardRenderer {

    private SessionCardRenderer() {}

    public static void renderSessions(VBox container, LinkedList<Session> sessions, boolean editable, boolean joinable, boolean cancelable, UniSystem sys) {
        container.getChildren().clear();
        for (Session s : sessions) container.getChildren().add(buildCard(s, container, editable, joinable, cancelable, sys));
    }

    private static VBox buildCard(Session s, VBox container, boolean editable, boolean joinable, boolean cancelable, UniSystem sys) {
        VBox card = new VBox(10); card.getStyleClass().add("session-card");
        Label title = new Label(s.getSubject()); title.getStyleClass().add("title-label");
        Label host = new Label("Host : " + s.getHost());
        Label location = new Label("Location: " + s.getLocation());
        Label time = new Label("Time: " + s.getTime());
        Label capacity = new Label("Capacity: " + s.getCapacity());
        Label joined = new Label("Currently Joined : " + s.getCurrentJoined());
        Label desc = new Label("Description : " + s.getDescription());
        Label code = new Label("Session Code : " + s.getJoinCode());
        card.getChildren().addAll(title, host, location, time, capacity,code, joined, desc);

        if (editable) { Button delete = new Button("Delete"); delete.getStyleClass().add("delete-button"); 
        delete.setOnAction(e -> { container.getChildren().remove(card); sys.getSessionManager().removeSession(s); 
        AlertShow.showInfo("Session is removed", "The session is deleted from the system"); }); card.getChildren().add(delete); }

        if (joinable) { Button join = new Button("Join"); join.getStyleClass().add("join-button"); 
        join.setOnAction(e -> { boolean success = s.addStudent(sys.getAuthService().getCurrentUser());
        if (!success) { AlertShow.showError("Couldnt Join session", "Please try again"); } 
        else { AlertShow.showInfo("Session joined", "You successfully joined the session!"); } }); card.getChildren().add(join); }

        if (cancelable) { Button cancel = new Button("Cancel Join"); 
        cancel.getStyleClass().add("delete-button"); 
        cancel.setOnAction(e -> { boolean success = s.removeStudent(sys.getAuthService().getCurrentUser());
        if (!success) { AlertShow.showError("Couldnt left session", "Please try again"); } 
        else { AlertShow.showInfo("Session Cancelled", "You successfully left the session!"); } }); card.getChildren().add(cancel); }

        return card;
    }
}
