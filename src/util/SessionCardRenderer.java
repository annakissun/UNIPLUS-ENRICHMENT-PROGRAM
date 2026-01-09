package util;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Session;
import structure.UniSystem;

import java.util.LinkedList;

public final class SessionCardRenderer {

    private SessionCardRenderer() {}

    public static void renderSessions(VBox container, LinkedList<Session> sessions, boolean editable, boolean joinable, UniSystem sys) {
        container.getChildren().clear();
        for (Session s : sessions) container.getChildren().add(buildCard(s, container, editable, joinable, sys));
    }

    private static VBox buildCard(Session s, VBox container, boolean editable, boolean joinable, UniSystem sys) {
        VBox card = new VBox(10);
        card.getStyleClass().add("session-card");

        Label title = new Label(s.getSubject()); title.getStyleClass().add("title-label");
        Label host = new Label("Host : " + s.getHost());
        Label location = new Label("Location: " + s.getLocation());
        Label time = new Label("Time: " + s.getTime());
        Label capacity = new Label("Capacity: " + s.getCapacity());
        Label joined = new Label("Currently Joined : " + s.getCurrentJoined());
        Label desc = new Label("Description : " + s.getDescription());

        card.getChildren().addAll(title,host, location, time, capacity,joined, desc);

        if (editable) {
            Button delete = new Button("Delete"); delete.getStyleClass().add("delete-button");
            delete.setOnAction(e -> { container.getChildren().remove(card); sys.getSessionManager().removeSession(s); });
            card.getChildren().add(delete);
        }

        if (joinable) {
        Button join = new Button("Join");
        join.getStyleClass().add("join-button");
        join.setOnAction(e -> {
            Session currentSession = sys.getSessionManager().getCurrentSession();
            if (currentSession == null) {
                System.out.println("No session selected!");
                return;
            }
            // Attempt to add current user (addStudent handles type checking)
            boolean success = currentSession.addStudent(sys.getAuthService().getCurrentUser());
            if (success) {
                System.out.println("Joined session successfully!");
            } else {
                System.out.println("Could not join session (check console for reason)");
            }
            // Optional: print session details
            sys.getSessionManager().printAllSess();
        });

        card.getChildren().add(join);
    }




        return card;
    }
}
