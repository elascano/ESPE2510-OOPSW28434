from pymongo import MongoClient

class MongoDBConnection:
    _db = None

    @staticmethod
    def connect():
        try:
            uri = "mongodb+srv://daniel:daniel2007@cluster0.v7buh9x.mongodb.net/?retryWrites=true&w=majority"
            client = MongoClient(uri)
            MongoDBConnection._db = client["ContactsDB"]
            print("Conectado a MongoDB correctamente")
        except Exception as e:
            print("Error al conectar:", e)

    @staticmethod
    def get_collection(name):
        if MongoDBConnection._db is None:
            MongoDBConnection.connect()
        return MongoDBConnection._db[name]