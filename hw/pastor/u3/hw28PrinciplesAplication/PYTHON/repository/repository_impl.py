from repository.i_repository import IRepository
from config.mongo_connection import MongoConnection
from model.generic_entity import GenericEntity

class RepositoryImpl(IRepository):
    def __init__(self, collection_name):
        self.db = MongoConnection().get_database()
        self.collection = self.db[collection_name]
        self.type_name = collection_name

    def create(self, entity):
        self.collection.insert_one(entity.to_dict())

    def read_all(self):
        cursor = self.collection.find()
        results = []
        for doc in cursor:
            entity = GenericEntity(self.type_name)
            entity.set_id(str(doc["_id"]))
            
            for key, value in doc.items():
                if key != "_id":
                    entity.set_data(key, value)
            
            results.append(entity)
        return results