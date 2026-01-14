import sys
from config.database import DatabaseConnection
from repositories.contact_repository import ContactRepository
from services.contact_service import ContactService
from controllers.contact_controller import ContactController
from views.contact_view import ContactView

def setup_dependencies() -> ContactView:
    try:
        database_connection = DatabaseConnection()
        repository = ContactRepository(database_connection)
        service = ContactService(repository)
        controller = ContactController(service)
        view = ContactView(controller)
        
        return view
        
    except Exception as e:
        print(f"Error setting up application: {e}")
        sys.exit(1)

def main():
    
    try:
        view = setup_dependencies()
        view.run()
        
    except KeyboardInterrupt:
        print("\nGoodbye!")
    except Exception as e:
        print(f"\nCritical error: {e}")
        sys.exit(1)

if __name__ == "__main__":
    main()