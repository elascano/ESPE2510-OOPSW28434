from controllers.chicken_controller import ChickenController




from views.chicken_view import ChickenView

def main():
    controller = ChickenController()
    view = ChickenView()
    
    while True:
        view.show_menu()
        choice = view.get_menu_choice()
        
        if choice == 1:
            suggested_id = controller.get_next_available_id()
            chicken_data = view.get_chicken_data(suggested_id)
            
            if chicken_data:
                id, name, color, age, is_molting = chicken_data
                if controller.insert_chicken(id, name, color, age, is_molting):
                    view.show_message("Chicken inserted correctly")
                else:
                    view.show_error("The chicken could not be inserted (duplicate ID)")
        
        elif choice == 2: 
            chickens = controller.list_chickens()
            view.show_chickens(chickens)
        
        elif choice == 3:  
            chicken_id = view.get_chicken_id()
            if chicken_id != -1:
                if controller.delete_chicken(chicken_id):
                    view.show_message("Chicken deleted successfully")
                else:
                    view.show_error("No chicken found with that ID")
            else:
                view.show_error("Invalid ID")

        elif choice == 4:
            chicken_id = view.get_chicken_id()
            if chicken_id != -1:
                existing_chicken = controller.get_chicken_by_id(chicken_id)
                if existing_chicken:
                    print(f"Chicken current: {existing_chicken}")
                    print("Enter the new data:")
                    
                    name = input(f"Name [{existing_chicken.name}]: ") or existing_chicken.name
                    color = input(f"Color [{existing_chicken.color}]: ") or existing_chicken.color
                    
                    try:
                        age_input = input(f"Age [{existing_chicken.age}]: ")
                        age = int(age_input) if age_input else existing_chicken.age

                        is_molting_input = input(f"Is molting? [{'true' if existing_chicken.is_molting else 'false'}]: ").lower()
                        is_molting = is_molting_input == 'true' if is_molting_input else existing_chicken.is_molting

                        if controller.update_chicken(chicken_id, name, color, age, is_molting):
                            view.show_message("Chicken updated successfully")
                        else:
                            view.show_error("Error updating chicken")
                    except ValueError:
                        view.show_error("Invalid age")
                else:
                    view.show_error("No chicken found with that ID")
            else:
                view.show_error("Invalid ID")

        elif choice == 5: 
            search_name = view.get_search_name()
            if search_name.strip():
                chickens = controller.find_chickens(search_name)
                view.show_chickens(chickens)
            else:
                view.show_error("Enter a name to search")

        elif choice == 6: 
            view.show_message("Goodbye!")
            break
        
        else:
            view.show_error("Invalid option")

if __name__ == "__main__":
    main()