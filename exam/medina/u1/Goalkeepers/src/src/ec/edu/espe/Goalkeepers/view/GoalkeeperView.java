package src.ec.edu.espe.Goalkeepers.view;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;                   
import com.google.gson.GsonBuilder;             
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;    
import java.io.FileWriter;  
import java.io.IOException;   
import java.io.Reader;        
import java.lang.reflect.Type;  

import src.ec.edu.espe.Goalkeepers.model.Goalkeeper;


/**
 *
 * @author Joseph Medina
 */
public class GoalkeeperView {

    public static void main(String[] args) {
                
        ArrayList<Goalkeeper> listOfGoalkeepers; 
        
        Scanner scanner = new Scanner(System.in); 
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        String jsonFileName = "goalkeepers.json";    
        
        try (Reader reader = new FileReader(jsonFileName)) {
            
            Type listType = new TypeToken<ArrayList<Goalkeeper>>(){}.getType();
            
       
           listOfGoalkeepers = gson.fromJson(reader, listType);
            
           
            if (listOfGoalkeepers == null) {
                
                listOfGoalkeepers = new ArrayList<>();
                System.out.println("DATA NO FOUNDED... Creating a new list ===");
            } else {
                System.out.println("Data uploaded successfuly " + jsonFileName + "!");
            }
            
        } catch (IOException e) {
            System.out.println("No fouded archive. CReating a new list");
            listOfGoalkeepers = new ArrayList<>();
        }

        int option; 
        
        do {
            System.out.println("\n=== Register of goalkeeepers ===");
            System.out.println("(1) ---> Registrer a goalkeeper");
            System.out.println("(2) ---> View list of goalkeeperss");
            System.out.println("(0) ---> Exit");
            System.out.print("Choose an option: ");
            
            option = scanner.nextInt();  
           
            scanner.nextLine();         
            
            switch (option) {
                
                
                case 1:
                    System.out.println("--- Register  ---");
                    
                    
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    
                    System.out.print("Namw: ");
                    String nameGoalkeeper = scanner.nextLine();
                    
                    System.out.print("How many saves he has?: ");
                    String saves = scanner.nextLine();
                    
                    System.out.print("What is the current team in he is playing?: ");
                    String actualTeam = scanner.nextLine();
                    
                    
                    int numberOfSaves = Integer.parseInt(saves);
                    
                    
                    Goalkeeper goalkeeper = new Goalkeeper(id, nameGoalkeeper, numberOfSaves, actualTeam);
                                     
                    listOfGoalkeepers.add(goalkeeper);

                    try (FileWriter writer = new FileWriter(jsonFileName)) {
                                           
                        gson.toJson(listOfGoalkeepers, writer);
                        
                    } catch (IOException e) {
                        System.out.println("ERROR: we cant save in " + jsonFileName + "close the file first");
                    }
                    
                    System.out.println("=======================");
                    System.out.println("Goalkeeper " + nameGoalkeeper + " added and saved");
                    
                    break; 

                case 2:
                    System.out.println("=== List of GOALKEEEPERS ===");
                    
                    if (listOfGoalkeepers.isEmpty()) {
                        System.out.println("There is no goalkeepers");
                    } else {
                     
                        for (Goalkeeper portero : listOfGoalkeepers) {
                            
                            System.out.println(portero); 
                        }
                    }
                    
                    break; 

                    case 0:
                    System.out.println("THX FOR USING BYE BYE BBY");
                    break;

                default:
                    System.out.println("cHOOSE A VALID OPTION");
                    break; 
            }
            
        } while (option != 0);
        
        scanner.close();
    }
}
