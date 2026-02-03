import tkinter as tk
from tkinter import ttk, messagebox
from controller.cellphone_controller import CellphoneController
from controller.mongo_update_strategy import MongoUpdateStrategy
from model.cellphone import Cellphone

# Configurar controlador
strategy = MongoUpdateStrategy()
controller = CellphoneController(strategy)

def open_management(parent):
    parent.withdraw()
    win = tk.Toplevel()
    win.title("Management")
    win.geometry("600x420")

    table = ttk.Treeview(win, columns=("id", "model", "price"), show="headings")
    table.heading("id", text="ID")
    table.heading("model", text="Model")
    table.heading("price", text="Price")
    table.pack(pady=10, fill="both", expand=True)

    def load():
        for row in table.get_children():
            table.delete(row)
        for c in controller.find_all():
            table.insert("", "end", values=(c["id"], c["model"], c["price"]))

    def delete():
        selected = table.focus()
        if not selected:
            messagebox.showwarning("Error", "Select row")
            return
        
        values = table.item(selected)["values"]
        id_to_delete = values[0]
        
        controller.delete(id_to_delete) # Borra DB
        load() # Recarga tabla
        messagebox.showinfo("Success", "Deleted")

    def update():
        selected = table.focus()
        if not selected: return
        id_, model, price = table.item(selected)["values"]
        cell = Cellphone(id_, model, float(price))
        controller.update(cell) # Usa strategy
        load()

    btn_frame = tk.Frame(win)
    btn_frame.pack(pady=10)
    tk.Button(btn_frame, text="Update", command=update, width=10).grid(row=0, column=0, padx=5)
    tk.Button(btn_frame, text="Delete", command=delete, width=10).grid(row=0, column=1, padx=5)
    tk.Button(btn_frame, text="Back", command=lambda: (win.destroy(), parent.deiconify()), width=10).grid(row=0, column=2, padx=5)

    load()