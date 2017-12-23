/*
 * Michael Pu
 * RecursionGraphics - Utilities
 * December 22, 2017
 */

package backend;

public final class Utilities {

    public static final double EPSILON = 1E-5;

    public static String zFill(int num, int places) {
        String strNum = Integer.toString(num);
        while(strNum.length() < places) {
            strNum = "0"+strNum;
        }
        return strNum;
    }

}
