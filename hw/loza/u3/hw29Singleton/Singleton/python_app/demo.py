import tkinter as tk
from tkinter import simpledialog, messagebox
from dataclasses import dataclass

print("=" * 50)
print("INICIANDO APLICACIÓN PYTHON - INVENTARIO ZAPATOS")
print("=" * 50)

@dataclass
class Shoe:
    id: str
    name: str
    stock: int

class MongoInventoryRepository:
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
            cls._instance._data = {}
            print("✓ Creando instancia única del Singleton...")
        return cls._instance

    @classmethod
    def get_instance(cls):
        return cls()

    def add_shoe(self, shoe_id, name, stock):
        self._data[shoe_id] = Shoe(shoe_id, name, stock)
        print(f"✓ Zapato agregado: {shoe_id} - {name} (Stock: {stock})")

    def find_by_id(self, shoe_id):
        return self._data.get(shoe_id)

    def buy(self, shoe_id, quantity):
        if quantity <= 0:
            raise ValueError("Cantidad invalida.")
        shoe = self._data.get(shoe_id)
        if shoe is None:
            return -1
        if shoe.stock < quantity:
            return -2
        shoe.stock -= quantity
        return shoe.stock

class InventoryView(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Inventario de Zapatos")
        self.geometry("300x160")
        print("✓ Ventana gráfica creada")
        
        button_frame = tk.Frame(self)
        button_frame.pack(padx=10, pady=10, fill=tk.BOTH, expand=True)
        
        self.add_button = tk.Button(button_frame, text="Agregar zapato", font=("Arial", 11))
        self.add_button.pack(fill=tk.BOTH, expand=True, pady=5)
        
        self.buy_button = tk.Button(button_frame, text="Comprar", font=("Arial", 11))
        self.buy_button.pack(fill=tk.BOTH, expand=True, pady=5)
        
        self.add_callback = None
        self.buy_callback = None
        
        self.add_button.config(command=self._on_add_click)
        self.buy_button.config(command=self._on_buy_click)
    
    def _on_add_click(self):
        if self.add_callback:
            self.add_callback()
    
    def _on_buy_click(self):
        if self.buy_callback:
            self.buy_callback()
    
    def on_add(self, callback):
        self.add_callback = callback

    def on_buy(self, callback):
        self.buy_callback = callback

    def prompt(self, message):
        return simpledialog.askstring("Input", message)

    def show_message(self, message):
        messagebox.showinfo("Info", message)

    def show_error(self, message):
        messagebox.showerror("Error", message)

    def show_low_stock_warning(self, stock):
        messagebox.showwarning("Alerta", f"Stock bajo: quedan {stock}")

class InventoryController:
    def __init__(self, repository, view):
        self.repository = repository
        self.view = view

    def init(self):
        self.view.on_add(self.handle_add)
        self.view.on_buy(self.handle_buy)

    def handle_add(self):
        shoe_id = self.view.prompt("Ingrese el id")
        if not shoe_id or not shoe_id.strip():
            self.view.show_error("Id invalido.")
            return
        name = self.view.prompt("Ingrese el nombre del zapato")
        if not name or not name.strip():
            self.view.show_error("Nombre invalido.")
            return
        stock_value = self.view.prompt("Ingrese el stock")
        if not stock_value or not stock_value.strip().isdigit():
            self.view.show_error("Stock invalido.")
            return
        stock = int(stock_value.strip())
        self.repository.add_shoe(shoe_id.strip(), name.strip(), stock)
        self.view.show_message("Zapato guardado.")

    def handle_buy(self):
        shoe_id = self.view.prompt("Ingrese el id")
        if not shoe_id or not shoe_id.strip():
            self.view.show_error("Id invalido.")
            return
        shoe = self.repository.find_by_id(shoe_id.strip())
        if shoe is None:
            self.view.show_error("No existe un zapato con ese id.")
            return
        self.view.show_message(f"Zapato: {shoe.name} (stock {shoe.stock})")
        quantity_value = self.view.prompt("Ingrese la cantidad a comprar")
        if not quantity_value or not quantity_value.strip().isdigit() or int(quantity_value) <= 0:
            self.view.show_error("Cantidad invalida.")
            return
        remaining = self.repository.buy(shoe_id.strip(), int(quantity_value.strip()))
        if remaining == -2:
            self.view.show_error("Stock insuficiente.")
            return
        if remaining == -1:
            self.view.show_error("No existe un zapato con ese id.")
            return
        self.view.show_message(f"Compra realizada. Stock restante: {remaining}")
        if remaining < 5:
            self.view.show_low_stock_warning(remaining)

if __name__ == "__main__":
    print("\n📦 DEMOSTRACIÓN DEL PATRÓN SINGLETON\n")
    
    # Crear instancias
    repo1 = MongoInventoryRepository.get_instance()
    repo2 = MongoInventoryRepository.get_instance()
    
    print(f"\n¿Son la misma instancia? {repo1 is repo2}")
    print(f"ID instancia 1: {id(repo1)}")
    print(f"ID instancia 2: {id(repo2)}")
    
    # Agregar datos de prueba
    print("\n📝 Agregando zapatos de prueba...\n")
    repo1.add_shoe("S001", "Nike Air Max", 10)
    repo1.add_shoe("S002", "Adidas Ultraboost", 5)
    repo1.add_shoe("S003", "Puma RS-X", 3)
    
    # Mostrar inventario
    print("\n📊 Inventario actual:")
    for shoe_id, shoe in repo1._data.items():
        print(f"  {shoe.id}: {shoe.name} - Stock: {shoe.stock}")
    
    print("\n" + "=" * 50)
    print("Abriendo interfaz gráfica...")
    print("=" * 50 + "\n")
    
    view = InventoryView()
    controller = InventoryController(repo1, view)
    controller.init()
    view.mainloop()
