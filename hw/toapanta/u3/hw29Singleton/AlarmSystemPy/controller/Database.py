from pymongo import MongoClient
import ssl

class Database:
    _mongo_client = None
    _db = None
    _URI = "mongodb+srv://Arelys:Arelys1234@cluster0.3u6ujwz.mongodb.net/"

    @staticmethod
    def get_database():
        if Database._mongo_client is None:
            # Solución al error de certifi: Ignorar verificación de SSL local
            Database._mongo_client = MongoClient(
                Database._URI,
                tlsAllowInvalidCertificates=True
            )
            Database._db = Database._mongo_client["Singleton"]
        return Database._db