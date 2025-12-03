import tkinter as tk
from tkinter import ttk

root = tk.Tk()
root.title("Resident Rental")
root.geometry("700x450")
root.configure(bg="#f5f7fa")

style = ttk.Style()
style.theme_use("clam")
style.configure("TLabel", background="#f5f7fa", foreground="#2c3e50", font=("Segoe UI", 10))
style.configure("TEntry", fieldbackground="white", foreground="#2c3e50", font=("Segoe UI", 10), borderwidth=1)
style.configure("TCombobox", fieldbackground="white", foreground="#2c3e50", font=("Segoe UI", 10))
style.configure("TButton", background="#4a90e2", foreground="white", font=("Segoe UI", 10, "bold"))

title = ttk.Label(root, text="Resident Rental", font=("Segoe UI", 20, "bold"), foreground="#2c3e50")
title.pack(pady=20)

frame = tk.Frame(root, bg="#f5f7fa")
frame.pack(padx=30, pady=10, fill="both", expand=True)

search_frame = tk.Frame(frame, bg="#f5f7fa")
search_frame.pack(fill="x", pady=(0, 20))

ttk.Label(search_frame, text="Entry ID or Full Name:", font=("Segoe UI", 11)).pack(side="left", padx=(0, 10))
search_entry = ttk.Entry(search_frame, width=30)
search_entry.pack(side="left", padx=(0, 15))

search_button = ttk.Button(search_frame, text="Search Resident")
search_button.pack(side="left")

divider = ttk.Separator(frame, orient="horizontal")
divider.pack(fill="x", pady=15)

top_frame = tk.Frame(frame, bg="#f5f7fa")
top_frame.pack(fill="x", pady=(0, 20))

value_label = ttk.Label(top_frame, text="Value to pay:", font=("Segoe UI", 12, "bold"), foreground="#2c3e50")
value_label.pack(side="left", anchor="w")

months_use_label = ttk.Label(top_frame, text="Months of use:", font=("Segoe UI", 12, "bold"), foreground="#2c3e50")
months_use_label.pack(side="right", anchor="e")

info_label = ttk.Label(frame, text="If you wish to renew your subscription, please fill in the following fields:", 
                       font=("Segoe UI", 10), foreground="#666666", wraplength=550)
info_label.pack(anchor="w", pady=(0, 20))

fields_frame = tk.Frame(frame, bg="#f5f7fa")
fields_frame.pack(fill="x", pady=10)

spaces_label = ttk.Label(fields_frame, text="Available spaces:", font=("Segoe UI", 11))
spaces_label.grid(row=0, column=0, sticky="w", pady=8, padx=(0, 15))

spaces_combo = ttk.Combobox(fields_frame, width=15)
spaces_combo['values'] = [
    "AZU-A-03", "AZU-B-02", "CAR-A-04", "CAR-B-03", 
    "CAN-B-03", "CHI-A-03", "CHI-B-02", "COT-B-03", 
    "ORO-A-03", "ORO-B-02", "ESM-B-03"
]
spaces_combo.current(0)
spaces_combo.grid(row=0, column=1, sticky="w", pady=8)

months_label = ttk.Label(fields_frame, text="Months of subscription:", font=("Segoe UI", 11))
months_label.grid(row=1, column=0, sticky="w", pady=8, padx=(0, 15))

months_combo = ttk.Combobox(fields_frame, width=15)
months_combo['values'] = ["1 to 3 months", "3 to 6 months", "6 to 12 months"]
months_combo.current(0)
months_combo.grid(row=1, column=1, sticky="w", pady=8)

buttons_frame = tk.Frame(frame, bg="#f5f7fa")
buttons_frame.pack(pady=40)

def dummy_action():
    print()

pay_button = ttk.Button(buttons_frame, text="Pay", command=dummy_action, width=12)
pay_button.pack(side="left", padx=5)

pay_renew_button = ttk.Button(buttons_frame, text="Pay and Renew", command=dummy_action, width=15)
pay_renew_button.pack(side="left", padx=5)

exit_button = ttk.Button(buttons_frame, text="Exit", command=root.quit, width=12)
exit_button.pack(side="left", padx=5)

root.mainloop()