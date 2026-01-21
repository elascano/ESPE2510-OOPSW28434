
import ec.espe.edu.ws32Singleton.controller.AlarmController;
import ec.espe.edu.ws32Singleton.view.FrmAlarm;


/**
 *
 * @author Arelis Samantha Bonilla Cruz, @ESPE
 */
public class Main {
    String option;
    public static void main(String[] args) {
        AlarmController controller = new AlarmController();
        controller.run();
    }
}
