from .Database import db
from .ILibrary import ILibrary

class ComputerModel(ILibrary):
    def __init__(self):
        self.collection = db.get_collection("Computers")

    def insert(self, data):
        return self.collection.insert_one(data)

    def get_all(self):
        return list(self.collection.find())
