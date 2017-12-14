package frontend;

import backend.Velocity;
import javafx.scene.canvas.Canvas;

public class Planet {
    private Velocity mWind;
    private Canvas mCanvas;

    public Planet(Velocity wind, Canvas canvas) {
        mWind = wind;
        mCanvas = canvas;
    }

    public Canvas getmCanvas() {
        return mCanvas;
    }

    public Velocity getmWind() {
        return mWind;
    }

    public void update() {
        return;
    }
}
