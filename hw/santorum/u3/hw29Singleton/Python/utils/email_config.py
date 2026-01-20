import json

class EmailConfig:
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(EmailConfig, cls).__new__(cls)
            cls._instance._load_config()
        return cls._instance

    def _load_config(self):
        try:
            with open("email_config.json", "r") as file:
                data = json.load(file)
                self.sender_email = data["sender_email"]
        except Exception:
            self.sender_email = "default@clinic.com"

    def get_sender_email(self):
        return self.sender_email
