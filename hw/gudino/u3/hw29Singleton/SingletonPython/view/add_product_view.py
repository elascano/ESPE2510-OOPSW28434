import tkinter as tk
from tkinter import messagebox

class AddProductView(tk.Toplevel):
    def __init__(self, controller):
        super().__init__()
        self.controller = controller
        self.title("Add Product")
        self.geometry("300x200")

        tk.Label(self, text="Product Name").pack()
        self.name_entry = tk.Entry(self)
        self.name_entry.pack()

        tk.Label(self, text="Quantity").pack()
        self.qty_entry = tk.Entry(self)
        self.qty_entry.pack()

        tk.Button(self, text="Add", command=self.add_product).pack(pady=10)

    def add_product(self):
        name = self.name_entry.get()
        qty = self.qty_entry.get()

        if not name or not qty.isdigit():
            messagebox.showerror("Error", "Invalid input")
            return

        self.controller.add_product(name, int(qty))
        messagebox.showinfo("Success", "Product added")
        self.destroy()
