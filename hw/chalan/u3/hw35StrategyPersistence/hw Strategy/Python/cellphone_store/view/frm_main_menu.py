import tkinter as tk
from view.frm_create import open_create
from view.frm_find import open_find
from view.frm_management import open_management

def open_main_menu():
    root = tk.Tk()
    root.title("Cellphone Store")
    root.geometry("350x280")
    root.resizable(False, False)

    tk.Label(root, text="Cellphone Store",
             font=("Segoe UI", 18, "bold")).pack(pady=20)

    tk.Button(root, text="Create Cellphone", width=25,
              command=lambda: open_create(root)).pack(pady=5)

    tk.Button(root, text="Find Cellphone", width=25,
              command=lambda: open_find(root)).pack(pady=5)

    tk.Button(root, text="Management", width=25,
              command=lambda: open_management(root)).pack(pady=5)

    tk.Button(root, text="Exit", width=25,
              command=root.destroy).pack(pady=20)

    root.mainloop()

