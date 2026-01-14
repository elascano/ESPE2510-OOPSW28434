from pymongo import MongoClient

class DB_Connection:
    _URI = "mongodb+srv://Cesar:Cesar2006@cluster0.tgbv2qc.mongodb.net/"
    _DB_NAME = "ResourcesDB"
    
    @staticmethod
    def get_database():
        try:
            client = MongoClient(DB_Connection._URI)
            return client[DB_Connection._DB_NAME]
        except Exception as e:
            print(f"Connection Error: {e}")
            return None