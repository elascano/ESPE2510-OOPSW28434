import tkinter as tk
from tkinter import ttk, messagebox
from tkcalendar import DateEntry
from datetime import datetime, date
import re
from contact import Contact
from contact_controller import ContactController

class ContactsGUI:
    def __init__(self, root):
        self.root = root
        self.controller = ContactController()  
        
        self.root.title("CONTACTS BOOK")
        self.root.geometry("750x600")  
        self.root.configure(bg="#e8f4f8")
        
        self.setup_styles()
        self.create_widgets()
        self.init_date_chooser()
        self.update_display()
    
    def setup_styles(self):
        style = ttk.Style()
        style.theme_use("clam")
        style.configure("TLabel", background="#e8f4f8", foreground="#2c3e50", font=("Verdana", 10))
        style.configure("TCheckbutton", background="#e8f4f8", foreground="#2c3e50")
        style.configure("TRadiobutton", background="#e8f4f8", foreground="#2c3e50")
        style.configure("TEntry", fieldbackground="white", foreground="#2c3e50")
        style.configure("TCombobox", fieldbackground="white", foreground="#2c3e50")
        style.configure("TButton", background="#3498db", foreground="white", font=("Verdana", 10, "bold"))
    
    def create_widgets(self):
        title_frame = tk.Frame(self.root, bg="#e8f4f8")
        title_frame.pack(pady=10)
        
        title = tk.Label(title_frame, text="CONTACTS BOOK", 
                         font=("Verdana", 24, "bold"), 
                         bg="#e8f4f8", fg="#2c3e50")
        title.pack()
        
        main_frame = tk.Frame(self.root, bg="#e8f4f8")
        main_frame.pack(padx=20, pady=10, fill="both", expand=True)
        
        form_frame = tk.LabelFrame(main_frame, text="Contact Information", 
                                  font=("Verdana", 12, "bold"),
                                  bg="#e8f4f8", fg="#2c3e50", padx=15, pady=15)
        form_frame.grid(row=0, column=0, sticky="nw", padx=(0, 10))
        
        ttk.Label(form_frame, text="Next ID:").grid(row=0, column=0, sticky="w", pady=5)
        self.next_id_label = ttk.Label(form_frame, text="1", font=("Verdana", 10, "bold"))
        self.next_id_label.grid(row=0, column=1, pady=5)
        
        ttk.Label(form_frame, text="First Name:").grid(row=1, column=0, sticky="w", pady=5)
        self.firstName = ttk.Entry(form_frame, width=25)
        self.firstName.grid(row=1, column=1, pady=5, padx=(0, 10))
        
        ttk.Label(form_frame, text="Last Name:").grid(row=2, column=0, sticky="w", pady=5)
        self.lastName = ttk.Entry(form_frame, width=25)
        self.lastName.grid(row=2, column=1, pady=5, padx=(0, 10))
        
        ttk.Label(form_frame, text="Age:").grid(row=3, column=0, sticky="w", pady=5)
        self.age_label = ttk.Label(form_frame, text="0 years", font=("Verdana", 10))
        self.age_label.grid(row=3, column=1, pady=5, padx=(0, 10))
        
        ttk.Label(form_frame, text="Birth Date:").grid(row=4, column=0, sticky="w", pady=5)
        self.birthDate = DateEntry(form_frame, width=22, background="#3498db", 
                                  foreground="white", date_pattern='yyyy-mm-dd')
        self.birthDate.grid(row=4, column=1, pady=5, padx=(0, 10))
        self.birthDate.bind("<<DateEntrySelected>>", self.update_age_label)
        
        ttk.Label(form_frame, text="Type:").grid(row=5, column=0, sticky="w", pady=5)
        self.typeCombo = ttk.Combobox(form_frame, values=["Family", "Friend", "Work", "Other"], 
                                     state="readonly", width=22)
        self.typeCombo.grid(row=5, column=1, pady=5, padx=(0, 10))
        self.typeCombo.current(0)
        
        ttk.Label(form_frame, text="Sex:").grid(row=6, column=0, sticky="w", pady=5)
        sex_frame = tk.Frame(form_frame, bg="#e8f4f8")
        sex_frame.grid(row=6, column=1, sticky="w", pady=5)
        
        self.sex_var = tk.StringVar(value="Male")
        ttk.Radiobutton(sex_frame, text="Male", variable=self.sex_var, value="Male").pack(anchor="w")
        ttk.Radiobutton(sex_frame, text="Female", variable=self.sex_var, value="Female").pack(anchor="w")
        
        ttk.Label(form_frame, text="Hobbies:").grid(row=7, column=0, sticky="nw", pady=10)
        
        hobby_frame = tk.Frame(form_frame, bg="#e8f4f8")
        hobby_frame.grid(row=7, column=1, sticky="w", pady=10)
        
        hobbies = ["Play Soccer", "Read", "Sing", "Play An Instrument", "DJing", "Cook", "Swim"]
        self.hobby_vars = []
        
        for h in hobbies:
            var = tk.BooleanVar()
            ttk.Checkbutton(hobby_frame, text=h, variable=var).pack(anchor="w")
            self.hobby_vars.append((h, var))
        
        other_frame = tk.Frame(form_frame, bg="#e8f4f8")
        other_frame.grid(row=8, column=1, sticky="w", pady=5)
        self.other_var = tk.BooleanVar()
        ttk.Checkbutton(other_frame, text="Other:", variable=self.other_var).pack(side="left")
        self.other_entry = ttk.Entry(other_frame, width=18)
        self.other_entry.pack(side="left", padx=5)
        
        right_frame = tk.Frame(main_frame, bg="#e8f4f8")
        right_frame.grid(row=0, column=1, sticky="nsew")
        
        comments_label = tk.Label(right_frame, text="Comments:", bg="#e8f4f8", 
                                 fg="#2c3e50", font=("Verdana", 10))
        comments_label.pack(anchor="nw", pady=(0, 5))
        
        self.comments = tk.Text(right_frame, width=40, height=15, bg="white", 
                               fg="#2c3e50", font=("Verdana", 9))
        self.comments.pack()
        
        button_frame = tk.Frame(self.root, bg="#e8f4f8")
        button_frame.pack(pady=15)
        
        self.save_button = ttk.Button(button_frame, text="Save", command=self.save_contact)
        self.save_button.pack()
    
    def init_date_chooser(self):
        today = datetime.now()
        max_date = today.date()
        min_date = date(today.year - 150, today.month, today.day)
        
        self.birthDate.configure(mindate=min_date, maxdate=max_date)
        
        default_date = date(today.year - 25, today.month, today.day)
        self.birthDate.set_date(default_date)
    
    def update_display(self):
        self.update_next_id()
        self.update_age_label()
    
    def update_next_id(self):
        next_id = self.controller.get_next_contact_id()
        self.next_id_label.config(text=str(next_id))
    
    def update_age_label(self, event=None):
        try:
            birth_date_str = self.birthDate.get_date().strftime("%Y-%m-%d")
            age = self.controller.calculate_age(birth_date_str)
            self.age_label.config(text=f"{age} years")
        except:
            self.age_label.config(text="0 years")

    
    def contains_numbers(self, text):
        return bool(re.search(r'\d', text))
    
    def contains_internal_spaces(self, text):
        return ' ' in text.strip()
    
    def validate_name(self, name, field_name):
        if not name:
            messagebox.showwarning("Required Field", f"Please enter {field_name}")
            return False
        
        if self.contains_numbers(name):
            messagebox.showwarning("Invalid Input", f"{field_name} cannot contain numbers")
            return False
        
        if self.contains_internal_spaces(name):
            messagebox.showwarning("Invalid Input", f"{field_name} cannot contain spaces")
            return False
        
        return True
    
    def validate_date(self, birth_date):
        if not birth_date:
            messagebox.showwarning("Required Field", "Please select a birth date")
            return False
        
        today = date.today()
        
        if birth_date > today:
            messagebox.showwarning("Invalid Date", "Birth date cannot be in the future")
            return False
        
        min_date = date(today.year - 150, today.month, today.day)
        if birth_date < min_date:
            messagebox.showwarning("Invalid Date", "Please select a more recent birth date (within the last 150 years)")
            return False
        
        return True
    
    def validate_form(self):
        first_name = self.firstName.get().strip()
        if not self.validate_name(first_name, "First Name"):
            self.firstName.focus_set()
            return False
        
        last_name = self.lastName.get().strip()
        if not self.validate_name(last_name, "Last Name"):
            self.lastName.focus_set()
            return False
        
        birth_date = self.birthDate.get_date()
        if not self.validate_date(birth_date):
            return False
        
        contact_type = self.typeCombo.get()
        if not contact_type:
            messagebox.showwarning("Required Field", "Please select a contact type")
            self.typeCombo.focus_set()
            return False
        
        sex = self.sex_var.get()
        if not sex:
            messagebox.showwarning("Required Field", "Please select gender")
            return False
        
        if self.other_var.get():
            other_hobby = self.other_entry.get().strip()
            if not other_hobby:
                messagebox.showwarning("Required Field", "Please enter the other hobby")
                self.other_entry.focus_set()
                return False
        
        return True
    
    def save_contact(self):
        if not self.validate_form():
            return
        
        first_name = self.firstName.get().strip()
        last_name = self.lastName.get().strip()
        birth_date = self.birthDate.get_date()
        contact_type = self.typeCombo.get()
        sex = self.sex_var.get()  
        comments = self.comments.get("1.0", tk.END).strip()
        
        selected_hobbies = [h for h, var in self.hobby_vars if var.get()]
        if self.other_var.get():
            selected_hobbies.append(self.other_entry.get().strip())
        
        birth_date_str = birth_date.strftime("%Y-%m-%d")
        calculated_age = self.controller.calculate_age(birth_date_str)
        next_id = self.controller.get_next_contact_id()
        
        temp_contact = Contact(
            next_id, first_name, last_name, calculated_age,
            contact_type, sex, selected_hobbies, comments
        )
        
        confirmation_message = f"Please confirm the contact information:\n\n{temp_contact}\n\n"
        confirmation_message += f"ID to be assigned: {next_id}\n"
        confirmation_message += "\nAre you sure you want to save this contact?"
        
        if not messagebox.askyesno("Confirm Contact Details", confirmation_message):
            messagebox.showinfo("Cancelled", "Save operation cancelled")
            return
        
        contact = Contact(0, first_name, last_name, 0, contact_type, sex, selected_hobbies, comments)
        success, assigned_id, final_age = self.controller.save_contact(contact, birth_date_str)
        
        if success:
            messagebox.showinfo(
                "Success",
                f"Contact saved successfully!\n"
                f"Name: {first_name} {last_name}\n"
                f"ID: {assigned_id}\n"
                f"Age: {final_age} years"
            )
            self.clear_form()
            self.update_display()
        else:
            messagebox.showerror("Error", "Error saving contact to database")
    
    def clear_form(self):
        self.firstName.delete(0, tk.END)
        self.lastName.delete(0, tk.END)
        self.other_entry.delete(0, tk.END)
        self.comments.delete("1.0", tk.END)
        
        self.typeCombo.current(0)
        self.sex_var.set("Male")
        
        today = datetime.now()
        default_date = date(today.year - 25, today.month, today.day)
        self.birthDate.set_date(default_date)
        
        for _, var in self.hobby_vars:
            var.set(False)
        self.other_var.set(False)
        
        self.update_age_label()
        self.firstName.focus_set()

def main():
    root = tk.Tk()
    app = ContactsGUI(root)
    root.mainloop()

if __name__ == "__main__":
    main()