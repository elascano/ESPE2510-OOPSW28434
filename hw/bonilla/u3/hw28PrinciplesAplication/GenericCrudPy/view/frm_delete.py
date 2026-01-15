import tkinter as tk
from tkinter import messagebox

class FrmDelete(tk.Toplevel):
    def __init__(self, controller):
        super().__init__()
        self.controller = controller
        self.title("Delete Entity")

        tk.Label(self, text="ID (numbers only):").pack()
        self.entry_id = tk.Entry(self)
        self.entry_id.pack()

        tk.Button(self, text="Delete", command=self.delete).pack(pady=5)

    def delete(self):
        entity_id = self.entry_id.get().strip()
        if not entity_id.isdigit():
            messagebox.showerror("Error", "ID must be numeric")
            return

        if self.controller.delete(entity_id):
            messagebox.showinfo("Success", "Entity deleted")
            self.destroy()
        else:
            messagebox.showerror("Error", "Entity not found")