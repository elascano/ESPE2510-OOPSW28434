import random
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
        print(f"\n--- Chickens in {coop.name} (ID: {coop.id})  ---")
        chickens = coop.get_chickens()
        
        if not chickens:
            print("This coop is currently empty.")
            return

        print(f"Number of Chickens: {len(chickens)}\n")

        for chicken in chickens:
            self.display_chicken_details(chicken)
  
    def get_valid_coop_choice(self, coops):
        minimunId = 1
        maximunId = len(coops)
        
        while True:
            choice_str = input(f"Which Chicken Coop would you like to view (1 or 2)? ").strip()

            if choice_str.isdigit():

                choice = int(choice_str)

                if minimunId <= choice <= maximunId:
                    return coops[choice - 1]

            print(f" Please enter a valid coop ID between {minimunId} and {maximunId}.")

    def display_repeat_prompt(self):
        return input("\nDo you want to view another coop? (yes/no): ").lower().strip()

    def display_exit_message(self):
        print("\nExiting the Chicken Farm Simulator. Bye Bye!")

    def setup_farm(self):

        chickens = [
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

        coop1 = ChickenCoop('Kikiriki Palace')
        coop2 = ChickenCoop('Chickens on Board')
 
        for i in range(6):
            coop1.add_chicken(chickens[i])
 
        for i in range(6, 13):
            coop2.add_chicken(chickens[i])
            
        return [coop1, coop2]

    def main_loop(self):
        self.display_welcome()

        coops = self.setup_farm()
        self.display_setup_complete(coops)

        while True:
            selectedCoop = self.get_valid_coop_choice(coops)
            self.display_coop_view(selectedCoop)

            repeat = self.display_repeat_prompt()
            
            if repeat not in ["yes", "y"]:
                self.display_exit_message()
                break

if __name__ == "__main__":
    simulator = ChickenFarmSimulator()
    simulator.main_loop()