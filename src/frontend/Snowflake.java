package frontend;

import backend.*;
import javafx.scene.canvas.GraphicsContext;

public class Snowflake extends Sprite {

    private static final double GRAVITY_UPDATE_TIME = 0.5;

    private GraphicsContext mGc;
    private Planet mPlanet;
    private int mLevels;
    private double mLen;
    private double mSpeed;
    private int mAngle;
    private double mLastUpdate;

    public Snowflake(Planet planet, GraphicsContext gc, Coordinate startPoint, int levels, int len) {
        super();
        mGc = gc;
        mPlanet = planet;
        mStartPoint = startPoint;
        mLevels = levels;
        mLen = len;
        mSpeed = 3;
        mAngle = -30;
        mLastUpdate = 0;
    }

    public void drawSnowflake(double x, double y, double len, int level) {
        if (level == 0) {
            return;
        }
        int add = 60;
        int angle = mAngle;
        while (angle <= (mAngle+360-add)) {
            double xOffset = Math.cos(Math.toRadians(angle)) * len;
            double yOffset = Math.sin(Math.toRadians(angle)) * len;
            mGc.strokeLine(x, y, x+xOffset, y+yOffset);
            drawSnowflake(x+xOffset, y+yOffset, len/3, level-1);
            angle += add;
        }
    }

    @Override
    public void update(double deltaTime) {
        mLastUpdate += deltaTime;
        if (mLastUpdate > GRAVITY_UPDATE_TIME) {
            int multi = mSpeed > 0.5 ? Math.random() > 0.5 ? 1 : -1 : 1;
            mSpeed += multi * Math.random()*0.5;
            mLastUpdate = 0;
        }
        mAngle += Math.random()*3;
        mVelocity = new Velocity(mSpeed, 270);
        mVelocity = mVelocity.add(mPlanet.getmWind());
        mStartPoint.move(mVelocity);
        mStartPoint.setX((mPlanet.getmCanvas().getWidth()+mStartPoint.getX())%mPlanet.getmCanvas().getWidth());
        mStartPoint.setY((mPlanet.getmCanvas().getHeight()+mStartPoint.getY())%mPlanet.getmCanvas().getHeight());
        drawSnowflake(mStartPoint.getX(), mStartPoint.getY(), mLen, mLevels);
    }
}
