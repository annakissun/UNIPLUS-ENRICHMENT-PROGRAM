package gui.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import util.Navigable;
import util.Navigator;

public class MakeSessCTRL implements Navigable {

    @FXML private VBox emptyCard;
    @FXML private VBox sessionContainer;

    private Navigator navigator;
    private static boolean has = false;

    private static String subject;
    private static String location;
    private static String desc;
    private static String time;
    private static int capacity;

    @FXML
    private void initialize() {
        if (has) {
            spawnSessionCard();
        }
        /*if (sessionContainer == null) {
            sessionContainer.getChildren().clear();
            emptyCard.setVisible(true);
            emptyCard.setManaged(true);
            has = false;
        }*/
    }

    private void spawnSessionCard() {
        emptyCard.setVisible(false);
        emptyCard.setManaged(false);

        sessionContainer.getChildren().clear();

        VBox card = new VBox(10);
        card.getStyleClass().add("session-card");
        //VBox card2 = new VBox(10);
        //card.getStyleClass().add("session-card");

        Label title = new Label(subject);
        title.getStyleClass().add("title-label");

        Label locationLbl = new Label("Location: " + location);
        Label timeLbl = new Label("Time: " + time);
        Label capacityLbl = new Label("Capacity: " + capacity);
        Label descLbl = new Label(desc);

        Button deleteBtn = new Button("Delete");
        deleteBtn.getStyleClass().add("delete-button");
        deleteBtn.setOnAction(e -> {
            //sessionContainer.getChildren().clear();
            //emptyCard.setVisible(true);
            //emptyCard.setManaged(true);
            sessionContainer.getChildren().remove(card);
        });

        Button moreSess= new Button("Add more session");
        moreSess.setOnAction(e -> addToCard());

        card.getChildren().addAll(
            title,
            locationLbl,
            timeLbl,
            capacityLbl,
            descLbl,
            deleteBtn
        );
        
        sessionContainer.getChildren().addAll(moreSess);
        sessionContainer.getChildren().add(card);
        
    }

    @FXML
    private void handleAddNewSession() {
        if (navigator != null) {
            navigator.navigateTo("/gui/view/AddSess.fxml");
        }
    }

    public static void setHas(boolean b) {
        has = b;
    }

    public static void setCardInfo(
            String subject,
            String location,
            String desc,
            String time,
            int capacity) {

        MakeSessCTRL.subject = subject;
        MakeSessCTRL.location = location;
        MakeSessCTRL.desc = desc;
        MakeSessCTRL.time = time;
        MakeSessCTRL.capacity = capacity;
    }

    @Override
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    public void addToCard() {
        VBox k = new VBox();

        Label title = new Label(subject);
        title.getStyleClass().add("title-label");

        Label locationLbl = new Label("Location: " + location);
        Label timeLbl = new Label("Time: " + time);
        Label capacityLbl = new Label("Capacity: " + capacity);
        Label descLbl = new Label(desc);

        Button deleteBtn = new Button("Delete");
        deleteBtn.getStyleClass().add("delete-button");
        deleteBtn.setOnAction(e -> {
            sessionContainer.getChildren().remove(k);
            //emptyCard.setVisible(true);
            //emptyCard.setManaged(true);
            //has = false;
        });

        k.getChildren().addAll(
            title,
            locationLbl,
            timeLbl,
            capacityLbl,
            descLbl,
            deleteBtn
        );
        k.getStyleClass().add("session-card");
        sessionContainer.getChildren().add(k);

        
    }
}
