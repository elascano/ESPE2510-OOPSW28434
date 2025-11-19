package src.ec.edu.espe.Goalkeepers.model;

/**
 *
 * @author Joseph Medina
 */
public class Goalkeeper {

    private String id;
    private String nameOfGoalkeeper;
    private int numberOfSaves;
    private String currentTeam;

    public Goalkeeper() {
        this.id = "";
        this.nameOfGoalkeeper = "N/A";
        this.numberOfSaves = 0;
        this.currentTeam = "N/A";
    }


    public Goalkeeper(String id, String nameOfGoalkeeper, int numberOfSaves, String currentTeam) {
        this.id = id; 
        this.nameOfGoalkeeper = nameOfGoalkeeper;
        this.numberOfSaves = numberOfSaves;
        this.currentTeam = currentTeam;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameOfGoalkeeper() {
        return nameOfGoalkeeper;
    }

    public void setNameOfGoalkeeper(String nameOfGoalkeeper) {
        this.nameOfGoalkeeper = nameOfGoalkeeper;
    }

    public int getNumberOfSaves() {
        return numberOfSaves;
    }

    public void setNumberOfSaves(int numberOfSaves) {
        this.numberOfSaves = numberOfSaves;
    }

    public String getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(String currentTeam) {
        this.currentTeam = currentTeam;
    }

    @Override
    public String toString() {
        return "Goalkeeper{" + "id=" + id + ", name=" + nameOfGoalkeeper + ", saves=" + numberOfSaves + ", team=" + currentTeam + '}';
    }
}
