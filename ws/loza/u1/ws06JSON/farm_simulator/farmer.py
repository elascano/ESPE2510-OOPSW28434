from coop import Coop

class Farmer:
    def __init__(self, name, age, gender):
        self.name = name
        self.age = age
        self.gender = gender
        self.coops = []

    def add_coop(self, coop):
        self.coops.append(coop)

    def review_coops(self):
        print(f"\n=== Farmer {self.name}'s Coops ===")
        for coop in self.coops:
            coop.review_coop()

    def manage_coop(self):
        if not self.coops:
            print("No coops to manage.")
            return
        for i, coop in enumerate(self.coops, 1):
            print(f"{i}. {coop.coop_name}")

        idx = int(input("Select a coop to manage: ")) - 1
        if 0 <= idx < len(self.coops):
            self.coops[idx].select_chickens_for_action()

    def to_dict(self):
        return {
            "name": self.name,
            "age": self.age,
            "gender": self.gender,
            "coops": [coop.to_dict() for coop in self.coops]
        }

    @staticmethod
    def from_dict(data):
        farmer = Farmer(data["name"], data["age"], data["gender"])
        for coop_data in data.get("coops", []):
            farmer.add_coop(Coop.from_dict(coop_data))
        return farmer
