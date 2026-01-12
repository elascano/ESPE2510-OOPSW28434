from pymongo import MongoClient

class ProfessorModel:
    def __init__(self):
        uri = "mongodb+srv://Arelys:Arelys1234@cluster0.3u6ujwz.mongodb.net/" 
        self.client = MongoClient(uri)
        self.db = self.client["RecoverData"]
        self.collection = self.db["Professors"] 

    def get_all(self):
        """Recupera todos los profesores de la colección."""
        return list(self.collection.find())