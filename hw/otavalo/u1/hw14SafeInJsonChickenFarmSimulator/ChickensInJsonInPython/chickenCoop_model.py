import json
from chicken_model import Chicken

class ChickenCoop:
    def __init__(self, filename="chickens2.json"): 
        self.filename = filename
        self.loadFromJson()

    def addChicken(self, chicken):
        self.chickens.append(chicken)

    def getChickens(self):
        return self.chickens

    def findChickenById(self, chickenId):
        for chicken in self.chickens:
            if chicken.id == chickenId:
                return chicken
        return None

    def editChicken(self, chickenId, newName, newColor, newAge, newIsMolting):
        chicken = self.findChickenById(chickenId)
        if chicken:
            chicken.name = newName
            chicken.color = newColor
            chicken.age = newAge
            chicken.is_molting = newIsMolting 
            self.saveToJson()
            return True
        return False

    def deleteChicken(self, chickenId):
        initialLength = len(self.chickens)
        self.chickens = [chicken for chicken in self.chickens if chicken.id != chickenId]
        if len(self.chickens) < initialLength:
            self.saveToJson()
            return True
        return False

    def saveToJson(self):
        data = [chicken.toDict() for chicken in self.chickens]
        try:
            with open(self.filename, "w", encoding="utf-8") as f:
                json.dump(data, f, indent=4, ensure_ascii=False)
        except IOError:
            print(f"Error saving data to {self.filename}.")

    def loadFromJson(self):
        try:
            with open(self.filename, "r", encoding="utf-8") as f:
                data = json.load(f)
                self.chickens = []
                max_id = 0
                for i in data:
                    try:
                        chicken = Chicken.fromDict(i)
                        self.chickens.append(chicken)
                        current_id = int(chicken.id)
                        if current_id > max_id:
                            max_id = current_id
                    except KeyError as e:
                        print(f" Error de formato: falta la clave {e} en un pollito del JSON. Se ignora.")
                    except Exception as e:
                         print(f" Error desconocido al cargar un pollito: {e}. Se ignora.")

                Chicken.nextId = max_id + 1 if self.chickens else 1

        except FileNotFoundError:
            self.chickens = []
            Chicken.nextId = 1 
        except json.JSONDecodeError:
            print(" Error de formato en el archivo JSON. Se inicia con la lista vacía.")
            self.chickens = []
            Chicken.nextId = 1