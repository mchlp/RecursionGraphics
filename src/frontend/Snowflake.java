package frontend;

import backend.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Snowflake extends Sprite {

    private GraphicsContext mGc;
    private Planet mPlanet;
    private int mLevels;
    private double mLen;

    public Snowflake(Planet planet, GraphicsContext gc, Coordinate startPoint, int levels, int len) {
        super();
        mVelocity = new Velocity(3, 270);
        mGc = gc;
        mPlanet = planet;
        mStartPoint = startPoint;
        mLevels = levels;
        mLen = len;
    }

    public void drawSnowflake(double x, double y, double len, int level) {
        if (level == 0) {
            return;
        }
        int add = 60;
        int angle = -30;
        for (int i=0; i<6; i++) {
            double xOffset = Math.cos(Math.toRadians(angle)) * len;
            double yOffset = Math.sin(Math.toRadians(angle)) * len;
            mGc.strokeLine(x, y, x+xOffset, y+yOffset);
            drawSnowflake(x+xOffset, y+yOffset, len/3, level-1);
            angle += add;
        }
    }

    @Override
    public void update() {

        drawSnowflake(mStartPoint.getX(), mStartPoint.getY(), mLen, mLevels);

        mStartPoint.move(mVelocity);
        mStartPoint.setX(mStartPoint.getX()%mPlanet.getmCanvas().getWidth());
        mStartPoint.setY(mStartPoint.getY()%mPlanet.getmCanvas().getHeight());

    }
}
