package ec.edu.espe.instrument;

import ec.edu.espe.instrument.view.FrmInstrument;
import javax.swing.SwingUtilities;
/**
 *
 * @author Arelys Otavalo, the POOwer Rangers of Programming
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FrmInstrument().setVisible(true);
        });
    }
}
