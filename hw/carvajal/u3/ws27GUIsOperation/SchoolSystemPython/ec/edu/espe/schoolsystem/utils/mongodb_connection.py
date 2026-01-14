from pymongo import MongoClient

class MongoDBConnection:

    URI = "mongodb+srv://Gabriel:Gabriel2007@cluster0.dgdm9az.mongodb.net/"
    DATABASE_NAME = "school"

    @staticmethod
    def get_database():
        client = MongoClient(MongoDBConnection.URI)
        return client[MongoDBConnection.DATABASE_NAME]
