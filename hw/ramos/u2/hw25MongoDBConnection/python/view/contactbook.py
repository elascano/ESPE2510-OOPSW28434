import sys
import os
sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
from controller.ContactController import ContactController
import tkinter as tk
from tkinter import ttk
from tkcalendar import DateEntry
from tkinter import messagebox
from datetime import date
import re

controller = ContactController() 
def is_alpha(text):
    return bool(re.match(r'^[A-Za-z\s]+$', text))

def no_numbers(text):
    return not any(char.isdigit() for char in text)

root = tk.Tk()
root.title("CONTACTS")
root.geometry("650x550")
root.configure(bg="#d0d3db")

title = tk.Label(root, text="CONTACTS", font=("Arial", 18, "bold"), bg="#d0d3db")
title.pack(pady=10)

frame = tk.Frame(root, bg="#d0d3db")
frame.pack(pady=10)

tk.Label(frame, text="id:", bg="#d0d3db").grid(row=0, column=0, sticky="w", pady=5)

tk.Label(frame, text="First Name:", bg="#d0d3db").grid(row=1, column=0, sticky="w", pady=5)
fname_entry = tk.Entry(frame)
fname_entry.grid(row=1, column=1, pady=5, sticky="w")

tk.Label(frame, text="Last Name:", bg="#d0d3db").grid(row=2, column=0, sticky="w", pady=5)
lname_entry = tk.Entry(frame)
lname_entry.grid(row=2, column=1, pady=5, sticky="w")

tk.Label(frame, text="Birth Date:", bg="#d0d3db").grid(row=3, column=0, sticky="w", pady=5)
birth_entry = DateEntry(frame, width=18, background='darkblue', foreground='white', date_pattern='dd/mm/yyyy')
birth_entry.grid(row=3, column=1, sticky="w")

tk.Label(frame, text="Comments:", bg="#d0d3db").grid(row=0, column=2, padx=20, sticky="w")
comments_text = tk.Text(frame, width=35, height=10)
comments_text.grid(row=1, column=2, rowspan=4, padx=20)

tk.Label(frame, text="Age:", bg="#d0d3db").grid(row=4, column=0, sticky="w", pady=5)

tk.Label(frame, text="Type:", bg="#d0d3db").grid(row=5, column=0, sticky="w", pady=5)
type_combo = ttk.Combobox(frame, values=["Family", "Friend", "Work", "Unknown"])
type_combo.current(0)
type_combo.grid(row=5, column=1, sticky="w")

tk.Label(frame, text="Sex:", bg="#d0d3db").grid(row=6, column=0, sticky="w", pady=5)

sex_var = tk.StringVar(value="Male")
tk.Radiobutton(frame, text="Male", variable=sex_var, value="Male", bg="#d0d3db").grid(row=6, column=1, sticky="w")
tk.Radiobutton(frame, text="Female", variable=sex_var, value="Female", bg="#d0d3db").grid(row=7, column=1, sticky="w")

tk.Label(frame, text="Hobbies:", bg="#d0d3db").grid(row=7, column=0, sticky="nw", pady=5)

hobbies_list = tk.Listbox(frame, height=8, width=20, selectmode=tk.MULTIPLE)
hobbies_list.grid(row=8, column=1, pady=5)

hobbies = ["Play Soccer", "Dijing", "Read", "Cook", "Swim", "Sing", "Play an instrument"]
for h in hobbies:
    hobbies_list.insert(tk.END, h)

save_button = tk.Button(root, text="Save", width=10)

def calculate_age(birth_date):
    today = date.today()
    return today.year - birth_date.year - (
        (today.month, today.day) < (birth_date.month, birth_date.day)
    )

def clear_fields():
    fname_entry.delete(0, tk.END)
    lname_entry.delete(0, tk.END)
    comments_text.delete("1.0", tk.END)
    type_combo.current(0)
    sex_var.set("Male")
    hobbies_list.selection_clear(0, tk.END)

def on_save():
    first_name = fname_entry.get().strip()
    last_name = lname_entry.get().strip()
    comments = comments_text.get("1.0", tk.END).strip()
    birth_date = birth_entry.get_date()
    type_of_contact = type_combo.get()
    sex = sex_var.get()

    if first_name == "" or not is_alpha(first_name):
        messagebox.showerror("Error", "The name must contain only letters and cannot be empty.")
        fname_entry.focus()
        return

    if last_name == "" or not is_alpha(last_name):
        messagebox.showerror("Error", "The last name must contain only letters and cannot be empty.")
        lname_entry.focus()
        return

    if hasattr(birth_date, "date"):
        birth_date = birth_date.date()

    if birth_date >= date.today():
        messagebox.showerror("Error", "The date of birth must be earlier than today.")
        return

    if sex not in ["Male", "Female"]:
        messagebox.showerror("Error", "You must select a sex option.")
        return

    selected_indices = hobbies_list.curselection()
    if len(selected_indices) == 0:
        messagebox.showerror("Error", "You must select at least one hobby.")
        hobbies_list.focus()
        return

    age = calculate_age(birth_date)
    hobbies = [hobbies_list.get(i) for i in selected_indices]

    option = messagebox.askyesnocancel(
        "CONTACTS",
        "Do you want to save this contact?"
    )

    if option is True:   
        saved = controller.add_contact(
            id=1,
            first_name=first_name,
            last_name=last_name,
            birth_date=birth_date,
            age=age,
            type_of_contact=type_of_contact,
            sex=sex,
            hobbies=hobbies,
            comments=comments
        )

        if saved:
            messagebox.showinfo("Success", "Contact saved successfully")
            clear_fields()
        else:
            messagebox.showerror("Error", "Failed to save contact in MongoDB")

    elif option is False: 
        messagebox.showwarning("Warning", "Your data will be lost.")
        clear_fields()

    else:  
        fname_entry.focus()

save_button = tk.Button(root, text="Save", width=10, command=on_save)
save_button.pack(pady=20)
root.mainloop()
