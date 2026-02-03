import tkinter as tk
from tkinter import ttk

def build_table(parent, fields, height=8):
    columns = [f["name"] for f in fields]

    table = ttk.Treeview(
        parent,
        columns=columns,
        show="headings",
        height=height
    )

    for col in columns:
        table.heading(col, text=col.capitalize())
        table.column(col, width=100, anchor=tk.CENTER)

    return table
