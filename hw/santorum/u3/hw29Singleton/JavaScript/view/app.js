import Appointment from "../model/Appointment.js";
import AppointmentController from "../controller/AppointmentController.js";

const controller = new AppointmentController();

document
  .getElementById("registerBtn")
  .addEventListener("click", async () => {
    const appointment = new Appointment(
      document.getElementById("appointmentId").value,
      document.getElementById("patientId").value,
      document.getElementById("patientEmail").value,
      document.getElementById("date").value,
      document.getElementById("time").value
    );

    await controller.registerAppointment(appointment);
    alert("Appointment registered and email sent.");
  });
