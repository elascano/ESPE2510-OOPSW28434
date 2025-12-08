import tkinter as tk
from tkinter import messagebox, Listbox, MULTIPLE
from tkcalendar import DateEntry
from datetime import date

from model.contact import Contact
from model.contact_dao import ContactDao


class FrmContacts(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Contacts Book")
        self.geometry("400x550")

        # Nombres
        tk.Label(self, text="First Name").pack()
        self.txtFirstName = tk.Entry(self)
        self.txtFirstName.pack()

        tk.Label(self, text="Last Name").pack()
        self.txtLastName = tk.Entry(self)
        self.txtLastName.pack()

        # Fecha y edad automática
        tk.Label(self, text="Birthday").pack()
        self.datePicker = DateEntry(self, date_pattern='yyyy-mm-dd')
        self.datePicker.pack()
        self.datePicker.bind("<<DateEntrySelected>>", self.update_age)

        tk.Label(self, text="Age").pack()
        self.txtAge = tk.Entry(self)
        self.txtAge.config(state="disabled")
        self.txtAge.pack()

        # Type
        tk.Label(self, text="Type of Contact").pack()
        self.cmbType = tk.StringVar(self)
        self.cmbType.set("Family")
        tk.OptionMenu(self, self.cmbType, "Family", "Friend", "Job", "Unknown").pack()

        # Sex
        tk.Label(self, text="Sex").pack()
        self.sex = tk.StringVar(value="")
        tk.Radiobutton(self, text="Male", value="Male", variable=self.sex).pack()
        tk.Radiobutton(self, text="Female", value="Female", variable=self.sex).pack()

        # Hobbies
        tk.Label(self, text="Hobbies").pack()
        self.lstHobbies = Listbox(self, selectmode=MULTIPLE)
        hobbies_options = ["Sports", "Gaming", "Music", "Art", "Reading"]
        for h in hobbies_options:
            self.lstHobbies.insert(tk.END, h)
        self.lstHobbies.pack()

        # Comments
        tk.Label(self, text="Comments").pack()
        self.txtComments = tk.Text(self, height=4)
        self.txtComments.pack()

        # Save
        tk.Button(self, text="Save Contact", command=self.save_contact).pack(pady=10)

    # Cálculo de la edad
    def update_age(self, event):
        birthday = self.datePicker.get_date()
        today = date.today()
        age = today.year - birthday.year - ((today.month, today.day) < (birthday.month, birthday.day))
        
        self.txtAge.config(state="normal")
        self.txtAge.delete(0, tk.END)
        self.txtAge.insert(0, str(age))
        self.txtAge.config(state="disabled")

    # Guardar contacto
    def save_contact(self):
        hobbies_selected = [self.lstHobbies.get(i) for i in self.lstHobbies.curselection()]

        contact = Contact(
            self.txtFirstName.get(),
            self.txtLastName.get(),
            int(self.txtAge.get()),
            self.cmbType.get(),
            self.sex.get(),
            hobbies_selected,
            self.txtComments.get("1.0", tk.END).strip()
        )

        dao = ContactDao()
        dao.insert(contact)
        messagebox.showinfo("Success", "Contact saved successfully!")
