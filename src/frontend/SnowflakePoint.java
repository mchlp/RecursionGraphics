/*
 * Michael Pu
 * RecursionGraphics - SnowflakePoint
 * December 22, 2017
 */

package frontend;

import backend.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnowflakePoint extends Sprite {

    private static final double GRAVITY_UPDATE_TIME = 0.5;

    private Planet mPlanet;
    private double mSpeed;
    private double mSize;
    private double mLastUpdate;

    public SnowflakePoint(Planet planet, Coordinate startPoint, double size) {
        super();
        mPlanet = planet;
        mStartPoint = startPoint;
        mSize = size;
        mSpeed = 0.006;
        mLastUpdate = 0;
    }

    public void drawSnowflake(double x, double y) {
        GraphicsContext gc = mPlanet.getmCanvas().getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillOval(x, y, mSize, mSize);
    }

    @Override
    public void update(double deltaTime) {
        mLastUpdate += deltaTime;
        if (mLastUpdate > GRAVITY_UPDATE_TIME) {
            int multi = mSpeed > mPlanet.getmState().getMaxSnowflakeSpeed() ? -1 : mSpeed < mPlanet.getmState().getMinSnowflakeSpeed() ? 1 : Math.random() > 0.5 ? 1 : -1;
            mSpeed += multi * Math.random()*mPlanet.getmState().getSnowflakeSpeedChangeMulti();
            mLastUpdate = 0;
        }
        mVelocity = new Velocity(mSpeed, 270);
        mVelocity = mVelocity.add(mPlanet.getmWind());
        mStartPoint.move(mVelocity);
        mStartPoint.setX((mStartPoint.getX()+1)%1);
        mStartPoint.setY((mStartPoint.getY()+1)%1);
        drawSnowflake(mPlanet.getmCanvas().getWidth()*mStartPoint.getX(), mPlanet.getmCanvas().getHeight()*mStartPoint.getY());
    }
}
