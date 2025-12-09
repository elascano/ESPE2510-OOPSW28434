import tkinter as tk
from tkinter import ttk, messagebox
from tkcalendar import DateEntry
from pymongo import MongoClient
from datetime import datetime, date
import re

client = MongoClient("mongodb+srv://Mateo:Mateo2006@cluster0.2mp0ve2.mongodb.net/?appName=Cluster0")
db = client["ContactDB"]        
collection = db["Contacs"]      

root = tk.Tk()
root.title("CONTACTS")
root.geometry("700x560")
root.configure(bg="#2b2f38")

style = ttk.Style()
style.theme_use("clam")
style.configure("TLabel", background="#2b2f38", foreground="white")
style.configure("TCheckbutton", background="#2b2f38", foreground="white")
style.configure("TRadiobutton", background="#2b2f38", foreground="white")
style.configure("TEntry", fieldbackground="#3a3f47", foreground="white")
style.configure("TCombobox", fieldbackground="#3a3f47", foreground="white")
style.configure("TButton", background="#3a3f47", foreground="white")

title = ttk.Label(root, text="CONTACTS", font=("Arial", 20))
title.pack(pady=10)

frame = tk.Frame(root, bg="#2b2f38")
frame.pack(padx=20, pady=10, fill="both", expand=True)

left = tk.Frame(frame, bg="#2b2f38")
left.grid(row=0, column=0, sticky="nw")


def calculate_age(event=None):
    try:
        
        birth_str = birthDate.get_date()  
        today = date.today()
        
        
        calculated_age = today.year - birth_str.year - (
            (today.month, today.day) < (birth_str.month, birth_str.day)
        )
        
        
        age.config(state="normal")
        age.delete(0, tk.END)
        age.insert(0, str(calculated_age))
        age.config(state="readonly")
        
    except Exception as e:
        
        age.config(state="normal")
        age.delete(0, tk.END)
        age.config(state="readonly")


ttk.Label(left, text="Id:").grid(row=0, column=0, sticky="w", pady=5)

ttk.Label(left, text="First Name:*").grid(row=1, column=0, sticky="w", pady=5)
firstName = ttk.Entry(left)
firstName.grid(row=1, column=1, pady=5)

ttk.Label(left, text="Last Name:*").grid(row=2, column=0, sticky="w", pady=5)
lastName = ttk.Entry(left)
lastName.grid(row=2, column=1, pady=5)

ttk.Label(left, text="Age:").grid(row=3, column=0, sticky="w", pady=5)
age = ttk.Entry(left, state="readonly")
age.grid(row=3, column=1, pady=5)

ttk.Label(left, text="Birth Date:*").grid(row=4, column=0, sticky="w", pady=5)

birthDate = DateEntry(
    left, 
    width=17, 
    background="darkblue", 
    foreground="white",
    date_pattern='mm/dd/yyyy',  
    year=2000, 
    month=1,    
    day=1,      
    mindate=date(1900, 1, 1),  
    maxdate=date.today()       
)
birthDate.grid(row=4, column=1, pady=5)

birthDate.bind("<<DateEntrySelected>>", calculate_age)

ttk.Label(left, text="Type:*").grid(row=5, column=0, sticky="w", pady=5)
typeCombo = ttk.Combobox(left, values=["Family", "Friend", "Work", "Other"])
typeCombo.grid(row=5, column=1, pady=5)

ttk.Label(left, text="Sex:*").grid(row=6, column=0, sticky="w", pady=5)
sex_var = tk.StringVar()
ttk.Radiobutton(left, text="Male", variable=sex_var, value="M").grid(row=6, column=1, sticky="w")
ttk.Radiobutton(left, text="Female", variable=sex_var, value="F").grid(row=7, column=1, sticky="w")

ttk.Label(left, text="Hobbies:").grid(row=8, column=0, sticky="w", pady=10)

hobby_frame = tk.Frame(left, bg="#2b2f38")
hobby_frame.grid(row=9, column=1, sticky="w")

hobbies = ["Play Soccer", "Read", "Sing", "Play An Instrument", "DJing", "Cook", "Swim"]
hobby_vars = []

for h in hobbies:
    var = tk.BooleanVar()
    ttk.Checkbutton(hobby_frame, text=h, variable=var).pack(anchor="w")
    hobby_vars.append((h, var))

other_frame = tk.Frame(left, bg="#2b2f38")
other_frame.grid(row=10, column=1, sticky="w", pady=5)
other_var = tk.BooleanVar()
ttk.Checkbutton(other_frame, text="Other:", variable=other_var).pack(side="left")
other_entry = ttk.Entry(other_frame, width=15)
other_entry.pack(side="left", padx=5)


right = tk.Frame(frame, bg="#2b2f38")
right.grid(row=0, column=1, padx=20, sticky="ne")

ttk.Label(right, text="Comments:").pack(anchor="nw")
comments = tk.Text(right, width=40, height=15, bg="#3a3f47", fg="white")
comments.pack()


def validate_fields():
    errors = []
    
    
    if not firstName.get().strip():
        errors.append("First Name is required")
    elif not re.match("^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+$", firstName.get().strip()):
        errors.append("First Name should contain only letters")
    
    
    if not lastName.get().strip():
        errors.append("Last Name is required")
    elif not re.match("^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+$", lastName.get().strip()):
        errors.append("Last Name should contain only letters")
    
    
    try:
        birth_date = birthDate.get_date()
        today = date.today()
        
        
        if birth_date > today:
            errors.append("Birth Date cannot be in the future")
        
       
        if birth_date.year < today.year - 120:
            errors.append("Birth Date seems unrealistic (more than 120 years old)")
            
    except Exception as e:
        errors.append(f"Invalid Birth Date: {str(e)}")
    
    
    if not typeCombo.get().strip():
        errors.append("Type is required")
    
   
    if not sex_var.get():
        errors.append("Sex is required")
    
    
    if not age.get().strip():
        errors.append("Please select a valid Birth Date to calculate age")
    
    return errors

def save_data():
   
    validation_errors = validate_fields()
    
    if validation_errors:
        error_message = "Please correct the following errors:\n\n" + "\n".join(validation_errors)
        messagebox.showerror("Validation Error", error_message)
        return
    
    
    selected_hobbies = [h for h, var in hobby_vars if var.get()]

    if other_var.get() and other_entry.get().strip():
        selected_hobbies.append(other_entry.get().strip())

    
    birth_date_obj = birthDate.get_date()
    birth_date_str = birth_date_obj.strftime("%Y-%m-%d")
    
    
    document = {
        "firstName": firstName.get().strip(),
        "lastName": lastName.get().strip(),
        "age": int(age.get()),  
        "birthDate": birth_date_str,  
        "type": typeCombo.get(),
        "sex": sex_var.get(),
        "hobbies": selected_hobbies,
        "comments": comments.get("1.0", tk.END).strip()
    }

    
    try:
        collection.insert_one(document)
        messagebox.showinfo("Saved", "Contact saved successfully in MongoDB Atlas!")
        clear_fields()
    except Exception as e:
        messagebox.showerror("Error", f"Error saving to MongoDB: {str(e)}")


def clear_fields():
    firstName.delete(0, tk.END)
    lastName.delete(0, tk.END)
    age.config(state="normal")
    age.delete(0, tk.END)
    age.config(state="readonly")
    typeCombo.set("")
    sex_var.set("")
    
    for _, var in hobby_vars:
        var.set(False)
    
    other_var.set(False)
    other_entry.delete(0, tk.END)
    comments.delete("1.0", tk.END)
    
    
    birthDate.set_date(date(2000, 1, 1))
    calculate_age()  

save_button = ttk.Button(root, text="Save", command=save_data)
save_button.pack(pady=15)


root.after(100, calculate_age)

root.mainloop()