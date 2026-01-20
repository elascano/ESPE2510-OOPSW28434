from utils.file_manager import FileManager
from utils.email_service import EmailService

class AppointmentController:

    def register_appointment(self, appointment):
        FileManager.save_appointment(appointment)

        EmailService.send_email(
            appointment.patient_email,
            "Your appointment has been registered successfully."
        )
