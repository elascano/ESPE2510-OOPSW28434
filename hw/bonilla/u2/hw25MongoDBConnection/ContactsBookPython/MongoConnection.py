from pymongo import MongoClient

class MongoConnection:
    uri = "mongodb+srv://Arelis:Arelis2006@cluster0.qdn4zsf.mongodb.net/?appName=Cluster0"
    client = None

    @staticmethod
    def get_database():
        if MongoConnection.client is None:
            MongoConnection.client = MongoClient(MongoConnection.uri)
        return MongoConnection.client["ContactsBook"]