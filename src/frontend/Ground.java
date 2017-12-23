/*
 * Michael Pu
 * RecursionGraphics - Ground
 * December 22, 2017
 */

package frontend;

import backend.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ground extends Sprite {

    private static final double INITAL_HEIGHT = 100;
    private static final double MOUND_HEIGHT_UPDATE_TIME = 0.05;
    private static final double MAX_MOUND_HEIGHT_PERCENT_OF_SCREEN = 0.5;

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
        gc.fillOval(-(mWidth*(1.5-1))/2, mGroundLevel - mHeight, mWidth*1.5, mHeight*2);
    }

    @Override
    public void update(double deltaTime) {

        mGroundLevel = mPlanet.getmDimensions().getHeight();
        mWidth = mPlanet.getmDimensions().getWidth();

        mLastUpdate += deltaTime;
        if (mLastUpdate > MOUND_HEIGHT_UPDATE_TIME) {
            if (mHeight <= mPlanet.getmDimensions().getHeight()*MAX_MOUND_HEIGHT_PERCENT_OF_SCREEN) {
                mHeight += mPlanet.getmState().getMoundGrowRate();
                mLastUpdate = 0;
            }
        }
        drawGround();
    }
}
