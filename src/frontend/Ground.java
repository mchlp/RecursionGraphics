/*
 * Michael Pu
 * RecursionGraphics - Ground
 * December 17, 2017
 */

package frontend;

import backend.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ground extends Sprite {

    private static final double INITAL_HEIGHT = 100;
    private static final double MOUND_HEIGHT_UPDATE_TIME = 0.05;

    private Planet mPlanet;
    private Color mColor;
    private double mWidth;
    private double mHeight;
    private double mGroundLevel;
    private double mLastUpdate;

    public Ground(Planet planet, Color color) {
        mColor = color;
        mPlanet = planet;
        mHeight = INITAL_HEIGHT;
        mLastUpdate = 0;
    }

    private void drawGround() {
        GraphicsContext gc = mPlanet.getmCanvas().getGraphicsContext2D();
        gc.setFill(Color.LIGHTGREY);
        gc.fillOval(-(mWidth*(1.5-1))/2, mGroundLevel - mHeight/2, mWidth*1.5, mHeight);
    }

    @Override
    public void update(double deltaTime) {

        mGroundLevel = mPlanet.getmDimensions().getHeight();
        mWidth = mPlanet.getmDimensions().getWidth();

        mLastUpdate += deltaTime;
        if (mLastUpdate > MOUND_HEIGHT_UPDATE_TIME) {
            mHeight++;
            mLastUpdate = 0;
        }
        drawGround();
    }
}
