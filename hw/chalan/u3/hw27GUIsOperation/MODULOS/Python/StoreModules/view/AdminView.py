import tkinter as tk
from tkinter import messagebox
from domain.domain import DOMAIN
from utils.TableUtil import build_table

class AdminView:
    def __init__(self, controller, refreshCallback, mainWindow):
        self.controller = controller
        self.refreshCallback = refreshCallback
        self.mainWindow = mainWindow
        self.fields = DOMAIN["fields"]

        self.win = tk.Toplevel()
        self.win.title("Admin")
        self.win.geometry("520x500")
        self.win.protocol("WM_DELETE_WINDOW", self.backToMenu)

        self.entries = {}

        form = tk.Frame(self.win)
        form.pack(pady=10)

        for field in self.fields:
            tk.Label(form, text=field["name"]).pack()
            entry = tk.Entry(form)
            entry.pack()
            self.entries[field["name"]] = (entry, field["type"])

        tk.Button(self.win, text="Add", command=self.add).pack(pady=5)
        tk.Button(self.win, text="Delete", command=self.delete).pack(pady=5)
        tk.Button(self.win, text="Back to Menu", command=self.backToMenu).pack(pady=10)

        self.table = build_table(self.win, self.fields)
        self.table.pack(fill=tk.BOTH, expand=True, padx=10, pady=10)

        self.refresh()

    def refresh(self):
        self.table.delete(*self.table.get_children())
        field_names = [f["name"] for f in self.fields]

        for item in self.controller.getAll():
            values = [item.get(name, "") for name in field_names]
            self.table.insert("", tk.END, values=values)

    def add(self):
        rawData = {}

        try:
            for key, (entry, _) in self.entries.items():
                rawData[key] = entry.get().strip()

            self.controller.add(rawData)
            self.refresh()
            self.refreshCallback()

            for entry, _ in self.entries.values():
                entry.delete(0, tk.END)

        except ValueError as e:
            messagebox.showerror("Validation error", str(e))

    def delete(self):
        selected = self.table.selection()
        if not selected:
            messagebox.showwarning("Warning", "Select an item")
            return

        values = self.table.item(selected[0], "values")
        identifier = values[0]  # primer campo = id lógico

        self.controller.delete(identifier)
        self.refresh()
        self.refreshCallback()

    def backToMenu(self):
        self.win.destroy()
        self.mainWindow.deiconify()
