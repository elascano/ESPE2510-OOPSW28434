package ec.edu.espe.mongo.controller;

import ec.edu.espe.mongo.model.Toy;
import java.util.List;

public class ToyController {

    private final MongoCrud mongoCrud;

    public ToyController() {
        this.mongoCrud = new MongoCrud();
    }

    public void save(Toy toy) {
        mongoCrud.create(toy);
    }

    public Toy findById(int id) {
        return mongoCrud.readById(id);
    }

    public void update(Toy toy) {
        mongoCrud.update(toy);
    }

    public void delete(int id) {
        mongoCrud.delete(id);
    }

    public List<Toy> findAll() {
        return mongoCrud.readAll();
    }
}
