import tkinter as tk
from tkinter import ttk, messagebox
from domain.domain import DOMAIN


class BuyView:
    def __init__(self, controller, refreshCallback, mainWindow):
        self.controller = controller
        self.refreshCallback = refreshCallback
        self.mainWindow = mainWindow
        self.fields = DOMAIN["fields"]
        self.idField = DOMAIN["idField"]

        self.win = tk.Toplevel()
        self.win.title("Buy")
        self.win.geometry("520x420")
        self.win.protocol("WM_DELETE_WINDOW", self.backToMenu)

        # TABLA DE PRODUCTOS
        self.table = ttk.Treeview(
            self.win,
            columns=[f["name"] for f in self.fields],
            show="headings",
            height=8
        )

        for field in self.fields:
            self.table.heading(field["name"], text=field["name"].capitalize())
            self.table.column(field["name"], anchor="center")

        self.table.pack(fill=tk.BOTH, expand=True, padx=10, pady=10)

        # CANTIDAD
        form = tk.Frame(self.win)
        form.pack(pady=5)

        tk.Label(form, text="Quantity").pack(side=tk.LEFT, padx=5)
        self.qtyEntry = tk.Entry(form, width=10)
        self.qtyEntry.pack(side=tk.LEFT)

        # RESULTADO
        self.resultLabel = tk.Label(self.win, text="", font=("Segoe UI", 10, "bold"))
        self.resultLabel.pack(pady=5)

        # BOTONES
        btns = tk.Frame(self.win)
        btns.pack(pady=5)

        tk.Button(btns, text="Buy", width=12, command=self.buy).pack(side=tk.LEFT, padx=5)
        tk.Button(btns, text="Back to Menu", width=12, command=self.backToMenu).pack(side=tk.LEFT, padx=5)

        self.refresh()

    def refresh(self):
        self.table.delete(*self.table.get_children())
        field_names = [f["name"] for f in self.fields]

        for item in self.controller.getAll():
            values = [item.get(name, "") for name in field_names]
            self.table.insert("", tk.END, values=values)

    def buy(self):
        try:
            selected = self.table.selection()
            if not selected:
                raise ValueError("Select a product")

            quantity = int(self.qtyEntry.get())
            if quantity <= 0:
                raise ValueError("Invalid quantity")

            item_values = self.table.item(selected[0])["values"]
            id_index = [f["name"] for f in self.fields].index(self.idField)
            identifier = item_values[id_index]

            total = self.controller.buy(identifier, quantity)

            if total is None:
                self.resultLabel.config(text="Not enough stock", fg="red")
            else:
                self.resultLabel.config(text=f"Total: ${total}", fg="green")
                self.refresh()
                self.refreshCallback()

        except Exception as e:
            messagebox.showerror("Error", str(e))

    def backToMenu(self):
        self.win.destroy()
        self.mainWindow.deiconify()
