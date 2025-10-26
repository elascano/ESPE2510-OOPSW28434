
package simulation;

/**
 *
 * @author ADRIAN TOAPANTA
 */


public class Time {
    private int ticks;
    private long startTime;
    
    public Time() {
        this.ticks = 0;
        this.startTime = System.currentTimeMillis();
    }
    
    public void tick() {
        ticks++;
    }
    
    public int getTicks() {
        return ticks;
    }
    
    public long getElapsedTime() {
        return System.currentTimeMillis() - startTime;
    }
}
