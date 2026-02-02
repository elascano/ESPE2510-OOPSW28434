from model.crud_context import CrudContext
from model.customer import Customer
from controller.validation_controller import ValidationController
from utils.id_generator import IdGenerator
from typing import List

class CustomerController:
    def __init__(self, storage_type: str = "JSON"):
        self.crud_context = CrudContext(storage_type)
    
    def set_storage_type(self, storage_type: str):
        self.crud_context.set_storage_strategy(storage_type)
    
    def get_current_storage_type(self) -> str:
        return self.crud_context.get_current_storage_type()
    
    def create_customer(self, name: str, apartment_number: str, email: str, phone: str) -> Customer:
        validation_errors = ValidationController.validate_all_fields(
            name, apartment_number, email, phone)
        
        if validation_errors:
            raise ValueError(f"Validation errors:\n{validation_errors}")
        
        customer_id = IdGenerator.generate_unique_id()
        
        return Customer(customer_id, name, apartment_number, email, phone)
    
    def add_customer(self, customer: Customer) -> bool:
        return self.crud_context.add_customer(customer)
    
    def add_customer_from_fields(self, name: str, apartment_number: str, email: str, phone: str) -> bool:
        try:
            customer = self.create_customer(name, apartment_number, email, phone)
            return self.add_customer(customer)
        except ValueError as e:
            raise e
        except Exception as e:
            print(f"Error adding customer: {e}")
            return False
    
    def delete_customer(self, customer_id: int) -> bool:
        return self.crud_context.delete_customer(customer_id)
    
    def update_customer(self, customer_id: int, name: str, apartment_number: str, 
                       email: str, phone: str) -> bool:
        try:
            validation_errors = ValidationController.validate_all_fields(
                name, apartment_number, email, phone)
            
            if validation_errors:
                raise ValueError(f"Validation errors:\n{validation_errors}")
            
            updated_customer = Customer(customer_id, name, apartment_number, email, phone)
            return self.crud_context.update_customer(customer_id, updated_customer)
        except ValueError as e:
            raise e
        except Exception as e:
            print(f"Error updating customer: {e}")
            return False
    
    def get_all_customers(self) -> List[Customer]:
        return self.crud_context.get_all_customers()
    
    def get_customer_by_id(self, customer_id: int) -> Customer:
        return self.crud_context.get_customer_by_id(customer_id)
    
    def get_all_customers_formatted(self) -> str:
        customers = self.get_all_customers()
        result = f"=== CUSTOMERS ({self.get_current_storage_type()}) ===\n"
        
        for customer in customers:
            result += f"{customer}\n"
        
        result += f"Total: {len(customers)} customers\n"
        return result