from utils.mongo_connection import get_collection
from model.cellphone import Cellphone

class CellphoneController:

    def __init__(self, strategy=None):
        self.collection = get_collection()
        self.strategy = strategy # Usa strategy

    def create(self, cellphone):
        self.collection.insert_one({
            "id": cellphone.id,
            "model": cellphone.model,
            "price": cellphone.price
        })

    def find_all(self):
        return list(self.collection.find({}, {"_id": 0}))

    def find_by_id(self, id_):
        data = self.collection.find_one({"id": id_}, {"_id": 0})
        if data:
            return Cellphone(data["id"], data["model"], data["price"])
        return None

    def update(self, cellphone):
        if self.strategy:
            self.strategy.update(self.collection, cellphone) # Aplica strategy
        else:
            self.collection.update_one(
                {"id": cellphone.id},
                {"$set": {"model": cellphone.model, "price": cellphone.price}}
            )

    def delete(self, id_):
        self.collection.delete_one({"id": str(id_)}) # Borra mongo