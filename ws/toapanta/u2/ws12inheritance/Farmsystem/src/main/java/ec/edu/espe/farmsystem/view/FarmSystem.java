
package ec.edu.espe.farmsystem.view;

import ec.edu.espe.farmsystem.model.Cage;
import ec.edu.espe.farmsystem.model.FarmAnimal;
import ec.edu.espe.farmsystem.model.Location;
import java.util.Date;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class FarmSystem {
    public static void main(String[] args){
        int id ;
        String breed;
        Date bornOn;
        String gender ;
        boolean isAbelToReproduce;
        float weigth;
        Cage cage;
        Location location;
        int xCoordinate;
        int yCoordinate;
       
        id= 1;
        xCoordinate=10;
        yCoordinate=20;
        weigth=10.4f;
        bornOn = new Date(2025, 2,1);
        breed="Holstein";
        gender="male";
        isAbelToReproduce=false;
        
        location = new Location(xCoordinate,yCoordinate);
        cage = new Cage(1,"stable for cows",2,location);
        
        FarmAnimal farmAnimal =new FarmAnimal(1,breed,bornOn,isAbelToReproduce,weigth,location,gender,id,cage);
        System.out.println("farmAnimal -->"+ farmAnimal);
 }
 
}
