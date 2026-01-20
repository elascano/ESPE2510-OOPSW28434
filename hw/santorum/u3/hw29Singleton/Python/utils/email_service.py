from utils.email_config import EmailConfig

class EmailService:

    @staticmethod
    def send_email(to, message):
        sender = EmailConfig().get_sender_email()

        print("----- EMAIL SENT -----")
        print(f"From: {sender}")
        print(f"To: {to}")
        print(f"Message: {message}")
        print("----------------------")
