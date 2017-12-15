package frontend;

import backend.Velocity;
import javafx.scene.canvas.Canvas;

public class Planet {

    private static final double WIND_UPDATE_TIME = 0.25;
    private static final double MAX_WIND_SPEED = 2;

    private Velocity mWind;
    private Canvas mCanvas;
    private double mLastUpdate;

    public Planet(Velocity wind, Canvas canvas) {
        mWind = wind;
        mCanvas = canvas;
        mLastUpdate = 0;
    }

    public Canvas getmCanvas() {
        return mCanvas;
    }

    public Velocity getmWind() {
        return mWind;
    }

    public void update(double deltaTime) {
        mLastUpdate += deltaTime;
        if (mLastUpdate > WIND_UPDATE_TIME) {
            double windDir = Math.random() > 0.5 ? 180 : 0;
            if (mWind.getXSpeed() > MAX_WIND_SPEED) {
                windDir = 180;
            }
            if (mWind.getXSpeed() < -MAX_WIND_SPEED) {
                windDir = 0;
            }
            mWind = mWind.add(new Velocity(0.5, windDir));
            mLastUpdate = 0;
        }
        System.out.println(mWind.getXSpeed());
    }
}
