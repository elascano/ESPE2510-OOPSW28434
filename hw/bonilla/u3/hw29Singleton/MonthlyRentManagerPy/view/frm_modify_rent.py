import tkinter as tk
from tkinter import messagebox
from utils.rental_manager import RentalManager

class FrmModifyRent(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Modify Rent")
        self.geometry("350x200")

        tk.Label(self, text="Modify Rent", font=("Perpetua", 20, "italic")).pack(pady=10)

        tk.Label(self, text="New monthly rent:").pack()
        self.txt_rent = tk.Entry(self)
        self.txt_rent.pack(pady=5)

        tk.Button(self, text="Update", command=self.update_rent).pack(pady=5)
        tk.Button(self, text="Back to menu", command=self.back_to_menu).pack(pady=5)

    def update_rent(self):
        try:
            new_rent = float(self.txt_rent.get())
            if new_rent <= 0:
                raise ValueError

            RentalManager().update_monthly_rent(new_rent)
            messagebox.showinfo("Success", f"New monthly rent: ${new_rent}")
        except ValueError:
            messagebox.showerror("Error", "Enter a valid rent value")

    def back_to_menu(self):
        from view.frm_menu import FrmMenu
        self.destroy()
        FrmMenu().mainloop()