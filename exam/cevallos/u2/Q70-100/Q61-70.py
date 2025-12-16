import tkinter as tk
from tkinter import ttk, messagebox
from pymongo import MongoClient
import re


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

title = ttk.Label(root, text="Soccer Team", font=("Arial", 20))
title.pack(pady=10)

frame = tk.Frame(root, bg="#2b2f38")
frame.pack(padx=20, pady=10, fill="both", expand=True)

left = tk.Frame(frame, bg="#2b2f38")
left.grid(row=0, column=0, sticky="nw")


ttk.Label(left, text="Id:").grid(row=0, column=0, sticky="w", pady=5)


ttk.Label(left, text="Team Name:").grid(row=1, column=0, sticky="w", pady=5)
firstName = ttk.Entry(left)
firstName.grid(row=1, column=1, pady=5)


ttk.Label(left, text="Id:").grid(row=0, column=0, sticky="w", pady=5)

ttk.Label(left, text="Team Name:*").grid(row=1, column=0, sticky="w", pady=5)
firstName = ttk.Entry(left)
firstName.grid(row=1, column=1, pady=5)


def save_data():
   
    document = {
        "teamName": firstName.get(),}
    

def validate_fields():
    errors = []
    
    
    if not firstName.get().strip():
        errors.append("First Name is required")
    elif not re.match("^[A-Za-zÁÉÍÓÚáéíóúÑñ\s]+$", firstName.get().strip()):
        errors.append("First Name should contain only letters")
    
    
    return errors

def save_data():
   
    validation_errors = validate_fields()
    
    if validation_errors:
        error_message = "Please correct the following errors:\n\n" + "\n".join(validation_errors)
        messagebox.showerror("Validation Error", error_message)
        return
    
    
    document = {
        "firstName": firstName.get().strip(),
        
    }


    collection.insert_one(document)
    messagebox.showinfo("Saved", "Contact saved successfully in MongoDB Atlas!")


save_button = ttk.Button(root, text="Save", command=save_data)
save_button.pack(pady=15)

root.mainloop()
   

def clear_fields():
    firstName.delete(0, tk.END)

    
save_button = ttk.Button(root, text="Save", command=save_data)
save_button.pack(pady=15)


root.mainloop()
