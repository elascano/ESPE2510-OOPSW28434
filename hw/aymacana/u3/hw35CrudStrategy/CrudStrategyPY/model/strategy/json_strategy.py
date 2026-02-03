import json
import os
from typing import List
from model.customer import Customer
from model.strategy.crud_strategy import CrudStrategy
from utils.file_manager import FileManager

class JsonStrategy(CrudStrategy):
    FILE_PATH = "customers.json"
    
    def add(self, customer: Customer) -> bool:
        try:
            customers = self.read_all()
            
            for c in customers:
                if c.id == customer.id:
                    print(f"Error: ID {customer.id} already exists in JSON")
                    return False
            
            customers.append(customer)
            
            customers_dict = [c.to_dict() for c in customers]
            json_data = json.dumps(customers_dict, indent=4)
            
            FileManager.save_to_file(self.FILE_PATH, json_data)
            return True
            
        except Exception as e:
            print(f"Error adding customer to JSON: {e}")
            return False
    
    def delete(self, customer_id: int) -> bool:
        try:
            customers = self.read_all()
            initial_count = len(customers)
            
            customers = [c for c in customers if c.id != customer_id]
            
            if len(customers) < initial_count:
                customers_dict = [c.to_dict() for c in customers]
                json_data = json.dumps(customers_dict, indent=4)
                FileManager.save_to_file(self.FILE_PATH, json_data)
                return True
            
            return False
            
        except Exception as e:
            print(f"Error deleting from JSON: {e}")
            return False
    
    def update(self, customer_id: int, customer: Customer) -> bool:
        try:
            customers = self.read_all()
            updated = False
            
            for i, c in enumerate(customers):
                if c.id == customer_id:
                    customer.id = customer_id  
                    customers[i] = customer
                    updated = True
                    break
            
            if updated:
                customers_dict = [c.to_dict() for c in customers]
                json_data = json.dumps(customers_dict, indent=4)
                FileManager.save_to_file(self.FILE_PATH, json_data)
            
            return updated
            
        except Exception as e:
            print(f"Error updating in JSON: {e}")
            return False
    
    def read_all(self) -> List[Customer]:
        try:
            content = FileManager.read_file(self.FILE_PATH)
            if not content:
                return []
            
            customers_dict = json.loads(content)
            customers = [Customer.from_dict(c) for c in customers_dict]
            return customers
            
        except Exception as e:
            print(f"Error reading JSON: {e}")
            return []
    
    def read_by_id(self, customer_id: int) -> Customer:
        customers = self.read_all()
        for customer in customers:
            if customer.id == customer_id:
                return customer
        return None
    
    def get_format_name(self) -> str:
        return "JSON"