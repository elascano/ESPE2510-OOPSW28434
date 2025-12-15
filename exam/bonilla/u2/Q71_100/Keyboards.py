from pymongo import MongoClient
import tkinter as tk
from tkinter import ttk

class Keyboard:
    def __init__(self, id, name, description, price, stock):
        self.id = id
        self.name = name
        self.description = description
        self.price = price
        self.stock = stock

    def get_id(self):
        return self.id

    def get_name(self):
        return self.name

    def get_description(self):
        return self.description

    def get_price(self):
        return self.price

    def get_stock(self):
        return self.stock


class KeyboardRepository:
    def __init__(self):
        client = MongoClient("mongodb+srv://Arelis:Arelis2006@cluster0.qdn4zsf.mongodb.net/")
        db = client["Acme"]
        self.collection = db["Keyboards"]

    def get_all_keyboards(self):
        keyboards = []
        for data in self.collection.find():
            keyboards.append(
                Keyboard(
                    str(data["_id"]),
                    data["name"],
                    data["description"],
                    data["price"],
                    data["stock"]
                )
            )
        return keyboards


class KeyboardController:
    def __init__(self, repository):
        self.repository = repository

    def list_all_keyboards(self):
        return self.repository.get_all_keyboards()


class KeyboardView:
    def __init__(self, controller):
        self.controller = controller
        self.window = tk.Tk()
        self.window.title("Keyboard List")

        self.table = ttk.Treeview(
            self.window,
            columns=("ID", "Name", "Description", "Price", "Stock"),
            show="headings"
        )

        self.table.heading("ID", text="ID")
        self.table.heading("Name", text="Name")
        self.table.heading("Description", text="Description")
        self.table.heading("Price", text="Price")
        self.table.heading("Stock", text="Stock")

        self.table.pack(fill=tk.BOTH, expand=True)
        self.load_data()
        self.window.mainloop()

    def load_data(self):
        keyboards = self.controller.list_all_keyboards()
        for k in keyboards:
            self.table.insert(
                "",
                tk.END,
                values=(
                    k.get_id(),
                    k.get_name(),
                    k.get_description(),
                    k.get_price(),
                    k.get_stock()
                )
            )


repository = KeyboardRepository()
controller = KeyboardController(repository)
KeyboardView(controller)
