from ec.edu.espe.tools.model.Tool import Tool

class ToolController:
    IVA_RATE = 0.15

    def __init__(self, strategy):
        self.strategy = strategy

    def setStrategy(self, strategy):
        self.strategy = strategy

    def calculateIva(self, price):
        return round(price * (1 + self.IVA_RATE), 2)

    def createSculpture(self, id, name, price, materials):
        finalPrice = self.calculateIva(price)
        return self.strategy.create(Tool(id, name, price, materials, finalPrice))

    def getAllTools(self):
        return self.strategy.read()

    def findSculptureById(self, id):
        return self.strategy.find(id)

    def updateSculpture(self, id, name, price, materials):
        newPriceWithIva = self.calculateIva(price)
        return self.strategy.update(id, Tool(id, name, price, materials, newPriceWithIva))

    def deleteSculpture(self, id):
        return self.strategy.delete(id)
