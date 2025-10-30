from chicken import Chicken

class Coop:
    def __init__(self, coop_name):
        self.coop_name = coop_name
        self.chickens = []

    def add_chicken(self, chicken):
        self.chickens.append(chicken)

    def review_coop(self):
        print(f"\n=== Coop: {self.coop_name} ===")
        if not self.chickens:
            print("No chickens in this coop.")
        else:
            for chicken in self.chickens:
                print(chicken)

    def select_chickens_for_action(self):
        if not self.chickens:
            print("No chickens in this coop.")
            return

        print(f"\nYou are reviewing coop '{self.coop_name}'.")
        for i, chicken in enumerate(self.chickens, 1):
            print(f"{i}. {chicken.name}")

        selection = input("Enter chicken numbers (comma-separated): ")
        indices = [int(x.strip()) - 1 for x in selection.split(",") if x.strip().isdigit()]

        print("\nChoose an action:")
        print("1. Make selected chickens poop")
        print("2. Set selected chickens to molting")
        action = int(input("Option: "))

        for i in indices:
            if 0 <= i < len(self.chickens):
                if action == 1:
                    self.chickens[i].poop(2)
                elif action == 2:
                    self.chickens[i].is_molting = True
                    print(f"{self.chickens[i].name} is now molting.")

    def to_dict(self):
        return {
            "coop_name": self.coop_name,
            "chickens": [chicken.to_dict() for chicken in self.chickens]
        }

    @staticmethod
    def from_dict(data):
        coop = Coop(data["coop_name"])
        for c in data.get("chickens", []):
            coop.add_chicken(Chicken.from_dict(c))
        return coop
