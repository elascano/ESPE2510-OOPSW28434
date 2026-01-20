from pymongo import MongoClient

class MongoDBUtil:
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(MongoDBUtil, cls).__new__(cls)
            cls._instance._connect()
        return cls._instance

    def _connect(self):
        self.client = MongoClient(
            "mongodb+srv://kevin:kevin2001@cluster0.oxinj5p.mongodb.net/contact"
        )
        self.db = self.client["contact"]
        self.collection = self.db["photographers"]
        print("Connected to MongoDB Atlas")

    def save(self, photographer):
        self.collection.insert_one({
            "name": photographer.name,
            "specialty": photographer.specialty,
            "experience": photographer.experience,
            "hourlyRate": photographer.hourly_rate
        })
