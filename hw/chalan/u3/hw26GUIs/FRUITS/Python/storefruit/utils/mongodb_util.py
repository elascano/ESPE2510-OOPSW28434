from pymongo import MongoClient


class MongoDBUtil:
    _client = None
    _database = None

    CONNECTION_STRING = "mongodb+srv://kevin:kevin2001@cluster0.oxinj5p.mongodb.net/contac"
    DATABASE_NAME = "fruit_store"

    @staticmethod
    def get_database():
        if MongoDBUtil._database is None:
            MongoDBUtil._client = MongoClient(MongoDBUtil.CONNECTION_STRING)
            MongoDBUtil._database = MongoDBUtil._client[MongoDBUtil.DATABASE_NAME]
            MongoDBUtil._create_collection_if_not_exists()
        return MongoDBUtil._database

    @staticmethod
    def _create_collection_if_not_exists():
        if "fruits" not in MongoDBUtil._database.list_collection_names():
            collection = MongoDBUtil._database["fruits"]

            collection.insert_many([
                {"name": "Apple", "price": 1.5, "stock": 20},
                {"name": "Banana", "price": 0.8, "stock": 30},
                {"name": "Orange", "price": 1.2, "stock": 25},
                {"name": "Strawberry", "price": 2.0, "stock": 15}
            ])
