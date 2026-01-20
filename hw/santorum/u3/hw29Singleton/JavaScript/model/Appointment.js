export default class Appointment {
  constructor(appointmentId, patientId, patientEmail, date, time) {
    this.appointmentId = appointmentId;
    this.patientId = patientId;
    this.patientEmail = patientEmail;
    this.date = date;
    this.time = time;
  }
}
