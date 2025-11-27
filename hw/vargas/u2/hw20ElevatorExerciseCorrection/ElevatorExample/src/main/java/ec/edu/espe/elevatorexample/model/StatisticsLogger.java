package ec.edu.espe.elevatorexample.model;

import java.time.LocalDateTime;

/**
 *
 * @author Adrian Toapanta, Object Masters, @ESPE
 */
public class StatisticsLogger {

    public StatisticsLogger() {
        System.out.println("  [StatisticsLogger] Logger system initialized.");
    }

    public void logEntry(int floor, LocalDateTime timestamp) {
        System.out.println("  [Logger] ENTRY recorded at floor " + floor + " at " + timestamp);
    }

    public void logExit(int floor, LocalDateTime timestamp) {
        System.out.println("  [Logger] EXIT recorded at floor " + floor + " at " + timestamp);
    }


    public void logFloorTime(int floor, LocalDateTime timestamp) {
        System.out.println("  [Logger] Floor time recorded for floor " + floor + " at " + timestamp);
    }
}

