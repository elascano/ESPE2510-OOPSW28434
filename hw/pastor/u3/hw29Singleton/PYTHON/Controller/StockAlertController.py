from pymongo import MongoClient
from Model.Product import Product
from Config.AlarmConfig import AlarmConfig

class StockAlertController:

    def __init__(self):
        self.client = MongoClient("mongodb+srv://Mathews:Mathews2007@cluster0.6l9ibfh.mongodb.net/?appName=Cluster0")
        self.db = self.client["SingletonDB"]
        self.productsCollection = self.db["products"]

        self.config = AlarmConfig.getInstance()
        self.view = None

    def setView(self, view):
        self.view = view

    def checkStock(self):
        minimumStock = self.config.getMinimumStock()
        lowStockProducts = []

        for doc in self.productsCollection.find():
            product = Product(doc["_id"], doc["name"], doc["stock"])
            if product.stock <= minimumStock:
                lowStockProducts.append(product)

        self.view.showLowStockProducts(lowStockProducts)

    def updateMinimumStock(self, newValue: int):
        self.config.updateMinimumStock(newValue)
        self.view.showInfoMessage(f"Minimum stock updated to {newValue}")
