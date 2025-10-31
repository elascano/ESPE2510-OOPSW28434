class ChickenCoop:
    allCoops: list = []
    
    def __init__(self, name, coop_id=None):
        self.name = name
        self.chickens = [] 
        
        if coop_id is None:
            self.id = len(ChickenCoop.allCoops) + 1
            ChickenCoop.allCoops.append(self)
        else:
            self.id = coop_id
            ChickenCoop.allCoops.append(self)
    
    def getId(self):
        return self.id

    def getName(self):
        return self.name

    def setName(self, new_name):
        self.name = new_name
    
    def getChickens(self):
        return self.chickens

    def addChicken(self, chicken):
        self.chickens.append(chicken)
    
    def removeChickenById(self, chicken_id):
        initial_length = len(self.chickens)
        self.chickens[:] = [c for c in self.chickens if c._id != chicken_id]
        return len(self.chickens) < initial_length

    def getNumberOfChickens(self):
        return len(self.chickens)

    def showCoopContents(self):
        print(f"\n---------------------------------------")
        print(f" CHICKEN COOP: {self.name} (ID: {self.id}, {self.getNumberOfChickens()} chickens)")
        print(f"---------------------------------------")
        if not self.chickens:
            print("This coop is empty.")
        else:
            for chicken in self.chickens:
                print(str(chicken) if hasattr(chicken, '__str__') else f"Chicken ID: {chicken._id}, Name: {chicken._name}")

    @staticmethod
    def listAllCoops():
        print("\n=== GLOBAL CHICKEN COOP COLLECTION ===")
        for coop in ChickenCoop.allCoops:
            coop.showCoopContents()