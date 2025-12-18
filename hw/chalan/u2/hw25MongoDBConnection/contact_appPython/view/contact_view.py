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
        title.grid(row=0, column=0, columnspan=3, pady=(0, 20)) 

        form_left = tk.Frame(container, bg="#f2f2f2")
        form_left.grid(row=1, column=0, sticky="nw", padx=20)

        form_right = tk.Frame(container, bg="#f2f2f2")
        form_right.grid(row=1, column=1, sticky="nw")

        table_frame = tk.Frame(container, bg="#f2f2f2") 
        table_frame.grid(row=1, column=2, sticky="ne", padx=20)

        self.add_label(form_left, "ID:", 0)
        self.id_entry = self.add_entry(form_left, 0, readonly=True) 

        self.add_label(form_left, "First Name:", 1)
        self.first_name = self.add_entry(form_left, 1)

        self.add_label(form_left, "Last Name:", 2)
        self.last_name = self.add_entry(form_left, 2)

        self.add_label(form_left, "Birth Date:", 3)
        self.birth = DateEntry(form_left, width=23, background='darkblue',
                               foreground='white', borderwidth=2, year=datetime.now().year, date_pattern='yyyy-mm-dd')
        self.birth.grid(row=3, column=1, pady=5)
        self.birth.bind("<<DateEntrySelected>>", self.calculate_age)

        self.add_label(form_left, "Age:", 4)
        self.age = self.add_entry(form_left, 4, readonly=True) 

        self.add_label(form_left, "Type:", 5)
        self.type_box = ttk.Combobox(form_left, values=["Family", "Friend", "Work", "Other"], width=23)
        self.type_box.grid(row=5, column=1, pady=5)
        self.type_box.current(0) 

        self.add_label(form_left, "Sex:", 6)
        sex_frame = tk.Frame(form_left, bg="#f2f2f2")
        sex_frame.grid(row=6, column=1, sticky="w")
        self.sex_value = tk.StringVar(value="Male") 
        tk.Radiobutton(sex_frame, text="Male", variable=self.sex_value, value="Male", bg="#f2f2f2").pack(side="left", padx=5)
        tk.Radiobutton(sex_frame, text="Female", variable=self.sex_value, value="Female", bg="#f2f2f2").pack(side="left")

        self.add_label(form_left, "Hobbies:", 7)
        self.hobby_list = tk.Listbox(form_left, height=8, width=22, selectmode=tk.MULTIPLE)
        self.hobby_list.grid(row=7, column=1, pady=5)
        hobbies = ["Play Soccer", "DJ", "Read", "Cook", "Swim", "Sing", "Play an Instrument"]
        for h in hobbies:
            self.hobby_list.insert(tk.END, h)

        btn_frame = tk.Frame(form_left, bg="#f2f2f2")
        btn_frame.grid(row=8, column=1, pady=10)

        save_btn = tk.Button(btn_frame, text="Save", width=12, bg="#ddd", command=self.save_contact)
        save_btn.pack(side="left", padx=5)

        self.add_label(form_right, "Comments:", 0)
        tk.Label(form_right, text="Comments:", bg="#f2f2f2", font=("Arial", 11, "bold")).grid(row=0, column=0, sticky="w")
        self.comments = tk.Text(form_right, width=40, height=15, borderwidth=1, relief="solid")
        self.comments.grid(row=1, column=0)
        
        columns = ("First Name", "Last Name", "Age", "Type", "Sex")
        
        self.tree = ttk.Treeview(table_frame, columns=("ID_H",) + columns, show="headings")
        
        self.tree.column("ID_H", width=0, stretch=tk.NO)
        
        self.tree.heading("First Name", text="First Name")
        self.tree.heading("Last Name", text="Last Name")
        self.tree.heading("Age", text="Age", anchor=tk.CENTER)
        self.tree.heading("Type", text="Type", anchor=tk.CENTER)
        self.tree.heading("Sex", text="Sex", anchor=tk.CENTER)
        
        self.tree.column("First Name", width=100, anchor=tk.W)
        self.tree.column("Last Name", width=100, anchor=tk.W)
        self.tree.column("Age", width=40, anchor=tk.CENTER)
        self.tree.column("Type", width=70, anchor=tk.CENTER)
        self.tree.column("Sex", width=70, anchor=tk.CENTER)
        
        self.tree.pack(side="top", fill="both", expand=True)

        scrollbar = ttk.Scrollbar(table_frame, orient="vertical", command=self.tree.yview)
        self.tree.configure(yscrollcommand=scrollbar.set)
        scrollbar.pack(side="right", fill="y")
        
        delete_btn = tk.Button(table_frame, text="Delete Selected", width=15, bg="#fbb", command=self.delete_contact)
        delete_btn.pack(pady=5)
        
        # NUEVO BOTÓN
        upload_btn = tk.Button(table_frame, text="Upload from Cloud", width=15, bg="#ddf", command=self.upload_from_cloud)
        upload_btn.pack(pady=5)
        
        self.calculate_age()
        self.load_contacts() 

    def add_label(self, parent, text, row):
        tk.Label(parent, text=text, bg="#f2f2f2", font=("Arial", 10, "bold")).grid(row=row, column=0, sticky="w", pady=5)

    def add_entry(self, parent, row, readonly=False):
        state = "readonly" if readonly else "normal"
        entry = tk.Entry(parent, width=25, state=state)
        entry.grid(row=row, column=1, pady=5)
        return entry

    def upload_from_cloud(self):
        self.load_contacts()
        messagebox.showinfo("Cloud Sync Complete", "All contacts have been synchronized/loaded from the Cloud (MongoDB).")

    def load_contacts(self):
        for item in self.tree.get_children():
            self.tree.delete(item)
            
        contacts = self.controller.get_all_contacts()
        
        for contact in contacts:
            self.tree.insert("", tk.END, values=(
                contact.id,
                contact.first_name,
                contact.last_name, 
                contact.age,
                contact.contact_type,
                contact.sex
            ), iid=contact.id)

    def delete_contact(self):
        selected_item = self.tree.focus()
        if not selected_item:
            messagebox.showwarning("Warning", "Please select a contact from the list to delete.")
            return

        contact_id = self.tree.item(selected_item, 'values')[0]
        contact_name = self.tree.item(selected_item, 'values')[1]

        response = messagebox.askyesno(
            "Confirm Deletion",
            f"Are you sure you want to delete '{contact_name}'?",
            icon='warning'
        )

        if response:
            try:
                self.controller.delete_contact(contact_id)
                messagebox.showinfo("Success", f"Contact '{contact_name}' deleted successfully.")
                self.load_contacts()
            except Exception as e:
                messagebox.showerror("Database Error", f"Error deleting contact: {e}")
                
    def calculate_age(self, event=None):
        try:
            birth_date_str = self.birth.get_date().strftime('%Y-%m-%d')
            birth_date = datetime.strptime(birth_date_str, '%Y-%m-%d')
            today = datetime.now()
            age = today.year - birth_date.year - ((today.month, today.day) < (birth_date.month, birth_date.day))
            
            self.age.config(state='normal')
            self.age.delete(0, tk.END)
            self.age.insert(0, str(age))
            self.age.config(state='readonly')

        except Exception as e:
            self.age.config(state='normal')
            self.age.delete(0, tk.END)
            self.age.config(state='readonly')

    def get_selected_hobbies(self):
        selected_indices = self.hobby_list.curselection()
        selected_hobbies = [self.hobby_list.get(i) for i in selected_indices]
        return selected_hobbies

    def validate_name_field(self, field_value, field_name):
        field_value = field_value.strip() 
        
        if not field_value:
            return True 
            
        if ' ' in field_value:
            messagebox.showerror("Validation Error", f"The '{field_name}' field must be a single word (no internal spaces allowed).")
            return False
        
        if any(char.isdigit() for char in field_value):
            messagebox.showerror("Validation Error", f"The '{field_name}' field cannot contain numbers.")
            return False
            
        return True

    def save_contact(self):
        
        first_name = self.first_name.get().strip()
        last_name = self.last_name.get().strip()
        birth_date = self.birth.get_date().strftime('%Y-%m-%d')
        
        if not first_name or not last_name or not birth_date:
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
        
        email = "" 
        phone = ""

        confirmation_message = (
            "¿Desea guardar el siguiente contacto?\n\n"
            "Contact{\n"
            f"  Name: {full_name},\n"
            f"  Birth Date: {birth_date},\n"
            f"  Age: {age},\n"
            f"  Type: {contact_type},\n"
            f"  Sex: {sex},\n"
            f"  Hobbies: {hobbies},\n"
            f"  Comments: '{comments}'\n"
            "}"
        )

        response = messagebox.askyesno(
            "Confirmar Guardado",
            confirmation_message,
            icon='question'
        )

        if response:
            try:
                inserted_id = self.controller.add_contact(
                    full_name, 
                    email, 
                    phone, 
                    birth_date, 
                    age, 
                    contact_type, 
                    sex, 
                    hobbies, 
                    comments
                )
                
                self.id_entry.config(state='normal')
                self.id_entry.delete(0, tk.END)
                self.id_entry.insert(0, inserted_id)
                self.id_entry.config(state='readonly')
                
                messagebox.showinfo("Success", f"Contact '{full_name}' saved successfully with ID: {inserted_id}")
                
                self.reset_form()
                self.load_contacts() 
                
            except Exception as e:
                messagebox.showerror("Database Error", f"An error occurred while saving the contact: {e}")
        else:
            messagebox.showinfo("Canceled", "Contact saving process canceled.")


    def reset_form(self):
        self.id_entry.config(state='normal')
        self.id_entry.delete(0, tk.END)
        self.id_entry.config(state='readonly')
        
        self.first_name.delete(0, tk.END)
        self.last_name.delete(0, tk.END)
        
        self.birth.set_date(datetime.now().date())
        self.calculate_age() 
        
        self.type_box.current(0)
        self.sex_value.set("Male")
        
        self.hobby_list.selection_clear(0, tk.END)
        
        self.comments.delete("1.0", tk.END)