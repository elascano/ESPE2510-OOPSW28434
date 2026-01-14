from pymongo import MongoClient

class DBProductos:
    def __init__(self):
        self.client = MongoClient("mongodb+srv://Josue:Josue2006@cluster0.da07rsq.mongodb.net/?appName=Cluster0")
        self.db = self.client["tienda_db"]
        self.collection = self.db["productos"]

    def id_exists(self, product_id):
        return self.collection.find_one({"_id": product_id}) is not None

    def find_by_id(self, product_id):
        return self.collection.find_one({"_id": product_id})

    def save(self, product_dict):
        self.collection.insert_one(product_dict)

    def delete(self, product_id):
        if self.id_exists(product_id):
            self.collection.delete_one({"_id": product_id})
            return True
        return False

    def update(self, product_id, new_data_dict):
        if self.id_exists(product_id):
            self.collection.update_one({"_id": product_id}, {"$set": new_data_dict})
            return True
        return False

    def get_all(self): # <--- ESTE ES EL NOMBRE QUE BUSCA EL ERROR
        return list(self.collection.find())