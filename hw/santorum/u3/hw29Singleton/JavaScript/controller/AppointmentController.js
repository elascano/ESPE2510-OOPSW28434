import StorageManager from "../utils/StorageManager.js";
import EmailService from "../utils/EmailService.js";

export default class AppointmentController {
  async registerAppointment(appointment) {
    StorageManager.saveAppointment(appointment);

    await EmailService.sendEmail(
      appointment.patientEmail,
      "Your appointment has been registered successfully."
    );
  }
}
