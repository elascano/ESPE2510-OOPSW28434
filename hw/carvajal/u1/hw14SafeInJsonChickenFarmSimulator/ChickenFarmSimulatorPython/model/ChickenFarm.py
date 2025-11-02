import json
import os
from model.Chicken import Chicken
from model.ChickenCoop import ChickenCoop




class ChickenFarm:
    def __init__(self, data_path="data/chickens.json"):
        self.data_path = data_path
        self.coops = [] # list of ChickenCoop
        self.next_chicken_id = 1
        self.next_coop_id = 1
        self._ensure_data_dir()
        self.load()


    def _ensure_data_dir(self):
        folder = os.path.dirname(self.data_path)
        if folder and not os.path.exists(folder):
            os.makedirs(folder)


    def load(self):
        if not os.path.exists(self.data_path):
            self.save() # create an empty file
            return
        try:
            with open(self.data_path, "r", encoding="utf-8") as f:
                data = json.load(f)
        except json.JSONDecodeError:
            data = {}


        self.coops = []
        for coop_d in data.get("coops", []):
            self.coops.append(ChickenCoop.from_dict(coop_d))


        self.next_chicken_id = data.get("next_chicken_id", self._calc_next_chicken_id())
        self.next_coop_id = data.get("next_coop_id", self._calc_next_coop_id())


    def save(self):
        data = {
            "coops": [coop.to_dict() for coop in self.coops],
            "next_chicken_id": self.next_chicken_id,
            "next_coop_id": self.next_coop_id,
        }
        with open(self.data_path, "w", encoding="utf-8") as f:
            json.dump(data, f, indent=2)


    def _calc_next_chicken_id(self):
        max_id = 0
        for coop in self.coops:
            for c in coop.chickens:
                if c.id > max_id:
                    max_id = c.id
        return max_id + 1


    def _calc_next_coop_id(self):
        max_id = 0
        for coop in self.coops:
            if coop.id > max_id:
                max_id = coop.id
        return max_id + 1


    def _find_coop_with_space(self):
        for coop in self.coops:
            if not coop.is_full():
                return coop
        return None


    def add_chicken(self, name: str, age: int, molting: bool):
        # assign an id
        cid = self.next_chicken_id
        self.next_chicken_id += 1


        # find coop with space or create new one
        coop = self._find_coop_with_space()
        if coop is None:
            coop = ChickenCoop(self.next_coop_id)
            self.next_coop_id += 1
            self.coops.append(coop)


        chicken = Chicken(cid, name, age, molting, coop.id)
        coop.add_chicken(chicken)
        self.save()
        return chicken


    def list_all_chickens(self):
        chickens = []
        for coop in self.coops:
            for c in coop.chickens:
                chickens.append((c, coop.id))
        return chickens


    def find_chicken(self, chicken_id: int):
        for coop in self.coops:
            for c in coop.chickens:
                if c.id == chicken_id:
                    return c, coop
        return None, None


    def remove_chicken(self, chicken_id: int):
        for coop in self.coops:
            removed = coop.remove_chicken(chicken_id)
            if removed:
                # if coop becomes empty, keep the coop (optional) or remove
                # we'll keep empty coops to preserve IDs
                self.save()
                return removed
        return None