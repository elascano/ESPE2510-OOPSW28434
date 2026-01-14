# controller/simple_crud.py
from utils.mongodb_connection import MongoDBConnection

class SimpleCrud:
    def __init__(self, collection_name):
        self.collection_name = collection_name
        self.collection = None
    
    def _init_collection(self):
        if self.collection is None:
            db = MongoDBConnection.get_connection()
            self.collection = db[self.collection_name]
            print(f"📁 Repositorio inicializado: {self.collection_name}")
    
    # CREATE
    def create(self, document):
        self._init_collection()
        result = self.collection.insert_one(document)
        print(f"✅ CREADO con ID: {document['id']}")
        return document["id"]
    
    # EXISTS
    def exists(self, doc_id):
        self._init_collection()
        return self.collection.count_documents({"id": doc_id}) > 0
    
    # COUNT
    def count(self):
        self._init_collection()
        return self.collection.count_documents({})
    
    # FIND BY FIELD
    def find_by_field(self, field_name, value):
        self._init_collection()
        return list(self.collection.find({field_name: value}))
    
    # GET MAX ID
    def get_max_id(self):
        self._init_collection()
        pipeline = [
            {
                "$group": {
                    "_id": None,
                    "maxId": {"$max": "$id"}
                }
            }
        ]
        
        result = list(self.collection.aggregate(pipeline))
        
        if result and result[0]["maxId"] is not None:
            return result[0]["maxId"]
        return 0