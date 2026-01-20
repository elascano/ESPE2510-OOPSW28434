
package ec.edu.espe.singleton.controller;

import ec.edu.espe.singleton.model.Appointment;
import ec.edu.espe.singleton.utils.EmailService;
import ec.edu.espe.singleton.utils.FileManager;

/**
 *
 * @author Thais Santorum
 *
 */


public class AppointmentController {

    public void registerAppointment(Appointment appointment) {
        FileManager.saveAppointment(appointment);

        EmailService.sendEmail(
                appointment.getPatientEmail(),
                "Your appointment has been registered successfully."
        );
    }
}
