import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart
from .gmail_configuration import GmailConfiguration

class AppointmentNotificationService:

    def __init__(self):
        config = GmailConfiguration.get_instance()
        self.sender_email = config.sender_email
        self.app_password = config.app_password
        self.smtp_host = config.smtp_host
        self.smtp_port = config.smtp_port

    def send_reservation_confirmation(self, recipient_email: str, patient_name: str, appointment_details: str):
        if not self.sender_email or not self.app_password:
            print("Error: No hay credenciales de correo configuradas.")
            return

        try:
            msg = MIMEMultipart()
            msg['From'] = self.sender_email
            msg['To'] = recipient_email
            msg['Subject'] = "Confirmación de Cita Médica - Clínica Toamedical"

            body = self._build_email_body(patient_name, appointment_details)
            msg.attach(MIMEText(body, 'plain'))

            # Conexión SSL
            server = smtplib.SMTP_SSL(self.smtp_host, self.smtp_port)
            server.login(self.sender_email, self.app_password)
            server.send_message(msg)
            server.quit()

            print(f"Notificación enviada correctamente a: {recipient_email}")

        except Exception as e:
            print(f"Error al enviar el correo: {e}")

    def _build_email_body(self, name: str, details: str) -> str:
        return (
            f"Estimado/a {name},\n\n"
            "Su cita ha sido reservada exitosamente.\n"
            f"Fecha de la cita agendada: {details}\n\n"
            "Por favor, llegue 10 minutos antes.\n"
            "Atentamente,\n"
            "Tu clínica de confianza Toamedical."
        )