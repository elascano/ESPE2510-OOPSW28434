import csv
import os 
from Chicken_model import Chicken 
from ChickenCoop_model import ChickenCoop

class ChickenFarmSimulator:

    def display_welcome(self):
        print("\n Welcome to my Chicken Farm Simulator ")

    def display_setup_complete(self, coops):
        print("\n--- Farm Setup Complete ---")
        for coop in coops:
            print(coop) 
        print('---------------------------')

    def display_chicken_details(self, chicken):
        print(chicken) 

    def display_coop_view(self, coop):
        print(f"\n--- Chickens in {coop.name} (ID: {coop.id}) ---")
        chickens = coop.get_chickens()
        
        if not chickens:
            print("This coop is currently empty.")
            return

        print(f"Number of Chickens: {len(chickens)}\n")

        for chicken in chickens:
            self.display_chicken_details(chicken)
 
    def get_valid_coop_choice(self, coops):
        minimumId = 1
        maximumId = len(coops)
        
        while True:
            choice_str = input(f"Which Chicken Coop would you like to view ({minimumId} or {maximumId})? ").strip()

            if choice_str.isdigit():
                choice = int(choice_str)

                if minimumId <= choice <= maximumId:
                    return coops[choice - 1]

            print(f" Please enter a valid coop ID between {minimumId} and {maximumId}.")

    def display_repeat_prompt(self):
        return input("\nDo you want to view another coop? (yes/no): ").lower().strip()

    def display_exit_message(self):
        print("\nExiting the Chicken Farm Simulator. Bye Bye!")

    def save_chickens_to_csv(self, chickens, filename='chickens.csv'):
        with open(filename, 'w', newline='', encoding='utf-8') as file:
            writer = csv.writer(file, delimiter=';')
            writer.writerow(['Name', 'Color', 'Age', 'IsMolting'])
            for chicken in chickens:
                writer.writerow([chicken.name, chicken.color, chicken.age, chicken.is_molting])
        print(f"\nAll chickens have been saved to {filename}")

    def load_chickens_from_csv(self, filename='chickens.csv'):
        chickens = []

        with open(filename, 'r', newline='', encoding='utf-8') as file:
            reader = csv.DictReader(file)
            for row in reader:

                is_molting = row['IsMolting'].lower() in ('true', 't', '1') 
                age = int(row['Age']) 
                    
                chicken = Chicken(
                    row['Name'], 
                    row['Color'], 
                    age, 
                    is_molting
                )
                chickens.append(chicken)
        
        print(f"\nCargadas {len(chickens)} gallinas desde {filename}.")
        return chickens
            
    def setup_farm(self):
        
        filename = 'chickens.csv'

        initial_chickens = [
            Chicken('Mariluna', 'Brown', 2, False), 
            Chicken('Clucky', 'White', 1, False),     
            Chicken('Josefa', 'Black and Brown', 3, False),   
            Chicken('Kiki', 'White', 3, False),     
            Chicken('Lucilda', 'Brown and White', 4, True),      
            Chicken('Ruperta', 'Yellow', 2, False),
            Chicken('Lili', 'White', 3, True),     
            Chicken('Coco', 'Black and White', 1, True),       
            Chicken('Pepa', 'Brown', 2, False),     
            Chicken('Sunshine', 'Brown and Yellow', 1, False),      
            Chicken('Yuka', 'Gray', 4, True),   
            Chicken('Mila', 'Orange', 2, False),   
            Chicken('Blanquita', 'Black', 2, False)    
        ]

        if os.path.exists(filename):
            chickens = self.load_chickens_from_csv(filename)
        else:
            print(f"\nEl archivo '{filename}' no existe. Creando la granja inicial y guardándola en CSV.")
            chickens = initial_chickens
            self.save_chickens_to_csv(chickens, filename) 

        coop1 = ChickenCoop('Kikiriki Palace')
        coop2 = ChickenCoop('Chickens on Board')

        num_chickens = len(chickens)
        coop1_count = min(6, num_chickens) 
        
        for chicken in chickens[:coop1_count]:
            coop1.add_chicken(chicken)

        for chicken in chickens[coop1_count:]:
            coop2.add_chicken(chicken)
            
        return [coop1, coop2]

    def main_loop(self):
        self.display_welcome()

        coops = self.setup_farm()
        self.display_setup_complete(coops)

        while True:
            selectedCoop = self.get_valid_coop_choice(coops)
            self.display_coop_view(selectedCoop)

            repeat = self.display_repeat_prompt()
            
            if repeat not in ["yes", "y", "si"]:
                self.display_exit_message()
                break

if __name__ == "__main__":
    simulator = ChickenFarmSimulator()
    simulator.main_loop()