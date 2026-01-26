package ec.edu.espe.alarmsystem.main;
import ec.edu.espe.alarmsystem.controller.AlarmController;
import ec.edu.espe.alarmsystem.model.AlarmService;
import ec.edu.espe.alarmsystem.view.FrmAlarm;

/**
 *
 * @author @author Adrian Toapanta 
 */


public class Main {
    public static void main(String[] args) {
        AlarmService model = AlarmService.getInstance();
        FrmAlarm view = new FrmAlarm();
        AlarmController controller = new AlarmController(model, view);
        controller.run();
    }
}