/*
 * Michael Pu
 * RecursionGraphics - Planet
 * December 22, 2017
 */

package frontend;

import backend.Coordinate;
import backend.Velocity;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;

public class Planet {

    private static final double WIND_UPDATE_TIME = 0.25;
    private static final double MAX_WIND_SPEED = 0.001;

    private Velocity mWind;
    private Canvas mCanvas;
    private double mLastUpdate;
    private Rectangle2D mDimensions;
    private State mState;

    public Planet(Canvas canvas, State state) {
        mWind = new Velocity();
        mCanvas = canvas;
        mLastUpdate = 0;
        mState = state;
    }

    public void setDimesions(Rectangle2D dimesions) {
        mDimensions = dimesions;
    }

    public Rectangle2D getmDimensions() {
        return mDimensions;
    }

    public Canvas getmCanvas() {
        return mCanvas;
    }

    public Velocity getmWind() {
        return mWind;
    }

    public State getmState() {
        return mState;
    }

    public void update(double deltaTime) {
        mLastUpdate += deltaTime;
        if (mLastUpdate > WIND_UPDATE_TIME) {
            double windDir = Math.random() > 0.5 ? 180 : 0;
            if (mWind.getXSpeed() > mState.getMaxWindSpeed()) {
                windDir = 180;
            }
            if (mWind.getXSpeed() < -mState.getMaxWindSpeed()) {
                windDir = 0;
            }
            mWind = mWind.add(new Velocity(mState.getWindChangeRate(), windDir));
            mLastUpdate = 0;
        }
    }
}
