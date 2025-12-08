import tkinter as tk
from tkinter import ttk
from tkcalendar import DateEntry

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
save_button.pack(pady=20)

root.mainloop()
