/*
 * Michael Pu
 * RecursionGraphics - Sky
 * December 17, 2017
 */

package frontend;

import backend.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Calendar;

public class Sky extends Sprite {

    private static final double MINUTE_TO_SECOND_RATIO = 60;
    private static final Color DEFAULT_COLOUR = Color.rgb(46, 14, 73);
    private static final Color[] SKY_COLOURS = {
            Color.rgb(0, 191, 255),
            Color.rgb(46, 14, 73)
    };

    private Planet mPlanet;
    private Color mColour;
    private double mHeight;
    private double mWidth;
    private Calendar mTime;
    private long mColourInterval;

    public Sky(Planet planet) {
        mPlanet = planet;
        mColour = DEFAULT_COLOUR;
        mTime = Calendar.getInstance();
        mColourInterval = 24*60*60*1000/SKY_COLOURS.length;
    }

    @Override
    public void update(double deltaTime) {
        mHeight = mPlanet.getmDimensions().getHeight();
        mWidth = mPlanet.getmDimensions().getWidth();

        mTime.add(Calendar.MINUTE, (int) (MINUTE_TO_SECOND_RATIO*deltaTime));

        GraphicsContext gc = mPlanet.getmCanvas().getGraphicsContext2D();
        gc.setFill(mColour);
        gc.fillRect(0, 0, mWidth, mHeight);
    }
}
