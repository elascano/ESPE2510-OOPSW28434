import tkinter as tk
from tkinter import messagebox

class StockAlertViewTk:

    def __init__(self, controller):
        self.controller = controller

        self.root = tk.Tk()
        self.root.title("Stock Alert System")
        self.root.geometry("500x400")

        # Title
        title = tk.Label(self.root, text="Stock Alert System", font=("Arial", 18))
        title.pack(pady=10)

        # Check stock button
        btnCheck = tk.Button(self.root, text="Check Low Stock", command=self.onCheckStock)
        btnCheck.pack(pady=10)

        # Minimum stock update section
        frame = tk.Frame(self.root)
        frame.pack(pady=20)

        lbl = tk.Label(frame, text="New Minimum Stock:")
        lbl.grid(row=0, column=0, padx=5)

        self.entryMinimum = tk.Entry(frame)
        self.entryMinimum.grid(row=0, column=1, padx=5)

        btnUpdate = tk.Button(frame, text="Update", command=self.onUpdateMinimumStock)
        btnUpdate.grid(row=0, column=2, padx=5)

        # Result area
        self.textArea = tk.Text(self.root, height=12, width=55)
        self.textArea.pack(pady=10)

    def start(self):
        self.root.mainloop()

    # ---------- Events ----------

    def onCheckStock(self):
        self.controller.checkStock()

    def onUpdateMinimumStock(self):
        value = self.entryMinimum.get()
        try:
            value = int(value)
            self.controller.updateMinimumStock(value)
        except ValueError:
            messagebox.showerror("Error", "Please enter a valid integer")

    # ---------- Methods called by Controller ----------

    def showLowStockProducts(self, products):
        self.textArea.delete("1.0", tk.END)

        if not products:
            self.textArea.insert(tk.END, "✅ No products with low stock.\n")
            return

        for p in products:
            msg = (
                "⚠️ LOW STOCK ALERT\n"
                f"ID: {p.id}\n"
                f"Name: {p.name}\n"
                f"Stock: {p.stock}\n"
                "--------------------------\n"
            )
            self.textArea.insert(tk.END, msg)

            messagebox.showwarning(
                "Low Stock Alert",
                f"Product: {p.name}\nStock: {p.stock}"
            )

    def showInfoMessage(self, msg):
        messagebox.showinfo("Info", msg)
