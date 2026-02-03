import tkinter as tk
from tkinter import ttk, messagebox
from datetime import datetime
from controller.parking_controller import ParkingController
from utils.json_persistence import JsonPersistence
from utils.csv_persistence import CsvPersistence
from utils.mongo_persistence import MongoPersistence

class FrmParking(tk.Tk):

    def __init__(self):
        super().__init__()
        self.title("Parking Management")
        self.geometry("700x500")

        self.controller = ParkingController()
        self._build_ui()

    def _build_ui(self):
        header = tk.Frame(self, bg="#4831DD")
        header.pack(fill="x")
        tk.Label(header, text="Parking Management", fg="white", bg="#4831DD", font=("Footlight MT Light", 18)).pack(pady=10)

        form = tk.Frame(self)
        form.pack(padx=10, pady=10, fill="x")

        tk.Label(form, text="Strategy").grid(row=0, column=0, sticky="e")
        self.cmb_strategy = ttk.Combobox(form, values=["Select...", "JSON", "CSV", "MongoDB"], state="readonly")
        self.cmb_strategy.current(0)
        self.cmb_strategy.grid(row=0, column=1)
        self.cmb_strategy.bind("<<ComboboxSelected>>", self.change_strategy)

        tk.Label(form, text="ID").grid(row=0, column=2, sticky="e")
        self.txt_id = tk.Entry(form, width=10)
        self.txt_id.grid(row=0, column=3)
        tk.Button(form, text="Find", command=self.find).grid(row=0, column=4)

        tk.Label(form, text="Plate").grid(row=1, column=0, sticky="e")
        self.txt_plate = tk.Entry(form)
        self.txt_plate.grid(row=1, column=1)

        tk.Label(form, text="Type").grid(row=1, column=2, sticky="e")
        self.cmb_type = ttk.Combobox(form, values=["Select...", "Car", "Moto", "Truck"], state="readonly")
        self.cmb_type.current(0)
        self.cmb_type.grid(row=1, column=3)

        tk.Label(form, text="Entry").grid(row=2, column=0, sticky="e")
        self.txt_entry = tk.Entry(form)
        self.txt_entry.grid(row=2, column=1)

        tk.Label(form, text="Exit").grid(row=2, column=2, sticky="e")
        self.txt_exit = tk.Entry(form)
        self.txt_exit.grid(row=2, column=3)

        tk.Label(form, text="Fee").grid(row=2, column=4, sticky="e")
        self.txt_fee = tk.Entry(form)
        self.txt_fee.grid(row=2, column=5)

        buttons = tk.Frame(self)
        buttons.pack(pady=10)

        tk.Button(buttons, text="Create", width=10, command=self.create).grid(row=0, column=0, padx=5)
        tk.Button(buttons, text="Update", width=10, command=self.update).grid(row=0, column=1, padx=5)
        tk.Button(buttons, text="Delete", width=10, command=self.delete).grid(row=0, column=2, padx=5)
        tk.Button(buttons, text="Clear", width=10, command=self.clear).grid(row=0, column=3, padx=5)

        table_frame = tk.Frame(self)
        table_frame.pack(fill="both", expand=True, padx=10, pady=10)

        columns = ("id", "plate", "type", "entry", "exit", "fee")
        self.table = ttk.Treeview(table_frame, columns=columns, show="headings")
        for col in columns:
            self.table.heading(col, text=col.capitalize())
        self.table.pack(fill="both", expand=True)

    def change_strategy(self, _):
        index = self.cmb_strategy.current()
        if index == 1:
            self.controller.set_strategy(JsonPersistence())
        elif index == 2:
            self.controller.set_strategy(CsvPersistence())
        elif index == 3:
            self.controller.set_strategy(MongoPersistence())
        else:
            self.controller.set_strategy(None)
        self.refresh()

    def refresh(self):
        self.table.delete(*self.table.get_children())
        for p in self.controller.get_all():
            self.table.insert("", "end", values=(p.id, p.plate, p.vehicle_type, p.entry_time, p.exit_time, p.fee))
        self.txt_id.delete(0, tk.END)
        self.txt_id.insert(0, self.controller.get_next_id())

    def create(self):
        plate = self.txt_plate.get().upper().strip()
        type_ = self.cmb_type.get()
        if not plate or type_ == "Select...":
            messagebox.showerror("Error", "Plate and Type are required")
            return
        if not __import__("re").match(r"^[A-Z]{3}-\d{4}$", plate):
            messagebox.showerror("Error", "Invalid plate format ABC-1234")
            return
        entry = datetime.now()
        self.controller.register_entry(self.txt_id.get(), plate, type_, entry)
        self.refresh()
        self.clear()

    def update(self):
        id_ = self.txt_id.get()
        self.controller.register_exit(id_)
        self.refresh()

    def delete(self):
        self.controller.delete(self.txt_id.get())
        self.refresh()
        self.clear()

    def find(self):
        p = self.controller.find_by_id(self.txt_id.get())
        if p:
            self.txt_plate.delete(0, tk.END)
            self.txt_plate.insert(0, p.plate)
            self.cmb_type.set(p.vehicle_type)
            self.txt_entry.delete(0, tk.END)
            self.txt_entry.insert(0, p.entry_time)
            self.txt_exit.delete(0, tk.END)
            self.txt_exit.insert(0, p.exit_time if p.exit_time else "")
            self.txt_fee.delete(0, tk.END)
            self.txt_fee.insert(0, p.fee)

    def clear(self):
        self.txt_plate.delete(0, tk.END)
        self.txt_entry.delete(0, tk.END)
        self.txt_exit.delete(0, tk.END)
        self.txt_fee.delete(0, tk.END)
        self.cmb_type.current(0)