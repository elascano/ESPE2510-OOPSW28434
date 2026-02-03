package ec.edu.espe.strategy.utils;

import ec.edu.espe.strategy.model.Parking;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arelis Samantha Bonilla Cruz, Student, @ESPE
 */
public class CsvPersistence implements Persistence {
    private final String fileName = "data/parking.csv";

    public boolean create(Parking parking){
        List<Parking> list = read();
        list.add(parking);
        return save(list);
    }

    public List<Parking> read(){
        List<Parking> list = new ArrayList<>();
        File file = new File(fileName);
        if(!file.exists()){
            try { file.getParentFile().mkdirs(); file.createNewFile(); } catch(IOException e) { }
            return list;
        }
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                if(line.trim().isEmpty() || line.startsWith("id,")) continue;
                String[] parts = line.split(",");
                Parking p = new Parking(
                    parts[0],
                    parts[1],
                    parts[2],
                    LocalDateTime.parse(parts[3]),
                    parts[4].isEmpty() ? null : LocalDateTime.parse(parts[4]),
                    Double.parseDouble(parts[5])
                );
                list.add(p);
            }
        } catch(IOException e){
            System.out.println("Error reading CSV: " + e.getMessage());
        }
        return list;
    }

    public boolean update(String id, Parking parking){
        List<Parking> list = read();
        for(int i=0; i<list.size(); i++){
            if(list.get(i).getId().equals(id)){
                list.set(i, parking);
                return save(list);
            }
        }
        return false;
    }

    public boolean delete(String id){
        List<Parking> list = read();
        list.removeIf(p -> p.getId().equals(id));
        return save(list);
    }

    public Parking find(String id){
        return read().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    private boolean save(List<Parking> list){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            bw.write("id,plate,vehicleType,entryTime,exitTime,fee\n");
            for(Parking p : list){
                bw.write(String.format("%s,%s,%s,%s,%s,%.2f\n",
                        p.getId(), p.getPlate(), p.getVehicleType(),
                        p.getEntryTime(), p.getExitTime() != null ? p.getExitTime() : "",
                        p.getFee()));
            }
            return true;
        } catch(IOException e){
            System.out.println("Error saving CSV: " + e.getMessage());
            return false;
        }
    }
}