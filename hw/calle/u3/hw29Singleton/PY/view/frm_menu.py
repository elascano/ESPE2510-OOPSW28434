import tkinter as tk
from view.frm_pay_rent import FrmPayRent
from view.frm_modify_rent import FrmModifyRent

class FrmMenu(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Rent Manager - ESPE")
        self.geometry("400x250")
        self.setup_ui()

    def setup_ui(self):
        tk.Label(self, text="WELCOME TO THE SYSTEM", font=("Perpetua", 18, "bold italic")).pack(pady=60)
        
        menubar = tk.Menu(self)
        options_menu = tk.Menu(menubar, tearoff=0)
        options_menu.add_command(label="Pay Rent", command=self.open_pay_rent)
        options_menu.add_command(label="Modify Rent", command=self.open_modify_rent)
        options_menu.add_separator()
        options_menu.add_command(label="Exit", command=self.quit)
        menubar.add_cascade(label="Options", menu=options_menu)
        self.config(menu=menubar)

    def open_pay_rent(self):
        self.destroy()
        FrmPayRent().mainloop()

    def open_modify_rent(self):
        self.destroy()
        FrmModifyRent().mainloop()