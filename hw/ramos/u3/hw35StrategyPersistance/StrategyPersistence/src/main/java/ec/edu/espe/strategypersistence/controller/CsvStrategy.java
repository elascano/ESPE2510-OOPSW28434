package ec.edu.espe.strategypersistence.controller;

import ec.edu.espe.strategypersistence.model.Store;
import java.io.*;
import java.util.*;

/**
 *
 * @author Paulo Ramos
 */

public class CsvStrategy implements PersistenceStrategy {
    private String path = "Store.csv";

    @Override
    public void create(Store s) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            pw.println(s.getId() + ";" + s.getName() + ";" + s.getPrice() + ";" + s.getCategory());
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public List<Store> loadAll() {
        List<Store> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] v = line.split(";");
                list.add(new Store(Integer.parseInt(v[0]), v[1], Float.parseFloat(v[2]), v[3]));
            }
        } catch (Exception e) {}
        return list;
    }

    @Override public Store find(int id) { 
        return loadAll().stream().filter(s -> s.getId() == id).findFirst().orElse(null); 
    }

    @Override public void update(int id, Store s) {
        List<Store> list = loadAll();
        list.removeIf(p -> p.getId() == id);
        list.add(s);
        rewriteCsv(list);
    }

    @Override public void delete(int id) {
        List<Store> list = loadAll();
        list.removeIf(p -> p.getId() == id);
        rewriteCsv(list);
    }

    private void rewriteCsv(List<Store> list) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            for(Store s : list) pw.println(s.getId() + ";" + s.getName() + ";" + s.getPrice() + ";" + s.getCategory());
        } catch (Exception e) {}
    }
}