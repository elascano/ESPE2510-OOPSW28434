package ec.edu.espe.tools;

import ec.edu.espe.tools.view.FrmTool;
import javax.swing.SwingUtilities;
/**
 *
 * @author César Vargas, Paradigm, @ESPE
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new FrmTool().setVisible(true);
        });
    }
}
