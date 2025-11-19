package ec.edu.espe.mask.controller;
import ec.edu.espe.mask.model.Mask;
import ec.edu.espe.mask.model.MaskCrud;

/**
 *
 * @author Arelys Otavalo, The POOwer Rangers of Programming, @ESPE
 */


import java.util.List;

public class MaskController {
    private final MaskCrud crud;

    public MaskController() {
        crud = new MaskCrud();
    }

    public Mask addMask(String name, List<Double> prices) {
        int id = crud.generateStudentId();
        Mask mask = new Mask(id, name, prices);
        crud.addStudent(mask);
        return mask;
    }

    public List<Mask> getAllMasks() {
        return crud.getAllMasks();
    }

    public boolean editMask(int id, String newName, List<Double> newPrices) {
        return crud.editMask(id, newName, newPrices);
    }

    public boolean deleteMask(int id) {
        return crud.deleteMask(id);
    }
}