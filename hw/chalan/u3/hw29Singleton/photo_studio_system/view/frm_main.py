import tkinter as tk
from view.frm_photographer import FrmPhotographer
from view.frm_contract import FrmContract

class FrmMain(tk.Tk):

    def __init__(self):
        super().__init__()
        self.title("Photo Studio System")
        self.geometry("300x250")
        self.eval('tk::PlaceWindow . center')

        tk.Label(self, text="PHOTO STUDIO SYSTEM", font=("Arial", 14, "bold")).pack(pady=10)
        tk.Label(self, text="Welcome").pack(pady=5)

        tk.Button(self, text=" Register Photographer ", command=self.open_register).pack(pady=5)
        tk.Button(self, text=" Contract ", command=self.open_contract).pack(pady=5)
        tk.Button(self, text=" Exit ", command=self.destroy).pack(pady=20)

    def open_register(self):
        self.withdraw()
        FrmPhotographer(self)

    def open_contract(self):
        self.withdraw()
        FrmContract(self)