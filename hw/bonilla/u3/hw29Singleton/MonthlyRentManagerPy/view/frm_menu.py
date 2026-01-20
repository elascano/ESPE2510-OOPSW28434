import tkinter as tk

class FrmMenu(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Monthly Rent Manager")
        self.geometry("350x200")

        lbl = tk.Label(
            self,
            text="WELCOME TO THE SYSTEM",
            font=("Perpetua", 16, "italic")
        )
        lbl.pack(pady=30)

        tk.Button(self, text="Pay Rent", command=self.open_pay_rent).pack(pady=5)
        tk.Button(self, text="Modify Rent", command=self.open_modify_rent).pack(pady=5)
        tk.Button(self, text="Exit", command=self.destroy).pack(pady=5)

    def open_pay_rent(self):
        from view.frm_pay_rent import FrmPayRent
        self.destroy()
        FrmPayRent().mainloop()

    def open_modify_rent(self):
        from view.frm_modify_rent import FrmModifyRent
        self.destroy()
        FrmModifyRent().mainloop()