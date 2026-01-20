import tkinter as tk
from tkinter import messagebox
from tkinter import ttk
from controller.photographer_controller import PhotographerController

class FrmPhotographer(tk.Toplevel):

    def __init__(self, parent):
        super().__init__(parent)
        self.parent = parent
        self.title("Register Photographer")
        self.geometry("350x300")

        self.controller = PhotographerController()

        self.create_widgets()
        self.load_specialties()

    def create_widgets(self):
        tk.Label(self, text="REGISTER PHOTOGRAPHER").pack(pady=10)

        tk.Label(self, text="Name").pack()
        self.txt_name = tk.Entry(self)
        self.txt_name.pack()

        tk.Label(self, text="Specialty").pack()
        self.cmb_specialty = ttk.Combobox(self, state="readonly")
        self.cmb_specialty.pack()

        tk.Label(self, text="Years of Experience").pack()
        self.txt_experience = tk.Entry(self)
        self.txt_experience.pack()

        tk.Label(self, text="Hourly Rate ($)").pack()
        self.txt_rate = tk.Entry(self)
        self.txt_rate.pack()

        tk.Button(self, text="[ Save ]", command=self.save).pack(pady=5)
        tk.Button(self, text="[ Cancel ]", command=self.close).pack()

    def load_specialties(self):
        self.cmb_specialty["values"] = (
            "Wedding",
            "Portrait",
            "Sports",
            "Nature",
            "Fashion"
        )
        self.cmb_specialty.current(0)

    def save(self):
        try:
            self.controller.register(
                self.txt_name.get(),
                self.cmb_specialty.get(),
                self.txt_experience.get(),
                self.txt_rate.get()
            )
            messagebox.showinfo("Success", "Photographer saved successfully")
            self.close()
        except Exception as e:
            messagebox.showerror("Error", str(e))

    def close(self):
        self.destroy()
        self.parent.deiconify()
