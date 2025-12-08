import tkinter as tk
from tkinter import ttk
from tkcalendar import DateEntry 
import re
from pymongo import MongoClient
import datetime

MONGO_URI = "mongodb+srv://Emily:Emily2006@cluster0.ynnit6l.mongodb.net/?retryWrites=true&w=majority" 
DB_NAME = "ContactsBookPY" 
COLLECTION_NAME = "Contacts" 

try:
    client = MongoClient(MONGO_URI)
    db = client[DB_NAME]
    contacts_collection = db[COLLECTION_NAME]
    print("Conexión a MongoDB exitosa.")
except Exception as e:
    print(f"Error al conectar a MongoDB: {e}")
    client = None
    db = None

def save_contact():
    if not client:
        print("ERROR: No se pudo conectar a la base de datos. Verifique su MONGO_URI y conexión.")
        return

    contact_id = entry_id.get().strip()
    first_name = entry_first_name.get().strip()
    last_name = entry_last_name.get().strip()
    birth_date_str = cal_birth_date.get()
    age_str = entry_age.get().strip()
    type_of_contact = combo_type.get()
    sex = var_sex.get()
    hobbies_selected = combo_hobbies.get() 
    comments = text_comments.get("1.0", tk.END).strip()

    if not first_name or not last_name:
        print("ERROR: First Name and Last Name are required.")
        return

    if contact_id and not contact_id.isdigit():
        print("ERROR: ID must contain only digits.")
        return
    
    try:
        age = int(age_str) if age_str.isdigit() else 0
    except ValueError:
        print("ERROR: Age must be a valid integer.")
        return
        
    try:
        birth_date = datetime.datetime.strptime(birth_date_str, '%d/%m/%Y').date()
    except ValueError:
        birth_date = None

    contact_data = {
        "contact_id": contact_id if contact_id else None,
        "first_name": first_name,
        "last_name": last_name,
        "birth_date": birth_date_str,
        "age": age,
        "type": type_of_contact,
        "sex": sex,
        "hobbies": hobbies_selected,
        "comments": comments,
        "created_at": datetime.datetime.now()
    }
    
    try:
        result = contacts_collection.insert_one(contact_data)
        
        entry_id.delete(0, tk.END)
        entry_first_name.delete(0, tk.END)
        entry_last_name.delete(0, tk.END)
        entry_age.delete(0, tk.END)
        combo_type.set("Friend")
        var_sex.set("Female")
        combo_hobbies.set("Cook")
        text_comments.delete("1.0", tk.END)

        print(f"Contacto '{first_name} {last_name}' guardado con éxito. ID de MongoDB: {result.inserted_id}")
        
    except Exception as e:
        print(f"ERROR al guardar en MongoDB: {e}")

root = tk.Tk()
root.title("CONTACTS")
root.geometry("650x550")
root.resizable(False, False)

header_frame = tk.Frame(root, height=50)
header_frame.pack(fill='x', padx=5, pady=(5, 0))
header_label = tk.Label(header_frame, text="CONTACTS", font=("Arial", 18, "bold"))
header_label.pack(pady=8)

body_frame = tk.Frame(root, padx=20, pady=20)
body_frame.pack(fill='both', expand=True, padx=5, pady=0)

content_grid_frame = tk.Frame(body_frame)
content_grid_frame.pack(expand=True, anchor="center")

left_frame = tk.Frame(content_grid_frame)
left_frame.grid(row=0, column=0, sticky="n", padx=(0, 40))

right_frame = tk.Frame(content_grid_frame)
right_frame.grid(row=0, column=1, sticky="n")

save_frame = tk.Frame(root, height=50)
save_frame.pack(fill='x', padx=5, pady=(0, 5))

entry_first_name = None
entry_last_name = None
entry_age = None
cal_birth_date = None
entry_id = None 

fields_left = [
    ("First Name:", 0),
    ("Last Name:", 1),
]

for i, (text, row) in enumerate(fields_left):
    label = tk.Label(left_frame, text=text)
    label.grid(row=row, column=0, sticky="w", pady=5, padx=5)

    entry = tk.Entry(left_frame, width=20)
    entry.grid(row=row, column=1, sticky="w", pady=5, padx=5)

    if text == "First Name:": entry_first_name = entry
    elif text == "Last Name:": entry_last_name = entry

row_birth_date = 2 
label_birth_date = tk.Label(left_frame, text="Birth Date:")
label_birth_date.grid(row=row_birth_date, column=0, sticky="w", pady=5, padx=5)

cal_birth_date = DateEntry(left_frame, width=18, 
                             date_pattern='dd/mm/yyyy', locale='es_ES') 
cal_birth_date.grid(row=row_birth_date, column=1, sticky="w", pady=5, padx=5)

label_age = tk.Label(left_frame, text="Age:")
label_age.grid(row=3, column=0, sticky="w", pady=5, padx=5)
entry_age = tk.Entry(left_frame, width=5)
entry_age.grid(row=3, column=1, sticky="w", pady=5, padx=5)

label_type = tk.Label(left_frame, text="Type:")
label_type.grid(row=4, column=0, sticky="w", pady=5, padx=5)
contact_types = ["Friend", "Family", "Job", "Unknown"] 
combo_type = ttk.Combobox(left_frame, values=contact_types, state="readonly", width=18)
combo_type.set("Friend")
combo_type.grid(row=4, column=1, sticky="w", pady=5, padx=5)

label_sex = tk.Label(left_frame, text="Sex:")
label_sex.grid(row=5, column=0, sticky="w", pady=5, padx=5)
var_sex = tk.StringVar(value="Female") 

radio_male = tk.Radiobutton(left_frame, text="Male", variable=var_sex, value="Male")
radio_female = tk.Radiobutton(left_frame, text="Female", variable=var_sex, value="Female")

radio_male.grid(row=5, column=1, sticky="w", padx=(5, 0))
radio_female.grid(row=6, column=1, sticky="w", padx=(5, 0))

label_hobbies = tk.Label(left_frame, text="Hobbies:")
label_hobbies.grid(row=7, column=0, sticky="w", pady=5, padx=5)
hobby_options = ["Cook", "PlaySoccer", "DJing", "Read", "Swim", "Sing", "Play an instrument"]
combo_hobbies = ttk.Combobox(left_frame, values=hobby_options, state="readonly", width=18)
combo_hobbies.set("Cook")
combo_hobbies.grid(row=7, column=1, sticky="w", pady=5, padx=5)

label_id = tk.Label(right_frame, text="id:")
label_id.grid(row=0, column=0, sticky="w", pady=5, padx=5)
entry_id = tk.Entry(right_frame, width=20)
entry_id.grid(row=0, column=1, sticky="w", pady=5, padx=5)

label_comments = tk.Label(right_frame, text="Comments")
label_comments.grid(row=1, column=0, sticky="w", pady=5, padx=5, columnspan=2)

text_comments = tk.Text(right_frame, width=25, height=5, wrap="word")
text_comments.grid(row=2, column=0, sticky="w", pady=5, padx=5, columnspan=2)

button_save = tk.Button(save_frame, text="SAVE", command=save_contact,
                             font=("Arial", 12, "bold"), width=10, relief="raised", bd=3)
button_save.pack(pady=10)

root.mainloop()

if client:
    client.close()
    print("Conexión a MongoDB cerrada.")