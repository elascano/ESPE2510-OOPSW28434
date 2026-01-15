import tkinter as tk
from tkinter import messagebox

class FrmAdd(tk.Toplevel):
    def __init__(self, controller):
        super().__init__()
        self.controller = controller
        self.title("Add Product")
        self.geometry("300x250")
        self.resizable(False, False)

        tk.Label(self, text="ID:").pack()
        self.entry_id = tk.Entry(self, state="readonly")
        self.entry_id.pack()

        tk.Label(self, text="Product:").pack()
        self.entry_product = tk.Entry(self)
        self.entry_product.pack()

        tk.Label(self, text="Price:").pack()
        self.entry_price = tk.Entry(self)
        self.entry_price.pack()

        tk.Button(self, text="Add", command=self.save).pack(pady=15)

        self.load_next_id()

    def load_next_id(self):
        next_id = self.controller.mongo.get_next_id()
        self.entry_id.config(state="normal")
        self.entry_id.delete(0, tk.END)
        self.entry_id.insert(0, next_id)
        self.entry_id.config(state="readonly")

    def save(self):
        product = self.entry_product.get().strip()
        price = self.entry_price.get().strip()

        if not product:
            messagebox.showerror("Error", "Product is required")
            return

        try:
            price = float(price)
        except ValueError:
            messagebox.showerror("Error", "Price must be numeric")
            return

        result = self.controller.mongo.insert(product, price)

        messagebox.showinfo(
            "Final Price",
            f"Price with IVA (15%): ${result['PriceTotal']}"
        )

        self.entry_product.delete(0, tk.END)
        self.entry_price.delete(0, tk.END)
        self.load_next_id()