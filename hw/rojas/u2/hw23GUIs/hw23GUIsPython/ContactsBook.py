import tkinter as tk
from tkinter import ttk, messagebox
from tkcalendar import Calendar
from datetime import date, datetime


class ContactForm(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("CONTACTS")
        self.configure(bg="#d9dde3") 
        title = tk.Label(self, text="CONTACTS", font=("Segoe UI", 20, "bold"),
                         bg="#d9dde3")
        title.grid(row=0, column=0, columnspan=4, pady=(15, 25))
        tk.Label(self, text="id:", bg="#d9dde3").grid(row=1, column=0,
                                                     sticky="e", padx=(30, 5))
        tk.Label(self, text="First Name:", bg="#d9dde3").grid(row=2, column=0,
                                                             sticky="e",
                                                             padx=(30, 5),
                                                             pady=2)
        self.first_name_var = tk.StringVar(value="Josue")
        tk.Entry(self, textvariable=self.first_name_var, width=25).grid(
            row=2, column=1, sticky="w", pady=2
        )
        tk.Label(self, text="Last Name:", bg="#d9dde3").grid(row=3, column=0,
                                                            sticky="e",
                                                            padx=(30, 5),
                                                            pady=2)
        self.last_name_var = tk.StringVar(value="Rojas")
        tk.Entry(self, textvariable=self.last_name_var, width=25).grid(
            row=3, column=1, sticky="w", pady=2
        )
        tk.Label(self, text="Birth Date:", bg="#d9dde3").grid(row=4, column=0,
                                                             sticky="e",
                                                             padx=(30, 5),
                                                             pady=2)

        self.birth_date_var = tk.StringVar(value="4 de noviembre de 2006")
        self.birth_entry = tk.Entry(self, textvariable=self.birth_date_var,
                                    width=25)
        self.birth_entry.grid(row=4, column=1, sticky="w", pady=2)

        tk.Button(self, text="...", width=3,
                  command=self.open_calendar).grid(row=4, column=2, padx=(5, 0))
        tk.Label(self, text="Age:", bg="#d9dde3").grid(row=5, column=0,
                                                      sticky="e",
                                                      padx=(30, 5),
                                                      pady=2)
        self.age_var = tk.StringVar(value="")
        tk.Label(self, textvariable=self.age_var, bg="#d9dde3").grid(
            row=5, column=1, sticky="w", pady=2
        )
        tk.Label(self, text="Type:", bg="#d9dde3").grid(row=6, column=0,
                                                       sticky="e",
                                                       padx=(30, 5),
                                                       pady=2)
        self.type_var = tk.StringVar()
        type_combo = ttk.Combobox(self, textvariable=self.type_var,
                                  values=["Family", "Friend", "Job", "Unknown"],
                                  width=18, state="readonly")
        type_combo.set("Family")
        type_combo.grid(row=6, column=1, sticky="w", pady=2)
        tk.Label(self, text="Sex:", bg="#d9dde3").grid(row=7, column=0,
                                                      sticky="e",
                                                      padx=(30, 5),
                                                      pady=2)
        self.sex_var = tk.StringVar(value="Male")
        tk.Radiobutton(self, text="Male", variable=self.sex_var, value="Male",
                       bg="#d9dde3").grid(row=7, column=1, sticky="w")
        tk.Radiobutton(self, text="Female", variable=self.sex_var,
                       value="Female", bg="#d9dde3").grid(row=8, column=1,
                                                          sticky="w")
        tk.Label(self, text="Hobbies:", bg="#d9dde3").grid(row=9, column=0,
                                                          sticky="ne",
                                                          padx=(30, 5),
                                                          pady=(10, 2))
        hobbies_frame = tk.Frame(self)
        hobbies_frame.grid(row=9, column=1, sticky="w", pady=(10, 2))

        self.hobbies_listbox = tk.Listbox(hobbies_frame, selectmode="extended",
                                          width=22, height=6)
        hobbies = [
            "Play Soccer",
            "Djing",
            "Read",
            "Cook",
            "Swim",
            "Sing",
            "Play an instrument",
        ]
        for hob in hobbies:
            self.hobbies_listbox.insert(tk.END, hob)

        self.hobbies_listbox.selection_set(5, 6)
        self.hobbies_listbox.pack()

        tk.Label(self, text="Comments:", bg="#d9dde3").grid(row=1, column=3,
                                                            sticky="w",
                                                            padx=(20, 30))

        self.comments_text = tk.Text(self, width=35, height=8)
        self.comments_text.grid(row=2, column=3, rowspan=4, padx=(20, 30),
                                pady=2)
        self.comments_text.insert("1.0", "That's my budy")

        save_button = tk.Button(self, text="SAVE", width=10,
                                command=self.save_contact)
        save_button.grid(row=11, column=0, columnspan=4, pady=(25, 20))

        self.grid_columnconfigure(1, weight=1)
        self.grid_columnconfigure(3, weight=1)

    def open_calendar(self):
        """Abre una ventana con calendario para elegir la fecha."""
        top = tk.Toplevel(self)
        top.title("Select Birth Date")
        cal = Calendar(top, selectmode="day", date_pattern="dd/mm/yyyy",
                       locale="es_ES")
        cal.pack(padx=10, pady=10)

        def on_ok():
            selected = cal.get_date() 
            try:
                d = datetime.strptime(selected, "%d/%m/%Y").date()
                meses = [
                    "enero", "febrero", "marzo", "abril", "mayo", "junio",
                    "julio", "agosto", "septiembre", "octubre", "noviembre",
                    "diciembre",
                ]
                texto = f"{d.day} de {meses[d.month-1]} de {d.year}"
                self.birth_date_var.set(texto)
                self.update_age(d)
            except ValueError:
                self.birth_date_var.set(selected)
            top.destroy()

        tk.Button(top, text="OK", command=on_ok).pack(pady=(0, 10))

    def update_age(self, birth_date: date):
        today = date.today()
        years = today.year - birth_date.year
        if (today.month, today.day) < (birth_date.month, birth_date.day):
            years -= 1
        self.age_var.set(f"{years} years")

    def save_contact(self):
        first = self.first_name_var.get()
        last = self.last_name_var.get()
        birth = self.birth_date_var.get()
        type_ = self.type_var.get()
        sex = self.sex_var.get()
        comments = self.comments_text.get("1.0", "end").strip()

        hobbies_indices = self.hobbies_listbox.curselection()
        hobbies = [self.hobbies_listbox.get(i) for i in hobbies_indices]

        summary = (
            f"First Name: {first}\n"
            f"Last Name: {last}\n"
            f"Birth Date: {birth}\n"
            f"Type: {type_}\n"
            f"Sex: {sex}\n"
            f"Hobbies: {', '.join(hobbies)}\n"
            f"Comments: {comments}"
        )

        messagebox.showinfo("Contact Saved", summary)


if __name__ == "__main__":
    app = ContactForm()
    app.mainloop()
