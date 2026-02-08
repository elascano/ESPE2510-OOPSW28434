import tkinter as tk
from tkinter import ttk, messagebox
from typing import Callable, List
from models.product import Product

class ProductView:
    def __init__(self, root: tk.Tk):
        self.root = root
        self.root.title("Sistema de Productos")
        self.root.geometry("800x600")
        
        self.setup_ui()
        self.setup_styles()
    
    def setup_ui(self):
        # Frame principal
        main_frame = ttk.Frame(self.root, padding="10")
        main_frame.grid(row=0, column=0, sticky=(tk.W, tk.E, tk.N, tk.S))
        
        # Configurar expansión
        self.root.columnconfigure(0, weight=1)
        self.root.rowconfigure(0, weight=1)
        main_frame.columnconfigure(1, weight=1)
        main_frame.rowconfigure(4, weight=1)
        
        # Campos de entrada - FILA 0: Nombre
        ttk.Label(main_frame, text="Nombre del Producto:").grid(row=0, column=0, sticky=tk.W, pady=5)
        self.name_entry = ttk.Entry(main_frame, width=20)
        self.name_entry.grid(row=0, column=1, sticky=(tk.W, tk.E), pady=5, padx=(5, 0))
        
        # Campos de entrada - FILA 1: Marca
        ttk.Label(main_frame, text="Marca:").grid(row=1, column=0, sticky=tk.W, pady=5)
        self.make_entry = ttk.Entry(main_frame, width=20)
        self.make_entry.grid(row=1, column=1, sticky=(tk.W, tk.E), pady=5, padx=(5, 0))
        
        # Campos de entrada - FILA 2: Precio
        ttk.Label(main_frame, text="Precio Base:").grid(row=2, column=0, sticky=tk.W, pady=5)
        self.price_entry = ttk.Entry(main_frame, width=20)
        self.price_entry.grid(row=2, column=1, sticky=(tk.W, tk.E), pady=5, padx=(5, 0))
        
        # Botones - FILA 3
        button_frame = ttk.Frame(main_frame)
        button_frame.grid(row=3, column=0, columnspan=2, pady=10)
        
        self.add_button = ttk.Button(button_frame, text="Agregar Producto")
        self.add_button.pack(side=tk.LEFT, padx=5)
        
        self.refresh_button = ttk.Button(button_frame, text="Actualizar Lista")
        self.refresh_button.pack(side=tk.LEFT, padx=5)
        
        self.clear_button = ttk.Button(button_frame, text="Limpiar Campos")
        self.clear_button.pack(side=tk.LEFT, padx=5)
        
        # Tabla de productos - FILA 4
        ttk.Label(main_frame, text="Productos Registrados:").grid(row=4, column=0, sticky=tk.W, pady=(10, 5))
        
        # Treeview con scrollbar - FILA 5
        tree_frame = ttk.Frame(main_frame)
        tree_frame.grid(row=5, column=0, columnspan=2, sticky=(tk.W, tk.E, tk.N, tk.S), pady=5)
        
        tree_frame.columnconfigure(0, weight=1)
        tree_frame.rowconfigure(0, weight=1)
        
        columns = ("name", "make", "base_price", "final_price")
        self.tree = ttk.Treeview(tree_frame, columns=columns, show="headings")
        
        # Configurar columnas
        self.tree.heading("name", text="Nombre")
        self.tree.heading("make", text="Marca")
        self.tree.heading("base_price", text="Precio Base")
        self.tree.heading("final_price", text="Precio Final")
        
        self.tree.column("name", width=200)
        self.tree.column("make", width=100)
        self.tree.column("base_price", width=150, anchor=tk.E)
        self.tree.column("final_price", width=150, anchor=tk.E)
        
        # Scrollbar
        scrollbar = ttk.Scrollbar(tree_frame, orient=tk.VERTICAL, command=self.tree.yview)
        self.tree.configure(yscrollcommand=scrollbar.set)
        
        self.tree.grid(row=0, column=0, sticky=(tk.W, tk.E, tk.N, tk.S))
        scrollbar.grid(row=0, column=1, sticky=(tk.N, tk.S))
        
        # Total - FILA 6
        self.total_label = ttk.Label(main_frame, text="Total: $0.00", font=('Arial', 12, 'bold'))
        self.total_label.grid(row=6, column=0, columnspan=2, pady=10, sticky=tk.E)
        
        # Status bar - FILA 7
        self.status_bar = ttk.Label(main_frame, text="Listo", relief=tk.SUNKEN, anchor=tk.W)
        self.status_bar.grid(row=7, column=0, columnspan=2, sticky=(tk.W, tk.E), pady=(10, 0))
    
    def setup_styles(self):
        style = ttk.Style()
        style.configure("Treeview", rowheight=25)
        style.configure("Treeview.Heading", font=('Arial', 10, 'bold'))
    
    def set_add_callback(self, callback: Callable[[str, str, str], None]):
        self.add_button.config(command=lambda: self._add_product(callback))
    
    def set_refresh_callback(self, callback: Callable[[], None]):
        self.refresh_button.config(command=callback)
    
    def set_clear_callback(self, callback: Callable[[], None]):
        self.clear_button.config(command=callback)
    
    def _add_product(self, callback: Callable[[str, str, str], None]):
        name = self.name_entry.get().strip()
        make = self.make_entry.get().strip()
        price = self.price_entry.get().strip()
        
        if not name:
            messagebox.showwarning("Advertencia", "Por favor ingrese un nombre para el producto")
            self.name_entry.focus()
            return
        
        if not make:
            messagebox.showwarning("Advertencia", "Por favor ingrese la marca del producto")
            self.make_entry.focus()
            return
        
        if not price:
            messagebox.showwarning("Advertencia", "Por favor ingrese un precio base")
            self.price_entry.focus()
            return
        
        try:
            float(price)
            callback(name, make, price)
            self.clear_fields()
            self.status_bar.config(text="Producto agregado exitosamente")
        except ValueError:
            messagebox.showerror("Error", "El precio debe ser un número válido")
            self.price_entry.select_range(0, tk.END)
            self.price_entry.focus()
    
    def clear_fields(self):
        self.name_entry.delete(0, tk.END)
        self.make_entry.delete(0, tk.END)
        self.price_entry.delete(0, tk.END)
        self.name_entry.focus()
    
    def update_product_list(self, products: List[Product], total: float):
        # Limpiar tabla
        for item in self.tree.get_children():
            self.tree.delete(item)
        
        # Agregar productos
        for product in products:
            self.tree.insert("", tk.END, values=(
                product.name,
                product.make,
                f"${product.base_price:.2f}",
                f"${product.final_price:.2f}"
            ))
        
        # Actualizar total
        self.total_label.config(text=f"Total: ${total:.2f}")
        self.status_bar.config(text=f"{len(products)} productos registrados")
    
    def show_error(self, message: str):
        messagebox.showerror("Error", message)
        self.status_bar.config(text="Error: " + message)