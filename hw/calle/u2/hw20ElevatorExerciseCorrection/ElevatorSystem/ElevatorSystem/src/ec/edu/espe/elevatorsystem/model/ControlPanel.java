
package ec.edu.espe.elevatorsystem.model;

/**
 *
 * @author Josue Carvajal, THE ART OF PROGRAMMING, @ESPE
 */
import java.util.List;

public class ControlPanel {
    private List<Button> buttons;

    public void pressButton(int index) {
        buttons.get(index).press();
    }
}

