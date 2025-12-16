class Chicken:
    nextId = 1 

    def __init__(self, name, color, age, is_molting, id=""):

        if id == "":
            self.id = str(Chicken.nextId)
            Chicken.nextId += 1
        else:
            self.id = id
            if int(id) >= Chicken.nextId:
                Chicken.nextId = int(id) + 1
                
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting

    def cluck(self):
        return f"{self.name} is clucking"

    def toDict(self):
        return {
            "id": self.id,
            "name": self.name,
            "color": self.color,
            "age": self.age,
            "is_molting": self.is_molting 
        }

    @staticmethod
    def fromDict(data):

        is_molting_value = data.get("is_molting", data.get("isMolting"))
        
        return Chicken(
            data["name"],
            data["color"],
            data["age"],
            is_molting_value,
            data["id"] 
        )