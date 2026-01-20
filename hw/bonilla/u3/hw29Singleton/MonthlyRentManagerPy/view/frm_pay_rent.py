import tkinter as tk
from tkinter import messagebox
from controller.rent_controller import RentController

class FrmPayRent(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Pay Rent")
        self.geometry("400x250")

        self.controller = RentController()
        self.resident_id = 1756055065
        self.resident_name = "John Doe"

        tk.Label(self, text="Pay Rent", font=("Perpetua", 20, "italic")).pack(pady=10)
        tk.Label(self, text=f"Resident ID: {self.resident_id}").pack()

        tk.Label(self, text="Select months:").pack(pady=5)

        self.month_var = tk.StringVar(value="0")
        tk.OptionMenu(
            self,
            self.month_var,
            *[str(i) for i in range(13)],
            command=self.calculate_rent
        ).pack()

        self.lbl_total = tk.Label(self, text="Total: $0.00")
        self.lbl_total.pack(pady=10)

        tk.Button(self, text="Pay", command=self.pay_rent).pack()
        tk.Button(self, text="Back to menu", command=self.back_to_menu).pack(pady=5)

    def calculate_rent(self, value):
        months = int(value)
        total = self.controller.get_total_to_pay(
            self.resident_id,
            self.resident_name,
            months
        )
        self.lbl_total.config(text=f"Total: ${total}")

    def pay_rent(self):
        months = int(self.month_var.get())
        if months <= 0:
            messagebox.showerror("Error", "Select at least 1 month")
            return

        total = self.controller.get_total_to_pay(
            self.resident_id,
            self.resident_name,
            months
        )

        messagebox.showinfo(
            "Payment Completed",
            f"Resident ID: {self.resident_id}\n"
            f"Months: {months}\n"
            f"Total Paid: ${total}"
        )

    def back_to_menu(self):
        from view.frm_menu import FrmMenu
        self.destroy()
        FrmMenu().mainloop()