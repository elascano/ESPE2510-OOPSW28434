from Chicken import Chicken
from Poop import make_chicken_poop
from Egg import lay_egg
import csv 

class ChickenCoop:
    def __init__(self, coop_id, name):
        self.id = coop_id
        self.name = name
        self.chickens = []  

    def add_chicken(self, chicken):
        self.chickens.append(chicken)

    def show_coop(self):
        print(f"\n--- Coop {self.name} (ID: {self.id}) ---")
        if not self.chickens:
            print("No chickens here!")
        else:
            print("Chickens:")
            for chicken in self.chickens:
                molting = "Yes" if chicken.is_molting else "No"
                print(f"  - ID: {chicken.id}, Name: {chicken.name}, Color: {chicken.color}, Age: {chicken.age}, Molting: {molting}")

class ChickenCoopSimulator:
    def __init__(self):
        self.coops = [] 
        self.initialize_data()

    def initialize_data(self):
        coop1 = ChickenCoop(1, "Coop A")
        coop2 = ChickenCoop(2, "Coop B")

        chickens = [
            Chicken(1, "Lucy", "White and Brown", False, 2),
            Chicken(2, "Maruja", "White", True, 1),
            Chicken(3, "Rosie", "Black", False, 3),
            Chicken(4, "Lola", "Golden", False, 4),
            Chicken(5, "Nina", "Gray", True, 2),
            Chicken(6, "Sofi", "Red", False, 1),
            Chicken(7, "Mona", "Brown", False, 3),
            Chicken(8, "Clara", "White", False, 5),
            Chicken(9, "Tina", "Yellow", True, 2),
            Chicken(10, "Lili", "Black and White", False, 1),
        ]

        for i in range(len(chickens)):
            if i < 5:
                coop1.add_chicken(chickens[i])
            else:
                coop2.add_chicken(chickens[i])

        self.coops.append(coop1)
        self.coops.append(coop2)

    def show_all_coops(self):
        for coop in self.coops:
            coop.show_coop()

    def save_to_csv(self):
        with open("chickens_data.csv", "w", newline="") as file:
            writer = csv.writer(file)
            writer.writerow(["CoopID", "CoopName", "ChickenID", "Name", "Color", "Age", "IsMolting"])
            for coop in self.coops:
                for chicken in coop.chickens:
                    writer.writerow([coop.id, coop.name, chicken.id, chicken.name, chicken.color, chicken.age, chicken.is_molting])

    def run(self):
        print("Chicken Coop Simulator - by Maryuri")
        self.show_all_coops()
        self.save_to_csv()

if __name__ == "__main__":
    try:
        simulator = ChickenCoopSimulator()
        simulator.run()
    except Exception as e:
        print(f"\nAn error occurred: {e}")