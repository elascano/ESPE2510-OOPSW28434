import tkinter as tk
from tkinter import messagebox, ttk
import threading
from controller.photographer_controller import PhotographerController

class FrmContract(tk.Toplevel):

    def __init__(self, parent):
        super().__init__(parent)
        self.parent = parent
        self.title("Contract")
        self.geometry("400x350")
        
        self.controller = PhotographerController()
        self.photographers_list = [] 
        
        self.create_widgets()
        self.load_data() 

    def create_widgets(self):
        tk.Label(self, text="HIRE PHOTOGRAPHER", font=("Arial", 12, "bold")).pack(pady=10)

        tk.Label(self, text="Select Photographer:").pack()
        self.cmb_photographers = ttk.Combobox(self, state="readonly")
        self.cmb_photographers.pack()

        tk.Label(self, text="Hours (1-5):").pack(pady=5)
        self.spn_hours = tk.Spinbox(self, from_=1, to=5, state="readonly")
        self.spn_hours.pack()

        tk.Button(self, text=" Calculate & Contract ", command=self.calculate).pack(pady=15)
        tk.Button(self, text=" Main Menu ", command=self.close).pack()

    def load_data(self):
        self.cmb_photographers.set("Loading...")
        
        def fetch():
            
            self.photographers_list = self.controller.get_photographers()
            
           
            names = [p.name for p in self.photographers_list]
            
            def update_ui():
                self.cmb_photographers["values"] = names
                if names:
                    self.cmb_photographers.current(0)
                else:
                    self.cmb_photographers.set("No data")
            
            self.after(0, update_ui)

        threading.Thread(target=fetch).start()

    def calculate(self):
        name = self.cmb_photographers.get()
        if not name or name == "Loading..." or name == "No data":
            messagebox.showwarning("Warning", "Select a photographer")
            return

       
        selected_p = next((p for p in self.photographers_list if p.name == name), None)
        
        if selected_p:
            hours = int(self.spn_hours.get())
            total = selected_p.hourly_rate * hours
            
            msg = (f"CONTRACT SUMMARY:\n\n"
                   f"Name: {selected_p.name}\n"
                   f"Rate: ${selected_p.hourly_rate}\n"
                   f"Hours: {hours}\n"
                   f"TOTAL: ${total}")
            
            messagebox.showinfo("Contract Signed", msg)

    def close(self):
        self.destroy()
        self.parent.deiconify()