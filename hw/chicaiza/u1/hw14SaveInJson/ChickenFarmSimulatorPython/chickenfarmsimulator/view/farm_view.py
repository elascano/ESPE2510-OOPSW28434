class FarmView:
    def __init__(self, controller):
        self.controller = controller
    
    def show_menu(self):
        """Muestra el menú principal"""
        print("\n" + "="*50)
        print("          CHICKEN FARM SIMULATOR")
        print("="*50)
        print(f"Coops: {self.controller.get_coop_count()} | Chickens: {self.controller.get_chicken_count()}")
        print("-"*50)
        print("1. Create coop")
        print("2. Add chicken to coop")
        print("3. View all coops and chickens")
        print("4. Edit chicken")
        print("5. Delete chicken")
        print("6. Perform chicken action")
        print("7. Exit")
        print("-"*50)
    
    def get_input(self, prompt):
        """Obtiene entrada del usuario"""
        return input(prompt).strip()
    
    def create_coop(self):
        """Interfaz para crear granja"""
        print("\n--- CREATE COOP ---")
        try:
            coop_id = int(self.get_input("Coop ID: "))
            if self.controller.create_coop(coop_id):
                print("Coop created successfully!")
            else:
                print("Error: A coop with that ID already exists")
        except ValueError:
            print("Error: ID must be an integer")
    
    def add_chicken(self):
        """Interfaz para añadir pollo"""
        print("\n--- ADD CHICKEN ---")
        try:
            coop_id = int(self.get_input("Coop ID: "))
            chicken_id = int(self.get_input("Chicken ID: "))
            name = self.get_input("Chicken name: ")
            color = self.get_input("Chicken color: ")
            age = int(self.get_input("Chicken age (months): "))
            molting_input = self.get_input("Is molting? (y/n): ").lower()
            is_molting = molting_input == 'y'
            
            if self.controller.add_chicken_to_coop(coop_id, chicken_id, name, color, age, is_molting):
                print("Chicken added successfully!")
            else:
                print("Error: Could not add chicken (coop not found or duplicate ID)")
        except ValueError:
            print("Error: IDs and age must be integers")
    
    def print_table_row(self, values, column_widths):
        """Imprime una fila de la tabla"""
        row = "|"
        for i, value in enumerate(values):
            row += f" {str(value).ljust(column_widths[i])} |"
        print(row)
    
    def print_table_separator(self, column_widths):
        """Imprime un separador de tabla"""
        separator = "+"
        for width in column_widths:
            separator += "-" * (width + 2) + "+"
        print(separator)
    
    def show_all_coops(self):
        """Muestra todas las granjas y pollos en formato de tabla"""
        print("\n--- COOPS AND CHICKENS ---")
        coops = self.controller.get_all_coops()
        
        if not coops:
            print("No coops registered.")
            return
        
        for coop in coops:
            print(f"\nCOOP ID: {coop.id}")
            print(f"Number of chickens: {len(coop.chickens)}")
            
            if not coop.chickens:
                print("No chickens in this coop")
                continue
            
            # Definir anchos de columnas para la tabla
            column_headers = ["ID", "Name", "Color", "Age", "Molting"]
            column_widths = [4, 15, 10, 5, 7]  # Anchuras mínimas
            
            # Calcular anchuras basadas en el contenido
            for chicken in coop.chickens:
                column_widths[0] = max(column_widths[0], len(str(chicken.id)))
                column_widths[1] = max(column_widths[1], len(chicken.name))
                column_widths[2] = max(column_widths[2], len(chicken.color))
                column_widths[3] = max(column_widths[3], len(str(chicken.age)))
                column_widths[4] = max(column_widths[4], len("Yes" if chicken.is_molting else "No"))
            
            # Imprimir tabla
            self.print_table_separator(column_widths)
            self.print_table_row(column_headers, column_widths)
            self.print_table_separator(column_widths)
            
            for chicken in coop.chickens:
                molting_status = "Yes" if chicken.is_molting else "No"
                row_data = [
                    chicken.id,
                    chicken.name,
                    chicken.color,
                    chicken.age,
                    molting_status
                ]
                self.print_table_row(row_data, column_widths)
            
            self.print_table_separator(column_widths)
    
    def edit_chicken(self):
        """Interfaz para editar pollo"""
        print("\n--- EDIT CHICKEN ---")
        try:
            coop_id = int(self.get_input("Coop ID: "))
            chicken_id = int(self.get_input("Chicken ID to edit: "))
            
            # Verificar si el pollo existe
            coop = self.controller.get_coop(coop_id)
            if not coop:
                print("Error: Coop not found")
                return
            
            chicken = coop.get_chicken(chicken_id)
            if not chicken:
                print("Error: Chicken not found")
                return
            
            print(f"\nEditing chicken: {chicken.name}")
            name = self.get_input(f"New name ({chicken.name}): ") or chicken.name
            color = self.get_input(f"New color ({chicken.color}): ") or chicken.color
            
            age_input = self.get_input(f"New age ({chicken.age}): ")
            age = int(age_input) if age_input else chicken.age
            
            molting_input = self.get_input(f"Is molting? (y/n) [{'y' if chicken.is_molting else 'n'}]: ").lower()
            is_molting = molting_input == 'y' if molting_input else chicken.is_molting
            
            if self.controller.update_chicken(coop_id, chicken_id, name, color, age, is_molting):
                print("Chicken updated successfully!")
            else:
                print("Error: Could not update chicken")
        except ValueError:
            print("Error: IDs and age must be integers")
    
    def delete_chicken(self):
        """Interfaz para eliminar pollo"""
        print("\n--- DELETE CHICKEN ---")
        try:
            coop_id = int(self.get_input("Coop ID: "))
            chicken_id = int(self.get_input("Chicken ID to delete: "))
            
            confirm = self.get_input("Are you sure you want to delete this chicken? (y/n): ").lower()
            if confirm == 'y':
                if self.controller.delete_chicken(coop_id, chicken_id):
                    print("Chicken deleted successfully!")
                else:
                    print("Error: Could not delete chicken (coop or chicken not found)")
            else:
                print("Operation cancelled")
        except ValueError:
            print("Error: IDs must be integers")
    
    def perform_chicken_action(self):
        """Interfaz para realizar acción del pollo"""
        print("\n--- CHICKEN ACTIONS ---")
        try:
            coop_id = int(self.get_input("Coop ID: "))
            chicken_id = int(self.get_input("Chicken ID: "))
            
            print("\nAvailable actions:")
            print("1. Cluck")
            print("2. Wander")
            print("3. Eat")
            print("4. Drink")
            print("5. Poop")
            print("6. Lay Egg")
            
            action_choice = self.get_input("Select an action (1-6): ")
            
            actions_map = {
                '1': 'cluck',
                '2': 'wander',
                '3': 'eat',
                '4': 'drink',
                '5': 'poop',
                '6': 'lay_egg'
            }
            
            if action_choice in actions_map:
                action = actions_map[action_choice]
                print("\n" + "="*30)
                if self.controller.perform_chicken_action(coop_id, chicken_id, action):
                    print("Action performed successfully!")
                else:
                    print("Error: Could not perform action (coop or chicken not found)")
                print("="*30)
            else:
                print("Error: Invalid option")
        except ValueError:
            print("Error: IDs must be integers")
    
    def run(self):
        """Ejecuta la aplicación principal"""
        while True:
            self.show_menu()
            choice = self.get_input("Select an option (1-7): ")
            
            if choice == '1':
                self.create_coop()
            elif choice == '2':
                self.add_chicken()
            elif choice == '3':
                self.show_all_coops()
            elif choice == '4':
                self.edit_chicken()
            elif choice == '5':
                self.delete_chicken()
            elif choice == '6':
                self.perform_chicken_action()
            elif choice == '7':
                print("Thank you for using Chicken Farm Simulator!")
                break
            else:
                print("Error: Invalid option. Please select 1-7.")
            
            input("\nPress Enter to continue...")