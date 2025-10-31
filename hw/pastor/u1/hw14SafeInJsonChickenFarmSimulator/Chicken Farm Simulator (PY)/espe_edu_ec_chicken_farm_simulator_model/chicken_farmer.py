from .chicken_coops import ChickenCoop

class ChickenFarmer:
    def __init__(self, id, name):
        self._id = id
        self._name = name
        self._coops = []
        self._next_coop_id = 1

    def add_coop(self, chicken_capacity):
        new_coop = ChickenCoop(self._next_coop_id, chicken_capacity)
        self._next_coop_id += 1
        self._coops.append(new_coop)
        return new_coop

    def remove_coop(self, coop_id):
        initial_length = len(self._coops)
        self._coops = [coop for coop in self._coops if coop.get_coop_coop_number() != coop_id]
        return len(self._coops) < initial_length

    def update_coop(self, coop_id, coop_updates):
        coop = self.find_coop(coop_id)
        if coop:
            if coop_updates.get('capacity') is not None:
                coop.set_capacity(coop_updates['capacity'])
            return True
        return False

    def find_coop(self, coop_id):
        for c in self._coops:
            if c.get_coop_coop_number() == coop_id:
                return c
        return None

    def __str__(self):
        return f"Farmer: {self.get_name()} (ID: {self.get_id()}), Chicken coops: {len(self.get_coops())}"

    def get_id(self): return self._id
    def get_name(self): return self._name
    def get_coops(self): return self._coops
    def get_next_coop_id(self): return self._next_coop_id

    def set_id(self, id): self._id = id
    def set_name(self, name): self._name = name
    def set_next_coop_id(self, next_coop_id): self._next_coop_id = next_coop_id