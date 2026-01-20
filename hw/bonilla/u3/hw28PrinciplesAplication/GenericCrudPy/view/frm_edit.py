import tkinter as tk
from tkinter import messagebox
from model.product import GenericEntity

class FrmEdit(tk.Toplevel):
    def __init__(self, controller):
        super().__init__()
        self.controller = controller
        self.title("Edit Entity")
        self.geometry("300x250")

        tk.Label(self, text="ID (numbers only):").pack()
        self.entry_id = tk.Entry(self)
        self.entry_id.pack()

        tk.Button(self, text="Search", command=self.search).pack(pady=5)

        tk.Label(self, text="New Attributes:").pack()
        self.entry_attributes = tk.Entry(self)
        self.entry_attributes.pack()

        tk.Button(self, text="Update", command=self.update).pack(pady=10)

    def search(self):
        entity_id = self.entry_id.get().strip()

        if not entity_id.isdigit():
            messagebox.showerror("Error", "ID must be numeric")
            return

        entity = self.controller.get_by_id(entity_id)
        if not entity:
            messagebox.showerror("Error", "Entity not found")
            return

        self.entry_attributes.delete(0, tk.END)
        self.entry_attributes.insert(0, entity.attributes)

    def update(self):
        entity_id = self.entry_id.get().strip()
        attributes = self.entry_attributes.get().strip()

        if attributes == "":
            messagebox.showerror("Error", "Attributes cannot be empty")
            return

        entity = GenericEntity(entity_id, attributes)

        if self.controller.update(entity_id, entity):
            messagebox.showinfo("Success", "Entity updated")
            self.destroy()
        else:
            messagebox.showerror("Error", "Update failed")