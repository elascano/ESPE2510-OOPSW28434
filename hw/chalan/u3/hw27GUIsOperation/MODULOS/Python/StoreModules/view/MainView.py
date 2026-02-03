import tkinter as tk
from domain.domain import DOMAIN
from utils.TableUtil import build_table
from view.BuyView import BuyView
from view.AdminView import AdminView


class MainView:
    def __init__(self, controller):
        self.controller = controller
        self.fields = DOMAIN["fields"]

        self.root = tk.Tk()
        self.root.title(DOMAIN["title"])
        self.root.geometry("520x450")

        tk.Label(
            self.root,
            text=DOMAIN["title"],
            font=("Segoe UI", 16, "bold")
        ).pack(pady=10)

        # TABLA
        self.table = build_table(self.root, self.fields)
        self.table.pack(fill=tk.BOTH, expand=True, padx=10)

        # BOTONES
        btns = tk.Frame(self.root)
        btns.pack(pady=10)

        tk.Button(
            btns,
            text="Buy",
            width=18,
            command=self.openBuy
        ).pack(side=tk.LEFT, padx=5)

        tk.Button(
            btns,
            text="Admin",
            width=18,
            command=self.openAdmin
        ).pack(side=tk.LEFT, padx=5)

        self.refresh()
        self.root.mainloop()

    def refresh(self):
        self.table.delete(*self.table.get_children())
        field_names = [f["name"] for f in self.fields]

        for item in self.controller.getAll():
            values = [item.get(name, "") for name in field_names]
            # ⚠️ no usamos iid para evitar "Item already exists"
            self.table.insert("", tk.END, values=values)

    def openBuy(self):
        self.root.withdraw()
        BuyView(self.controller, self.refresh, self.root)

    def openAdmin(self):
        self.root.withdraw()   # 🔒 se cierra la ventana principal
        AdminView(self.controller, self.refresh, self.root)