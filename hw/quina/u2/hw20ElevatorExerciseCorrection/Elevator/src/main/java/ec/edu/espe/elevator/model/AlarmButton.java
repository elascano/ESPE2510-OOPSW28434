package ec.edu.espe.elevator.model;

/**
 *
 * @author Maryuri Quiña
 */
public class AlarmButton extends Button {

    public AlarmButton(String nameButton) {
        super(nameButton);
    }

    public void selectAlarm() {
        System.out.println("alarm activate");
    }

}
