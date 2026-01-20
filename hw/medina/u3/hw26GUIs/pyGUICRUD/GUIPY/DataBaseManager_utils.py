from pymongo import MongoClient, errors
from Product_model import Product # Importamos nuestro modelo

class DatabaseManager:
    def __init__(self, connection_uri, db_name, collection_name):
        self.uri = connection_uri
        self.db_name = db_name
        self.collection_name = collection_name
        self.client = None
        self.db = None
        self.collection = None
        self._connect_to_db()

    def _connect_to_db(self):
        try:
            self.client = MongoClient(self.uri)
            self.db = self.client[self.db_name]
            self.collection = self.db[self.collection_name]
            print(f"-> DB Connected: {self.db_name}")
        except errors.ConnectionFailure as e:
            print(f"-> Connection Error: {e}")

    def find_product_by_id(self, product_id):
        """Busca y retorna una INSTANCIA de Product o None."""
        data = self.collection.find_one({"id": product_id}, {"_id": 0})
        if data:
            # Convertimos el diccionario de Mongo a Objeto Python
            return Product.from_dict(data)
        return None

    def insert_product(self, product_obj):
        """Recibe un objeto Product y lo guarda."""
        if self.find_product_by_id(product_obj.id):
            return False 

        try:
            # Convertimos el Objeto a Diccionario para Mongo
            self.collection.insert_one(product_obj.to_dict())
            return True
        except Exception as e:
            print(f"Error inserting: {e}")
            return False

    def update_product(self, product_obj):
        """Actualiza usando el ID del objeto Product recibido."""
        if not self.find_product_by_id(product_obj.id):
            return False

        try:
            self.collection.update_one(
                {"id": product_obj.id}, 
                {"$set": product_obj.to_dict()}
            )
            return True
        except Exception as e:
            print(f"Error updating: {e}")
            return False

    def delete_product(self, product_id):
        """Elimina por ID."""
        if not self.find_product_by_id(product_id):
            return False
        try:
            self.collection.delete_one({"id": product_id})
            return True
        except Exception as e:
            print(f"Error deleting: {e}")
            return False

    def get_all_products(self):
        """Retorna una lista de OBJETOS Product."""
        try:
            cursor = self.collection.find({}, {"_id": 0})
            # Convertimos cada resultado de Mongo en un Objeto Product
            return [Product.from_dict(doc) for doc in cursor]
        except Exception as e:
            print(f"Error loading: {e}")
            return []