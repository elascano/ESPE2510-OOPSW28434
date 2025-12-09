import tkinter as tk
from tkinter import ttk, messagebox
from controller import Controller

class ContactApp:

    def __init__(self):
        self.controller = Controller()
        self.root = tk.Tk()
        self.root.title("CONTACTS")
        self.root.geometry("1050x600")
        self.root.configure(bg="#f0f0f0")

        self.build_ui()

    def build_ui(self):

        # ---- Title ----
        title = tk.Label(self.root, text="CONTACTS", font=("Arial", 22, "bold"), bg="#f0f0f0")
        title.place(x=420, y=20)

        # === LEFT PANEL ===
        left = tk.Frame(self.root, bg="#f0f0f0")
        left.place(x=40, y=80)

        labels = [
            "ID:", "First Name:", "Last Name:",
            "Birth Date:", "Age:", "Type:", "Sex:", "Hobbies:"
        ]

        self.fields = {}
        row = 0

        # ---- TEXT FIELDS ----
        for text in labels[:5]:
            tk.Label(left, text=text, font=("Arial", 12), bg="#f0f0f0").grid(row=row, column=0, sticky="w", pady=5)
            entry = tk.Entry(left, font=("Arial", 12), width=25)
            entry.grid(row=row, column=1, pady=5)
            self.fields[text] = entry
            row += 1

        self.id_entry = self.fields["ID:"]
        self.first_entry = self.fields["First Name:"]
        self.last_entry = self.fields["Last Name:"]
        self.birth_entry = self.fields["Birth Date:"]
        self.age_entry = self.fields["Age:"]

        # ---- TYPE (Combobox) ----
        tk.Label(left, text="Type:", font=("Arial", 12), bg="#f0f0f0").grid(row=row, column=0, sticky="w")
        self.type_cb = ttk.Combobox(left, values=["Family", "Friend", "Job", "Unknown"], width=22)
        self.type_cb.grid(row=row, column=1, pady=5)
        self.type_cb.current(0)
        row += 1

        # ---- SEX (Radio) ----
        tk.Label(left, text="Sex:", font=("Arial", 12), bg="#f0f0f0").grid(row=row, column=0, sticky="w")
        self.sex_var = tk.StringVar(value="male")
        sex_frame = tk.Frame(left, bg="#f0f0f0")
        sex_frame.grid(row=row, column=1, pady=5, sticky="w")
        tk.Radiobutton(sex_frame, text="Male", variable=self.sex_var, value="male", bg="#f0f0f0").pack(side="left", padx=3)
        tk.Radiobutton(sex_frame, text="Female", variable=self.sex_var, value="female", bg="#f0f0f0").pack(side="left")
        row += 1

        # ---- HOBBIES ----
        tk.Label(left, text="Hobbies:", font=("Arial", 12), bg="#f0f0f0").grid(row=row, column=0, sticky="nw")
        self.hobbies_list = tk.Listbox(left, selectmode="multiple", height=6, width=22)
        hobbies = ["Play Soccer", "Djing", "Read", "Cook", "Swim", "Sing", "Play an instrument"]
        for h in hobbies:
            self.hobbies_list.insert(tk.END, h)
        self.hobbies_list.grid(row=row, column=1, pady=5, sticky="w")
        row += 1

        # === COMMENTS (RIGHT PANEL) ===
        comments_frame = tk.LabelFrame(self.root, text="Comments:", font=("Arial", 11, "bold"))
        comments_frame.place(x=420, y=80, width=600, height=220)

        self.comments_txt = tk.Text(comments_frame, font=("Arial", 12))
        self.comments_txt.place(x=10, y=10, width=570, height=185)

        # === TABLE ===
        self.table = ttk.Treeview(self.root,
                                  columns=("id", "first", "last", "age", "type", "sex"),
                                  show="headings")

        self.table.place(x=420, y=320, width=600, height=200)

        for col in self.table["columns"]:
            self.table.heading(col, text=col.capitalize())
            self.table.column(col, width=90)

        # === BUTTONS ===
        button_frame = tk.Frame(self.root, bg="#f0f0f0")
        button_frame.place(x=400, y=540)

        tk.Button(button_frame, text="Save", width=10, command=self.save).grid(row=0, column=0, padx=5)
        tk.Button(button_frame, text="Update", width=10, command=self.update).grid(row=0, column=1, padx=5)
        tk.Button(button_frame, text="Delete", width=10, command=self.delete).grid(row=0, column=2, padx=5)
        tk.Button(button_frame, text="Load All", width=10, command=self.load_all).grid(row=0, column=3, padx=5)

    # ===========================
    # 🔹 UTILITY
    # ===========================
    def get_form_data(self):
        hobbies = [self.hobbies_list.get(i) for i in self.hobbies_list.curselection()]

        return {
            "id": int(self.id_entry.get()),
            "first_name": self.first_entry.get(),
            "last_name": self.last_entry.get(),
            "birth_date": self.birth_entry.get(),
            "age": int(self.age_entry.get()),
            "type_of_contact": self.type_cb.get(),
            "sex": self.sex_var.get(),
            "hobbies": hobbies,
            "comments": self.comments_txt.get("1.0", tk.END).strip()
        }

    # ===========================
    # 🔹 BUTTON ACTIONS
    # ===========================

    def save(self):
        try:
            data = self.get_form_data()
            self.controller.save_contact(data)
            messagebox.showinfo("OK", "Saved Successfully")
        except Exception as e:
            messagebox.showerror("Error", str(e))

    def update(self):
        try:
            data = self.get_form_data()
            self.controller.update_contact(data)
            messagebox.showinfo("OK", "Updated Successfully")
        except Exception as e:
            messagebox.showerror("Error", str(e))

    def delete(self):
        try:
            _id = int(self.id_entry.get())
            self.controller.delete_contact(_id)
            messagebox.showinfo("OK", "Deleted Successfully")
        except Exception as e:
            messagebox.showerror("Error", str(e))

    def load_all(self):
        self.table.delete(*self.table.get_children())
        contacts = self.controller.load_contacts()
        for c in contacts:
            self.table.insert("", tk.END, values=(
                c["id"], c["firstName"], c["lastName"], c["age"], c["typeOfContact"], c["sex"]
            ))

    def run(self):
        self.root.mainloop()
