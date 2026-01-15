import tkinter as tk
from tkinter import ttk

class FrmList(tk.Toplevel):
    def __init__(self, controller):
        super().__init__()
        self.controller = controller
        self.title("List Entities")
        self.geometry("500x300")

        self.table = ttk.Treeview(self, columns=("ID", "Attributes"), show="headings")
        self.table.heading("ID", text="ID")
        self.table.heading("Attributes", text="Attributes")

        self.table.pack(fill=tk.BOTH, expand=True)

        self.load_data()

    def load_data(self):
        for row in self.table.get_children():
            self.table.delete(row)

        entities = self.controller.get_all()
        for entity in entities:
            self.table.insert("", tk.END, values=(entity.id, entity.attributes))