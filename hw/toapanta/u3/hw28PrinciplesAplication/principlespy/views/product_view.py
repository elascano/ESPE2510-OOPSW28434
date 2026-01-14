import tkinter as tk
from tkinter import ttk

class ProductView(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Sistema de Facturación - MVC")
        self.geometry("800x600")

        # --- Título y Formulario ---
        tk.Label(self, text="REGISTRO DE PRODUCTOS", font=("Arial", 14, "bold")).pack(pady=10)
        
        form_frame = tk.Frame(self)
        form_frame.pack(pady=10)

        # Campos de entrada
        tk.Label(form_frame, text="ID:").grid(row=0, column=0, padx=5)
        self.ent_id = tk.Entry(form_frame)
        self.ent_id.grid(row=0, column=1, padx=5)

        tk.Label(form_frame, text="Nombre:").grid(row=1, column=0, padx=5)
        self.ent_name = tk.Entry(form_frame)
        self.ent_name.grid(row=1, column=1, padx=5)

        tk.Label(form_frame, text="Cantidad:").grid(row=2, column=0, padx=5)
        self.ent_qty = tk.Entry(form_frame)
        self.ent_qty.grid(row=2, column=1, padx=5)

        tk.Label(form_frame, text="Precio Unitario:").grid(row=3, column=0, padx=5)
        self.ent_price = tk.Entry(form_frame)
        self.ent_price.grid(row=3, column=1, padx=5)

        # Botón
        self.btn_guardar = tk.Button(self, text="Guardar en MongoDB", bg="green", fg="white", font=("Arial", 10, "bold"))
        self.btn_guardar.pack(pady=10)

        # --- Tabla (Treeview) ---
        columnas = ("ID", "Nombre", "Cant", "Precio", "Subtotal", "IVA", "Total")
        self.tabla = ttk.Treeview(self, columns=columnas, show='headings')
        
        for col in columnas:
            self.tabla.heading(col, text=col)
            self.tabla.column(col, width=100, anchor="center")
        
        self.tabla.pack(pady=10, fill="x", padx=20)

        # --- Label de Suma Total ---
        self.lbl_gran_total = tk.Label(self, text="TOTAL GENERAL: $0.00", font=("Arial", 16, "bold"), fg="blue")
        self.lbl_gran_total.pack(pady=20)