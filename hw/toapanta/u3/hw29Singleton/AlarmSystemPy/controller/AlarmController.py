from tkinter import messagebox
from model.AlarmService import AlarmService
from controller.Database import Database

class AlarmController:
    def __init__(self):
        self.view = None
        self.service = AlarmService.get_instance()

    def set_view(self, view):
        self.view = view

    def run(self):
        self.check_inventory()
        self.view.mainloop()

    def handle_update(self, value):
        try:
            new_limit = int(value)
            self.service.update_min_stock(new_limit)
            messagebox.showinfo("Success", "Stock updated!")
            self.check_inventory()
        except ValueError:
            messagebox.showerror("Error", "Invalid number")

    def check_inventory(self):
        db = Database.get_database()
        products = db["Products"].find()
        for product in products:
            if self.service.is_low_stock(product["stock"]):
                self.view.show_alert(f"Product: {product['name']}\nStock: {product['stock']}")