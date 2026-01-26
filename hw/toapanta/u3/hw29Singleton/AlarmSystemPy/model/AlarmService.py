from controller.Database import Database

class AlarmService:
    _instance = None
    _minStock = 0

    def __init__(self):
        if AlarmService._instance is not None:
            raise Exception("Use get_instance()")
        self._load_from_db()

    @classmethod
    def get_instance(cls):
        if cls._instance is None:
            cls._instance = AlarmService()
        return cls._instance

    def _load_from_db(self):
        db = Database.get_database()
        config = db["Config"].find_one({"type": "alarm_config"})
        if not config:
            db["Config"].insert_one({"type": "alarm_config", "minStock": 10})
            self._minStock = 10
        else:
            self._minStock = config["minStock"]

    def update_min_stock(self, new_value):
        self._minStock = new_value
        db = Database.get_database()
        db["Config"].update_one(
            {"type": "alarm_config"},
            {"$set": {"minStock": new_value}}
        )

    def is_low_stock(self, stock):
        return stock < self._minStock

    def get_min_stock(self):
        return self._minStock