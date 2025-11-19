class ChickenCoop:
    def __init__(self, coop_id, name):
        self.id = coop_id
        self.name = name
        self.chickens = []

    def add_chicken(self, chicken, silent=False):
        self.chickens.append(chicken)
        if not silent:
            print(f"Chicken {chicken.name} added to {self.name}.")

    def remove_chicken(self, name):
        try:
            index_to_remove = next(
                (i for i, c in enumerate(self.chickens) if c.name.lower() == name.lower()),
                -1
            )

            if index_to_remove != -1:
                removed_chicken = self.chickens.pop(index_to_remove)
                print(f"Chicken {removed_chicken.name} has been removed.")
                return True
            else:
                return False
        except Exception:
            return False

    def update_chicken(self, name, new_color=None, new_age=None, new_molting=None):
        chicken = self.get_chicken_by_name(name)
        if chicken:
            if new_color is not None:
                chicken.color = new_color
            if new_age is not None:
                chicken.age = new_age
            if new_molting is not None:
                chicken.is_molting = new_molting
            print(f"Chicken {chicken.name} updated successfully.")
            return True
        return False

    def show_coop(self):
        print(f"\n--- {self.name} (ID: {self.id}) ---")
        
        if not self.chickens:
            print("No chickens in this coop yet.")
        else:
            header = "{:<5} {:<10} {:<15} {:<5} {:<10}"
            print(header.format("ID", "NAME", "COLOR", "AGE", "MOLTING"))
            print("-" * 45)
            
            for chicken in self.chickens:
                molting = "Yes" if chicken.is_molting else "No"
                print(header.format(
                    chicken.id, 
                    chicken.name, 
                    chicken.color, 
                    chicken.age, 
                    molting
                ))

    def get_chicken_by_name(self, name):
        return next((c for c in self.chickens if c.name.lower() == name.lower()), None)
        
    def to_dict(self):
        return {
            "id": self.id,
            "name": self.name,
            "chickens": [c.to_dict() for c in self.chickens]
        }