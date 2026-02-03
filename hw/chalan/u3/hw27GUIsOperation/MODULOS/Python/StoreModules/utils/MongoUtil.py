from pymongo import MongoClient

class MongoUtil:
    client = MongoClient(
        "mongodb+srv://kevin:kevin2001@cluster0.oxinj5p.mongodb.net/contac"
    )
    db = client.get_database()

    @staticmethod
    def getCollection(name):
        return MongoUtil.db[name]
