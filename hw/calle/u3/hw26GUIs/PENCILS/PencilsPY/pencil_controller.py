from database_manager import DatabaseManager

class PencilController:
    def __init__(self):
        db = DatabaseManager.get_database()
        self.collection = db['pencils']

    def create(self, pencil):
        self.collection.insert_one(pencil.__dict__)

    def read_all(self):
        return list(self.collection.find())

    def update(self, pencil_id, data):
        self.collection.update_one({"id": pencil_id}, {"$set": data})

    def delete(self, pencil_id):
        self.collection.delete_one({"id": pencil_id})