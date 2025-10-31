import json
import os
from espe_edu_ec_chicken_farm_simulator_model.chicken import Chicken
from espe_edu_ec_chicken_farm_simulator_model.chicken_farmer import ChickenFarmer
from espe_edu_ec_chicken_farm_simulator_model.chicken_coops import ChickenCoop
from espe_edu_ec_chicken_farm_simulator_view.chicken_farm_simulator import ChickenFarmView

class ChickenFarmController:
    def __init__(self):
        self.view = ChickenFarmView(self)
        self.running = True
        self.farmer = None
        self.chicken_id_counter = 1
        self.load_data_from_json()

    def start(self):
        while self.running:
            self.view.show_main_menu()
            choice = self.view.get_menu_choice()
            self.handle_main_menu(choice)
    
    def handle_main_menu(self, choice):
        if choice == '1':
            self.manage_coops_loop()
        elif choice == '2':
            self.simulate_coop_day()
        elif choice == '3':
            self.manage_farmer_loop()
        elif choice == '4':
            self.save_data_to_json()
        elif choice == '5':
            self.view.display_message('Leaving the program, bye')
            self.running = False
            self.view.close()
        else:
            self.view.display_message('Invalid option. Please try again.')

    def manage_coops_loop(self):
        coop_running = True
        while coop_running:
            self.view.show_coop_management_menu()
            choice = self.view.get_menu_choice()
            
            if choice == '1':
                self.request_new_coop_capacity()
            elif choice == '2':
                self.read_coops()
            elif choice == '3':
                self.request_coop_id_to_update()
            elif choice == '4':
                self.request_coop_id_to_remove()
            elif choice == '5':
                coop_running = False
            else:
                self.view.display_message('Invalid option. Please try again.')

    def manage_farmer_loop(self):
        farmer_running = True
        while farmer_running:
            self.view.show_farmer_management_menu()
            choice = self.view.get_menu_choice()
            
            if choice == '1':
                self.read_farmer_info()
            elif choice == '2':
                self.request_farmer_name_update()
            elif choice == '3':
                farmer_running = False
            else:
                self.view.display_message('Invalid option. Please try again.')

    def manage_chicken_loop(self, coop):
        chicken_running = True
        coop_id = coop.get_coop_coop_number()
        
        while chicken_running:
            self.view.show_chicken_management_menu(coop_id)
            choice = self.view.get_menu_choice()

            if choice == '1':
                self.request_number_of_chickens(coop)
            elif choice == '2':
                self.read_chickens(coop)
            elif choice == '3':
                self.request_chicken_id_to_update(coop)
            elif choice == '4':
                self.request_chicken_id_to_remove(coop)
            elif choice == '5':
                chicken_running = False
            else:
                self.view.display_message('Invalid option. Please try again.')

    def read_farmer_info(self):
        self.view.display_farmer_info(self.farmer)

    def request_farmer_name_update(self):
        new_name = self.view.request_farmer_name(self.farmer.get_name())
        trimmed_name = new_name.strip()
        if trimmed_name:
            self.farmer.set_name(trimmed_name)
            self.view.display_message(f"Farmer name updated {trimmed_name}")
        else:
            self.view.display_message('Name not modified')

    def request_new_coop_capacity(self):
        capacity_str = self.view.request_new_coop_capacity()
        try:
            num_capacity = int(capacity_str.strip())
            if num_capacity <= 0:
                raise ValueError
            
            new_coop = self.farmer.add_coop(num_capacity)
            self.view.display_message(f"Chicken coop ID {new_coop.get_coop_coop_number()} created with the capacity of {num_capacity} chickens.")
        except ValueError:
            self.view.display_message('Invalid option. Please try again.')

    def read_coops(self):
        coops = self.farmer.get_coops()
        if not coops:
            self.view.display_message('There are no registered chicken coops')
            return

        coop_id_input = self.view.request_coop_id_to_manage_chickens(coops)
        
        if coop_id_input.strip().lower() == 'n':
            return

        try:
            id = int(coop_id_input.strip())
            coop = self.farmer.find_coop(id)

            if coop:
                self.manage_chicken_loop(coop) 
            else:
                self.view.display_message(f"Chicken coop ID {id} not found")
        except ValueError:
            self.view.display_message("Invalid ID format.")


    def request_coop_id_to_update(self):
        if not self.farmer.get_coops():
            self.view.display_message('There are no chicken coops to edit')
            return

        coop_id_str = self.view.request_coop_id_for_action('edit')
        try:
            id = int(coop_id_str.strip())
            coop = self.farmer.find_coop(id)

            if not coop:
                self.view.display_message(f"Chicken coop ID {id} not found")
                return

            new_capacity_str = self.view.request_new_coop_capacity_value(coop.get_capacity())
            num_capacity = int(new_capacity_str.strip())
            current_chickens = len(coop.get_chickens())

            if num_capacity < current_chickens:
                self.view.display_message(f"The new capacity must be greater than or equal to {current_chickens}")
                return

            self.farmer.update_coop(id, {'capacity': num_capacity})
            self.view.display_message(f"Chicken coop ID {id} update to capacity {num_capacity}.")

        except ValueError:
            self.view.display_message("Invalid number entered.")

    def request_coop_id_to_remove(self):
        if not self.farmer.get_coops():
            self.view.display_message('There are no chicken coops to erase')
            return
        
        coop_id_str = self.view.request_coop_id_for_action('delete')
        try:
            id = int(coop_id_str.strip())
            if self.farmer.remove_coop(id):
                self.view.display_message(f"Chicken coop ID {id} erased")
            else:
                self.view.display_message(f"Chicken coop ID {id} not found")
        except ValueError:
            self.view.display_message("Invalid ID format.")


    def request_number_of_chickens(self, coop):
        available_space = coop.get_capacity() - len(coop.get_chickens())
        if available_space <= 0:
            self.view.display_message(f"Chicken coop {coop.get_coop_coop_number()} is full")
            return

        count_str = self.view.request_number_of_chickens(coop.get_coop_coop_number(), available_space)
        try:
            num_chickens = int(count_str.strip())
            
            if not (0 < num_chickens <= available_space):
                self.view.display_message(f"Please enter a valid positive number less than or equal to {available_space}.")
                return

            chickens_to_create = []
            for i in range(num_chickens):
                current_number = i + 1
                while True: 
                    details = self.view.request_chicken_details(current_number, num_chickens, self.chicken_id_counter)
                    try:
                        data = {
                            "id": self.chicken_id_counter,
                            "name": details['name'].strip(),
                            "color": details['color'].strip(),
                            "age": int(details['age'].strip()),
                            "is_molting": details['is_molting_input'].strip().lower() == 'y'
                        }
                        if data['age'] < 0:
                            raise ValueError("Age cannot be negative")
                        
                        chickens_to_create.append(data)
                        self.chicken_id_counter += 1
                        break 
                    except ValueError:
                        self.view.display_message('Invalid age. Please try again with a number >= 0.')
            
            self.process_chicken_creation(coop, chickens_to_create)

        except ValueError:
            self.view.display_message(f"Please enter a valid positive number less than or equal to {available_space}.")

    def process_chicken_creation(self, coop, chickens_to_create):
        self.view.display_message(f"\n--- Creating and adding {len(chickens_to_create)} chicken to Chicken coop {coop.get_coop_coop_number()}... ---")
        for data in chickens_to_create:
            new_chicken = Chicken(data['id'], data['name'], data['color'], data['age'], data['is_molting'])
            if coop.add_chicken(new_chicken):
                self.view.display_message(f"Chicken {data['name']} (ID: {data['id']}) added.")
            else:
                self.view.display_message(f"The chicken could not be added {data['name']}. Chicken coop is full")
                self.chicken_id_counter -= 1 

    def read_chickens(self, coop):
        self.view.display_chickens(coop.get_coop_coop_number(), coop.get_chickens(), coop.get_capacity())

    def request_chicken_id_to_update(self, coop):
        if not coop.get_chickens():
            self.view.display_message('There are no chickens to edit.')
            return

        chicken_id_str = self.view.request_chicken_id_to_update()
        try:
            id = int(chicken_id_str.strip())
            chicken = coop.find_chicken(id)

            if not chicken:
                self.view.display_message(f"Chicken ID {id} not found")
                return

            updates = self.view.request_chicken_updates(chicken)
            final_updates = {}

            if updates['name'].strip():
                final_updates['name'] = updates['name'].strip()
            if updates['color'].strip():
                final_updates['color'] = updates['color'].strip()

            if updates['age'].strip():
                try:
                    num_age = int(updates['age'].strip())
                    if num_age >= 0:
                        final_updates['age'] = num_age
                    else:
                        raise ValueError
                except ValueError:
                    self.view.display_message('Age invalid. Edition cancelled')
                    return
            
            trimmed_input = updates['is_molting_input'].strip().lower()
            if trimmed_input == 'y':
                final_updates['isMolting'] = True
            if trimmed_input == 'n':
                final_updates['isMolting'] = False

            if coop.update_chicken(id, final_updates):
                self.view.display_message(f"Chicken ID {id} updated")

        except ValueError:
            self.view.display_message("Invalid ID format.")

    def request_chicken_id_to_remove(self, coop):
        if not coop.get_chickens():
            self.view.display_message('There are no chickens to erase.')
            return
        
        chicken_id_str = self.view.request_chicken_id_to_remove()
        try:
            id = int(chicken_id_str.strip())
            if coop.remove_chicken(id):
                self.view.display_message(f"Chicken ID {id} erased from Chicken coop {coop.get_coop_coop_number()}.")
            else:
                self.view.display_message(f"Chicken ID {id} not found in the Chicken coop {coop.get_coop_coop_number()}.")
        except ValueError:
            self.view.display_message("Invalid ID format.")

    def simulate_coop_day(self):
        total_eggs = 0
        self.view.display_message('\n--- Simulate ChickenCoop Day---')
        
        coops = self.farmer.get_coops()
        
        if not coops:
            self.view.display_message('Chicken coop not found')
        else:
            for coop in coops:
                eggs_laid = coop.simulate_coop_day()
                total_eggs += eggs_laid
            self.view.display_message(f"\nToday a grand total of {total_eggs} eggs were laid across the entire farm")

    def save_data_to_json(self):
        filename = 'farm_data.json'
        
        coops_data = [
            {
                "coopId": coop.get_coop_coop_number(),
                "capacity": coop.get_capacity(),
                "totalEggs": coop.get_total_eggs(),
                "chickens": [
                    {
                        "id": chicken.get_id(),
                        "name": chicken.get_name(),
                        "color": chicken.get_color(),
                        "age": chicken.get_age(),
                        "isMolting": chicken.get_is_molting(),
                        "eggsProduced": chicken.get_eggs_produced(),
                    } for chicken in coop.get_chickens()
                ]
            } for coop in self.farmer.get_coops()
        ]

        data_to_save = {
            "farmer": {
                "id": self.farmer.get_id(),
                "name": self.farmer.get_name(),
            },
            "coops": coops_data,
            "nextChickenId": self.chicken_id_counter,
            "nextCoopId": self.farmer.get_next_coop_id()
        }

        try:
            with open(filename, 'w') as f:
                json.dump(data_to_save, f, indent=2)

            self.view.display_message('\n======================================')
            self.view.display_message(f'DATA SAVED IN: {filename}')
            self.view.display_message('======================================\n')
            
        except IOError as e:
            self.view.display_message(f"ERROR saving the JSON file: {e}")

    def load_data_from_json(self):
            filename = 'farm_data.json'
            
            if not os.path.exists(filename):
                self.view.display_message("No saved data found. Starting a new farm.")
                self.farmer = ChickenFarmer(1, 'Mathews')
                self.chicken_id_counter = 1
                return
            try:
                with open(filename, 'r') as f:
                    data = json.load(f)

                farmer_data = data['farmer']
                self.farmer = ChickenFarmer(farmer_data['id'], farmer_data['name'])
                
                coops_data = data.get('coops', [])
                for coop_data in coops_data:
                    new_coop = ChickenCoop(coop_data['coopId'], coop_data['capacity'])
                    new_coop.set_total_eggs(coop_data.get('totalEggs', 0))
                    
                    chickens_data = coop_data.get('chickens', [])
                    for chicken_data in chickens_data:
                        new_chicken = Chicken(
                            chicken_data['id'],
                            chicken_data['name'],
                            chicken_data['color'],
                            chicken_data['age'],
                            chicken_data['isMolting']
                        )
                        new_chicken.set_eggs_produced(chicken_data.get('eggsProduced', 0))
                        new_coop.add_chicken(new_chicken)
                    
                    self.farmer.get_coops().append(new_coop)

                self.chicken_id_counter = data.get('nextChickenId', 1)
                self.farmer.set_next_coop_id(data.get('nextCoopId', 1))

                self.view.display_message(f"Data successfully loaded from {filename}")

            except Exception as e:
                self.view.display_message(f"ERROR loading data: {e}. Starting a new farm.")
                self.farmer = ChickenFarmer(1, 'Mathews')
                self.chicken_id_counter = 1