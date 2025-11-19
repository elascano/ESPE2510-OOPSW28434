package ec.edu.espe.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Mateo Cevallos, Object Masters, @ESPE
 */
public class JsonManager {
    private String filename;
    private Gson gson;
    
    public JsonManager(){
        this("compact_Disk_Player.json");
    }
    
    public JsonManager(String filename){
        this.filename = filename;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    public void saveData(CompactDiskPlayer compactDiskPlayer) {
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(compactDiskPlayer, writer);
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    
    public CompactDiskPlayer loadData() {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File not found. Creating...");
            CompactDiskPlayer newPlayer = new CompactDiskPlayer(1, "PLAYER 1");
            saveData(newPlayer);
            return newPlayer;
        }
        
        try {
            String content = new String(Files.readAllBytes(Paths.get(filename)));
            CompactDiskPlayer compactDiskPlayer = gson.fromJson(content, CompactDiskPlayer.class);
            System.out.println("Data loaded successfully!");
            return compactDiskPlayer;
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
            CompactDiskPlayer newplayer = new CompactDiskplayer(1, "player_1");
            saveData(newPlayer);
            return newPlayer;
        }
    }
    
    public void showPlayingDisk() {
        CompactDiskPlayer compactDiskPlayer = loadData();
        
        if (compactDiskPlayer != null) {
            System.out.printf("Player: %s%n", compactDiskPlayer.getPlayername());
            System.out.printf("ID: %d%n", compactDiskPlayer.getId());
            
            if (compactDiskPlayer.getCompactDisks().isEmpty()) {
                System.out.println("The player is empty");
            } else {
                for (CompactDisk compactDisk : CompactDiskPlayer.getCompactDisks(){
                    System.out.println(compactDisk.toString());
                }
            }
        }
    }
    
    
    
}
