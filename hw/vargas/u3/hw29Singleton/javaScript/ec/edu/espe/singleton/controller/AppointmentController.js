export class AppointmentController {
    
    cancel(appointment) {
        appointment.status = "Cancelada";
        console.log(`Cita ${appointment.appointmentId} ha sido cancelada.`);
    }

    markCompleted(appointment) {
        appointment.status = "Completada";
        console.log(`Cita ${appointment.appointmentId} marcada como completada.`);
    }
}