class AlertConfigSingleton:
    _instance = None
    _file_path = "alert_config.json"

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(AlertConfigSingleton, cls).__new__(cls)
            cls._instance._alert_days = 3
            cls._instance._load()
        return cls._instance

    @classmethod
    def get_instance(cls):
        return cls()

    def get_alert_days(self) -> int:
        return self._alert_days

    def set_alert_days(self, alert_days: int):
        self._alert_days = alert_days
        self._save()

    def _load(self):
        try:
            with open(self._file_path, "r") as file:
                self._alert_days = int(file.read())
        except (FileNotFoundError, ValueError):
            self._alert_days = 3
            self._save()

    def _save(self):
        try:
            with open(self._file_path, "w") as file:
                file.write(str(self._alert_days))
        except IOError as e:
            print("Error saving configuration:", e)
