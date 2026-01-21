from .date_model import DateModel

class Appointment:

    def __init__(self, appointment_id: int = 0, date: DateModel = None, 
                 patient_id: int = 0, doctor_id: int = 0):
        self.appointment_id = appointment_id
        self.date = date
        self.patient_id = patient_id
        self.doctor_id = doctor_id
        self.status = "Agendado"

    def __str__(self):
        return (
            "-------------------------\n"
            f"CITA({self.appointment_id})\n"
            f"Fecha: {self.date}\n"
            f"Estado: {self.status}\n"
            f"ID del paciente: {self.patient_id}\n"
            f"ID del Doctor: {self.doctor_id}\n"
            "-------------------------"
        )