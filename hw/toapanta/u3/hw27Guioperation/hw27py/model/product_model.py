from pymongo import MongoClient

class ProductModel:
    def __init__(self):
        self.url = "mongodb+srv://Cesar:Cesar2006@cluster0.tgbv2qc.mongodb.net/"
        self.client = MongoClient(self.url)
        self.db = self.client['factura_db']
        self.collection = self.db['ProductsJava']

    def guardar_producto(self, producto):
        return self.collection.insert_one(producto)

    def obtener_todos(self):
        return list(self.collection.find())
    