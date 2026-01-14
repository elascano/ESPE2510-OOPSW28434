import sys
from typing import Optional, Dict, Any
from models.contact_model import ContactType
from controllers.contact_controller import ContactController

class ContactView:
    def __init__(self, controller: ContactController):
        self.controller = controller
    
    def display_menu(self) -> None:
        print("\n" + "="*50)
        print("CONTACT MANAGEMENT SYSTEM")
        print("="*50)
        print("1. View all contacts")
        print("2. Search contact by ID")
        print("3. Exit")
        print("="*50)
    
    def get_user_choice(self) -> str:
        while True:
            try:
                choice = input("\nSelect an option (1-3): ").strip()
                if choice in ['1', '2', '3']:
                    return choice
                else:
                    print("Please select a valid option (1-3)")
            except KeyboardInterrupt:
                print("\nGoodbye!")
                sys.exit(0)
            except Exception as e:
                print(f"Error: {e}")
    
    def get_contact_id_input(self) -> Optional[str]:
        try:
            contact_id = input("\nEnter contact ID: ").strip()
            if not contact_id:
                print("ID not provided")
                return None
            return contact_id
        except KeyboardInterrupt:
            print("\nGoodbye!")
            sys.exit(0)
    
    def run(self) -> None:
        print("Starting Contact Management System...")
        
        while True:
            try:
                self.display_menu()
                choice = self.get_user_choice()
                
                if choice == '1':
                    self.controller.display_all_contacts()
                
                elif choice == '2':
                    contact_id = self.get_contact_id_input()
                    if contact_id:
                        self.controller.display_contact_by_id(contact_id)
                
                elif choice == '3':
                    print("\nThank you!!")
                    break
                
                input("\nPress Enter to continue...")
                
            except KeyboardInterrupt:
                print("\nGoodbye!")
                break
            except Exception as e:
                print(f"\nUnexpected error: {e}")
                input("\nPress Enter to continue...")