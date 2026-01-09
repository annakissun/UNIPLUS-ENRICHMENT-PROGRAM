package util;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;

public class AnimatedBackground {

    private double t = 0;
    private AnimationTimer timer;

    public void attach(Rectangle rect) {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                t += 0.0006;
                if (t > 1) t = 0;

                double fade = (Math.sin(t * 2 * Math.PI) + 1) / 2;

                Color c1 = lerp(Color.web("#8DB9FF"), Color.web("#D3C2FF"), fade);
                Color c2 = lerp(Color.web("#D3C2FF"), Color.web("#FFC1E8"), fade);
                Color c3 = lerp(Color.web("#FFC1E8"), Color.web("#8DB9FF"), fade);

                LinearGradient gradient = new LinearGradient(
                        0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                        new Stop(0, c1),
                        new Stop(0.5, c2),
                        new Stop(1, c3)
                );

                rect.setFill(gradient);
            }
        };
        timer.start();
    }

    public void stop() {
        if (timer != null) timer.stop();
    }

    private Color lerp(Color a, Color b, double t) {
        return new Color(
            a.getRed() * (1 - t) + b.getRed() * t,
            a.getGreen() * (1 - t) + b.getGreen() * t,
            a.getBlue() * (1 - t) + b.getBlue() * t,
            1
        );
    }
}
