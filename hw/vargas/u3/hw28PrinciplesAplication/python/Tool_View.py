import tkinter as tk
from tkinter import ttk, messagebox
from ITool_View import ITool_View

class Tool_View(ITool_View):
    def __init__(self, root):
        self.root = root
        self.root.title("Tool Manager System")
        self.controller = None 
        self._setup_ui()

    def set_controller(self, controller):
        self.controller = controller

    def _setup_ui(self):
        form = ttk.LabelFrame(self.root, text="Tool Details", padding=10)
        form.pack(fill="x", padx=10, pady=5)

        ttk.Label(form, text="ID:").grid(row=0, column=0)
        self.ent_id = ttk.Entry(form)
        self.ent_id.grid(row=0, column=1)

        ttk.Label(form, text="Desc:").grid(row=0, column=2)
        self.ent_desc = ttk.Entry(form)
        self.ent_desc.grid(row=0, column=3)

        ttk.Label(form, text="Price:").grid(row=1, column=0)
        self.ent_price = ttk.Entry(form)
        self.ent_price.grid(row=1, column=1)

        ttk.Label(form, text="Stock:").grid(row=1, column=2)
        self.ent_stock = ttk.Entry(form)
        self.ent_stock.grid(row=1, column=3)

        btn_box = ttk.Frame(form)
        btn_box.grid(row=2, column=0, columnspan=4, pady=10)
        ttk.Button(btn_box, text="Save Tool", command=lambda: self.controller.handle_save()).pack(side="left")
        ttk.Button(btn_box, text="Refresh", command=lambda: self.controller.handle_refresh()).pack(side="left")

        self.tree = ttk.Treeview(self.root, columns=("id", "desc", "base", "total", "stock"), show="headings")
        self.tree.heading("id", text="ID")
        self.tree.heading("desc", text="Description")
        self.tree.heading("base", text="Base Price")
        self.tree.heading("total", text="Total (+Tax)")
        self.tree.heading("stock", text="Stock")
        self.tree.pack(fill="both", expand=True, padx=10)

    def get_form_data(self) -> dict:
        return {
            "id": self.ent_id.get(),
            "description": self.ent_desc.get(),
            "price_text": self.ent_price.get(),
            "stock_text": self.ent_stock.get()
        }

    def show_message(self, title: str, msg: str, is_error: bool = False):
        if is_error: messagebox.showerror(title, msg)
        else: messagebox.showinfo(title, msg)

    def update_list(self, tools: list):
        for item in self.tree.get_children(): self.tree.delete(item)
        for t in tools:
            self.tree.insert("", "end", values=(t.id, t.description, t.base_price, t.total_with_tax, t.stock))

    def clear_form(self):
        self.ent_id.delete(0, tk.END)
        self.ent_desc.delete(0, tk.END)
        self.ent_price.delete(0, tk.END)
        self.ent_stock.delete(0, tk.END)