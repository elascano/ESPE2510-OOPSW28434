import tkinter as tk
from tkinter import ttk
from tkcalendar import DateEntry

root = tk.Tk()
root.title("CONTACTS")
root.geometry("700x560")
root.configure(bg="#f5f7fa")

style = ttk.Style()
style.theme_use("clam")
style.configure("TLabel", background="#f5f7fa", foreground="#2c3e50", font=("Segoe UI", 10))
style.configure("TCheckbutton", background="#f5f7fa", foreground="#2c3e50", font=("Segoe UI", 10))
style.configure("TRadiobutton", background="#f5f7fa", foreground="#2c3e50", font=("Segoe UI", 10))
style.configure("TEntry", fieldbackground="white", foreground="#2c3e50", font=("Segoe UI", 10), borderwidth=1)
style.configure("TCombobox", fieldbackground="white", foreground="#2c3e50", font=("Segoe UI", 10))
style.configure("TButton", background="#4a90e2", foreground="white", font=("Segoe UI", 10, "bold"))

title = ttk.Label(root, text="CONTACTS", font=("Segoe UI", 22, "bold"), foreground="#2c3e50")
title.pack(pady=15)

frame = tk.Frame(root, bg="#f5f7fa")
frame.pack(padx=20, pady=10, fill="both", expand=True)

left = tk.Frame(frame, bg="#f5f7fa")
left.grid(row=0, column=0, sticky="nw")

ttk.Label(left, text="Id:").grid(row=0, column=0, sticky="w", pady=6)

ttk.Label(left, text="First Name:").grid(row=1, column=0, sticky="w", pady=6)
first_entry = ttk.Entry(left, width=22)
first_entry.grid(row=1, column=1, pady=6)

ttk.Label(left, text="Last Name:").grid(row=2, column=0, sticky="w", pady=6)
last_entry = ttk.Entry(left, width=22)
last_entry.grid(row=2, column=1, pady=6)

ttk.Label(left, text="Age:").grid(row=3, column=0, sticky="w", pady=6)

ttk.Label(left, text="Birth Date:").grid(row=4, column=0, sticky="w", pady=6)
birth_date = DateEntry(left, width=19, background="#4a90e2", foreground="white", 
                      borderwidth=1, date_pattern='yyyy-mm-dd')
birth_date.grid(row=4, column=1, pady=6)

ttk.Label(left, text="Type:").grid(row=5, column=0, sticky="w", pady=6)
type_combo = ttk.Combobox(left, values=["Family", "Friend", "Work", "Other"], width=19)
type_combo.grid(row=5, column=1, pady=6)
type_combo.current(0)

ttk.Label(left, text="Sex:").grid(row=6, column=0, sticky="w", pady=6)
sex_var = tk.StringVar(value="M")
ttk.Radiobutton(left, text="Male", variable=sex_var, value="M").grid(row=6, column=1, sticky="w")
ttk.Radiobutton(left, text="Female", variable=sex_var, value="F").grid(row=7, column=1, sticky="w")

ttk.Label(left, text="Hobbies:").grid(row=8, column=0, sticky="w", pady=10)

hobby_frame = tk.Frame(left, bg="#f5f7fa")
hobby_frame.grid(row=9, column=1, sticky="w")

hobbies = ["Play Soccer", "Read", "Sing", "Play An Instrument", "DJing", "Cook", "Swim"]
hobby_vars = []

for h in hobbies:
    var = tk.BooleanVar()
    cb = ttk.Checkbutton(hobby_frame, text=h, variable=var)
    cb.pack(anchor="w", pady=2)
    hobby_vars.append(var)

other_frame = tk.Frame(left, bg="#f5f7fa")
other_frame.grid(row=10, column=1, sticky="w", pady=8)
other_var = tk.BooleanVar()
ttk.Checkbutton(other_frame, text="Other:", variable=other_var).pack(side="left")
other_entry = ttk.Entry(other_frame, width=15)
other_entry.pack(side="left", padx=5)

right = tk.Frame(frame, bg="#f5f7fa")
right.grid(row=0, column=1, padx=30, sticky="ne")

ttk.Label(right, text="Comments:").pack(anchor="nw")
comments = tk.Text(right, width=35, height=15, bg="white", fg="#2c3e50", 
                   font=("Segoe UI", 10), relief="solid", borderwidth=1)
comments.pack()

save_button = ttk.Button(root, text="Save")
save_button.pack(pady=15)

root.mainloop()