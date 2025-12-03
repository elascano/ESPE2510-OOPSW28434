import tkinter as tk
from tkinter import ttk
import re

FONT_GEORGIA_TITLE = ("Georgia", 18, "bold")
FONT_GEORGIA_TEXT = ("Georgia", 11)
COLOR_PRINCIPAL_TEXT = "#4A235A" 

COLOR_HEADER_FOOTER = "#FDBE8B" 
COLOR_BODY = "#FFFFCC"         
COLOR_SAVE_BUTTON = "#7CBA00"

def save_contact():
    try:
        try:
            contact_id = entry_id.get().strip()
            if not contact_id.isdigit() and contact_id != "":
                raise ValueError("ID must contain only digits.")
            
        except ValueError as e:
            print(f"ERROR: {e}")
            return
            
        first_name = entry_first_name.get()
        last_name = entry_last_name.get()
        birth_date = entry_birth_date.get()
        
        try:
            age_str = entry_age.get().strip()
            if age_str.isdigit():
                age = int(age_str)
            elif age_str == "":
                 age = 0
            else:
                raise ValueError("Age must be a valid integer.")
        except ValueError as e:
            print(f"ERROR: {e}")
            return

        type_of_contact = combo_type.get()
        sex = var_sex.get()
        hobbies_selected = combo_hobbies.get() 
        comments = text_comments.get("1.0", tk.END).strip()

        print("\n==================================")
        print("CONTACTS:")
        print(f"  ID: {contact_id}")
        print(f"  First Name: {first_name}")
        print(f"  Last Name: {last_name}")
        print(f"  Birth Date: {birth_date}")
        print(f"  Age: {age}")
        print(f"  Type: {type_of_contact}")
        print(f"  Sex: {sex}")
        print(f"  Hobbies: {hobbies_selected}")
        print(f"  Comments: {comments}")
        print("==================================")
        
    except Exception as e:
        print(f"An error occurred while saving data: {e}")

root = tk.Tk()
root.title("CONTACTS")
root.geometry("650x550")
root.resizable(False, False)

header_frame = tk.Frame(root, bg=COLOR_HEADER_FOOTER, height=50)
header_frame.pack(fill='x', padx=5, pady=(5, 0))
header_label = tk.Label(header_frame, text="CONTACTS", font=FONT_GEORGIA_TITLE, bg=COLOR_HEADER_FOOTER, fg=COLOR_PRINCIPAL_TEXT)
header_label.pack(pady=8)

body_frame = tk.Frame(root, bg=COLOR_BODY, padx=20, pady=20)
body_frame.pack(fill='both', expand=True, padx=5, pady=0)

content_grid_frame = tk.Frame(body_frame, bg=COLOR_BODY)
content_grid_frame.pack(expand=True, anchor="center")

left_frame = tk.Frame(content_grid_frame, bg=COLOR_BODY)
left_frame.grid(row=0, column=0, sticky="n", padx=(0, 40))

right_frame = tk.Frame(content_grid_frame, bg=COLOR_BODY)
right_frame.grid(row=0, column=1, sticky="n")

save_frame = tk.Frame(root, bg=COLOR_HEADER_FOOTER, height=50)
save_frame.pack(fill='x', padx=5, pady=(0, 5))

entry_first_name = None
entry_last_name = None
entry_birth_date = None
entry_age = None

fields_left = [
    ("First Name:", 0),
    ("Last Name:", 1),
    ("Birth Date:", 2),
]

for i, (text, row) in enumerate(fields_left):
    label = tk.Label(left_frame, text=text, bg=COLOR_BODY, font=FONT_GEORGIA_TEXT, fg=COLOR_PRINCIPAL_TEXT)
    label.grid(row=row, column=0, sticky="w", pady=5, padx=5)

    entry = tk.Entry(left_frame, width=20, font=FONT_GEORGIA_TEXT)
    entry.grid(row=row, column=1, sticky="w", pady=5, padx=5)

    if text == "First Name:": entry_first_name = entry
    elif text == "Last Name:": entry_last_name = entry
    elif text == "Birth Date:": entry_birth_date = entry

label_age = tk.Label(left_frame, text="Age:", bg=COLOR_BODY, font=FONT_GEORGIA_TEXT, fg=COLOR_PRINCIPAL_TEXT)
label_age.grid(row=3, column=0, sticky="w", pady=5, padx=5)
entry_age = tk.Entry(left_frame, width=5, font=FONT_GEORGIA_TEXT)
entry_age.grid(row=3, column=1, sticky="w", pady=5, padx=5)

label_type = tk.Label(left_frame, text="Type:", bg=COLOR_BODY, font=FONT_GEORGIA_TEXT, fg=COLOR_PRINCIPAL_TEXT)
label_type.grid(row=4, column=0, sticky="w", pady=5, padx=5)
contact_types = ["Friend", "Family", "Job", "Unknown"] 

combo_type = ttk.Combobox(left_frame, values=contact_types, state="readonly", width=18, font=FONT_GEORGIA_TEXT)
combo_type.set("Friend")
combo_type.grid(row=4, column=1, sticky="w", pady=5, padx=5)

label_sex = tk.Label(left_frame, text="Sex:", bg=COLOR_BODY, font=FONT_GEORGIA_TEXT, fg=COLOR_PRINCIPAL_TEXT)
label_sex.grid(row=5, column=0, sticky="w", pady=5, padx=5)
var_sex = tk.StringVar(value="Female") 

radio_male = tk.Radiobutton(left_frame, text="Male", variable=var_sex, value="Male", bg=COLOR_BODY, fg=COLOR_PRINCIPAL_TEXT, font=FONT_GEORGIA_TEXT)
radio_female = tk.Radiobutton(left_frame, text="Female", variable=var_sex, value="Female", bg=COLOR_BODY, fg=COLOR_PRINCIPAL_TEXT, font=FONT_GEORGIA_TEXT)

radio_male.grid(row=5, column=1, sticky="w", padx=(5, 0))
radio_female.grid(row=6, column=1, sticky="w", padx=(5, 0))

label_hobbies = tk.Label(left_frame, text="Hobbies:", bg=COLOR_BODY, font=FONT_GEORGIA_TEXT, fg=COLOR_PRINCIPAL_TEXT)
label_hobbies.grid(row=7, column=0, sticky="w", pady=5, padx=5)
hobby_options = ["Cook", "PlaySoccer", "DJing", "Read", "Swim", "Sing", "Play an instrument"]
combo_hobbies = ttk.Combobox(left_frame, values=hobby_options, state="readonly", width=18, font=FONT_GEORGIA_TEXT)
combo_hobbies.set("Cook")
combo_hobbies.grid(row=7, column=1, sticky="w", pady=5, padx=5)

label_id = tk.Label(right_frame, text="id:", bg=COLOR_BODY, font=FONT_GEORGIA_TEXT, fg=COLOR_PRINCIPAL_TEXT)
label_id.grid(row=0, column=0, sticky="w", pady=5, padx=5)
entry_id = tk.Entry(right_frame, width=20, font=FONT_GEORGIA_TEXT)
entry_id.grid(row=0, column=1, sticky="w", pady=5, padx=5)

label_comments = tk.Label(right_frame, text="Comments", bg=COLOR_BODY, font=FONT_GEORGIA_TEXT, fg=COLOR_PRINCIPAL_TEXT)
label_comments.grid(row=1, column=0, sticky="w", pady=5, padx=5, columnspan=2)

text_comments = tk.Text(right_frame, width=25, height=5, wrap="word", font=FONT_GEORGIA_TEXT)
text_comments.grid(row=2, column=0, sticky="w", pady=5, padx=5, columnspan=2)

button_save = tk.Button(save_frame, text="SAVE", command=save_contact,
                        bg=COLOR_SAVE_BUTTON, fg="black", font=("Georgia", 12, "bold"),
                        width=10, relief="raised", bd=3)
button_save.pack(pady=10)

root.mainloop()