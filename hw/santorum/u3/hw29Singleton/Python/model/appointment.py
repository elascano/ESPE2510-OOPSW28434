class Appointment:
    def __init__(self, appointment_id, patient_id, patient_email, date, time):
        self.appointment_id = appointment_id
        self.patient_id = patient_id
        self.patient_email = patient_email
        self.date = date
        self.time = time

    def to_file_string(self):
        return f"{self.appointment_id},{self.patient_id},{self.patient_email},{self.date},{self.time}\n"
