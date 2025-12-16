import tkinter as tk
from tkinter import ttk, messagebox
from datetime import datetime


class Building(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Building")
        self.configure(bg="#d9dde3")

        title = tk.Label(self, text="Building", font=("Segoe UI", 22, "bold"), bg="#d9dde3")
        title.grid(row=0, column=0, columnspan=4, pady=(20, 30))

        tk.Label(self, text="Name:", bg="#d9dde3").grid(row=1, column=0, sticky="e", padx=20)
        self.name_var = tk.StringVar()
        tk.Entry(self, textvariable=self.name_var, width=30).grid(row=1, column=1, pady=5, sticky="w")

        tk.Label(self, text="Description:", bg="#d9dde3").grid(row=2, column=0, sticky="e", padx=20)
        self.last_var = tk.StringVar()
        tk.Entry(self, textvariable=self.last_var, width=30).grid(row=2, column=1, pady=5, sticky="w")
        tk.Label(self, text="Id:", bg="#d9dde3").grid(row=3, column=0, sticky="e", padx=20)
        self.id_var = tk.StringVar()
        tk.Entry(self, textvariable=self.id_var, width=30).grid(row=3, column=1, pady=5, sticky="w")

        tk.Label(self, text="Id1:", bg="#d9dde3").grid(row=3, column=0, sticky="e", padx=20)
        self.id_var = tk.StringVar()
        tk.Entry(self, textvariable=self.id_var, width=30).grid(row=3, column=1, pady=5, sticky="w")
        tk.Label(self, text="Id2:", bg="#d9dde3").grid(row=3, column=0, sticky="e", padx=20)
        self.id_var = tk.StringVar()
        tk.Entry(self, textvariable=self.id_var, width=30).grid(row=3, column=1, pady=5, sticky="w")

        tk.Label(self, text="Birth Date:", bg="#d9dde3").grid(row=5, column=0, sticky="e", padx=20)
        self.birth_var = tk.StringVar()
        self.birth_entry = tk.Entry(self, textvariable=self.birth_var, width=27)
        self.birth_entry.grid(row=5, column=1, sticky="w")

        tk.Button(self, text="...", width=3, command=self.open_calendar).grid(row=5, column=2, padx=5)

        tk.Label(self, text="Occupation:", bg="#d9dde3").grid(row=6, column=0, sticky="ne", padx=20, pady=5)

        occupations = [
            "Lawyer", "Actor/Actress", "Administrator", "Bricklayer", "Architect",
            "Artist", "Astronaut", "Firefighter", "Doctor", "Engineer", "Nurse",
            "Teacher", "Police Officer", "Chef", "Pilot"
        ]

        self.occ_listbox = tk.Listbox(self, selectmode="single", width=27, height=10)
        for item in occupations:
            self.occ_listbox.insert(tk.END, item)
        self.occ_listbox.grid(row=6, column=1, pady=5, sticky="w")

        tk.Button(self, text="Create", width=12, command=self.save).grid(row=9, column=0, columnspan=3, pady=25)


    def save(self):
        name = self.name_var.get()
        lastname = self.last_var.get()
        ident = self.id_var.get()
        nationality = self.nationality_var.get()
        birth = self.birth_var.get()
        occ_index = self.occ_listbox.curselection()
        occupation = self.occ_listbox.get(occ_index) if occ_index else "None"
        gender = self.gender_var.get()

        info = (
            f"Name: {name}\n"
            f"LastName: {lastname}\n"
            f"Identification: {ident}\n"
            f"Nationality: {nationality}\n"
            f"Birth Date: {birth}\n"
            f"Occupation: {occupation}\n"
            f"Gender: {gender}"
        )

        messagebox.showinfo("Saved", info)


if __name__ == "__main__":
    app = NaturalPersonsForm()
    app.mainloop()
