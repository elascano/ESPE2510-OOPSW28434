import tkinter as tk
from tkinter import messagebox
from view.add_product_view import AddProductView

class MainView(tk.Tk):
    def __init__(self, controller):
        super().__init__()
        self.controller = controller
        self.title("Stock Control System")
        self.geometry("400x300")

        self.listbox = tk.Listbox(self)
        self.listbox.pack(fill=tk.BOTH, expand=True)

        tk.Button(self, text="Add Product", command=self.open_add).pack()
        tk.Button(self, text="Sell", command=self.sell).pack()

        self.refresh()

    def refresh(self):
        self.listbox.delete(0, tk.END)
        for p in self.controller.get_products():
            self.listbox.insert(tk.END, f"{p.name} - Stock: {p.stock}")

    def open_add(self):
        AddProductView(self.controller)
        self.after(500, self.refresh)

    def sell(self):
        index = self.listbox.curselection()
        if not index:
            messagebox.showerror("Error", "Select a product")
            return

        qty = messagebox.askinteger("Sell", "Quantity to sell")
        if qty:
            self.controller.sell_product(index[0], qty)
            self.refresh()
