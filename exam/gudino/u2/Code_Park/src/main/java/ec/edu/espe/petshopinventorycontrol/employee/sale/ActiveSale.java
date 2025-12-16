package ec.edu.espe.petshopinventorycontrol.employee.sale;

import ec.edu.espe.petshopinventorycontrol.employee.view.Summary;


/**
 *
 * @author Bryan Gudino, KNOWLEDGE ENCAPSULATE, @ESPE
 */
public class ActiveSale {
    
    private static Summary summary;

    private ActiveSale() {
    }

    public static Summary getSummary() {
        if (summary == null) {
            summary = Summary.getInstance();
        }
        return summary;
    }
    
}
