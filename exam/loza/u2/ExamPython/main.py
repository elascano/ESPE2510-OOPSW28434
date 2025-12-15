import tkinter as tk
from tkinter import messagebox, ttk
from crud_customers import *

def clear_fields():
    entry_name.delete(0, tk.END)
    entry_email.delete(0, tk.END)
    entry_balance.delete(0, tk.END)

def create():
    try:
        create_customer(
            entry_name.get(),
            entry_email.get(),
            float(entry_balance.get())
        )
        messagebox.showinfo("Success", "Customer created successfully")
        load_customers()
        clear_fields()
    except:
        messagebox.showerror("Error", "Invalid data")

def load_customers():
    for row in tree.get_children():
        tree.delete(row)

    for c in get_customers():
        tree.insert("", tk.END, values=(c["_id"], c["name"], c["email"], c["balance"]))

def update():
    selected = tree.focus()
    if not selected:
        messagebox.showwarning("Warning", "Select a customer")
        return

    customer_id = tree.item(selected)["values"][0]
    try:
        update_customer(customer_id, float(entry_balance.get()))
        messagebox.showinfo("Updated", "Balance updated")
        load_customers()
    except:
        messagebox.showerror("Error", "Invalid balance")

def delete():
    selected = tree.focus()
    if not selected:
        messagebox.showwarning("Warning", "Select a customer")
        return

    customer_id = tree.item(selected)["values"][0]
    delete_customer(customer_id)
    messagebox.showinfo("Deleted", "Customer deleted")
    load_customers()


root = tk.Tk()
root.title("🏦 Bank Management System")
root.geometry("700x400")


frame_form = tk.Frame(root)
frame_form.pack(pady=10)

tk.Label(frame_form, text="Name").grid(row=0, column=0)
entry_name = tk.Entry(frame_form)
entry_name.grid(row=0, column=1)

tk.Label(frame_form, text="Email").grid(row=1, column=0)
entry_email = tk.Entry(frame_form)
entry_email.grid(row=1, column=1)

tk.Label(frame_form, text="Initial Balance").grid(row=2, column=0)
entry_balance = tk.Entry(frame_form)
entry_balance.grid(row=2, column=1)


frame_buttons = tk.Frame(root)
frame_buttons.pack()

tk.Button(frame_buttons, text="Create", command=create).grid(row=0, column=0, padx=5)
tk.Button(frame_buttons, text="Update Balance", command=update).grid(row=0, column=1, padx=5)
tk.Button(frame_buttons, text="Delete", command=delete).grid(row=0, column=2, padx=5)


tree = ttk.Treeview(
    root,
    columns=("ID", "Name", "Email", "Balance"),
    show="headings"
)
tree.heading("ID", text="ID")
tree.heading("Name", text="Name")
tree.heading("Email", text="Email")
tree.heading("Balance", text="Balance")
tree.pack(fill=tk.BOTH, expand=True)

load_customers()
root.mainloop()