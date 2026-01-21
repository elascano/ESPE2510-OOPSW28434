from pymongo import MongoClient

class AlarmConfig:
    __instance = None

    def __init__(self):
        if AlarmConfig.__instance is not None:
            raise Exception("Singleton already exists")

        self.client = MongoClient("mongodb+srv://Mathews:Mathews2007@cluster0.6l9ibfh.mongodb.net/?appName=Cluster0")
        self.db = self.client["SingletonDB"]
        self.collection = self.db["sales_configuration"]

        config = self.collection.find_one({"_id": "alarm_config"})

        if config is None:
            self.minimumStock = 10
            self.collection.insert_one({
                "_id": "alarm_config",
                "minimumStock": self.minimumStock
            })
        else:
            self.minimumStock = config["minimumStock"]

        AlarmConfig.__instance = self

    @staticmethod
    def getInstance():
        if AlarmConfig.__instance is None:
            AlarmConfig()
        return AlarmConfig.__instance

    def getMinimumStock(self):
        return self.minimumStock

    def updateMinimumStock(self, newStock: int):
        self.minimumStock = newStock
        self.collection.update_one(
            {"_id": "alarm_config"},
            {"$set": {"minimumStock": newStock}}
        )
