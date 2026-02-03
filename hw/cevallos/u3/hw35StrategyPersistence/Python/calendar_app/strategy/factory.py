from .json_strategy import JsonStorageStrategy
from .csv_strategy import CsvStorageStrategy
from .mongodb_strategy import MongoDBStorageStrategy

class StorageStrategyFactory:
    class StorageType:
        JSON = "json"
        CSV = "csv"
        MONGODB = "mongodb"
    
    @staticmethod
    def create_strategy(storage_type: str):
        if storage_type == StorageStrategyFactory.StorageType.JSON:
            return JsonStorageStrategy()
        elif storage_type == StorageStrategyFactory.StorageType.CSV:
            return CsvStorageStrategy()
        elif storage_type == StorageStrategyFactory.StorageType.MONGODB:
            return MongoDBStorageStrategy()
        else:
            raise ValueError(f"Tipo de almacenamiento no válido: {storage_type}")