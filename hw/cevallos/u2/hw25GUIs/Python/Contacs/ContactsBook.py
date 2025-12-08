import tkinter as tk
from tkinter import ttk, messagebox
from tkcalendar import DateEntry
from pymongo import MongoClient

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

# ------ Campos ------
ttk.Label(left, text="Id:").grid(row=0, column=0, sticky="w", pady=5)
# (ID automático, no entry)

ttk.Label(left, text="First Name:").grid(row=1, column=0, sticky="w", pady=5)
firstName = ttk.Entry(left)
firstName.grid(row=1, column=1, pady=5)

ttk.Label(left, text="Last Name:").grid(row=2, column=0, sticky="w", pady=5)
lastName = ttk.Entry(left)
lastName.grid(row=2, column=1, pady=5)

ttk.Label(left, text="Age:").grid(row=3, column=0, sticky="w", pady=5)
age = ttk.Entry(left)
age.grid(row=3, column=1, pady=5)

ttk.Label(left, text="Birth Date:").grid(row=4, column=0, sticky="w", pady=5)
birthDate = DateEntry(left, width=17, background="darkblue", foreground="white")
birthDate.grid(row=4, column=1, pady=5)

ttk.Label(left, text="Type:").grid(row=5, column=0, sticky="w", pady=5)
typeCombo = ttk.Combobox(left, values=["Family", "Friend", "Work", "Other"])
typeCombo.grid(row=5, column=1, pady=5)

ttk.Label(left, text="Sex:").grid(row=6, column=0, sticky="w", pady=5)
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



def save_data():
    selected_hobbies = [h for h, var in hobby_vars if var.get()]

    if other_var.get() and other_entry.get().strip():
        selected_hobbies.append(other_entry.get())

    document = {
        "firstName": firstName.get(),
        "lastName": lastName.get(),
        "age": age.get(),
        "birthDate": birthDate.get(),
        "type": typeCombo.get(),
        "sex": sex_var.get(),
        "hobbies": selected_hobbies,
        "comments": comments.get("1.0", tk.END).strip()
    }

    collection.insert_one(document)
    messagebox.showinfo("Saved", "Contact saved successfully in MongoDB Atlas!")


save_button = ttk.Button(root, text="Save", command=save_data)
save_button.pack(pady=15)

root.mainloop()
