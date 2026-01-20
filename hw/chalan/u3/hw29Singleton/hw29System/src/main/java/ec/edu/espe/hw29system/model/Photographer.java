package ec.edu.espe.hw29system.model;

public class Photographer {

    private String name;
    private String specialty;
    private int experience;
    private double hourlyRate;

    public Photographer(String name, String specialty, int experience, double hourlyRate) {
        this.name = name;
        this.specialty = specialty;
        this.experience = experience;
        this.hourlyRate = hourlyRate;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public int getExperience() {
        return experience;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }
}
