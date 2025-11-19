package ec.espe.edu.question33.controller;
import ec.espe.edu.question33.model.Picture;
import ec.espe.edu.question33.model.Galery;
import java.util.ArrayList;
import ec.espe.edu.question33.view.Question33Main;
/**
 *
 * @author Mathews Pastor, Poower Rangers of Programing, @ESPE
 */
public class Controller {
    private Question33Main view;
    private Picture management;

    public Controller() {
        this.view = new Question33Main();
        this.management = new Picture();
    }

    public void start() {
        management.cargarJson();
        view.showMessage("Well");

        boolean continuar = true;
        while (continuar) {
            String opcion = view.viewMenu();

            switch (opcion) {
                case "1": 
                    Picture data = view.setDatastart();
                    if (data != null) {
                        management.registrarPlan(data.getName(), data.getAuthor(), data.getPrice());
                        management.saveJson();
                        view.showMessage("Save.");
                    }
                    break;

                case "3": 
                    view.showColors(management.getCompleteList());
                    int idxAdd = view.setNumber("Picture: ");
                    double valAdd = view.setDouble("M: ");
                    if (management.registrarConsumo(idxAdd, valAdd)) {
                        management.saveJson();
                        view.showMessage("C");
                    } else {
                        view.showMessage("Error.");
                    }
                    break;

                case "4": 
                    view.showColors(management.getCompleteList());
                    int idxDel = view.setNumber("Índice a eliminar: ");
                    if (management.eliminarPlan(idxDel)) {
                        management.saveJson();
                        view.showMessage("Eliminado.");
                    } else {
                        view.showMessage("Error.");
                    }
                    break;
                
                case "5": 
                    editColor();
                    break;

                case "7": 
                    view.showColors(management.getCompleteList());
                    int idxLim = view.setNumber("Num Picture ");
                    double nuevoLim = view.setDouble("New Price ");
                    if(management.actualizarLimitePlan(idxLim, nuevoLim)){
                        management.saveJson();
                        view.showMessage("Well.");
                    } else {
                        view.showMessage("Error.");
                    }
                    break;
                    
                default:
                    view.showMessage("Error.");
            }
        }
    }
    private void editColor() {
        ArrayList<color> list = management.getCompleteList();
        view.showColors(list);
        int idxPlan = view.setNumber("Num Picture ");
        
        if (idxPlan >= 0 && idxPlan < list.size()) {
            color p = list.get(idxPlan);
            view.mostrarDetalleConsumos(p.getName(), p.getHistorialUso());
            
            int idxConsumo = view.setNumber("Which Color ");
            double nuevoVal = view.setDouble("New Color ");
            
            if(management.editColorEspecifico(idxPlan, idxConsumo, nuevoVal)){
                management.saveJson();
                view.showMessage("Well");
            } else {
                view.showMessage("Erro");
            }
        }
    }

}
