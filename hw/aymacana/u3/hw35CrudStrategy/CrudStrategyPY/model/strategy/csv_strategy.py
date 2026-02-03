import os
from typing import List
from model.customer import Customer
from model.strategy.crud_strategy import CrudStrategy
from utils.file_manager import FileManager

class CsvStrategy(CrudStrategy):
    FILE_PATH = "customers.csv"
    
    def add(self, customer: Customer) -> bool:
        try:
            existing_customers = self.read_all()
            for c in existing_customers:
                if c.id == customer.id:
                    print(f"Error: ID {customer.id} already exists in CSV")
                    return False
            
            csv_line = customer.to_csv()
            FileManager.append_to_file(self.FILE_PATH, csv_line)
            return True
            
        except Exception as e:
            print(f"Error adding customer to CSV: {e}")
            return False
    
    def delete(self, customer_id: int) -> bool:
        try:
            customers = self.read_all()
            initial_count = len(customers)
            
            customers = [c for c in customers if c.id != customer_id]
            
            if len(customers) < initial_count:
                content = "\n".join([c.to_csv() for c in customers])
                FileManager.save_to_file(self.FILE_PATH, content)
                return True
            
            return False
            
        except Exception as e:
            print(f"Error deleting from CSV: {e}")
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
                content = "\n".join([c.to_csv() for c in customers])
                FileManager.save_to_file(self.FILE_PATH, content)
            
            return updated
            
        except Exception as e:
            print(f"Error updating in CSV: {e}")
            return False
    
    def read_all(self) -> List[Customer]:
        try:
            content = FileManager.read_file(self.FILE_PATH)
            if not content:
                return []
            
            lines = content.strip().split("\n")
            customers = []
            
            for line in lines:
                if line.strip():  
                    customer = Customer.from_csv(line)
                    if customer:
                        customers.append(customer)
            
            return customers
            
        except Exception as e:
            print(f"Error reading CSV: {e}")
            return []
    
    def read_by_id(self, customer_id: int) -> Customer:
        customers = self.read_all()
        for customer in customers:
            if customer.id == customer_id:
                return customer
        return None
    
    def get_format_name(self) -> str:
        return "CSV"