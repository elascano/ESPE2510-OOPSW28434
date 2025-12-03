import tkinter as tk
from tkinter import ttk
from datetime import datetime

class ParkingGUI:
    def __init__(self, root):
        self.root = root
        self.root.title("Parking Control System - Entrada/Salida")

        # ===== TITLE =====
        title = tk.Label(root, text="REGISTRO DE VEHÍCULOS", font=("Arial", 16))
        title.grid(row=0, column=0, columnspan=2, pady=10)

        # ===== PLACA =====
        tk.Label(root, text="Placa:").grid(row=1, column=0, sticky="e", padx=5, pady=5)
        self.plate_entry = tk.Entry(root, width=20)
        self.plate_entry.grid(row=1, column=1, padx=5, pady=5)

        # ===== HORA ENTRADA =====
        tk.Label(root, text="Hora Entrada:").grid(row=2, column=0, sticky="e", padx=5, pady=5)
        self.entry_time = tk.Entry(root, width=20)
        self.entry_time.insert(0, self.get_time())
        self.entry_time.grid(row=2, column=1, padx=5, pady=5)

        # ===== HORA SALIDA =====
        tk.Label(root, text="Hora Salida:").grid(row=3, column=0, sticky="e", padx=5, pady=5)
        self.exit_time = tk.Entry(root, width=20)
        self.exit_time.insert(0, self.get_time())
        self.exit_time.grid(row=3, column=1, padx=5, pady=5)

        # ===== ESPACIO =====
        tk.Label(root, text="Espacio:").grid(row=4, column=0, sticky="e", padx=5, pady=5)
        self.space_combo = ttk.Combobox(root, values=["A1", "A2", "A3", "B1", "B2", "B3"], width=17)
        self.space_combo.current(0)
        self.space_combo.grid(row=4, column=1, padx=5, pady=5)

        # ===== BUTTONS =====
        self.btn_entry = tk.Button(root, text="Registrar Entrada", command=self.register_entry, width=20)
        self.btn_entry.grid(row=5, column=0, pady=10)

        self.btn_exit = tk.Button(root, text="Registrar Salida", command=self.register_exit, width=20)
        self.btn_exit.grid(row=5, column=1, pady=10)

        # ===== OUTPUT =====
        tk.Label(root, text="Registro:").grid(row=6, column=0, sticky="ne")
        self.output = tk.Text(root, width=40, height=10)
        self.output.grid(row=6, column=1, pady=10, padx=5)

    def get_time(self):
        return datetime.now().strftime("%Y-%m-%d %H:%M:%S")

    def register_entry(self):
        plate = self.plate_entry.get()
        entry = self.entry_time.get()
        space = self.space_combo.get()

        self.output.insert(tk.END, f"[ENTRADA] Placa: {plate}, Hora: {entry}, Espacio: {space}\n")

    def register_exit(self):
        plate = self.plate_entry.get()
        exit_time = self.exit_time.get()

        self.output.insert(tk.END, f"[SALIDA] Placa: {plate}, Hora: {exit_time}\n")


# ===== RUN APP =====
root = tk.Tk()
app = ParkingGUI(root)
root.mainloop()
