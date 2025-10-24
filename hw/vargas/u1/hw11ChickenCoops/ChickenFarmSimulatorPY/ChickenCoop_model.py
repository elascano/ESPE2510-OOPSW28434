from Chicken_model import Chicken
class ChickenCoop:
    
    all_coops = [] 

    def __init__(self, name):

        self.name = name
        self.chickens = []
        
        ChickenCoop.all_coops.append(self) 

    def add_chicken(self, chicken):
        if isinstance(chicken, Chicken):
            self.chickens.append(chicken)
        
    def get_number_of_chickens(self):
        return len(self.chickens)

    def show_coop_contents(self):
        print(f"\nChicken Coop: {self.name}")
        print(f"Number of Chickens: {self.get_number_of_chickens()}")
        for chicken in self.chickens:
            print(chicken)

    @classmethod 
    def list_all_coops(cls):
        print("\n=========================================")
        print(f" Global Coop Collection: {len(cls.all_coops)}")
        print("=========================================")
        for coop in cls.all_coops:
            coop.show_coop_contents()