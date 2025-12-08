import tkinter as tk
from tkinter import ttk, messagebox
from tkcalendar import DateEntry
from Contact import Contact
from MongoConnection import MongoConnection
import sys
import os

sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))


class FrmContacts(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Contacts")
        self.geometry("600x600")

        self.contact = Contact()

        title = tk.Label(self, text="CONTACTS", font=("Segoe UI", 20))
        title.pack(pady=10)

        frame = tk.Frame(self)
        frame.pack(pady=10)

        tk.Label(frame, text="First Name:").grid(row=0, column=0, sticky="e")
        self.txtFirstName = tk.Entry(frame)
        self.txtFirstName.grid(row=0, column=1)

        tk.Label(frame, text="Last Name:").grid(row=1, column=0, sticky="e")
        self.txtLastName = tk.Entry(frame)
        self.txtLastName.grid(row=1, column=1)

        tk.Label(frame, text="Birth Date:").grid(row=2, column=0, sticky="e")
        self.birthDate = DateEntry(frame, date_pattern="yyyy-mm-dd")
        self.birthDate.grid(row=2, column=1)

        tk.Label(frame, text="Age:").grid(row=3, column=0, sticky="e")
        self.txtAge = tk.Entry(frame)
        self.txtAge.insert(0, "19")
        self.txtAge.grid(row=3, column=1)

        tk.Label(frame, text="Type:").grid(row=4, column=0, sticky="e")
        self.cmbType = ttk.Combobox(frame, values=["Family", "Friend", "Job", "Unknown"])
        self.cmbType.current(0)
        self.cmbType.grid(row=4, column=1)

        tk.Label(frame, text="Sex:").grid(row=5, column=0, sticky="e")
        self.sexVar = tk.StringVar(value="Female")
        tk.Radiobutton(frame, text="Male", variable=self.sexVar, value="Male").grid(row=5, column=1, sticky="w")
        tk.Radiobutton(frame, text="Female", variable=self.sexVar, value="Female").grid(row=5, column=1, sticky="e")

        tk.Label(frame, text="Hobbies:").grid(row=6, column=0, sticky="e")
        self.lstHobbies = tk.Listbox(frame, selectmode=tk.MULTIPLE, height=5)
        hobbies = ["Play Soccer", "Dijing", "Read", "Cook", "Swim", "Sing", "Play an instrument"]
        for h in hobbies:
            self.lstHobbies.insert(tk.END, h)
        self.lstHobbies.grid(row=6, column=1)

        tk.Label(frame, text="Comments:").grid(row=7, column=0, sticky="e")
        self.txaComments = tk.Text(frame, width=20, height=5)
        self.txaComments.grid(row=7, column=1)

        btnSave = tk.Button(self, text="SAVE", command=self.save_contact)
        btnSave.pack(pady=20)

    def read_values(self):
        first_name = self.txtFirstName.get()
        last_name = self.txtLastName.get()
        birth_date = self.birthDate.get_date().strftime("%Y-%m-%d")
        age = int(self.txtAge.get())
        type_contact = self.cmbType.get()
        sex = self.sexVar.get()
        hobbies = [self.lstHobbies.get(i) for i in self.lstHobbies.curselection()]
        comments = self.txaComments.get("1.0", tk.END).strip()

        self.contact = Contact(
            first_name=first_name,
            last_name=last_name,
            birth_date=birth_date,
            age=age,
            type_of_contact=type_contact,
            sex=sex,
            hobbies=hobbies,
            comments=comments
        )

    def save_contact(self):
        self.read_values()

        op = messagebox.askyesnocancel("Contacts", "Saving contact?")
        if op is None:
            return
        if not op:
            messagebox.showinfo("Contacts", "Your data will be lost")
            self.empty_fields()
            return

        database = MongoConnection.get_database()
        collection = database["ContactsPython"]

        data = {
            "firstName": self.contact.first_name,
            "lastName": self.contact.last_name,
            "birthDate": self.contact.birth_date,
            "age": self.contact.age,
            "type": self.contact.type_of_contact,
            "sex": self.contact.sex,
            "hobbies": self.contact.hobbies,
            "comments": self.contact.comments
        }

        collection.insert_one(data)
        messagebox.showinfo("Contacts", "Your contact is saved")
        self.empty_fields()

    def empty_fields(self):
        self.txtFirstName.delete(0, tk.END)
        self.txtLastName.delete(0, tk.END)
        self.txtAge.delete(0, tk.END)
        self.txtAge.insert(0, "19")
        self.cmbType.current(0)
        self.sexVar.set("Female")
        self.lstHobbies.selection_clear(0, tk.END)
        self.txaComments.delete("1.0", tk.END)

if __name__ == "__main__":
    app = FrmContacts()
    app.mainloop()