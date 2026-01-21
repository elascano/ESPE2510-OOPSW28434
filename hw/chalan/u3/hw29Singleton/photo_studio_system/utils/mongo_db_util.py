from pymongo import MongoClient
from model.photographer import Photographer

class MongoDBUtil:
    _instance = None #sta

    def __new__(cls): # C
        if cls._instance is None:
            # I
            cls._instance = super(MongoDBUtil, cls).__new__(cls)
            cls._instance._connect()
        return cls._instance # Get Instance

    def _connect(self): 
        try:
            
            self.client = MongoClient("mongodb://localhost:27017/")
            self.db = self.client["contact"]
            self.collection = self.db["photographers"]
            print("Connected to MongoDB")
        except Exception as e:
            print(f"Error connecting: {e}")

    def save(self, photographer):
        self.collection.insert_one({
            "name": photographer.name,
            "specialty": photographer.specialty,
            "experience": photographer.experience,
            "hourlyRate": photographer.hourly_rate
        })
        print("Saved in Mongo")

    def get_all(self):
        photographers = []
        try:
            cursor = self.collection.find()
            for doc in cursor:
                p = Photographer(
                    doc["name"],
                    doc["specialty"],
                    doc["experience"],
                    doc["hourlyRate"]
                )
                photographers.append(p)
        except Exception as e:
            print(f"Error fetching: {e}")
        return photographers