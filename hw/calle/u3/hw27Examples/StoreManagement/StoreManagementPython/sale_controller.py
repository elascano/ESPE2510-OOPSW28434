from pymongo import MongoClient
from sale_model import Sale

class SaleController:
    def __init__(self):
        self.uri = "mongodb+srv://Emily:Emily2006@cluster0.ynnit6l.mongodb.net/"
        self.client = MongoClient(self.uri)
        self.db = self.client['StoreDB']
        self.collection = self.db['Sales']

    def create(self, name, price, qty):
        new_sale = Sale(name, price, qty)
        return self.collection.insert_one(new_sale.to_dict())

    def get_all(self):
        return list(self.collection.find())

    def update(self, original_name, new_name, price, qty):
        updated_data = Sale(new_name, price, qty)
        return self.collection.update_one(
            {"productName": original_name}, 
            {"$set": updated_data.to_dict()}
        )

    def delete(self, name):
        return self.collection.delete_one({"productName": name})

    def find_by_name(self, name):
        return self.collection.find_one({"productName": name})