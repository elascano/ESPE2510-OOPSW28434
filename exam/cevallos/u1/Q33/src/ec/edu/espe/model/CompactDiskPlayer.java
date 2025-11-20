package ec.edu.espe.model;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Mateo Cevallos, Object Masters, @ESPE
 */
public class CompactDiskPlayer {
    private int id;
    private String playername;
    private List<CompactDisk> compactDisks;
    
    public CompactDiskPlayer(int par, String player_1){
        this.compactDisks = new ArrayList<>();
    }

    public CompactDiskPlayer(int id, String player_1, List<CompactDisk> compactDisks) {
        this.id = id;
        this.playername = player_1;
        this.compactDisks = compactDisks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public List<CompactDisk> getCompactDisks() {
        return compactDisks;
    }

    public void setCompactDisks(List<CompactDisk> compactDisks) {
        this.compactDisks = compactDisks;
    }
    
    public void addCompactDisk(CompactDisk compactDisk){
        this.compactDisks.add(compactDisk);
        System.out.print("Disk ready to play");
    }
    
    
}
