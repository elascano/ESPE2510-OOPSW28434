import json
import os
from model.ChickenFarmer import ChickenFarmer
from model.ChickenCoop import ChickenCoop
from model.Chicken import Chicken

DATA_FILE = "farmers.json"

class FarmController:
    def __init__(self):
        self.farmers = self.load_data()

    def add_farmer(self, farmer: ChickenFarmer):
        """Add a new farmer to the list and save data."""
        self.farmers.append(farmer)
        self.save_data()

    def add_chicken_to_coop(self, farmer_name: str, coop_id: int, chicken: Chicken):
        """Find a farmer and coop, then add a chicken."""
        for farmer in self.farmers:
            if farmer.name == farmer_name:
                for coop in farmer.coops:
                    if coop.id == coop_id:
                        coop.add_chicken(chicken)
                        self.save_data()
                        return
        print("Farmer or coop not found.")

    def save_data(self):
        """Save all farmers and their data to JSON."""
        data = [f.to_dict() for f in self.farmers]
        with open(DATA_FILE, "w", encoding="utf-8") as file:
            json.dump(data, file, indent=4)

    def load_data(self):
        """Load farmers from JSON file if it exists."""
        if not os.path.exists(DATA_FILE):
            return []
        with open(DATA_FILE, "r", encoding="utf-8") as file:
            data = json.load(file)
            farmers = []
            for f in data:
                farmer = ChickenFarmer(f["name"])
                for coop_data in f["coops"]:
                    coop = ChickenCoop(coop_data["id"])
                    for c_data in coop_data["chickens"]:
                        chicken = Chicken(**c_data)
                        coop.add_chicken(chicken)
                    farmer.add_coop(coop)
                farmers.append(farmer)
            return farmers

    def get_farmers(self):
        """Return the list of farmers (used to display data)."""
        return self.farmers