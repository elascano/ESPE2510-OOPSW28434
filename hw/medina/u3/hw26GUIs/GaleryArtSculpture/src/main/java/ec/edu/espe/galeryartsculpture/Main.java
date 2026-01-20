package ec.edu.espe.galeryartsculpture;

import javax.swing.SwingUtilities;
import ec.edu.espe.galeryartsculpture.view.FrmSculptures;
/**
 *
 * @author Mathews Pastor, The POOwer Rangers Of Programming
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FrmSculptures().setVisible(true);
        });
    }
}
