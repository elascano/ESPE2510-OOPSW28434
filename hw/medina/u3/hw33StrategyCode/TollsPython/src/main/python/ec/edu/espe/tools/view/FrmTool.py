import tkinter as tk
from tkinter import ttk

from ec.edu.espe.tools.controller.ToolController import ToolController
from ec.edu.espe.tools.utils.CsvPersistence import CsvPersistence
from ec.edu.espe.tools.utils.JsonPersistence import JsonPersistence

class FrmTool(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("ToolsManagement")
        self.geometry("1100x650")
        self.minsize(900, 550)

        self.strategyVar = tk.StringVar(value="csv")
        self.idVar = tk.StringVar()
        self.nameVar = tk.StringVar()
        self.priceVar = tk.StringVar()
        self.materialsVar = tk.StringVar()
        self.statusVar = tk.StringVar(value="")

        self.persistence = CsvPersistence()
        self.controller = ToolController(self.persistence)

        self._build()
        self._changeStrategy(True)
        self._refresh()

    def setVisible(self, visible):
        if visible:
            self.mainloop()

    def _build(self):
        root = ttk.Frame(self, padding=16)
        root.pack(fill=tk.BOTH, expand=True)

        ttk.Label(root, text="ToolsManagement", font=("Segoe UI", 18, "bold")).pack(anchor="w")

        split = ttk.Frame(root)
        split.pack(fill=tk.BOTH, expand=True, pady=(14, 0))
        split.columnconfigure(0, weight=0)
        split.columnconfigure(1, weight=1)
        split.rowconfigure(0, weight=1)

        left = ttk.Frame(split, padding=14)
        left.grid(row=0, column=0, sticky="nsew", padx=(0, 12))
        right = ttk.Frame(split, padding=14)
        right.grid(row=0, column=1, sticky="nsew")

        form = ttk.Frame(left)
        form.pack(fill=tk.X)

        ttk.Label(form, text="Estrategia").grid(row=0, column=0, sticky="w")
        self.strategy = ttk.Combobox(form, textvariable=self.strategyVar, values=["csv", "json", "mongo"], state="readonly", width=18)
        self.strategy.grid(row=1, column=0, sticky="ew", padx=(0, 10), pady=(2, 10))

        ttk.Label(form, text="ID").grid(row=0, column=1, sticky="w")
        ttk.Entry(form, textvariable=self.idVar, width=18).grid(row=1, column=1, sticky="ew", padx=(0, 10), pady=(2, 10))

        ttk.Label(form, text="Name").grid(row=0, column=2, sticky="w")
        ttk.Entry(form, textvariable=self.nameVar, width=18).grid(row=1, column=2, sticky="ew", padx=(0, 10), pady=(2, 10))

        ttk.Label(form, text="Price").grid(row=0, column=3, sticky="w")
        ttk.Entry(form, textvariable=self.priceVar, width=18).grid(row=1, column=3, sticky="ew", pady=(2, 10))

        ttk.Label(left, text="Materials (separados por coma)").pack(anchor="w")
        ttk.Entry(left, textvariable=self.materialsVar).pack(fill=tk.X, pady=(2, 12))

        actions = ttk.Frame(left)
        actions.pack(fill=tk.X, pady=(0, 10))

        ttk.Button(actions, text="Crear", command=self._create).grid(row=0, column=0, padx=(0, 8), pady=4)
        ttk.Button(actions, text="Actualizar", command=self._update).grid(row=0, column=1, padx=(0, 8), pady=4)
        ttk.Button(actions, text="Eliminar", command=self._delete).grid(row=0, column=2, padx=(0, 8), pady=4)
        ttk.Button(actions, text="Buscar", command=self._find).grid(row=0, column=3, padx=(0, 8), pady=4)
        ttk.Button(actions, text="Refrescar", command=self._refresh).grid(row=0, column=4, padx=(0, 8), pady=4)
        ttk.Button(actions, text="Limpiar", command=self._clear).grid(row=0, column=5, pady=4)

        self.strategy.bind("<<ComboboxSelected>>", lambda e: self._changeStrategy(False))

        ttk.Label(left, textvariable=self.statusVar, foreground="#444").pack(anchor="w", pady=(6, 0))

        header = ttk.Frame(right)
        header.pack(fill=tk.X)
        ttk.Label(header, text="Lista de Tools", font=("Segoe UI", 12, "bold")).pack(side=tk.LEFT)
        self.subtitle = ttk.Label(header, text="", foreground="#555")
        self.subtitle.pack(side=tk.RIGHT)

        cols = ("id", "name", "price", "priceWithIva", "materials")
        self.table = ttk.Treeview(right, columns=cols, show="headings", height=18)
        for c, t, w in [
            ("id","ID",110),
            ("name","Name",220),
            ("price","Price",110),
            ("priceWithIva","Price+IVA",120),
            ("materials","Materials",360),
        ]:
            self.table.heading(c, text=t)
            self.table.column(c, width=w, anchor="w")
        self.table.pack(fill=tk.BOTH, expand=True, pady=(10, 0))
        self.table.bind("<<TreeviewSelect>>", self._onSelect)

    def _status(self, msg):
        self.statusVar.set(msg or "")

    def _parse(self):
        id = self.idVar.get().strip()
        name = self.nameVar.get().strip()
        priceRaw = self.priceVar.get().strip()
        try:
            price = float(priceRaw)
        except:
            price = None
        materials = [m.strip() for m in self.materialsVar.get().split(",") if m.strip()]
        return id, name, price, materials

    def _clear(self):
        self.idVar.set("")
        self.nameVar.set("")
        self.priceVar.set("")
        self.materialsVar.set("")
        self._status("")

    def _changeStrategy(self, silent):
        key = self.strategyVar.get().strip().lower()
        if key == "csv":
            self.persistence = CsvPersistence()
            label = "CsvPersistence"
        elif key == "json":
            self.persistence = JsonPersistence()
            label = "JsonPersistence"
        else:
            try:
                from ec.edu.espe.tools.utils.MongoPersistence import MongoPersistence
                self.persistence = MongoPersistence()
                label = "MongoPersistence"
            except Exception as e:
                self.strategyVar.set("csv")
                self.persistence = CsvPersistence()
                label = "CsvPersistence"
                self.controller.setStrategy(self.persistence)
                self.subtitle.config(text=f"Estrategia: {label}")
                self._status(str(e))
                self._refresh()
                return
        self.controller.setStrategy(self.persistence)
        self.subtitle.config(text=f"Estrategia: {label}")
        if not silent:
            self._status("Estrategia cambiada")
        self._refresh()

    def _clearTable(self):
        for i in self.table.get_children():
            self.table.delete(i)

    def _refresh(self):
        self._clearTable()
        try:
            tools = self.controller.getAllTools()
        except Exception as e:
            self._status(str(e))
            return
        for t in tools:
            self.table.insert("", tk.END, values=(
                t.getId(),
                t.getName(),
                f"{float(t.getPrice()):.2f}",
                f"{float(t.getPriceWithIva()):.2f}",
                ", ".join(t.getMaterials() or [])
            ))
        self._status(f"Total: {len(tools)}")

    def _create(self):
        id, name, price, materials = self._parse()
        if not id or not name or price is None:
            self._status("Completa ID, Name y Price")
            return
        ok = self.controller.createSculpture(id, name, price, materials)
        self._status("Creado" if ok else "No se pudo crear")
        self._refresh()

    def _update(self):
        id, name, price, materials = self._parse()
        if not id or not name or price is None:
            self._status("Completa ID, Name y Price")
            return
        ok = self.controller.updateSculpture(id, name, price, materials)
        self._status("Actualizado" if ok else "No se pudo actualizar")
        self._refresh()

    def _delete(self):
        id = self.idVar.get().strip()
        if not id:
            self._status("Ingresa ID")
            return
        ok = self.controller.deleteSculpture(id)
        self._status("Eliminado" if ok else "No se pudo eliminar")
        self._refresh()

    def _find(self):
        id = self.idVar.get().strip()
        if not id:
            self._status("Ingresa ID")
            return
        t = self.controller.findSculptureById(id)
        if t is None:
            self._status("No encontrado")
            return
        self.nameVar.set(t.getName())
        self.priceVar.set(str(t.getPrice()))
        self.materialsVar.set(", ".join(t.getMaterials() or []))
        self._status("Encontrado")

    def _onSelect(self, event):
        sel = self.table.selection()
        if not sel:
            return
        vals = self.table.item(sel[0], "values")
        if len(vals) < 5:
            return
        self.idVar.set(vals[0])
        self.nameVar.set(vals[1])
        self.priceVar.set(vals[2])
        self.materialsVar.set(vals[4])

    def destroy(self):
        try:
            from ec.edu.espe.tools.utils.MongoConnection import MongoConnection
            MongoConnection.getInstance().close()
        except:
            pass
        super().destroy()
