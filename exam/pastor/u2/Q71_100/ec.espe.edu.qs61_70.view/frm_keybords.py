import customtkinter as ctk
import tkinter as tk
from tkinter import ttk
from kaybord_controller import keybord_controller

class frm_keybords:
    def __init__(self, contoller):
        super().__init__()
        root = tk.Tk()
        root.title("Keybords")
        root.geometry("600x400") 
        columns = ('#1', '#2', '#3') 
        tree = ttk.Treeview(root, columns=columns, show='headings')
        tree.heading('#1', text='ID ')
        tree.heading('#2', text='Name')
        tree.heading('#3', text='Price')
        tree.column('#1', width=100, anchor='center')
        tree.column('#2', width=200, anchor='w')
        tree.column('#3', width=100, anchor='center')

        data = [
        ]

        for item in data:
            tree.insert('', tk.END, values=item)
        vsb = ttk.Scrollbar(root, orient="vertical", command=tree.yview)
        tree.configure(yscrollcommand=vsb.set)
        vsb.pack(side='right', fill='y')
        tree.pack(pady=20, padx=20, fill='both', expand=True)
        root.mainloop()

if __name__ == "__main__":
    ctk.set_appearance_mode("Light")
    ctk.set_default_color_theme("blue")

    root = frm_keybords()
    root.mainloop()
