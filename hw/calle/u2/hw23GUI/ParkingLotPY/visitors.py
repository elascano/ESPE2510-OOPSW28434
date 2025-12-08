import tkinter as tk
from tkinter import ttk

def create_visitor_registration_form():
    root = tk.Tk()
    root.title("VISITORS REGISTRATION")
    root.config(bg="#CCCCFF")

    header_frame = tk.Frame(root, bg="#CCCCFF")
    header_frame.pack(pady=10)
    tk.Label(header_frame, text="VISITORS REGISTRATION", font=("Verdana", 14, "bold"), bg="#CCCCFF").pack()

    personal_data_frame = tk.LabelFrame(root, text="Personal data", font=("Verdana", 12, "bold"), bg="#CCCCFF", padx=10, pady=10)
    personal_data_frame.pack(padx=20, pady=10, fill="x")

    fields = [
        ("Visitor ID:", 0, 0, 1),
        ("CI:", 0, 2, 3),
        ("First Name:", 1, 0, 1),
        ("Age:", 1, 2, 3),
        ("Last Name:", 2, 0, 1),
    ]
    
    entry_vars = {} 

    for label_text, row, col_label, col_entry in fields:
        tk.Label(personal_data_frame, text=label_text, bg="#CCCCFF").grid(row=row, column=col_label, padx=5, pady=5, sticky="w")
        
        entry_var = tk.StringVar()
        entry = ttk.Entry(personal_data_frame, textvariable=entry_var)
        entry.grid(row=row, column=col_entry, padx=5, pady=5, sticky="ew")
        entry_vars[label_text] = entry_var

    personal_data_frame.grid_columnconfigure(1, weight=1) 
    personal_data_frame.grid_columnconfigure(3, weight=1) 


    vehicle_details_frame = tk.LabelFrame(root, text="Vehicle Details", font=("Verdana", 12, "bold"), bg="#CCCCFF", padx=10, pady=10)
    vehicle_details_frame.pack(padx=20, pady=10, fill="x")

    tk.Label(vehicle_details_frame, text="Plate:", bg="#CCCCFF").grid(row=0, column=0, padx=5, pady=5, sticky="w")
    plate_entry = ttk.Entry(vehicle_details_frame)
    plate_entry.grid(row=0, column=1, padx=5, pady=5, sticky="ew")
    
    tk.Label(vehicle_details_frame, text="Vehicle Type:", bg="#CCCCFF").grid(row=1, column=0, padx=5, pady=5, sticky="w")
    vehicle_types = ["Car", "SUV", "Hatchback", "Motorcycle", "Truck", "Other"]
    vehicle_combo = ttk.Combobox(vehicle_details_frame, values=vehicle_types, state="readonly")
    vehicle_combo.set("Car")
    vehicle_combo.grid(row=1, column=1, padx=5, pady=5, sticky="ew")
    
    tk.Label(vehicle_details_frame, text="Access Type:", bg="#CCCCFF").grid(row=0, column=2, padx=20, pady=5, sticky="w")
    access_types = ["temporary", "recurrent"]
    access_combo = ttk.Combobox(vehicle_details_frame, values=access_types, state="readonly")
    access_combo.set("temporary")
    access_combo.grid(row=0, column=3, padx=5, pady=5, sticky="ew")

    vehicle_details_frame.grid_columnconfigure(1, weight=1)
    vehicle_details_frame.grid_columnconfigure(3, weight=1)


    button_frame = tk.Frame(root, bg="#CCCCFF")
    button_frame.pack(pady=20)
    
    ttk.Button(button_frame, text="DELETE", command=lambda: print("DELETE clicked")).pack(side="left", padx=20)
    ttk.Button(button_frame, text="SAVE", command=lambda: print("SAVE clicked")).pack(side="left", padx=20)
    ttk.Button(button_frame, text="UPLOAD", command=lambda: print("UPLOAD clicked")).pack(side="left", padx=20)

    root.mainloop()

if __name__ == "__main__":
    create_visitor_registration_form()