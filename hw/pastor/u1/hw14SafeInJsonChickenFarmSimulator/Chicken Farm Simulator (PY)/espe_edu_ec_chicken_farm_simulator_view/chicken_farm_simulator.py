class ChickenFarmView:
    def __init__(self, controller):
        self.controller = controller 

    def get_menu_choice(self, prompt="Select an option: "):
        return input(prompt).strip()

    def show_main_menu(self):
        print('\n======================================')
        print(' FARM SIMULATOR MENU')
        print('======================================')
        print('1. Manage Farm ')
        print('2. Simulate Daily Actions ') 
        print('3. Manage Current Farmer')
        print('4. Save Data ')
        print('5. Exit')
        print('======================================')

    def show_coop_management_menu(self):
        print('\n======================================')
        print(' CHICKEN COOP MANAGEMENT ')
        print('======================================')
        print('1. Create Coop')
        print('2. Read/Show Coops ')
        print('3. Edit Coop ')
        print('4. Delete Coop')
        print('5. Back to Main Menu')
        print('======================================')
    
    def show_farmer_management_menu(self):
        print('\n======================================')
        print(' FARMER MANAGEMENT')
        print('======================================')
        print('1. View Current Farmer Info')
        print('2. Edit Farmer Name')
        print('3. Back to Main Menu')
        print('======================================')

    def show_chicken_management_menu(self, coop_id):
        print(f'\n======================================')
        print(f' CHICKEN MANAGEMENT - COOP ID {coop_id}')
        print('======================================')
        print('1. Create Chicken(s)')
        print('2. Read/Show Chickens')
        print('3. Edit Chicken')
        print('4. Delete Chicken')
        print('5. Back to Coop Menu')
        print('======================================')

    def request_farmer_name(self, current_name):
        return input(f"Enter new Farmer Name (Current: {current_name}): ")
    
    def request_new_coop_capacity(self):
        return input('Enter maximum capacity for the new coop: ')

    def request_coop_id_for_action(self, action_name):
        return input(f"Enter the Coop ID to {action_name}: ")
    
    def request_coop_id_to_manage_chickens(self, coops_list):
        print('\n--- FARMER\'S CHICKEN COOPS ---')
        if not coops_list:
            print("  No coops available.")
        else:
            for coop in coops_list:
                print(f"  {coop}")
        print('-------------------------------')
        return input('Do you want to manage chickens in a coop? (Enter Coop ID or "n" to go back): ')

    def request_new_coop_capacity_value(self, current_capacity):
        return input(f"Enter the new capacity for the Coop (Current: {current_capacity}): ")

    def request_number_of_chickens(self, coop_id, available_space):
        return input(f"How many chickens to add to Coop {coop_id}? (Max: {available_space}): ")
    
    def request_chicken_details(self, current_number, total_count, chicken_id):
        print(f'\n--- Details for Chicken {current_number} of {total_count} (ID: {chicken_id}) ---')
        name = input('Name: ')
        color = input('Color: ')
        age = input('Age (years): ')
        is_molting_input = input('Is it molting (y/n)?: ')
        return {"name": name, "color": color, "age": age, "is_molting_input": is_molting_input}

    def request_chicken_id_to_update(self):
        return input('Enter the ID of the chicken to edit: ')

    def request_chicken_updates(self, chicken):
        print(f'\nEditing Chicken {chicken.get_name()} (ID: {chicken.get_id()}). Leave blank to keep current value.')
        
        name = input(f'New Name (Current: {chicken.get_name()}): ')
        color = input(f'New Color (Current: {chicken.get_color()}): ')
        age = input(f'New Age (Current: {chicken.get_age()}): ')
        is_molting_input = input(f"Is it Molting? (y/n) (Current: {'Yes' if chicken.get_is_molting() else 'No'}): ")
        
        return {"name": name, "color": color, "age": age, "is_molting_input": is_molting_input}

    def request_chicken_id_to_remove(self):
        return input('Enter the ID of the chicken to delete: ')

    def display_message(self, message):
        print(message)

    def display_chickens(self, coop_id, chickens, capacity):
        print(f'\n--- CHICKENS IN COOP {coop_id} ({len(chickens)}/{capacity}) ---')
        if not chickens:
            print('No chickens in this coop.')
        else:
            for chicken in chickens:
                print(f"  {chicken}")
        print('----------------------------------------------------')
    
    def display_farmer_info(self, farmer):
        print('\n======================================')
        print(f'  FARMER INFORMATION: {farmer.get_name()}')
        print('======================================')
        print(f'ID: {farmer.get_id()}')
        print(f'Name: {farmer.get_name()}')
        print(f'Total Coops: {len(farmer.get_coops())}')
        print('======================================')

    def close(self):
        pass