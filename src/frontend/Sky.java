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

    private static final int MAX_COLOUR_VALUE = 255;
    private static final long MILLISECONDS_IN_DAY = 24*60*60*1000;
    private static final double MINUTE_TO_SECOND_RATIO = 600;
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

        int section = (int) ((mTime.getTimeInMillis()%MILLISECONDS_IN_DAY)/mColourInterval);
        double offsetFromLast = ((mTime.getTimeInMillis()%MILLISECONDS_IN_DAY)%mColourInterval) / (double) (mColourInterval);

        double rDiff = (SKY_COLOURS[section].getRed()*MAX_COLOUR_VALUE) - (SKY_COLOURS[(section+1)%SKY_COLOURS.length].getRed()*MAX_COLOUR_VALUE);
        double gDiff = (SKY_COLOURS[section].getGreen()*MAX_COLOUR_VALUE) - (SKY_COLOURS[(section+1)%SKY_COLOURS.length].getGreen()*MAX_COLOUR_VALUE);
        double bDiff = (SKY_COLOURS[section].getBlue()*MAX_COLOUR_VALUE) - (SKY_COLOURS[(section+1)%SKY_COLOURS.length].getBlue()*MAX_COLOUR_VALUE);

        System.out.println(rDiff + " " + gDiff + " " + bDiff);

        int newR = (int) (((SKY_COLOURS[section].getRed())*MAX_COLOUR_VALUE + rDiff + MAX_COLOUR_VALUE)%MAX_COLOUR_VALUE);
        int newG = (int) (((SKY_COLOURS[section].getGreen())*MAX_COLOUR_VALUE + gDiff + MAX_COLOUR_VALUE)%MAX_COLOUR_VALUE);
        int newB = (int) (((SKY_COLOURS[section].getBlue())*MAX_COLOUR_VALUE + bDiff+ MAX_COLOUR_VALUE)%MAX_COLOUR_VALUE);

        System.out.println(newR + " " + newG + " " + newB);

        Color curColour = Color.rgb(newR, newG, newB);

        GraphicsContext gc = mPlanet.getmCanvas().getGraphicsContext2D();
        gc.setFill(curColour);
        gc.fillRect(0, 0, mWidth, mHeight);
    }
}
