package ec.edu.espe.tools.controller;

import ec.edu.espe.tools.model.Tool;
import ec.edu.espe.tools.utils.IToolRepository;
import ec.edu.espe.tools.utils.IToolView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 *@author César Vargas, Paradigm, @ESPE
 */
public class ToolController {
private final IToolView view;
    private final IToolRepository repository;
    private static final double IVA_RATE = 0.15;

    public ToolController(IToolView view, IToolRepository repository) {
        this.view = view;
        this.repository = repository;
        this.view.setController(this);
        this.loadAllData(); 
    }


    private double calculatePriceWithIva(double basePrice) {
        if (basePrice < 0) return 0;
        double result = basePrice * (1 + IVA_RATE);
        return Math.round(result * 100.0) / 100.0;
    }

    private List<String> parseMaterials(String text) {
        if (text == null || text.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.stream(text.split(","))
                     .map(String::trim)
                     .filter(s -> !s.isEmpty())
                     .collect(Collectors.toList());
    }


    public void create() {
        try {
            String id = view.getIdInput();
            String name = view.getNameInput();
            double price = view.getPriceInput();

            if (id.isEmpty() || name.isEmpty()) {
                view.showError("ID and Name are required.");
                return;
            }

            double finalPrice = calculatePriceWithIva(price);
            List<String> materials = parseMaterials(view.getMaterialsInput());

            Tool tool = new Tool(id, name, price, materials, finalPrice);

            if (repository.create(tool)) {
                view.showMessage("Saved successfully!");
                view.clearFields();
                loadAllData();
            } else {
                view.showError("Error: ID already exists.");
            }
        } catch (Exception e) {
            view.showError("Data error (Check if price is a valid number).");
        }
    }

    public void update() {
        try {
            String id = view.getIdInput();
            if (repository.findById(id) == null) {
                view.showError("Cannot update: ID does not exist.");
                return;
            }

            double price = view.getPriceInput();
            double finalPrice = calculatePriceWithIva(price);
            
            Tool tool = new Tool(
                id, 
                view.getNameInput(), 
                price, 
                parseMaterials(view.getMaterialsInput()), 
                finalPrice
            );

            if (repository.update(tool)) {
                view.showMessage("Updated successfully.");
                loadAllData();
            } else {
                view.showError("Update failed.");
            }
        } catch (Exception e) {
            view.showError("Error updating data.");
        }
    }

    public void delete() {
        String id = view.getIdInput();
        if (id.isEmpty()) {
            view.showError("Please enter an ID to delete.");
            return;
        }
        if (repository.delete(id)) {
            view.showMessage("Deleted successfully.");
            view.clearFields();
            loadAllData();
        } else {
            view.showError("ID not found.");
        }
    }

    public void find() {
        String id = view.getIdInput();
        if (id.isEmpty()) {
            loadAllData(); 
            return;
        }
        Tool t = repository.findById(id);
        if (t != null) {
            List<Tool> list = new ArrayList<>();
            list.add(t);
            view.refreshTable(list);
        } else {
            view.showError("Not found.");
            loadAllData();
        }
    }

    public void loadAllData() {
        view.refreshTable(repository.findAll());
    }
}