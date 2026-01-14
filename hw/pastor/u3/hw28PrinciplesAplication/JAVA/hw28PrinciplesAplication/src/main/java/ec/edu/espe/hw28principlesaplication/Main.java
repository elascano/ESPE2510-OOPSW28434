package ec.edu.espe.hw28principlesaplication;

import ec.edu.espe.hw28principlesaplication.controller.ProductController;
import ec.edu.espe.hw28principlesaplication.interfaces.IGenericRepository;
import ec.edu.espe.hw28principlesaplication.model.GenericEntity;
import ec.edu.espe.hw28principlesaplication.interfaces.IGenericRepository;
import ec.edu.espe.hw28principlesaplication.service.IGenericService;
import ec.edu.espe.hw28principlesaplication.interfaces.impl.GenericRepositoryImpl;
import ec.edu.espe.hw28principlesaplication.service.impl.CalculateService;
import ec.edu.espe.hw28principlesaplication.view.FrmPrincipleView;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                System.out.println("Start...");
                
                IGenericRepository<GenericEntity> repository = new GenericRepositoryImpl("products");
                
                IGenericService servicio = new CalculateService(repository);
                
                ProductController controller = new ProductController(servicio);
                
                FrmPrincipleView view = new FrmPrincipleView(controller);
                view.setVisible(true);
                System.out.println("Successful.");

            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Fatal Error " + e.getMessage());
            }
        });
    }
}
