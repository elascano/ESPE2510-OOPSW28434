from model.alarm_config import AlarmConfig
from utils.mongo_connection import MongoConnection
from tkinter import messagebox

class AlarmController:
    def __init__(self):
        self.config = AlarmConfig()
        db = MongoConnection.get_database()
        self.product_collection = db["Products"]

    def handle_update(self, str_input, view):
        try:
            new_limit = int(str_input)
            self.config.update_minimum_stock(new_limit)
            messagebox.showinfo("Success", "Configuration updated in Atlas")
            self.check_stock_levels(view)
        except ValueError:
            messagebox.showerror("Error", "Please enter a valid number")

    def check_stock_levels(self, view):
        
        for doc in self.product_collection.find():
            stock = doc.get("stock")
            if stock <= self.config.get_minimum_stock():
                view.show_low_stock_alert(doc.get("id"), doc.get("name"), stock)