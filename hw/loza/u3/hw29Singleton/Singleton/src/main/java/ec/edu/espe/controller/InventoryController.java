package ec.edu.espe.controller;

import ec.edu.espe.model.MongoInventoryRepository;
import ec.edu.espe.model.Shoe;
import ec.edu.espe.view.InventoryView;

public class InventoryController {
    private final MongoInventoryRepository repository;
    private final InventoryView view;

    public InventoryController(MongoInventoryRepository repository, InventoryView view) {
        this.repository = repository;
        this.view = view;
    }

    public void init() {
        view.onAdd(event -> handleAdd());
        view.onBuy(event -> handleBuy());
        view.setVisible(true);
    }

    private void handleAdd() {
        String id = view.prompt("Ingrese el id");
        if (isBlank(id)) {
            view.showError("Id invalido.");
            return;
        }
        String name = view.prompt("Ingrese el nombre del zapato");
        if (isBlank(name)) {
            view.showError("Nombre invalido.");
            return;
        }
        Integer stock = parseInt(view.prompt("Ingrese el stock"));
        if (stock == null || stock < 0) {
            view.showError("Stock invalido.");
            return;
        }
        repository.addShoe(id.trim(), name.trim(), stock);
        view.showMessage("Zapato guardado.");
    }

    private void handleBuy() {
        String id = view.prompt("Ingrese el id");
        if (isBlank(id)) {
            view.showError("Id invalido.");
            return;
        }
        Shoe shoe = repository.findById(id.trim());
        if (shoe == null) {
            view.showError("No existe un zapato con ese id.");
            return;
        }
        view.showMessage("Zapato: " + shoe.name() + " (stock " + shoe.stock() + ")");
        Integer quantity = parseInt(view.prompt("Ingrese la cantidad a comprar"));
        if (quantity == null || quantity <= 0) {
            view.showError("Cantidad invalida.");
            return;
        }
        int remaining = repository.buy(id.trim(), quantity);
        if (remaining == -2) {
            view.showError("Stock insuficiente.");
            return;
        }
        if (remaining == -1) {
            view.showError("No existe un zapato con ese id.");
            return;
        }
        view.showMessage("Compra realizada. Stock restante: " + remaining);
        if (remaining < 5) {
            view.showLowStockWarning(remaining);
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private Integer parseInt(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
