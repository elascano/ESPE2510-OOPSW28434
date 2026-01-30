package ec.edu.espe.instrument.utils;

import ec.edu.espe.instrument.model.Instrument;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Arelys Otavalo, the POOwer Rangers of Programming
 */
public class CsvPersistence implements Persistence {

    private static final String FILE_NAME = "instruments.csv";

    @Override
    public boolean create(Instrument instrument) {
        List<Instrument> allInstruments = read();
        for (Instrument i : allInstruments) {
            if (i.getId().equals(instrument.getId())) {
                return false;
            }
        }
        allInstruments.add(instrument);
        return saveAll(allInstruments);
    }

    @Override
    public List<Instrument> read() {
        List<Instrument> instruments = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return instruments;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 5) {
                    String id = parts[0];
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    double priceWithIva = Double.parseDouble(parts[3]);                  
                    String materialsString = parts[4];  //list of materials by ; in the csv
                    List<String> materials = new ArrayList<>(Arrays.asList(materialsString.split(";")));
                    
                    instruments.add(new Instrument(id, name, price, materials, priceWithIva));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV: " + e.getMessage());
        }
        return instruments;
    }

    @Override
    public boolean update(String id, Instrument instrument) {
        List<Instrument> instruments = read();
        boolean found = false;
        for (int i = 0; i < instruments.size(); i++) {
            if (instruments.get(i).getId().equals(id)) {
                instruments.set(i, instrument);
                found = true;
                break;
            }
        }
        if (found) {
            return saveAll(instruments);
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        List<Instrument> instruments = read();
        boolean removed = instruments.removeIf(t -> t.getId().equals(id));
        if (removed) {
            return saveAll(instruments);
        }
        return false;
    }

    @Override
    public Instrument find(String id) {
        List<Instrument> instruments = read();
        for (Instrument t : instruments) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    private boolean saveAll(List<Instrument> instruments) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Instrument i : instruments) {
                String materialsString = String.join(";", i.getMaterials()); //join material with ; for the format to the cvs
                pw.println(i.getId() + ";" + i.getName() + ";" + i.getPrice() + ";" + i.getPriceWithIva() + ";" + materialsString);
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error writing CSV: " + e.getMessage());
            return false;
        }
    }
}