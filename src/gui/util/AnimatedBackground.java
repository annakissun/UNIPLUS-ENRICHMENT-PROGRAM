package gui.util;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;

public class AnimatedBackground {

    private Rectangle gradientRect;
    private double t = 0;

    // Constructor for BorderPane
    public AnimatedBackground(BorderPane root) {
        setup(root);
    }
    //(Method overloading)
    // Constructor for StackPane
    public AnimatedBackground(StackPane stackPane) {
        setup(stackPane);
    }

    // ===== Setup for BorderPane =====
    private void setup(BorderPane root) {
        gradientRect = new Rectangle();
        gradientRect.widthProperty().bind(root.widthProperty());
        gradientRect.heightProperty().bind(root.heightProperty());

        // Attach background to center StackPane
        StackPane stack = (StackPane) root.getCenter();
        stack.getChildren().add(0, gradientRect);

        startAnimation();
    }

    // ===== Setup for StackPane =====
    private void setup(StackPane stackPane) {
        gradientRect = new Rectangle();
        gradientRect.widthProperty().bind(stackPane.widthProperty());
        gradientRect.heightProperty().bind(stackPane.heightProperty());

        stackPane.getChildren().add(0, gradientRect);

        startAnimation();
    }

    // ===== Start animation timer =====
    private void startAnimation() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                animate();
            }
        };
        timer.start();
    }

    // ===== Animation logic =====
    private void animate() {
        t += 0.000575;
        if (t > 1) t = 0;

        double fade = (Math.sin(t * 2 * Math.PI) + 1) / 2;

        Color c1 = lerp(Color.web("#8DB9FF"), Color.web("#D3C2FF"), fade);
        Color c2 = lerp(Color.web("#D3C2FF"), Color.web("#FFC1E8"), fade);
        Color c3 = lerp(Color.web("#FFC1E8"), Color.web("#8DB9FF"), fade);

        LinearGradient gradient = new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0.0, c1),
                new Stop(0.5, c2),
                new Stop(1.0, c3)
        );

        gradientRect.setFill(gradient);
    }

    // ===== Linear interpolation between two colors =====
    private Color lerp(Color a, Color b, double t) {
        double r = a.getRed() * (1 - t) + b.getRed() * t;
        double g = a.getGreen() * (1 - t) + b.getGreen() * t;
        double bl = a.getBlue() * (1 - t) + b.getBlue() * t;
        return new Color(r, g, bl, 1.0);
    }
}
