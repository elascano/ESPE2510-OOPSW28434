import tkinter as tk
from tkinter import ttk, messagebox
from controller.fruit_controller import FruitController


class FruitStoreView(tk.Tk):
    def __init__(self):
        super().__init__()

        self.title("Fruit Store")
        self.geometry("350x300")
        self.resizable(False, False)

        self.controller = FruitController()

        # ===== TITLE =====
        tk.Label(self, text="FRUIT STORE", font=("Arial", 16, "bold")).pack(pady=10)

        # ===== FRUIT =====
        tk.Label(self, text="Fruit name").pack()
        self.combo = ttk.Combobox(self, state="readonly")
        self.combo.pack()
        self.combo.bind("<<ComboboxSelected>>", self.update_data)

        # ===== QUANTITY =====
        tk.Label(self, text="Quantity").pack()
        self.quantity = tk.Spinbox(self, from_=1, to=100)
        self.quantity.pack()

        # ===== INFO =====
        self.lbl_price = tk.Label(self, text="Price: $0")
        self.lbl_price.pack()

        self.lbl_total = tk.Label(self, text="Total: $0")
        self.lbl_total.pack()

        # ===== BUTTONS =====
        tk.Button(self, text="Buy", command=self.buy).pack(pady=5)
        tk.Button(self, text="Manage Stock", command=self.open_admin).pack()

        self.load_fruits()

    # ================= METHODS =================

    def load_fruits(self):
        fruits = self.controller.get_fruit_names()
        self.combo["values"] = fruits
        if fruits:
            self.combo.current(0)
            self.update_data()

    def update_data(self, event=None):
        name = self.combo.get()
        price = self.controller.get_price_by_name(name)
        self.lbl_price.config(text=f"Price: ${price}")

    def buy(self):
        name = self.combo.get()

        try:
            qty = int(self.quantity.get())
            if qty <= 0:
                raise ValueError
        except ValueError:
            messagebox.showerror("Error", "Quantity must be a positive number")
            return

        total = self.controller.buy_fruit(name, qty)

        if total == -1:
            messagebox.showerror("Error", "Fruit not found")
        elif total == -2:
            messagebox.showerror("Error", "Not enough stock")
        else:
            self.lbl_total.config(text=f"Total: ${total}")
            messagebox.showinfo("Success", "Purchase successful")
            self.load_fruits()

    def open_admin(self):
        from view.manage_stock_view import ManageStockView  # ✅ IMPORT LOCAL
        self.destroy()
        ManageStockView().mainloop()
