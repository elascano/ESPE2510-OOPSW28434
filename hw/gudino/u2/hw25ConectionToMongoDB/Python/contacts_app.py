import tkinter as tk
from tkinter import ttk, messagebox
from datetime import datetime
from pymongo import MongoClient
from tkcalendar import DateEntry  # pip install tkcalendar

# ------------------ MongoDB Connection ------------------
def connect_mongo():
    try:
        uri = "mongodb+srv://Bryan:Bryan2000@cluster0.sx9cpnq.mongodb.net/"
        client = MongoClient(uri)
        db = client["ContactsDB"]
        print("Conection to MongoDB (Python)")
        return db["contacts"]
    except Exception as e:
        print(" Error to conection MongoDB:", e)
        return None

collection = connect_mongo()

# ------------------ App Class ------------------
class ContactsApp:
    def __init__(self, root):
        self.root = root
        root.title("Contacts")
        root.geometry("600x600")

        title = tk.Label(root, text="CONTACTS", font=("Segoe UI", 20))
        title.pack(pady=10)

        form = tk.Frame(root)
        form.pack(pady=10)

        # ---------- First Name ----------
        tk.Label(form, text="First Name:").grid(row=0, column=0, sticky="e")
        self.first_name = tk.Entry(form)
        self.first_name.grid(row=0, column=1)

        # ---------- Last Name ----------
        tk.Label(form, text="Last Name:").grid(row=1, column=0, sticky="e")
        self.last_name = tk.Entry(form)
        self.last_name.grid(row=1, column=1)

        # ---------- Date of Birth ----------
        tk.Label(form, text="Birth Date:").grid(row=2, column=0, sticky="e")
        # Usamos formato yyyy-mm-dd (seguro y fácil de parsear)
        self.birth_date = DateEntry(form, date_pattern="yyyy-mm-dd")
        self.birth_date.grid(row=2, column=1)

        # ---------- Age (auto) ----------
        tk.Label(form, text="Age:").grid(row=3, column=0, sticky="e")
        self.age_label = tk.Label(form, text="0")
        self.age_label.grid(row=3, column=1)

        # ---------- Contact Type ----------
        tk.Label(form, text="Type:").grid(row=4, column=0, sticky="e")
        self.type_var = tk.StringVar()
        self.type_combo = ttk.Combobox(
            form, 
            textvariable=self.type_var, 
            values=["Family", "Friends", "Job", "Unknown"]
        )
        self.type_combo.current(0)
        self.type_combo.grid(row=4, column=1)

        # ---------- Sex ----------
        tk.Label(form, text="Sex:").grid(row=5, column=0, sticky="e")
        self.sex_var = tk.StringVar()
        tk.Radiobutton(form, text="Male", value="Male", variable=self.sex_var)\
            .grid(row=5, column=1, sticky="w")
        tk.Radiobutton(form, text="Female", value="Female", variable=self.sex_var)\
            .grid(row=5, column=1, sticky="e")

        # ---------- Hobbies ----------
        tk.Label(form, text="Hobbies:").grid(row=6, column=0, sticky="ne")
        self.hobbies_list = tk.Listbox(form, selectmode="multiple", height=5)
        hobbies = ["Play soccer", "Djng", "Read", "Cook", "Swim", "Sing", "Play an instrument"]
        for h in hobbies:
            self.hobbies_list.insert(tk.END, h)
        self.hobbies_list.grid(row=6, column=1)

        # ---------- Comments ----------
        tk.Label(form, text="Comments:").grid(row=7, column=0, sticky="ne")
        self.comments = tk.Text(form, width=30, height=4)
        self.comments.grid(row=7, column=1)

        # ---------- Save Button ----------
        save_btn = tk.Button(root, text="Save", command=self.save_contact)
        save_btn.pack(pady=20)

        # Calcular edad automáticamente
        self.birth_date.bind("<<DateEntrySelected>>", self.update_age)

    # ---------------------- Age calc ----------------------
    def update_age(self, event=None):
        try:
            bdate = datetime.strptime(self.birth_date.get(), "%Y-%m-%d")
            today = datetime.today()

            age = today.year - bdate.year - (
                (today.month, today.day) < (bdate.month, bdate.day)
            )

            self.age_label.config(text=str(age))
        except Exception as e:
            print("Error calculando edad:", e)

    # ---------------------- Save Contact ----------------------
    def save_contact(self):
        fname = self.first_name.get()
        lname = self.last_name.get()
        bdate = self.birth_date.get()
        age = int(self.age_label.cget("text"))
        type_contact = self.type_var.get()
        sex = self.sex_var.get()

        if sex == "":
            messagebox.showerror("Error", "You must select a sex.")
            return

        hobbies = [self.hobbies_list.get(i) for i in self.hobbies_list.curselection()]
        comments = self.comments.get("1.0", tk.END).strip()

        doc = {
            "firstName": fname,
            "lastName": lname,
            "age": age,
            "birthDate": bdate,
            "type": type_contact,
            "sex": sex,
            "hobbies": hobbies,
            "comments": comments
        }

        collection.insert_one(doc)
        messagebox.showinfo("Saved", "Contact saved in MongoDB!")

        self.clear_fields()

    # ---------------------- Clear ----------------------
    def clear_fields(self):
        self.first_name.delete(0, tk.END)
        self.last_name.delete(0, tk.END)
        self.comments.delete("1.0", tk.END)
        self.hobbies_list.selection_clear(0, tk.END)
        self.type_combo.current(0)
        self.sex_var.set("")
        self.age_label.config(text="0")

# ------------------ MAIN ------------------
root = tk.Tk()
app = ContactsApp(root)
root.mainloop()
