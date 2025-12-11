import tkinter as tk
from tkinter import ttk, messagebox
from tkcalendar import DateEntry
from datetime import datetime

class ContactView:
    def __init__(self, root, controller):
        self.controller = controller
        self.root = root
        self.root.title("Contacts")
        self.root.configure(bg="#e6e6e6")

        container = tk.Frame(root, bg="#f2f2f2", padx=30, pady=30)
        container.pack(pady=20)

        title = tk.Label(container, text="CONTACTS", font=("Arial", 20, "bold"), bg="#f2f2f2")
        title.grid(row=0, column=0, columnspan=2, pady=(0, 20))

        form_left = tk.Frame(container, bg="#f2f2f2")
        form_left.grid(row=1, column=0, sticky="nw", padx=20)

        form_right = tk.Frame(container, bg="#f2f2f2")
        form_right.grid(row=1, column=1, sticky="nw")

        # First Name
        self.add_label(form_left, "First Name:", 1)
        self.first_name = self.add_entry(form_left, 1)

        # Last Name
        self.add_label(form_left, "Last Name:", 2)
        self.last_name = self.add_entry(form_left, 2)

        # Birth Date
        self.add_label(form_left, "Birth Date:", 3)
        self.birth = DateEntry(
            form_left,
            width=23,
            background='darkblue',
            foreground='white',
            borderwidth=2,
            year=datetime.now().year,
            date_pattern='dd/mm/yyyy'
        )
        self.birth.grid(row=3, column=1, pady=5)
        self.birth.bind("<<DateEntrySelected>>", self.calculate_age)

        # Age (readonly)
        self.add_label(form_left, "Age:", 4)
        self.age = self.add_entry(form_left, 4, readonly=True)

        # Type
        self.add_label(form_left, "Type:", 5)
        self.type_box = ttk.Combobox(form_left, values=["Family", "Friend", "Work", "Other"], width=23)
        self.type_box.grid(row=5, column=1, pady=5)
        self.type_box.current(0)

        # Sex
        self.add_label(form_left, "Sex:", 6)
        sex_frame = tk.Frame(form_left, bg="#f2f2f2")
        sex_frame.grid(row=6, column=1, sticky="w")
        self.sex_value = tk.StringVar(value="Male")
        tk.Radiobutton(sex_frame, text="Male", variable=self.sex_value, value="Male", bg="#f2f2f2").pack(side="left", padx=5)
        tk.Radiobutton(sex_frame, text="Female", variable=self.sex_value, value="Female", bg="#f2f2f2").pack(side="left")

        # Hobbies
        self.add_label(form_left, "Hobbies:", 7)
        self.hobby_list = tk.Listbox(form_left, height=8, width=22, selectmode=tk.MULTIPLE)
        self.hobby_list.grid(row=7, column=1, pady=5)
        hobbies = ["Play Soccer", "DJ", "Read", "Cook", "Swim", "Sing", "Play an Instrument"]
        for h in hobbies:
            self.hobby_list.insert(tk.END, h)

        # Buttons
        btn_frame = tk.Frame(form_left, bg="#f2f2f2")
        btn_frame.grid(row=8, column=1, pady=10)
        save_btn = tk.Button(btn_frame, text="Save", width=12, bg="#ddd", command=self.save_contact)
        save_btn.pack(side="left", padx=5)

        # Comments on the right
        self.add_label(form_right, "Comments:", 0)
        self.comments = tk.Text(form_right, width=40, height=15, borderwidth=1, relief="solid")
        self.comments.grid(row=1, column=0)

        # Inicializar edad calculada
        self.calculate_age()

    def add_label(self, parent, text, row):
        tk.Label(parent, text=text, bg="#f2f2f2", font=("Arial", 10, "bold")).grid(row=row, column=0, sticky="w", pady=5)

    def add_entry(self, parent, row, readonly=False):
        state = "readonly" if readonly else "normal"
        entry = tk.Entry(parent, width=25, state=state)
        entry.grid(row=row, column=1, pady=5)
        return entry

    def calculate_age(self, event=None):
        try:
            birth_date_str = self.birth.get_date().strftime('%d/%m/%Y')
            birth_date = datetime.strptime(birth_date_str, '%d/%m/%Y')
            today = datetime.now()
            age = today.year - birth_date.year - ((today.month, today.day) < (birth_date.month, birth_date.day))
            self.age.config(state='normal')
            self.age.delete(0, tk.END)
            self.age.insert(0, str(age))
            self.age.config(state='readonly')
        except Exception:
            self.age.config(state='normal')
            self.age.delete(0, tk.END)
            self.age.config(state='readonly')

    def get_selected_hobbies(self):
        selected_indices = self.hobby_list.curselection()
        return [self.hobby_list.get(i) for i in selected_indices]

    def validate_name_field(self, field_value, field_name):
        field_value = field_value.strip()
        if not field_value:
            return True
        if ' ' in field_value:
            messagebox.showerror("Validation Error", f"The '{field_name}' field must be a single word (no spaces).")
            return False
        if any(char.isdigit() for char in field_value):
            messagebox.showerror("Validation Error", f"The '{field_name}' field cannot contain numbers.")
            return False
        return True

    def save_contact(self):
        # Debug: confirma que se ejecuta
        print("DEBUG: save_contact called")

        first_name = self.first_name.get().strip()
        last_name = self.last_name.get().strip()
        birth_date_str = self.birth.get_date().strftime('%d/%m/%Y')  # dd/mm/YYYY

        if not first_name or not last_name or not birth_date_str:
            messagebox.showerror("Error", "First Name, Last Name, and Birth Date are required.")
            return

        if not self.validate_name_field(first_name, "First Name"):
            return
        if not self.validate_name_field(last_name, "Last Name"):
            return

        full_name = f"{first_name} {last_name}"
        age = self.age.get()
        contact_type = self.type_box.get()
        sex = self.sex_value.get()
        hobbies = self.get_selected_hobbies()
        comments = self.comments.get("1.0", tk.END).strip()

        # Desglose del día, mes y año
        try:
            d, m, y = birth_date_str.split('/')
            day = int(d)
            month = int(m)
            year = int(y)
        except Exception:
            day = month = year = None

        confirmation_message = (
            "¿Desea guardar el siguiente contacto?\n\n"
            f"Name: {full_name}\nBirth Date: {birth_date_str}\nAge: {age}\nType: {contact_type}\nSex: {sex}\nHobbies: {hobbies}\nComments: {comments}"
        )
        resp = messagebox.askyesno("Confirm Save", confirmation_message, icon='question')
        if not resp:
            return

        # Llamar al controller para guardar
        try:
            inserted_id = self.controller.add_contact(
                full_name,
                birth_date_str,
                day,
                month,
                year,
                age,
                contact_type,
                sex,
                hobbies,
                comments
            )

            if inserted_id:
                messagebox.showinfo("Success", f"Contact saved successfully (id: {inserted_id})")
                self.reset_form()
            else:
                messagebox.showerror("Error", "Contact not saved (no id returned).")
        except Exception as e:
            messagebox.showerror("Database Error", f"An error occurred while saving the contact: {e}")

    def reset_form(self):
        self.first_name.delete(0, tk.END)
        self.last_name.delete(0, tk.END)
        self.birth.set_date(datetime.now().date())
        self.calculate_age()
        self.type_box.current(0)
        self.sex_value.set("Male")
        self.hobby_list.selection_clear(0, tk.END)
        self.comments.delete("1.0", tk.END)
