from typing import Dict, Any
from tabulate import tabulate

from models.contact_model import ContactType
from services.contact_service import ContactService
from exceptions.database_exceptions import DatabaseException, NotFoundException

class ContactController:
    def __init__(self, service: ContactService):
        self.service = service
    
    def display_all_contacts(self) -> None:
        try:
            contacts = self.service.get_all_contacts()
            
            if not contacts:
                print("\nNo contacts in database")
                return
            
            table_data = self.service.prepare_contacts_for_display(contacts)
            headers = ["ID", "Full Name", "Email", "Type", "Discount", 
                      "Total Sale", "Final Price", "Savings"]
            
            print("\n" + "="*100)
            print("CONTACTS LIST")
            print("="*100)
            print(tabulate(table_data, headers=headers, tablefmt="grid"))
            print("="*100)
            
            self.display_summary()
            
        except DatabaseException as e:
            print(f"\nError getting contacts: {e}")
    
    def display_contact_by_id(self, contact_id: str) -> None:
        try:
            contact_id_int = int(contact_id)
            contact = self.service.get_contact_by_id(contact_id_int)
            final_price = contact.calculate_final_price()
            savings = contact.totalSale - final_price
            
            print("\n" + "="*50)
            print("CONTACT DETAILS")
            print("="*50)
            print(f"ID: {contact.id}")
            print(f"Full Name: {contact.fullName}")
            print(f"Email: {contact.email}")
            print(f"Type: {contact.type.value}")
            print(f"Discount: {contact.discount}%")
            print(f"Total Sale: ${contact.totalSale:,.2f}")
            print(f"Final Price: ${final_price:,.2f}")
            print(f"Savings: ${savings:,.2f}")
            print("="*50)
            
        except ValueError:
            print("\nID must be an integer")
        except NotFoundException as e:
            print(f"\n{e}")
        except DatabaseException as e:
            print(f"\nError getting contact: {e}")
    
    def display_summary(self) -> None:
        try:
            distribution = self.service.get_type_distribution()
            
            if not distribution:
                return
            
            print("\nSTATISTICAL SUMMARY")
            print("-" * 40)
            
            for type_str, count, percentage in distribution:
                print(f"{type_str}: {count} contacts ({percentage})")
            
            print("-" * 40)
            
        except DatabaseException as e:
            print(f"\nError getting summary: {e}")