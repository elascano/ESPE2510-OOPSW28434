class ChickenCoop:
    def __init__(self, coop_id, name):
        self.id = coop_id
        self.name = name
        self.chickens = []

    def add_chicken(self, chicken):
        self.chickens.append(chicken)
        print(f"Chicken {chicken.name} added to {self.name}.")

    def show_coop(self):
        print(f"\n--- Coop {self.name} (ID: {self.id}) ---")
        if not self.chickens:
            print("No chickens in this coop yet.")
        else:
            for chicken in self.chickens:
                molting = "Yes" if chicken.is_molting else "No"
                print(f"  ID: {chicken.id}, Name: {chicken.name}, Color: {chicken.color}, Age: {chicken.age}, Molting: {molting}")

    def to_dict(self):
        return {
            "id": self.id,
            "name": self.name,
            "chickens": [c.to_dict() for c in self.chickens]
        }