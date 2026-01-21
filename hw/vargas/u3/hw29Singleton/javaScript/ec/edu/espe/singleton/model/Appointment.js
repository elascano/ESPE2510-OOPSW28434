
export class Appointment {
    constructor(appointmentId = 0, date = null, patientId = 0, doctorId = 0) {
        this.appointmentId = appointmentId;
        this.date = date; 
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.status = "Agendado";
    }

    toString() {
        return `-------------------------
CITA(${this.appointmentId})
Fecha: ${this.date ? this.date.toString() : 'N/A'}
Estado: ${this.status}
ID del paciente: ${this.patientId}
ID del Doctor: ${this.doctorId}
-------------------------`;
    }
}