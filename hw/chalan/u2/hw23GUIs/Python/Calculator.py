import tkinter as tk
from tkinter import ttk
from datetime import datetime

class FrmContacts(tk.Tk):
    def __init__(self):
        super().__init__()

        self.title("Contacts")
        self.geometry("700x650")

        # -------- TITLE PANEL --------
        title_frame = tk.Frame(self)
        title_frame.pack(pady=10)

        lbl_title = tk.Label(title_frame, text="CONTACTS", font=("Segoe UI", 25))
        lbl_title.pack()

        # -------- MAIN FORM PANEL --------
        form_frame = tk.Frame(self)
        form_frame.pack(pady=10)

        # ----- ID LABEL (solo texto, sin entrada) -----
        lbl_id_text = tk.Label(form_frame, text="ID:")
        lbl_id_text.grid(row=0, column=0, sticky="e", padx=5, pady=5)

        lbl_id = tk.Label(form_frame, text="(auto)")
        lbl_id.grid(row=0, column=1, sticky="w", padx=5, pady=5)

        # ----- FIRST NAME -----
        lbl_fname = tk.Label(form_frame, text="First Name:")
        lbl_fname.grid(row=1, column=0, sticky="e", padx=5, pady=5)

        self.txt_fname = tk.Entry(form_frame, width=25)
        self.txt_fname.grid(row=1, column=1, padx=5, pady=5)

        # ----- LAST NAME -----
        lbl_lname = tk.Label(form_frame, text="Last Name:")
        lbl_lname.grid(row=2, column=0, sticky="e", padx=5, pady=5)

        self.txt_lname = tk.Entry(form_frame, width=25)
        self.txt_lname.grid(row=2, column=1, padx=5, pady=5)

        # ----- BORN DAY (3 COMBOBOX) -----
        lbl_born = tk.Label(form_frame, text="Born Day:")
        lbl_born.grid(row=3, column=0, sticky="e", padx=5, pady=5)

        # Día
        self.cmb_day = ttk.Combobox(form_frame, values=list(range(1, 32)), width=5)
        self.cmb_day.grid(row=3, column=1, sticky="w", padx=2)
        self.cmb_day.current(0)

        # Mes
        self.cmb_month = ttk.Combobox(
            form_frame,
            values=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"],
            width=7
        )
        self.cmb_month.grid(row=3, column=1, padx=2)
        self.cmb_month.current(0)

        # Año
        years = list(range(1970, 2025))
        self.cmb_year = ttk.Combobox(form_frame, values=years, width=6)
        self.cmb_year.grid(row=3, column=1, sticky="e", padx=2)
        self.cmb_year.current(0)
        self.cmb_year.bind("<<ComboboxSelected>>", self.update_age)

        # ----- AGE (Label auto calculado) -----
        lbl_age_title = tk.Label(form_frame, text="Age:")
        lbl_age_title.grid(row=4, column=0, sticky="e", padx=5, pady=5)

        self.lbl_age = tk.Label(form_frame, text="0")
        self.lbl_age.grid(row=4, column=1, sticky="w", padx=5, pady=5)

        # ----- TYPE -----
        lbl_type = tk.Label(form_frame, text="Type:")
        lbl_type.grid(row=5, column=0, sticky="e", padx=5, pady=5)

        self.cmb_type = ttk.Combobox(
            form_frame,
            values=["Family", "Friend", "Job", "Unknown"],
            width=15
        )
        self.cmb_type.grid(row=5, column=1, padx=5, pady=5)
        self.cmb_type.current(0)

        # ----- SEX -----
        lbl_sex = tk.Label(form_frame, text="Sex:")
        lbl_sex.grid(row=6, column=0, sticky="e", padx=5, pady=5)

        self.sex_var = tk.StringVar()

        rad_male = tk.Radiobutton(form_frame, text="Male", variable=self.sex_var, value="Male")
        rad_male.grid(row=6, column=1, sticky="w", padx=5)

        rad_female = tk.Radiobutton(form_frame, text="Female", variable=self.sex_var, value="Female")
        rad_female.grid(row=6, column=1, padx=5)

        self.sex_var.set("Male")

        # ----- HOBBIES -----
        lbl_hobbies = tk.Label(form_frame, text="Hobbies:")
        lbl_hobbies.grid(row=7, column=0, sticky="ne", padx=5, pady=5)

        self.lst_hobbies = tk.Listbox(form_frame, height=6, selectmode="multiple")
        hobbies = ["Play Soccer", "DJ", "Read", "Cook", "Swim", "Sing", "Play an Instrument"]
        for h in hobbies:
            self.lst_hobbies.insert(tk.END, h)
        self.lst_hobbies.grid(row=7, column=1, sticky="w")

        # ----- COMMENTS -----
        lbl_comments = tk.Label(form_frame, text="Comments:")
        lbl_comments.grid(row=8, column=0, sticky="ne", padx=5, pady=5)

        self.txa_comments = tk.Text(form_frame, width=35, height=5)
        self.txa_comments.grid(row=8, column=1, padx=5, pady=5)

        # ----- SAVE BUTTON -----
        btn_save = tk.Button(self, text="Save", width=10, command=self.save_data)
        btn_save.pack(pady=20)

    # ---------- FUNCION PARA CALCULAR EDAD ----------
    def update_age(self, event=None):
        try:
            year = int(self.cmb_year.get())
            current_year = datetime.now().year
            age = current_year - year
            self.lbl_age.config(text=str(age))
        except:
            self.lbl_age.config(text="0")

    # ---------- GUARDAR DATOS ----------
    def save_data(self):
        print("Saving data...")
        # Puedes programar la lógica aquí

if __name__ == "__main__":
    app = FrmContacts()
    app.mainloop()
