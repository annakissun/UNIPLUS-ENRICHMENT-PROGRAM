package gui.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AddTestCTRL {

    @FXML
    private VBox container;

    @FXML
    private Label emptyLabel;

    private int count = 1;

    public void createSession() {

        container.getChildren().remove(emptyLabel);

        VBox card = new VBox();
        card.setStyle("""
            -fx-background-color: white;
            -fx-padding: 15;
            -fx-background-radius: 15;
            -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10,0,0,5);
        """);

        Label title = new Label("Session " + count++);
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        card.getChildren().add(title);
        container.getChildren().add(card);

        System.out.println("Session created");
    }
}