package ec.edu.espe.singleton.model;
/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */

public class Date {

    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;

    public Date() {
    }

    public Date(int day, int month, int year, int hour, int minute) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d @ %02d:%02d", year, month, day, hour, minute);
    }
      public boolean isBefore(Date other) {

        if (this.year != other.year) return this.year < other.year;

        if (this.month != other.month) return this.month < other.month;

        if (this.day != other.day) return this.day < other.day;

        if (this.hour != other.hour) return this.hour < other.hour;

        return this.minute < other.minute;

    }
}