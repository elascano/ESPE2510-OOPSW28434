import tkinter as tk
from tkinter import messagebox
from sale_controller import SaleController

#py -m pip install pymongo dnspython

class BillingView:
    def __init__(self, root):
        self.controller = SaleController()
        self.root = root
        self.root.title("Billing System - ESPE")
        self.root.geometry("350x300")

        tk.Label(root, text="Search Product by Name:", font=("Arial", 10)).pack(pady=10)
        self.txt_search = tk.Entry(root)
        self.txt_search.pack(pady=5)

        tk.Label(root, text="Quantity to Sell:", font=("Arial", 10)).pack(pady=10)
        self.txt_buy_qty = tk.Entry(root)
        self.txt_buy_qty.pack(pady=5)

        tk.Button(root, text="Calculate Total", command=self.calculate, bg="#2ecc71", fg="white", font=("Arial", 10, "bold")).pack(pady=20)

        self.lbl_result = tk.Label(root, text="TOTAL: $0.00", font=("Arial", 14, "bold"), fg="#e74c3c")
        self.lbl_result.pack(pady=10)

    def calculate(self):
        name = self.txt_search.get().strip()
        doc = self.controller.find_by_name(name)
        
        if doc:
            try:
                price_in_db = doc["unitPrice"]
                qty_to_buy = int(self.txt_buy_qty.get())
                total = price_in_db * qty_to_buy
                self.lbl_result.config(text=f"TOTAL: ${total:.2f}")
            except ValueError:
                messagebox.showwarning("Warning", "Please enter a valid number for quantity.")
        else:
            messagebox.showerror("Error", f"Product '{name}' not found in the database.")

if __name__ == "__main__":
    root = tk.Tk()
    app = BillingView(root)
    root.mainloop()