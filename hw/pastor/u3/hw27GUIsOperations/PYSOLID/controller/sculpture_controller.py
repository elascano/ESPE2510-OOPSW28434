from utils.mongo_connection import MongoConnection
from model.sculpture import Sculpture

# [SRP] RESPONSABILIDAD ÚNICA: Controlar el flujo de datos y aplicar reglas de negocio.
# [M] MODULARITY: Separado de la Vista y del Modelo.

class SculptureController:
    def __init__(self):
        self.db = MongoConnection.get_database()
        self.collection = self.db["sculptures"] # Cambiamos la colección a 'sculptures'
        
        # [REUSABLE] Constante para reglas de negocio
        self.IVA_RATE = 0.15

    # ================================================================
    #  SECCIÓN DE LÓGICA DE NEGOCIO (BUSINESS RULES) - [EXAMEN AQUÍ]
    #  Aquí es donde debes modificar si mañana te piden:
    #  "Calcular descuento mayorista" o "Impuesto por material de lujo"
    # ================================================================

    def calculate_iva(self, price):
        # [A] ABSTRACTION: Ocultamos la fórmula matemática aquí.
        result = price * (1 + self.IVA_RATE)
        return round(result, 2)

    # Ejemplo de cómo sería una regla nueva (COMENTADO PARA TU REFERENCIA):
    # def calculate_special_price(self, price, materials):
    #     if "Oro" in materials:
    #         return price * 1.50 # Regla especial
    #     return price

    # ================================================================
    #  SECCIÓN DE PERSISTENCIA (CRUD) - Mongo Logic
    # ================================================================

    # --- CREATE ---
    def create_sculpture(self, id_sculpture, name, price, materials):
        try:
            # 1. Aplicar Regla de Negocio (Llamada al método de arriba)
            final_price = self.calculate_iva(price)
            
            # 2. Crear Documento
            doc = {
                "id": id_sculpture,
                "name": name,
                "price": price,
                "materials": materials, # Guardamos lista de materiales
                "priceWithIva": final_price
            }
            
            self.collection.insert_one(doc)
            return True
        except Exception as e:
            print(f"Error creating: {e}")
            return False

    # --- READ (ALL) ---
    def get_all_sculptures(self):
        sculptures = []
        try:
            for doc in self.collection.find():
                sculptures.append(self._map_document_to_sculpture(doc))
        except Exception as e:
            print(f"Error reading: {e}")
        return sculptures

    # --- FIND (BY ID) ---
    def find_sculpture_by_id(self, id_sculpture):
        try:
            doc = self.collection.find_one({"id": id_sculpture})
            if doc:
                return self._map_document_to_sculpture(doc)
        except Exception as e:
            print(f"Error finding: {e}")
        return None

    # --- UPDATE ---
    def update_sculpture(self, id_sculpture, name, price, materials):
        try:
            # 1. Recalcular Regla de Negocio (CRÍTICO AL ACTUALIZAR)
            final_price = self.calculate_iva(price)

            # 2. Actualizar en Mongo
            result = self.collection.update_one(
                {"id": id_sculpture},
                {"$set": {
                    "name": name,
                    "price": price,
                    "materials": materials,
                    "priceWithIva": final_price
                }}
            )
            # Retorna True solo si encontró y procesó el ID
            return result.matched_count > 0
        except Exception as e:
            print(f"Error updating: {e}")
            return False

    # --- DELETE ---
    def delete_sculpture(self, id_sculpture):
        try:
            result = self.collection.delete_one({"id": id_sculpture})
            return result.deleted_count > 0
        except Exception as e:
            print(f"Error deleting: {e}")
            return False

    # --- [M] Helper Mapper (Privado) ---
    # Convierte el diccionario de Mongo en un Objeto Sculpture limpio
    def _map_document_to_sculpture(self, doc):
        id_val = doc.get("id")
        if id_val is None: id_val = str(doc.get("_id"))
        
        name = doc.get("name", "")
        price = doc.get("price", 0.0)
        
        # Aseguramos que materials sea lista
        materials = doc.get("materials", [])
        if not isinstance(materials, list): materials = []
        
        # Fallback para el cálculo si viene nulo de la BD
        price_iva = doc.get("priceWithIva")
        if price_iva is None:
            price_iva = self.calculate_iva(price)
            
        return Sculpture(id_val, name, price, materials, price_iva)