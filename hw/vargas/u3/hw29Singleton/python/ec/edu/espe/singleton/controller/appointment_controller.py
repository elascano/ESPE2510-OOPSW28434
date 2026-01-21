class AppointmentController:
    
    def cancel(self, appointment):
        appointment.status = "Cancelada"
        print(f"Cita {appointment.appointment_id} ha sido cancelada.")

    def mark_completed(self, appointment):
        appointment.status = "Completada"
        print(f"Cita {appointment.appointment_id} marcada como completada.")