package ec.edu.espe.galeryartsculpture;

import ec.edu.espe.tools.controller.ToolController;
import ec.edu.espe.tools.utils.IToolRepository;
import ec.edu.espe.tools.utils.MongoToolRepository;
import ec.edu.espe.tools.view.FrmTool;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mikael Hidalgo, Paradigm, @ESPE
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FrmTool view = new FrmTool();

            IToolRepository repository = new MongoToolRepository();

            new ToolController(view, repository);

            view.setVisible(true);
        });
    }
}
