import tkinter as tk
from tkinter import messagebox
from controller.fruit_controller import FruitController


class ManageStockView(tk.Tk):
    def __init__(self):
        super().__init__()

        self.controller = FruitController()

        self.title("ADMIN – MANAGE STOCK")
        self.geometry("350x420")
        self.resizable(False, False)

        # ===== Title =====
        tk.Label(
            self,
            text="ADMIN – MANAGE STOCK",
            font=("Arial", 14, "bold")
        ).pack(pady=10)

        # ===== Delete =====
        tk.Label(self, text="Select Fruit to Delete").pack()

        self.fruit_var = tk.StringVar()
        self.combo = tk.OptionMenu(self, self.fruit_var, "")
        self.combo.config(width=20)
        self.combo.pack(pady=5)

        tk.Button(self, text="Delete", command=self.delete_fruit).pack(pady=10)

        # ===== Add =====
        tk.Label(self, text="Add New Fruit", font=("Arial", 12, "bold")).pack(pady=10)

        tk.Label(self, text="Fruit Name").pack()
        self.name_entry = tk.Entry(self)
        self.name_entry.pack()

        tk.Label(self, text="Price").pack()
        self.price_entry = tk.Entry(self)
        self.price_entry.pack()

        tk.Label(self, text="Stock").pack()
        self.stock_entry = tk.Entry(self)
        self.stock_entry.pack()

        tk.Button(self, text="Add", command=self.add_fruit).pack(pady=10)
        tk.Button(self, text="Back to Store", command=self.back_to_store).pack()

        self.refresh_combo()

    # ===== METHODS =====

    def refresh_combo(self):
        menu = self.combo["menu"]
        menu.delete(0, "end")

        fruits = self.controller.get_fruit_names()
        if fruits:
            self.fruit_var.set(fruits[0])
            for fruit in fruits:
                menu.add_command(
                    label=fruit,
                    command=lambda v=fruit: self.fruit_var.set(v)
                )

    def delete_fruit(self):
        name = self.fruit_var.get()
        if not name:
            messagebox.showerror("Error", "Select a fruit")
            return

        self.controller.delete_fruit(name)
        messagebox.showinfo("Success", "Fruit deleted")
        self.refresh_combo()

    def add_fruit(self):
        name = self.name_entry.get().strip()

        try:
            price = float(self.price_entry.get())
            stock = int(self.stock_entry.get())
            if price <= 0 or stock < 0:
                raise ValueError
        except ValueError:
            messagebox.showerror("Error", "Invalid price or stock")
            return

        if self.controller.exists_fruit(name):
            messagebox.showerror("Error", "Fruit already exists")
            return

        self.controller.add_fruit(name, price, stock)
        messagebox.showinfo("Success", "Fruit added")

        self.name_entry.delete(0, tk.END)
        self.price_entry.delete(0, tk.END)
        self.stock_entry.delete(0, tk.END)
        self.refresh_combo()

    def back_to_store(self):
        from view.fruit_store_view import FruitStoreView  # ✅ IMPORT LOCAL
        self.destroy()
        FruitStoreView().mainloop()
