package backend;

import javafx.geometry.Point2D;

import java.util.ArrayList;

public abstract class Sprite {

    protected Coordinate mStartPoint;
    protected Velocity mVelocity;

    private static ArrayList<Sprite> sSpriteList;

    public Sprite() {
        sSpriteList.add(this);
        mVelocity = new Velocity(0, 0);
    }

    public static void setSpriteList(ArrayList<Sprite> spriteList) {
        sSpriteList = spriteList;
    }


    public abstract void update();
}
