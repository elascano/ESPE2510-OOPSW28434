from controller.MongoConnection import MongoConnection
from model.Toy import Toy

class MongoCrud:

    def __init__(self):
        db = MongoConnection.get_database()
        self.collection = db["toys"]  


    def create(self, toy: Toy):
        toy.price_iva = toy.calculate_price_iva()
        self.collection.insert_one({
            "id": toy.id,
            "name": toy.name,
            "price": toy.price,
            "priceIva": toy.price_iva
        })


    def read_by_id(self, id):
        d = self.collection.find_one({"id": id})
        if d is None:
            return None

        return Toy(
            d["id"],
            d["name"],
            d["price"],
            d["priceIva"]
        )


    def update(self, toy: Toy):
        toy.price_iva = toy.calculate_price_iva()
        self.collection.update_one(
            {"id": toy.id},
            {"$set": {
                "name": toy.name,
                "price": toy.price,
                "priceIva": toy.price_iva
            }}
        )


    def delete(self, id):
        self.collection.delete_one({"id": id})


    def read_all(self):
        toys = []
        cursor = self.collection.find()

        for d in cursor:
            toy = Toy(
                d["id"],
                d["name"],
                d["price"],
                d["priceIva"]
            )
            toys.append(toy)

        return toys
