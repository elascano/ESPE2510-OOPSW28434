import tkinter as tk
from tkinter import ttk, messagebox
from tkcalendar import DateEntry
from datetime import date
from MonitorModel import Monitor
from MonitorController import MonitorController

class FrmContacts(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Monitors")
        self.geometry("850x600")
        
        self.lbl_title = tk.Label(self, text="ADD MONITORS", font=("Segoe UI", 24))
        self.lbl_title.pack(pady=20)

        self.frm_form = tk.Frame(self)
        self.frm_form.pack(expand=True, fill="both", padx=20)
        self.txt_id = tk.Entry(self.frm_form, width=20)
        self.txt_id.grid(row=1, column=1, sticky="w", pady=5)

        tk.Label(self.frm_form, text="id:").grid(row=0, column=0, sticky="w", pady=5)


        tk.Label(self.frm_form, text="brand:").grid(row=1, column=0, sticky="w", pady=5)
        self.txt_brand = tk.Entry(self.frm_form, width=20)
        self.txt_brand.grid(row=0, column=1, sticky="w", pady=5)


        tk.Label(self.frm_form, text="Date of Fabrication:").grid(row=3, column=0, sticky="w", pady=5)
        self.cal_date = DateEntry(self.frm_form, width=17, background='darkblue',
                                  foreground='white', borderwidth=2, date_pattern='dd/mm/yyyy')
        self.cal_date.grid(row=3, column=1, sticky="w", pady=5)

        tk.Label(self.frm_form, text="Age:").grid(row=4, column=0, sticky="w", pady=5)
        self.lbl_age_val = tk.Entry(self.frm_form, width=20)
        self.lbl_age_val.grid(row=4, column=1, sticky="w", pady=5)
   

        self.frm_buttons = tk.Frame(self)
        self.frm_buttons.pack(side=tk.BOTTOM, pady=20)
        
        self.btn_save = tk.Button(self.frm_buttons, text="Save", width=10, command=self.btn_save_action)
        self.btn_save.pack()

    def read_values(self):
        id_val = self.txt_id.get()
        first_name = self.txt_brand.get()
        
        birth_date = self.cal_date.get_date()
        birth_year = birth_date.year
        current_year = date.today().year
        age = current_year - birth_year
        self.lbl_age_val.config(text=str(age))

        return Monitor(
            id=id_val, 
            brand=first_name,
            dateOfFabrication=birth_date,
            age=age
        )

    def btn_save_action(self):
        id = self.txt_brand.get()
        birth_date = self.cal_date.get_date() 
            
        monitor = self.read_values()

        response = messagebox.askyesnocancel("SAVE MONITORS?", f"saving monitor --> {monitor}")

        if response is True:
            saved = MonitorController.save(monitor)
            if saved:
                messagebox.showinfo("Success", f"your monitor is saved --> {monitor}")
                self.empty_fields()
            else:
                messagebox.showerror("Error", "Could not save to MongoDB")
                
        elif response is False:
            messagebox.showwarning("Warning", "Your data will be lost")
        else:
            self.txt_id.focus_set()

    def empty_fields(self):
        self.txt_id.delete(0, tk.END)
        self.txt_brand.delete(0, tk.END)
        self.cal_date.set_date(date.today())
        self.lbl_age_val.config(text="0")


if __name__ == "__main__":
    app = FrmContacts()
    app.mainloop()