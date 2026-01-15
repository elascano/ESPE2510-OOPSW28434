from utils.mongodb_manager import MongoDBManager

class GenericController:
    def __init__(self):
        self.mongo = MongoDBManager()

    def get_all(self):
        return self.mongo.find_all()

    def get_by_id(self, entity_id):
        return self.mongo.find_by_id(entity_id)

    def create(self, entity):
        return self.mongo.insert(entity)

    def update(self, entity_id, entity):
        return self.mongo.update(entity_id, entity)

    def delete(self, entity_id):
        return self.mongo.delete(entity_id)