/*
 * Michael Pu
 * RecursionGraphics - Clock
 * December 18, 2017
 */

package frontend;

import backend.Sprite;
import backend.Utilities;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Calendar;

public class Clock extends Sprite {

    private static final int FONT_SIZE = 20;
    private static final Color FONT_COLOUR = Color.BLACK;


    private Text mText;
    private Sky mSky;

    public Clock(Sky sky, Text text) {
        mSky = sky;
        mText = text;
        mText.setX(15);
        mText.setY(30);
        mText.setFont(new Font(FONT_SIZE));
        mText.setFill(FONT_COLOUR);
    }

    @Override
    public void update(double deltaTime) {
        Calendar curTime = mSky.getmTime();
        String hour = Utilities.zFill(curTime.get(Calendar.HOUR) == 0 ? 12 : curTime.get(Calendar.HOUR), 2);
        String min = Utilities.zFill(curTime.get(Calendar.MINUTE), 2);
        String ampm = curTime.get(Calendar.AM_PM) == 0 ? "AM" : "PM";
        mText.setText(hour + ":" + min + " " + ampm);
    }
}
