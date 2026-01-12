package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import model.*;
import structure.UniSystem;
import gui.Controller.EditSessCTRL;
import java.io.IOException;
import java.util.List;


public final class SessionCardRenderer {

    private SessionCardRenderer() {}

    public static void renderSessions(VBox container, List<Session> sessions, boolean editable, boolean joinable, boolean cancelable, UniSystem sys) {
        container.getChildren().clear();
        for (Session s : sessions) {
            container.getChildren().add(buildCard(s, container, editable, joinable, cancelable, sys));
        }
    }

    private static VBox buildCard(Session s, VBox container, boolean editable, boolean joinable, boolean cancelable, UniSystem sys) {

        VBox card = new VBox(10);
        card.getStyleClass().add("session-card");

        // Labels
        Label title = new Label(s.getSubject());
        title.getStyleClass().add("title-label");

        Label host = new Label("Host: " + s.getHost());
        Label location = new Label("Location: " + s.getLocation());
        Label time = new Label("Time: " + s.getTime());
        Label capacity = new Label("Capacity: " + s.getCurrentJoined() + "/" + s.getCapacity());
        Label joined = new Label("Students Joined: " + s.getStudentNames());
        Label inQueue = new Label("Waitlist: " + s.getWaitlistNames());
        Label desc = new Label("Description: " + s.getDescription());
        Label code = new Label("Session Code: " + (s.isPrivate() ? s.getJoinCode() : "Public"));

        card.getChildren().addAll(title, host, location, time, capacity, joined, inQueue, code, desc);

        // Buttons container
        HBox buttons = new HBox(10);

        // DELETE BUTTON
        if (editable) {
            Button delete = new Button("Delete");
            delete.getStyleClass().add("delete-button");
            delete.setOnAction(e -> {
                container.getChildren().remove(card);
                sys.getSessionManager().removeSession(s);
                AlertShow.showInfo("Session removed", "The session is deleted from the system");
            });
            buttons.getChildren().add(delete);
        }

        // EDIT BUTTON
        if (editable) {
            Button edit = new Button("Edit");
            edit.getStyleClass().add("edit-button");
            edit.setOnAction(e -> {
                try {
                    FXMLLoader loader = new FXMLLoader(SessionCardRenderer.class.getResource("/gui/view/EditSess.fxml"));
                    Parent root = loader.load();

                    EditSessCTRL ctrl = loader.getController();
                    ctrl.init(s);

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Edit Session");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.showAndWait();

                    // Refresh labels after editing
                    title.setText(s.getSubject());
                    location.setText(s.getLocation());
                    time.setText(s.getTime());
                    capacity.setText("Capacity: " + s.getCurrentJoined() + "/" + s.getCapacity());
                    desc.setText(s.getDescription());

                } catch (IOException ex) {
                    ex.printStackTrace();
                    AlertShow.showError("Error", "Failed to open edit window");
                }
            });
            buttons.getChildren().add(edit);
        }

        // JOIN BUTTON
        if (joinable) {
            Button join = new Button("Join");
            join.getStyleClass().add("join-button");
            join.setOnAction(e -> {
                Student current = (Student) sys.getAuthService().getCurrentUser();
                boolean success = s.join(current);
                if (!success) {
                    AlertShow.showError("Couldn't join session", "Please try again");
                } else {
                    AlertShow.showInfo("Session joined", "You successfully joined the session!");
                    capacity.setText("Capacity: " + s.getCurrentJoined() + "/" + s.getCapacity());
                    joined.setText("Students Joined: " + s.getStudentNames());
                    inQueue.setText("Waitlist: " + s.getWaitlistNames());
                }
            });
            buttons.getChildren().add(join);
        }

        // CANCEL BUTTON
        if (cancelable) {
            Button cancel = new Button("Cancel Join");
            cancel.getStyleClass().add("delete-button");
            cancel.setOnAction(e -> {
                Student current = (Student) sys.getAuthService().getCurrentUser();
                boolean success = s.leaveSession(current);
                if (!success) {
                    AlertShow.showError("Couldn't leave session", "Please try again");
                } else {
                    AlertShow.showInfo("Session cancelled", "You successfully left the session!");
                    capacity.setText("Capacity: " + s.getCurrentJoined() + "/" + s.getCapacity());
                    joined.setText("Students Joined: " + s.getStudentNames());
                    inQueue.setText("Waitlist: " + s.getWaitlistNames());
                }
            });
            buttons.getChildren().add(cancel);
        }

        // Add buttons only if there are any
        if (!buttons.getChildren().isEmpty()) card.getChildren().add(buttons);

        return card;
    }
}
