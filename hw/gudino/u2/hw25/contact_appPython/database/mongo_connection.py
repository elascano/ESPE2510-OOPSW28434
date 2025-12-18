from pymongo import MongoClient

class MongoConnection:
    def __init__(self,
                 uri="mongodb+srv://Bryan:Bryan2000@cluster0.sx9cpnq.mongodb.net/?retryWrites=true&w=majority",
                 db_name="ContactsDB"):
        # Conexión simple; si tu contraseña es distinta cámbiala en la URI arriba.
        self.client = MongoClient(uri)
        self.db = self.client[db_name]

    def get_collection(self, name):
        return self.db[name]
