/*
 * Michael Pu
 * RecursionGraphics - State
 * December 17, 2017
 */

package frontend;

public enum State {
    NORMAL (1.0/20000, 0.0005, 0.001, 0.005, 0.008, 0.001),
    BLIZZARD (1.0/5000, 0.001, 0.005, 0.005, 0.008, 0.001),
    FLURRIES (1.0/80000, 0.00005, 0.0001, 0.005, 0.008, 0.001);

    private final double snowflakePerPixel;
    private final double windChangeRate;
    private final double maxWindSpeed;
    private final double minSnowflakeSpeed;
    private final double maxSnowflakeSpeed;
    private final double snowflakeSpeedChangeMulti;

    State(double snowflakePerPixel, double windChangeRate, double maxWindSpeed, double minSnowflakeSpeed, double maxSnowflakeSpeed, double snowflakeSpeedChangeMulti) {
        this.snowflakePerPixel = snowflakePerPixel;
        this.windChangeRate = windChangeRate;
        this.maxWindSpeed = maxWindSpeed;
        this.minSnowflakeSpeed = minSnowflakeSpeed;
        this.maxSnowflakeSpeed = maxSnowflakeSpeed;
        this.snowflakeSpeedChangeMulti = snowflakeSpeedChangeMulti;
    }

    public double getSnowflakePerPixel() {
        return this.snowflakePerPixel;
    }

    public double getWindChangeRate() {
        return windChangeRate;
    }

    public double getMaxWindSpeed() {
        return maxWindSpeed;
    }

    public double getMinSnowflakeSpeed() {
        return minSnowflakeSpeed;
    }

    public double getSnowflakeSpeedChangeMulti() {
        return snowflakeSpeedChangeMulti;
    }
}
