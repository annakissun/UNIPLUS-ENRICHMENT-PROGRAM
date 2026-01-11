package util;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Session;
import model.Student;
import structure.UniSystem;
import java.util.LinkedList;

public final class SessionCardRenderer {

    private SessionCardRenderer() {}

    public static void renderSessions(VBox container, LinkedList<Session> sessions,
                                      boolean editable, boolean joinable, boolean cancelable,
                                      UniSystem sys) {
        container.getChildren().clear();
        for (Session s : sessions) {
            container.getChildren().add(buildCard(s, container, editable, joinable, cancelable, sys));
        }
    }

    private static VBox buildCard(Session s, VBox container,
                                  boolean editable, boolean joinable, boolean cancelable,
                                  UniSystem sys) {
        VBox card = new VBox(10);
        card.getStyleClass().add("session-card");

        Label title = new Label(s.getSubject());
        title.getStyleClass().add("title-label");

        Label host = new Label("Host : " + s.getHost());
        Label location = new Label("Location: " + s.getLocation());
        Label time = new Label("Time: " + s.getTime());
        Label capacity = new Label("Capacity: " + s.getCurrentJoined() + "/" + s.getCapacity());
        Label joined = new Label("Students Joined : " + s.getStudentNames());
        Label inQueue = new Label("Waitlist: " + s.getWaitlistNames());
        Label desc = new Label("Description: " + s.getDescription());
        Label code = new Label("Session Code: " + (s.isPrivate() ? s.getJoinCode() : "Public"));

        card.getChildren().addAll(title, host, location, time, capacity, joined, inQueue, code, desc);

        // DELETE BUTTON
        if (editable) {
            Button delete = new Button("Delete");
            delete.getStyleClass().add("delete-button");
            delete.setOnAction(e -> {
                container.getChildren().remove(card);
                sys.getSessionManager().removeSession(s);
                AlertShow.showInfo("Session removed", "The session is deleted from the system");
            });
            card.getChildren().add(delete);
        }

        // JOIN BUTTON
        if (joinable) {
            Button join = new Button("Join");
            join.getStyleClass().add("join-button");
            join.setOnAction(e -> {
                Student current = (Student) sys.getAuthService().getCurrentUser();
                boolean success = s.addStudent(current);
                if (!success) {
                    AlertShow.showError("Couldn't join session", "Please try again");
                } else {
                    AlertShow.showInfo("Session joined", "You successfully joined the session!");
                    // Update labels dynamically
                    capacity.setText("Capacity: " + s.getCurrentJoined() + "/" + s.getCapacity());
                    joined.setText("Students: " + s.getStudentNames());
                    inQueue.setText("Waitlist: " + s.getWaitlistNames());
                }
            });
            card.getChildren().add(join);
        }

        // CANCEL BUTTON
        if (cancelable) {
            Button cancel = new Button("Cancel Join");
            cancel.getStyleClass().add("delete-button");
            cancel.setOnAction(e -> {
                Student current = (Student) sys.getAuthService().getCurrentUser();
                boolean success = s.removeStudent(current);
                if (!success) {
                    AlertShow.showError("Couldn't leave session", "Please try again");
                } else {
                    AlertShow.showInfo("Session cancelled", "You successfully left the session!");
                    // Update labels dynamically
                    capacity.setText("Capacity: " + s.getCurrentJoined() + "/" + s.getCapacity());
                    joined.setText("Students: " + s.getStudentNames());
                    inQueue.setText("Waitlist: " + s.getWaitlistNames());
                }
            });
            card.getChildren().add(cancel);
        }

        return card;
    }
}
