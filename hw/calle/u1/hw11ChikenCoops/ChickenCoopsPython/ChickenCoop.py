class ChickenCoop:
    def __init__(self, id, name):
        self._id = id
        self._name = name
        self._chickens = [] 

    def get_id(self):
        return self._id

    def get_name(self):
        return self._name

    def get_chickens(self):
        return self._chickens

    def set_id(self, id):
        self._id = id

    def set_name(self, name):
        self._name = name

    def add_chicken(self, chicken):
        self._chickens.append(chicken)

    def remove_chicken(self, chicken_id):
        self._chickens = [chicken for chicken in self._chickens if chicken.get_id() != chicken_id]

    def get_chicken_count(self):
        return len(self._chickens)

    def __str__(self):
        coop_info = (f"Chicken Coop ID: {self._id}\n"
                    f"Name: {self._name}\n"
                    f"Number of Chickens: {self.get_chicken_count()}\n")
        
        if self._chickens:
            coop_info += "Chickens in this coop:\n"
            for chicken in self._chickens:
                coop_info += f"- {chicken.get_name()} (ID: {chicken.get_id()})\n"
        else:
            coop_info += "No chickens in this coop.\n"
        
        return coop_info

    def display_all_chickens(self):
        print(f"\n=== Detailed info for {self._name} ===")
        for chicken in self._chickens:
            print(chicken)
            print("---")