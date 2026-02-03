from models.notebook import Notebook
from utils.json_handler import JsonFileHandler

class NotebookService:
    def __init__(self):
        self.json_handler = JsonFileHandler()
        self.notebooks = self.json_handler.load_from_file()
    
    def add_notebook(self):
        print("\n" + "="*50)
        print("📓 AGREGAR NUEVO NOTEBOOK")
        print("="*50)
        
        try:
            id = input("Ingrese ID: ").strip()
            
            if self.find_by_id(id):
                print(f"❌ Error: Ya existe un notebook con ID {id}!")
                return
            
            brand = input("Ingrese Marca: ").strip()
            pages = int(input("Ingrese Número de Páginas: "))
            size = input("Ingrese Tamaño (A4, A5, Carta, etc.): ").strip()
            price = float(input("Ingrese Precio: "))
            
            if pages <= 0:
                print("❌ Error: El número de páginas debe ser mayor a 0")
                return
            
            if price < 0:
                print("❌ Error: El precio no puede ser negativo")
                return
            
            notebook = Notebook(id, brand, pages, size, price)
            self.notebooks.append(notebook)
            self.json_handler.save_to_file(self.notebooks)
            
            print("✅ Notebook agregado exitosamente!")
            
        except ValueError as e:
            print(f"❌ Error: Entrada inválida - {e}")
        except Exception as e:
            print(f"❌ Error inesperado: {e}")
    
    def display_all_notebooks(self):
        print("\n" + "="*50)
        print("📚 TODOS LOS NOTEBOOKS")
        print("="*50)
        
        if not self.notebooks:
            print("📭 No se encontraron notebooks.")
        else:
            for i, notebook in enumerate(self.notebooks, 1):
                print(f"{i}. {notebook}")
    
    def find_by_id(self, id):
        for notebook in self.notebooks:
            if notebook.id == id:
                return notebook
        return None
    
    def search_notebook(self):
        print("\n" + "="*50)
        print("🔍 BUSCAR NOTEBOOK")
        print("="*50)
        
        id = input("Ingrese ID del notebook a buscar: ").strip()
        notebook = self.find_by_id(id)
        
        if notebook:
            print(f"✅ Notebook encontrado:\n{notebook}")
        else:
            print(f"❌ No se encontró notebook con ID: {id}")
    
    def update_notebook(self):
        print("\n" + "="*50)
        print("✏️ ACTUALIZAR NOTEBOOK")
        print("="*50)
        
        id = input("Ingrese ID del notebook a actualizar: ").strip()
        notebook = self.find_by_id(id)
        
        if not notebook:
            print(f"❌ No se encontró notebook con ID: {id}")
            return
        
        print(f"📋 Datos actuales: {notebook}")
        print("\nIngrese nuevos datos (presione Enter para mantener valor actual):")
        
        try:
            brand = input(f"Marca ({notebook.brand}): ").strip()
            if brand:
                notebook.brand = brand
            
            pages_input = input(f"Páginas ({notebook.pages}): ").strip()
            if pages_input:
                pages = int(pages_input)
                if pages > 0:
                    notebook.pages = pages
                else:
                    print("❌ El número de páginas debe ser mayor a 0")
                    return
            
            size = input(f"Tamaño ({notebook.size}): ").strip()
            if size:
                notebook.size = size
            
            price_input = input(f"Precio (${notebook.price:.2f}): ").strip()
            if price_input:
                price = float(price_input)
                if price >= 0:
                    notebook.price = price
                else:
                    print("❌ El precio no puede ser negativo")
                    return
            
            self.json_handler.save_to_file(self.notebooks)
            print("✅ Notebook actualizado exitosamente!")
            
        except ValueError as e:
            print(f"❌ Error: Entrada inválida - {e}")
    
    def delete_notebook(self):
        print("\n" + "="*50)
        print("🗑️ ELIMINAR NOTEBOOK")
        print("="*50)
        
        id = input("Ingrese ID del notebook a eliminar: ").strip()
        notebook = self.find_by_id(id)
        
        if notebook:
            self.notebooks.remove(notebook)
            self.json_handler.save_to_file(self.notebooks)
            print("✅ Notebook eliminado exitosamente!")
        else:
            print(f"❌ No se encontró notebook con ID: {id}")
    
    def get_statistics(self):
        if not self.notebooks:
            print("📭 No hay notebooks para mostrar estadísticas.")
            return
        
        total_notebooks = len(self.notebooks)
        avg_price = sum(notebook.price for notebook in self.notebooks) / total_notebooks
        avg_pages = sum(notebook.pages for notebook in self.notebooks) / total_notebooks
        
        print("\n" + "="*50)
        print("📊 ESTADÍSTICAS")
        print("="*50)
        print(f"Total de notebooks: {total_notebooks}")
        print(f"Precio promedio: ${avg_price:.2f}")
        print(f"Páginas promedio: {avg_pages:.1f}")
        
        brands = [notebook.brand for notebook in self.notebooks]
        most_common_brand = max(set(brands), key=brands.count)
        print(f"Marca más común: {most_common_brand}")