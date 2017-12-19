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

    private static final double MINUTE_TO_SECOND_RATIO = 50;
    private static final Color DEFAULT_COLOUR = Color.rgb(14, 4, 21);
    private static final Color DAYTIME_COLOUR = Color.rgb(135, 206, 235);
    private static final Color DUSK_DAWN_COLOUR = Color.rgb(255, 102, 0);

    private Planet mPlanet;
    private Color mColour;
    private double mHeight;
    private double mWidth;
    private Calendar mTime;

    public Sky(Planet planet) {
        mPlanet = planet;
        mColour = DEFAULT_COLOUR;
        mTime = Calendar.getInstance();
    }

    @Override
    public void update(double deltaTime) {
        mHeight = mPlanet.getmDimensions().getHeight();
        mWidth = mPlanet.getmDimensions().getWidth();

        mTime.add(Calendar.SECOND, (int) (MINUTE_TO_SECOND_RATIO * deltaTime * 60));

        double timeInHours = mTime.get(Calendar.HOUR_OF_DAY) + mTime.get(Calendar.MINUTE) / 60.0 + mTime.get(Calendar.SECOND) / (60.0 * 60.0);

        GraphicsContext gc = mPlanet.getmCanvas().getGraphicsContext2D();
        gc.setFill(DEFAULT_COLOUR);
        gc.fillRect(0, 0, mWidth, mHeight);

        double daytimeOpacity = Math.pow((12-Math.abs(12 - timeInHours)) / 12.0, 2.2);
        Color curDaytimeColour = new Color(DAYTIME_COLOUR.getRed(), DAYTIME_COLOUR.getGreen(), DAYTIME_COLOUR.getBlue(), daytimeOpacity);
        gc.setFill(curDaytimeColour);
        gc.fillRect(0, 0, mWidth, mHeight);

        if (timeInHours > 5.5 && timeInHours < 8.5 || timeInHours > 16 && timeInHours < 19) {
            double startTime;
            if (timeInHours < 8.5) {
                startTime = 7;
            } else {
                startTime = 17.5;
            }
            double duskDawnOpacity = 0.5*Math.pow((1.5-(Math.abs(startTime - timeInHours))) / 1.5, 1);
            Color curDuskDawnColour = new Color(DUSK_DAWN_COLOUR.getRed(), DUSK_DAWN_COLOUR.getGreen(), DUSK_DAWN_COLOUR.getBlue(), duskDawnOpacity);
            gc.setFill(curDuskDawnColour);
            gc.fillRect(0, 0, mWidth, mHeight);
        }
    }

    public Calendar getmTime() {
        return mTime;
    }

}
