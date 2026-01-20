import tkinter as tk
from view.frm_photographer import FrmPhotographer

class FrmMain(tk.Tk):

    def __init__(self):
        super().__init__()
        self.title("Photo Studio System")
        self.geometry("300x200")

        tk.Label(self, text="PHOTO STUDIO SYSTEM").pack(pady=10)
        tk.Label(self, text="Welcome").pack(pady=5)

        tk.Button(
            self,
            text="[ Register Photographer ]",
            command=self.open_photographer_form
        ).pack(pady=10)

        tk.Button(self, text="[ Exit ]", command=self.destroy).pack()

    def open_photographer_form(self):
        self.withdraw()
        FrmPhotographer(self)
