import tkinter as tk
from tkinter import ttk
from tkcalendar import DateEntry

root = tk.Tk()
root.title("Event Menu")
root.geometry("750x500")
root.configure(bg="#2b2f38")

style = ttk.Style()
style.theme_use("clam")
style.configure("TLabel", background="#2b2f38", foreground="white")
style.configure("TButton", background="#3a3f47", foreground="white")
style.configure("TEntry", fieldbackground="#3a3f47", foreground="white")
style.configure("TCombobox", fieldbackground="#3a3f47", foreground="white")
style.configure("TSpinbox", fieldbackground="#3a3f47", foreground="white")

title = ttk.Label(root, text="Event Menu:", font=("Arial", 22))
title.pack(pady=15)

frame = tk.Frame(root, bg="#2b2f38")
frame.pack(fill="both", expand=True, pady=10)

row1 = tk.Frame(frame, bg="#2b2f38")
row1.pack(fill="x", pady=10)

ttk.Label(row1, text="Search for a client (ID): ").pack(side="left", padx=5)
search_entry = ttk.Entry(row1, width=12)
search_entry.pack(side="left", padx=5)
ttk.Button(row1, text="Search").pack(side="left", padx=5)

tk.Label(row1, text=" " * 10, bg="#2b2f38").pack(side="left")

ttk.Label(row1, text="Assing Event: ").pack(side="left", padx=5)
combo = ttk.Combobox(row1, values=["Weddings", "Birthday", "Baptism", "Conference", "Other"], width=15)
combo.set("Weddings")
combo.pack(side="left", padx=5)

row2 = tk.Frame(frame, bg="#2b2f38")
row2.pack(anchor="w", pady=10, padx=20)

ttk.Label(row2, text="Date: ").pack(side="left", padx=5)
date_picker = DateEntry(row2, width=15, background="darkblue", foreground="white", borderwidth=2)
date_picker.pack(side="left", padx=5)

row3 = tk.Frame(frame, bg="#2b2f38")
row3.pack(anchor="w", pady=5, padx=20)
ttk.Label(row3, text="ID: ").pack(side="left", padx=5)
ttk.Entry(row3, width=20).pack(side="left", padx=5)

row4 = tk.Frame(frame, bg="#2b2f38")
row4.pack(fill="x", pady=30)

left_section = tk.Frame(row4, bg="#2b2f38")
left_section.pack(side="left", padx=40)

ttk.Label(left_section, text="Discount: ").grid(row=0, column=0, padx=5)
disc_spin = ttk.Spinbox(left_section, from_=0, to=100, width=5)
disc_spin.set(0)
disc_spin.grid(row=0, column=1, padx=5)
ttk.Button(left_section, text="Apply").grid(row=0, column=2, padx=10)

right_section = tk.Frame(row4, bg="#2b2f38")
right_section.pack(side="left", padx=40)
ttk.Label(right_section, text="Value to be paid:").pack(anchor="w")

ttk.Button(root, text="Register", width=15).pack(pady=15)

root.mainloop()
