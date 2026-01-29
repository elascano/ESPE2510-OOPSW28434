class Instrument:
    def __init__(self, id, name, price, materials, priceWithIva):
        self.id = id
        self.name = name
        self.price = price
        self.materials = materials
        self.priceWithIva = priceWithIva

    def getId(self): return self.id
    def setId(self, id): self.id = id

    def getName(self): return self.name
    def setName(self, name): self.name = name

    def getPrice(self): return self.price
    def setPrice(self, price): self.price = price

    def getMaterials(self): return self.materials
    def setMaterials(self, materials): self.materials = materials

    def getPriceWithIva(self): return self.priceWithIva
    def setPriceWithIva(self, priceWithIva): self.priceWithIva = priceWithIva
