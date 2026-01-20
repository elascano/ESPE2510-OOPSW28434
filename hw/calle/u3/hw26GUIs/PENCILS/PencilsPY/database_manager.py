from pymongo import MongoClient

class DatabaseManager:
    @staticmethod
    def get_database():
        uri = "mongodb+srv://Emily:Emily2006@cluster0.ynnit6l.mongodb.net/"
        client = MongoClient(uri)
        return client['PencilsDB']