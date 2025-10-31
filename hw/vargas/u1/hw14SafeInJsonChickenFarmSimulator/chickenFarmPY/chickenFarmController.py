import asyncio
import sys
from typing import List, Dict, Any, Union

try:
    from chicken import Chicken
    from chickenCoop import ChickenCoop
    from fileMangement import ChickenSave 
except ImportError:
    from chicken import Chicken
    from chickenCoop import ChickenCoop
    from fileMangement import ChickenSave

async def ainput(prompt: str) -> str:
    return await asyncio.to_thread(input, prompt)

initial_chickens_data = [
    { 'id': '1', "name": "Lucy", "color": "White and Brown", "age": 2, "isMolting": False },
    { 'id': '2', "name": "Maruja", "color": "White", "age": 1, "isMolting": True },
    { 'id': '3', "name": "Lola", "color": "White", "age": 2, "isMolting": True },
    { 'id': '4', "name": "Pepa", "color": "Black", "age": 1, "isMolting": False },
    { 'id': '5', "name": "Gusepa", "color": "Brown and white", "age": 4, "isMolting": False },
    { 'id': '6', "name": "Pancracia", "color": "Gray", "age": 2, "isMolting": True },
    { 'id': '7', "name": "Federica", "color": "Brown", "age": 1, "isMolting": False },
    { 'id': '8', "name": "Pancha", "color": "White", "age": 3, "isMolting": False },
    { 'id': '9', "name": "Zoe", "color": "Black", "age": 2, "isMolting": False },
    { 'id': '10', "name": "Lina", "color": "Brown and white", "age": 1, "isMolting": True },
]

class FarmController:
    __storage: ChickenSave
    __farmCoops: List[ChickenCoop]

    def __init__(self, filename: str = 'chickenFarm.json'):
        self.__storage = ChickenSave(filename) 
        self.__farmCoops = [] 
        
        print("This is my Chicken Farm Simulator Controller (Python)")

    def __create_coops_from_data(self, loaded_coop_data: List[Dict[str, Any]], chicken_data: List[Dict[str, Any]]) -> List[ChickenCoop]:
        if hasattr(ChickenCoop, 'allCoops'):
             ChickenCoop.allCoops = [] 

        if loaded_coop_data:
            reconstructed_coops = []
            for coop_data in loaded_coop_data:
                coop = ChickenCoop(coop_data.get('name'), coop_data.get('id')) 
                
                for c in coop_data.get('chickens', []):
                    chicken = Chicken(c.get('id'), c.get('name'), c.get('color'), c.get('age'), c.get('isMolting'))
                    coop.addChicken(chicken)
                reconstructed_coops.append(coop)
            return reconstructed_coops

        coop1 = ChickenCoop("Chicken Coop 1", 'COOP_1') 
        coop2 = ChickenCoop("Chicken Coop 2", 'COOP_2') 
        
        all_chickens = [Chicken(c['id'], c['name'], c['color'], c['age'], c['isMolting']) for c in chicken_data]

        for i in range(min(7, len(all_chickens))):
            coop1.addChicken(all_chickens[i]) 

        for i in range(7, len(all_chickens)):
            coop2.addChicken(all_chickens[i])
        
        return [coop1, coop2]

    async def initializeFarm(self):
        print('Initializing farm data...')
        loaded_coop_data = await self.__storage.load()

        if not loaded_coop_data:
            print("JSON file empty. Initializing with hardcoded data and saving...")
            
            self.__farmCoops = self.__create_coops_from_data([], initial_chickens_data)
            
            await self.__storage.save(self.__farmCoops)
            
        else:
            self.__farmCoops = self.__create_coops_from_data(loaded_coop_data, [])
            print("Farm data loaded successfully from JSON.")

    async def mainMenu(self):
        await self.initializeFarm()

        exit_app = False
        while not exit_app:
            print("\n---------------------------------------")
            print("  CHICKEN FARM MANAGEMENT MENU ")
            print("---------------------------------------")
            print("1. Add New Chicken")
            print("2. Display All Chickens")
            print("3. Edit Data (Chicken/Coop)")
            print("4. Delete Data (Chicken/Coop)")
            print("5. Exit")
            print("---------------------------------------")

            choice = await ainput('Select an option: ')
            
            try:
                if choice.strip() == '1':
                    await self.__add_new_chicken()
                elif choice.strip() == '2':
                    await self.__list_chickens_from_json()
                elif choice.strip() == '3':
                    await self.__handle_update_menu()
                elif choice.strip() == '4':
                    await self.__handle_delete_menu()
                elif choice.strip() == '5':
                    exit_app = True
                else:
                    print("Invalid option, please try again.")
            except Exception as e:
                print(f"An unexpected error occurred: {repr(e)}")

        print("Simulator closed")

    async def __add_new_chicken(self):
        print("\n--- ADD NEW CHICKEN ---")
        chicken_id = await ainput('Chicken ID (must be unique): ')
        name = await ainput('Chicken name: ')
        color = await ainput('Chicken color: ')
        age_str = await ainput('Chicken age: ')
        is_molting = False 

        try:
            age = int(age_str)
        except ValueError:
            print("Error: Age must be a valid number.")
            return

        if not all([chicken_id.strip(), name.strip(), color.strip()]):
            print("Error: Please enter valid data for ID, name, and color.")
            return

        existing_item = await self.__storage.find_coop_or_chicken_by_id(chicken_id, 'chicken')
        if existing_item:
            print(f"Error: A chicken with ID {chicken_id} already exists.")
            return

        new_chicken = Chicken(chicken_id, name, color, age, is_molting)
        
        if not self.__farmCoops:
            print("Error: No coops initialized to add the chicken to.")
            return
            
        print("\n--- Select Chicken Coop ---")
        for index, coop in enumerate(self.__farmCoops):
            print(f"{index + 1}. {coop.getName()} (ID: {coop.getId()})")
        print("---------------------------")

        coop_choice = await ainput(f"Enter the number of the coop (1-{len(self.__farmCoops)}): ")
        
        try:
            coop_index = int(coop_choice) - 1
            selected_coop = self.__farmCoops[coop_index]
        except (ValueError, IndexError):
            print("Error: Invalid coop selection.")
            return

        selected_coop.addChicken(new_chicken)
        
        await self.__storage.save(self.__farmCoops)

        print(f"\nChicken \"{name}\" (ID: {new_chicken.id}) added and saved successfully to {selected_coop.getName()}!")

    async def __list_chickens_from_json(self):
        print("\n--- STORED CHICKENS AND COOPS ---")
        loaded_coop_data = await self.__storage.load()

        total_chickens = 0
        
        if not loaded_coop_data:
            print("No coop data registered in the JSON file.")
            return
        
        for coop_data in loaded_coop_data:
            chickens_count = len(coop_data.get('chickens', []))
            print(f"\nCoop ID: {coop_data.get('id')}, Name: {coop_data.get('name')}, Total Chickens: {chickens_count}")
            if chickens_count > 0:
                for c in coop_data['chickens']:
                    print(f" [ID: {c.get('id')}] Name: {c.get('name')}, Color: {c.get('color')}, Age: {c.get('age')}, Molting: {'Yes' if c.get('isMolting') else 'No'}")
                    total_chickens += 1
            else:
                print(' (No chickens in this coop)')
        
        print(f"\nTotal chickens found across all coops: {total_chickens}")

    async def __handle_update_menu(self):
        print("\n--- EDIT DATA MENU ---")
        print("1. Edit Chicken")
        print("2. Edit Coop")
        print("3. Back to Main Menu")
        print("----------------------")

        choice = await ainput('Select data type to edit: ')
        
        if choice.strip() == '1':
            await self.__edit_chicken()
        elif choice.strip() == '2':
            await self.__edit_coop()
        elif choice.strip() != '3':
            print("Invalid option.")

    async def __edit_chicken(self):
        print("\n--- EDIT CHICKEN ---")
        await self.__list_chickens_from_json()

        chicken_id = await ainput('Enter the ID of the Chicken to edit: ')
        
        try:
            found_data = await self.__storage.find_coop_or_chicken_by_id(chicken_id, 'chicken')
            if not found_data:
                print(f"Error: Chicken with ID {chicken_id} not found.")
                return

            current_chicken = found_data['chicken']
            print(f"\nEditing Chicken: {current_chicken.get('name')} (ID: {current_chicken.get('id')})")

            new_name = await ainput(f"Enter new Name (Current: {current_chicken.get('name')}, leave blank to skip): ")
            new_color = await ainput(f"Enter new Color (Current: {current_chicken.get('color')}, leave blank to skip): ")
            new_age_str = await ainput(f"Enter new Age (Current: {current_chicken.get('age')}, leave blank to skip): ")
            new_molting_str = await ainput(f"Is it Molting? (Current: {'Y' if current_chicken.get('isMolting') else 'N'}, Enter Y/N, leave blank to skip): ")
            
            update_data = {}
            if new_name.strip(): update_data['name'] = new_name.strip()
            if new_color.strip(): update_data['color'] = new_color.strip()
            
            if new_age_str.strip():
                try:
                    update_data['age'] = int(new_age_str.strip())
                except ValueError:
                    print('Warning: Invalid age entered. Skipping age update.')
            
            if new_molting_str.strip():
                molting_input = new_molting_str.strip().upper()
                if molting_input in ('Y', 'YES'):
                    update_data['isMolting'] = True
                elif molting_input in ('N', 'NO'):
                    update_data['isMolting'] = False
                else:
                    print('Warning: Invalid molting status entered. Skipping status update.')

            if not update_data:
                print('No changes specified. Aborting update.')
                return

            await self.__storage.update_data(chicken_id, 'chicken', update_data)
            
            coop_instance = next((c for c in self.__farmCoops if c.getId() == found_data['coop']['id']), None)
            if coop_instance:
                chicken_instance = next((c for c in coop_instance.getChickens() if c.id == chicken_id), None)
                if chicken_instance:
                    if 'name' in update_data: chicken_instance.name = update_data['name']
                    if 'color' in update_data: chicken_instance.color = update_data['color']
                    if 'age' in update_data: chicken_instance.age = update_data['age']
                    if 'isMolting' in update_data: chicken_instance.isMolting = update_data['isMolting']
                    print('Internal instance updated.')

        except Exception as e:
            print(f"Error during chicken update: {e}")

    async def __edit_coop(self):
        print("\n--- EDIT COOP ---")
        await self.__list_chickens_from_json() 
        
        coop_id = await ainput('Enter the ID of the Coop to edit: ')
        
        try:
            current_coop = await self.__storage.find_coop_or_chicken_by_id(coop_id, 'coop')
            if not current_coop:
                print(f"Error: Coop with ID {coop_id} not found.")
                return
            
            print(f"\nEditing Coop: {current_coop.get('name')} (ID: {current_coop.get('id')})")
            
            new_name = await ainput(f"Enter new Name (Current: {current_coop.get('name')}, leave blank to skip): ")
            
            update_data = {}
            if new_name.strip(): update_data['name'] = new_name.strip()
            
            if not update_data:
                print('No changes specified. Aborting update.')
                return

            await self.__storage.update_data(coop_id, 'coop', update_data)
            
            coop_instance = next((c for c in self.__farmCoops if c.getId() == coop_id), None)
            if coop_instance:
                coop_instance.setName(update_data['name'])
                print('Internal instance updated.')

        except Exception as e:
            print(f"Error during coop update: {e}")

    async def __handle_delete_menu(self):
        print("\n--- DELETE DATA MENU ---")
        print("1. Delete Chicken")
        print("2. Delete Coop")
        print("3. Back to Main Menu")
        print("----------------------")

        choice = await ainput('Select data type to delete: ')
        
        if choice.strip() == '1':
            await self.__delete_chicken()
        elif choice.strip() == '2':
            await self.__delete_coop()
        elif choice.strip() != '3':
            print("Invalid option.")

    async def __delete_chicken(self):
        print("\n--- DELETE CHICKEN ---")
        await self.__list_chickens_from_json()

        chicken_id = await ainput('Enter the ID of the Chicken to delete: ')
        if not chicken_id.strip():
            print("ID cannot be empty.")
            return

        try:
            found_data = await self.__storage.find_coop_or_chicken_by_id(chicken_id, 'chicken')
            
            success = await self.__storage.delete_data(chicken_id, 'chicken')

            if success and found_data:
                coop_instance = next((c for c in self.__farmCoops if c.getId() == found_data['coop']['id']), None)
                if coop_instance:
                    coop_instance.removeChickenById(chicken_id)
                    print('Internal chicken instance deleted.')

        except Exception as e:
            print(f"Error during chicken deletion: {e}")

    async def __delete_coop(self):
        print("\n--- DELETE COOP ---")
        await self.__list_chickens_from_json()
        
        coop_id = await ainput('Enter the ID of the Coop to delete (WARNING: This will delete ALL chickens inside!): ')
        if not coop_id.strip():
            print("ID cannot be empty.")
            return
        
        try:
            coop_instance = next((c for c in self.__farmCoops if c.getId() == coop_id), None)

            success = await self.__storage.delete_data(coop_id, 'coop')

            if success:
                if coop_instance:
                    self.__farmCoops = [c for c in self.__farmCoops if c.getId() != coop_id]
                    print('Internal coop instance deleted.')

        except Exception as e:
            print(f"Error during coop deletion: {e}")

async def main():
    print("=======================================")
    print(" INICIANDO SIMULADOR DE GRANJA ")
    print("=======================================")
    
    try:
        controller = FarmController('chickenFarm.json') 
        await controller.mainMenu() 
        
    except Exception as e:
        print("\n--- ERROR FATAL EN LA APLICACIÓN ---", file=sys.stderr)
        print(e, file=sys.stderr)


if __name__ == "__main__":
    asyncio.run(main())