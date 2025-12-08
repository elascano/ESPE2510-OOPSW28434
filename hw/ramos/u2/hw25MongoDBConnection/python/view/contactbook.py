import sys
import os
sys.path.append(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
from controller.ContactController import ContactController
import tkinter as tk
from tkinter import ttk
from tkcalendar import DateEntry
from tkinter import messagebox

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
def on_save():
    first_name = fname_entry.get().strip()
    last_name = lname_entry.get().strip()
    comments = comments_text.get("1.0", tk.END).strip()

    if not is_alpha(first_name):
        messagebox.showerror("Error", "First Name solo debe contener letras.")
        return

    if not is_alpha(last_name):
        messagebox.showerror("Error", "Last Name solo debe contener letras.")
        return

    id = 1
    birth_date = birth_entry.get_date()
    age = 21
    type_of_contact = type_combo.get()
    sex = sex_var.get()

    selected_indices = hobbies_list.curselection()
    hobbies = [hobbies_list.get(i) for i in selected_indices]

    saved = controller.add_contact(
        id=id,
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
    else:
        messagebox.showerror("Error", "Failed to save contact in MongoDB")

save_button = tk.Button(root, text="Save", width=10, command=on_save)
save_button.pack(pady=20)
root.mainloop()
