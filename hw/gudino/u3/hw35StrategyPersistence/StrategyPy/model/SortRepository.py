from pymongo import MongoClient

class SortRepository:
    """
    Model – Persistencia (MongoDB)
    """

    def __init__(self):
        self.client = MongoClient(
            "mongodb+srv://Bryan:B2000@cluster0.sx9cpnq.mongodb.net/"
        )
        self.db = self.client["Prueba"]
        self.collection = self.db["Pruebita"]

    def save(self, unsorted, sorted_data, algorithm):
        self.collection.insert_one({
        "unsorted": ",".join(map(str, unsorted)),
        "size": len(unsorted),
        "algorithm": algorithm,
        "sorted": ",".join(map(str, sorted_data))
    })
