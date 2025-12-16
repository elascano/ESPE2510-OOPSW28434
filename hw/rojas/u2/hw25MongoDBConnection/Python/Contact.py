from pymongo import MongoClient
import certifi

class ContactModel:
    def __init__(self):
        try:
            URI = "mongodb+srv://Josue:Josue2006@cluster0.da07rsq.mongodb.net/?retryWrites=true&w=majority"
            self.client = MongoClient(URI, tlsCAFile=certifi.where())
            self.db = self.client['ConectionMongoDB']
            self.collection = self.db['PyContactsBook']
            print("Database Connected Successfully")
        except Exception as e:
            print(f"Database Connection Error: {e}")
            self.collection = None

    def save(self, contactData):
        if self.collection is not None:
            self.collection.insert_one(contactData)
            return True
        return False