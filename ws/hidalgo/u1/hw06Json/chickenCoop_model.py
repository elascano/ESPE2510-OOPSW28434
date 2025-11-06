import json

from chicken_model import Chicken

class ChickenCoop:
    def __init__(self):
        self._chickens_registry = [] 
    
    @property
    def chickens(self):
        return self._chickens_registry

    def addChicken(self, chicken):
       
        if not isinstance(chicken, Chicken):
            raise TypeError("Only Chicken objects can be added to the coop.")
        self._chickens_registry.append(chicken)

    def getChickens(self):
        return list(self._chickens_registry) # Retorna una copia para evitar modificación externa

    def saveToJson(self, filename="chickens.json"):
        # Usando un pequeño cambio en la forma de obtener los datos
        data_to_save = [c.to_dict() for c in self.getChickens()]
        with open(filename, "w", encoding="utf-8") as f:
            json.dump(data_to_save, f, indent=4, ensure_ascii=False)

    def loadFromJson(self, filename="chickens.json"):
        # Usando el método from_dict de Chicken y limpiando la lista antes de cargar
        try:
            with open(filename, "r", encoding="utf-8") as f:
                data = json.load(f)
                self._chickens_registry = [Chicken.from_dict(c) for c in data]
        except FileNotFoundError:
            print(f"File {filename} not found. Starting with an empty coop.")
            self._chickens_registry = []
        except json.JSONDecodeError:
            print(f"Error decoding JSON from {filename}. Starting with an empty coop.")
            self._chickens_registry = []
