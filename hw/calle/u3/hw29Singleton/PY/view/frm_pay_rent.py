import tkinter as tk
from tkinter import messagebox
from controller.rent_controller import RentController

class FrmPayRent(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Pay Rent")
        self.geometry("400x300")
        self.controller = RentController()
        self.resident_id = 1756055065
        self.resident_name = "John Doe"
        self.setup_ui()

    def setup_ui(self):
        tk.Label(self, text="Pay Rent", font=("Perpetua", 24, "bold")).pack(pady=10)
        tk.Label(self, text=f"Resident ID: {self.resident_id}").pack()
        
        tk.Label(self, text="Select Month:").pack()
        self.month_var = tk.StringVar(self)
        self.month_var.set("0")
        
        options = [str(i) for i in range(13)]
        self.lbl_total = tk.Label(self, text="Value for rental: $0.00", fg="blue")
        
        def on_change(*args):
            total = self.controller.get_total_to_pay(self.resident_id, self.resident_name, int(self.month_var.get()))
            self.lbl_total.config(text=f"Value for rental: ${total:.2f}")

        tk.OptionMenu(self, self.month_var, *options, command=on_change).pack()
        self.lbl_total.pack(pady=10)
        
        tk.Button(self, text="Pay", command=self.process_payment).pack(pady=5)
        tk.Button(self, text="Back", command=self.back_to_menu).pack()

    def process_payment(self):
        total = self.lbl_total.cget("text")
        messagebox.showinfo("Success", f"Payment successful!\n{total}")

    def back_to_menu(self):
        from view.frm_menu import FrmMenu
        self.destroy()
        FrmMenu().mainloop()