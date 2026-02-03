import tkinter as tk
from tkinter import ttk, messagebox
from controller.cellphone_controller import CellphoneController

# Controller instance
controller = CellphoneController()

# =========================
# STRATEGY use
# =========================
def open_find(parent):  # ← ESTE NOMBRE ES CLAVE
    parent.withdraw()

    win = tk.Toplevel()
    win.title("Find Cellphone")
    win.geometry("520x360")
    win.resizable(False, False)

    # ---------- Title ----------
    tk.Label(
        win,
        text="Find Cellphone",
        font=("Segoe UI", 15, "bold")
    ).pack(pady=10)

    # ---------- Search area ----------
    search_frame = tk.Frame(win)
    search_frame.pack(pady=5)

    tk.Label(search_frame, text="ID:").grid(row=0, column=0, padx=5)
    id_entry = tk.Entry(search_frame, width=20)
    id_entry.grid(row=0, column=1, padx=5)

    # ---------- Table ----------
    table = ttk.Treeview(
        win,
        columns=("id", "model", "price"),
        show="headings",
        height=6
    )
    table.pack(pady=10)

    table.heading("id", text="ID")
    table.heading("model", text="Model")
    table.heading("price", text="Price")

    table.column("id", width=120, anchor="center")
    table.column("model", width=180, anchor="center")
    table.column("price", width=100, anchor="center")

    # ---------- Actions ----------
    def find():
        table.delete(*table.get_children())

        id_value = id_entry.get().strip()
        if not id_value:
            messagebox.showwarning("Warning", "Enter an ID")
            return

        cell = controller.find_by_id(id_value)
        if cell:
            table.insert(
                "",
                "end",
                values=(cell.id, cell.model, cell.price)
            )
        else:
            messagebox.showinfo("Info", "Cellphone not found")

    def info():
        selected = table.focus()
        if not selected:
            messagebox.showwarning("Warning", "Select a cellphone")
            return

        id_, model, price = table.item(selected)["values"]
        messagebox.showinfo(
            "Cellphone Info",
            f"ID: {id_}\nModel: {model}\nPrice: ${price}"
        )

    def back():
        win.destroy()
        parent.deiconify()

    # ---------- Buttons ----------
    buttons = tk.Frame(win)
    buttons.pack(pady=10)

    tk.Button(buttons, text="Find", width=12, command=find)\
        .grid(row=0, column=0, padx=5)
    tk.Button(buttons, text="Info", width=12, command=info)\
        .grid(row=0, column=1, padx=5)
    tk.Button(buttons, text="Back", width=12, command=back)\
        .grid(row=0, column=2, padx=5)
