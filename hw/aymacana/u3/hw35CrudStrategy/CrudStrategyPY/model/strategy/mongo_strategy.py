from typing import List
from model.customer import Customer
from model.strategy.crud_strategy import CrudStrategy
from utils.mongo_connection import MongoConnection

class MongoStrategy(CrudStrategy):
    COLLECTION_NAME = "customers"
    
    def add(self, customer: Customer) -> bool:
        try:
            collection = MongoConnection.get_collection(self.COLLECTION_NAME)
            
            existing = collection.find_one({"id": customer.id})
            if existing:
                print(f"Error: ID {customer.id} already exists in MongoDB")
                return False
            
            collection.insert_one(customer.to_dict())
            return True
            
        except Exception as e:
            print(f"Error adding customer to MongoDB: {e}")
            return False
    
    def delete(self, customer_id: int) -> bool:
        try:
            collection = MongoConnection.get_collection(self.COLLECTION_NAME)
            result = collection.delete_one({"id": customer_id})
            return result.deleted_count > 0
            
        except Exception as e:
            print(f"Error deleting from MongoDB: {e}")
            return False
    
    def update(self, customer_id: int, customer: Customer) -> bool:
        try:
            collection = MongoConnection.get_collection(self.COLLECTION_NAME)
            
            customer_dict = customer.to_dict()
            customer_dict["id"] = customer_id
            
            result = collection.update_one(
                {"id": customer_id},
                {"$set": customer_dict}
            )
            return result.modified_count > 0
            
        except Exception as e:
            print(f"Error updating in MongoDB: {e}")
            return False
    
    def read_all(self) -> List[Customer]:
        try:
            collection = MongoConnection.get_collection(self.COLLECTION_NAME)
            customers_data = collection.find()
            
            customers = []
            for data in customers_data:
                customers.append(Customer.from_dict(data))
            
            return customers
            
        except Exception as e:
            print(f"Error reading from MongoDB: {e}")
            return []
    
    def read_by_id(self, customer_id: int) -> Customer:
        try:
            collection = MongoConnection.get_collection(self.COLLECTION_NAME)
            data = collection.find_one({"id": customer_id})
            
            if data:
                return Customer.from_dict(data)
            return None
            
        except Exception as e:
            print(f"Error reading customer by ID from MongoDB: {e}")
            return None
    
    def get_format_name(self) -> str:
        return "MongoDB"