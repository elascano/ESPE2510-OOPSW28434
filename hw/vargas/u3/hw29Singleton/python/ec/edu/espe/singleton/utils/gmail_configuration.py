from .mongo_manager import MongoManager

class GmailConfiguration:

    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(GmailConfiguration, cls).__new__(cls)
            cls._instance._load_configuration_from_database()
        return cls._instance

    def _load_configuration_from_database(self):
        self.sender_email = None
        self.app_password = None
        self.smtp_host = "smtp.gmail.com"
        self.smtp_port = 465
        
        mongo_manager = MongoManager()
        results = mongo_manager.find("system_config", {})

        if results:
            config_doc = results[0]
            self.sender_email = config_doc.get("sender_email")
            self.app_password = config_doc.get("app_password")
            self.smtp_host = config_doc.get("smtp_host", "smtp.gmail.com")
            self.smtp_port = int(config_doc.get("smtp_port", 465))
        else:
            print("Advertencia: No se encontró configuración de email en la base de datos.")

    @classmethod
    def get_instance(cls):
        return cls()