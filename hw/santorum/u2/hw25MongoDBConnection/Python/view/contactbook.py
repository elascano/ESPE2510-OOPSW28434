# view/frm_contacts.py
import tkinter as tk
from tkinter import ttk, messagebox
from model.contact import Contact
from pymongo import MongoClient
from datetime import datetime

class FrmContacts:
    def __init__(self):
        self.contact = None
        self.root = tk.Tk()
        self.root.title("Contacts")

        tk.Label(self.root, text="First Name:").grid(row=0, column=0, sticky="e")
        self.txt_first_name = tk.Entry(self.root)
        self.txt_first_name.grid(row=0, column=1)

        tk.Label(self.root, text="Last Name:").grid(row=1, column=0, sticky="e")
        self.txt_last_name = tk.Entry(self.root)
        self.txt_last_name.grid(row=1, column=1)

        tk.Label(self.root, text="Birth Date (dd/mm/yyyy):").grid(row=2, column=0, sticky="e")
        self.txt_birth_date = tk.Entry(self.root)
        self.txt_birth_date.grid(row=2, column=1)
        self.txt_birth_date.bind("<FocusOut>", self.calculate_age)

        tk.Label(self.root, text="Age:").grid(row=3, column=0, sticky="e")
        self.txt_age = tk.Entry(self.root)
        self.txt_age.grid(row=3, column=1)

        tk.Label(self.root, text="Type of Contact:").grid(row=4, column=0, sticky="e")
        self.cmb_type = ttk.Combobox(self.root, values=["Family", "Friend", "Job", "Unknown"])
        self.cmb_type.current(0)
        self.cmb_type.grid(row=4, column=1)

        tk.Label(self.root, text="Sex:").grid(row=5, column=0, sticky="e")
        self.sex_var = tk.StringVar(value="Male")
        tk.Radiobutton(self.root, text="Male", variable=self.sex_var, value="Male").grid(row=5, column=1, sticky="w")
        tk.Radiobutton(self.root, text="Female", variable=self.sex_var, value="Female").grid(row=5, column=1, sticky="e")

        tk.Label(self.root, text="Hobbies:").grid(row=6, column=0, sticky="ne")
        self.lst_hobbies = tk.Listbox(self.root, selectmode="multiple")
        hobbies_options = ["Play Soccer", "Djing", "Read", "Cook", "Swim", "Sing", "Play an instrument"]
        for hobby in hobbies_options:
            self.lst_hobbies.insert(tk.END, hobby)
        self.lst_hobbies.grid(row=6, column=1)

        tk.Label(self.root, text="Comments:").grid(row=7, column=0, sticky="ne")
        self.txt_comments = tk.Text(self.root, width=30, height=5)
        self.txt_comments.grid(row=7, column=1)


        self.btn_save = tk.Button(self.root, text="Save", command=self.save_contact)
        self.btn_save.grid(row=8, column=0, columnspan=2, pady=10)


    def calculate_age(self, event=None):
        fecha_texto = self.txt_birth_date.get().strip()
        try:
            fecha_nac = datetime.strptime(fecha_texto, "%d/%m/%Y")
            age = (datetime.now() - fecha_nac).days // 365
            self.txt_age.delete(0, tk.END)
            self.txt_age.insert(0, str(age))
        except:
            self.txt_age.delete(0, tk.END)

    def read_values(self):
        first_name = self.txt_first_name.get().strip()
        last_name = self.txt_last_name.get().strip()
        age = int(self.txt_age.get()) if self.txt_age.get().isdigit() else 0
        type_of_contact = self.cmb_type.get()
        sex = self.sex_var.get()
        selected_indices = self.lst_hobbies.curselection()
        hobbies = [self.lst_hobbies.get(i) for i in selected_indices]
        comments = self.txt_comments.get("1.0", tk.END).strip()
        self.contact = Contact(first_name, last_name, age, type_of_contact, sex, hobbies, comments)


    def save_contact(self):
        self.read_values()
        if not self.contact:
            messagebox.showerror("Error", "No contact to save")
            return

        option = messagebox.askyesno("Save Contact", f"Saving contact → {self.contact}")
        if not option:
            return

        try:
            uri = "mongodb+srv://thais:thais@cluster0.9yfzmcp.mongodb.net/ContactsDB?retryWrites=true&w=majority"
            client = MongoClient(uri)
            db = client.ContactsDB
            collection = db.Contacts

            doc = {
                "first_name": self.contact.first_name,
                "last_name": self.contact.last_name,
                "age": self.contact.age,
                "type": self.contact.type_of_contact,
                "sex": self.contact.sex,
                "hobbies": self.contact.hobbies,
                "comments": self.contact.comments
            }

            collection.insert_one(doc)
            client.close()
            messagebox.showinfo("Success", f"Contact saved → {self.contact}")
            self.empty_fields()
        except Exception as e:
            messagebox.showerror("Error", f"Error saving to MongoDB: {e}")


    def empty_fields(self):
        self.txt_first_name.delete(0, tk.END)
        self.txt_last_name.delete(0, tk.END)
        self.txt_birth_date.delete(0, tk.END)
        self.txt_age.delete(0, tk.END)
        self.cmb_type.current(0)
        self.sex_var.set("Male")
        self.lst_hobbies.selection_clear(0, tk.END)
        self.txt_comments.delete("1.0", tk.END)


    def run(self):
        self.root.mainloop()



if __name__ == "__main__":
    app = FrmContacts()
    app.run()
