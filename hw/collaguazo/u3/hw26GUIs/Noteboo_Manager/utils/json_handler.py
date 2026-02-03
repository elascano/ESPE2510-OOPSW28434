import json
import os

class JsonFileHandler:
    def __init__(self, file_path='data/notebooks.json'):
        self.file_path = file_path
        self.ensure_directory_exists()
    
    def ensure_directory_exists(self):
        directory = os.path.dirname(self.file_path)
        if directory and not os.path.exists(directory):
            os.makedirs(directory)
    
    def save_to_file(self, notebooks):
        try:
            notebooks_data = [notebook.to_dict() for notebook in notebooks]
            
            with open(self.file_path, 'w', encoding='utf-8') as file:
                json.dump(notebooks_data, file, indent=4, ensure_ascii=False)
            
            print(f"✅ Datos guardados exitosamente en {self.file_path}")
            return True
        except Exception as e:
            print(f"❌ Error guardando en archivo: {e}")
            return False
    
    def load_from_file(self):
        try:
            if not os.path.exists(self.file_path):
                print("📄 Archivo no existe. Creando nueva lista vacía.")
                return []
            
            with open(self.file_path, 'r', encoding='utf-8') as file:
                notebooks_data = json.load(file)
            
            # Importar aquí para evitar dependencia circular
            from models.notebook import Notebook
            notebooks = [Notebook.from_dict(data) for data in notebooks_data]
            print(f"✅ Datos cargados exitosamente desde {self.file_path}")
            return notebooks
        except Exception as e:
            print(f"❌ Error cargando desde archivo: {e}")
            return []