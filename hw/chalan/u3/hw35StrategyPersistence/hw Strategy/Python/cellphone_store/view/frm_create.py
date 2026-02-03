import tkinter as tk
from tkinter import messagebox
from controller.cellphone_controller import CellphoneController
from model.cellphone import Cellphone
from utils.id_generator import IdGenerator

def open_create(parent):
    parent.withdraw()

    window = tk.Toplevel()
    window.title("Create Cellphone")
    window.geometry("380x300")
    window.resizable(False, False)

    controller = CellphoneController()

    tk.Label(window, text="Create Cellphone",
             font=("Segoe UI", 16, "bold")).pack(pady=15)

    # ID (auto-generated, preview only)
    tk.Label(window, text="ID").pack()
    id_var = tk.StringVar(value=IdGenerator.preview_id())
    id_entry = tk.Entry(window, textvariable=id_var, state="readonly")
    id_entry.pack(pady=5)

    # Model
    tk.Label(window, text="Model").pack()
    model_entry = tk.Entry(window)
    model_entry.pack(pady=5)

    # Price
    tk.Label(window, text="Price").pack()
    price_entry = tk.Entry(window)
    price_entry.pack(pady=5)

    def save():
        model = model_entry.get().strip()
        price_text = price_entry.get().strip()

        if not model:
            messagebox.showerror("Error", "Model is required")
            return

        try:
            price = float(price_text)
        except ValueError:
            messagebox.showerror("Error", "Price must be numeric")
            return

        cellphone_id = IdGenerator.generate_id()
        controller.create(Cellphone(cellphone_id, model, price))

        messagebox.showinfo("Saved", f"Cellphone {cellphone_id} created")
        window.destroy()
        parent.deiconify()

    tk.Button(window, text="Save", width=15, command=save).pack(pady=10)
    tk.Button(window, text="Back", width=15,
              command=lambda: (window.destroy(), parent.deiconify())
              ).pack()

    window.mainloop()

