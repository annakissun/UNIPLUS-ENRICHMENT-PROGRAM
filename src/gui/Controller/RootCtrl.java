package gui.Controller;

import javafx.animation.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.util.*;
import util.*;
public class RootCtrl implements Navigator {
    //Data members
    @FXML private StackPane root;
    @FXML private StackPane contentHolder;
    @FXML private StackPane sidebarHolder;
    @FXML private Button toggleBtn;
    @FXML private Rectangle bgRect;

    private boolean sidebarVisible = false;
    private AnimatedBackground ab;

    @FXML //method to initialize
    public void initialize() {

        loadContent("/gui/view/Dashboard.fxml");
        loadSidebar();
        ab = new AnimatedBackground();
        ab.attach(bgRect);

        bgRect.widthProperty().bind(root.widthProperty());
        bgRect.heightProperty().bind(root.heightProperty());

        // Initially hide the sidebar outside of view
        sidebarHolder.setTranslateX(-sidebarHolder.getPrefWidth());

        // Add click action to toggle button
        toggleBtn.setOnAction(e -> toggleSidebar());
    }

    //method for animating sidebar
    private void toggleSidebar() {
        TranslateTransition tt = new TranslateTransition(Duration.millis(300), sidebarHolder);

        if (!sidebarVisible) {
            sidebarHolder.setVisible(true);
            tt.setToX(0); // slide in
            sidebarVisible = true;
        } else {
            tt.setToX(-sidebarHolder.getPrefWidth()); // slide out
            tt.setOnFinished(ev -> sidebarHolder.setVisible(false));
            sidebarVisible = false;
        }
        tt.play();
    }

    //method to load sidebar
    private void loadSidebar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/view/SideBar.fxml"));
            Parent sidebar = loader.load();

            Object ctrl = loader.getController();
            if (ctrl instanceof Navigable) {
                ((Navigable)ctrl).setNavigator(this); // 🔑 ROOT passed here
            }

            sidebarHolder.getChildren().setAll(sidebar);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //method to load the content in the content area
    public void loadContent(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));

            Parent page = loader.load();

            Object ctrl = loader.getController();
            if (ctrl instanceof Navigable nav) {
                nav.setNavigator(this);
            }

            contentHolder.getChildren().setAll(page);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void navigateTo(String fxml) {
        loadContent(fxml);
    }
}