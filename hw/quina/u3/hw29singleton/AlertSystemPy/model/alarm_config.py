from utils.mongo_connection import MongoConnection

class AlarmConfig:
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(AlarmConfig, cls).__new__(cls)

            db = MongoConnection.get_database()
            cls._instance.collection = db["SalesConfiguration"]
            

            config = cls._instance.collection.find_one()
            cls._instance.minimum_stock = config["minimumStock"] if config else 10
        return cls._instance

    def update_minimum_stock(self, new_stock):
        self.minimum_stock = int(new_stock)
        self.collection.update_one({}, {"$set": {"minimumStock": self.minimum_stock}}, upsert=True)

    def get_minimum_stock(self):
        return self.minimum_stock