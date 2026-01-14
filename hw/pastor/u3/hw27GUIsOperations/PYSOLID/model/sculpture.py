# [SRP] RESPONSABILIDAD ÚNICA: Esta clase solo define el Modelo de Datos.
# No calcula impuestos, no conecta a BD, no muestra ventanas.

class Sculpture:
    def __init__(self, id_sculpture, name, price, materials, price_with_iva=None):
        # [E] ENCAPSULATION: Agrupamos los datos relacionados en un solo objeto
        self.id = str(id_sculpture)
        self.name = name
        self.price = float(price)
        
        # Validación básica para asegurar que materials sea una lista
        self.materials = materials if isinstance(materials, list) else []
        
        # Manejo de nulos para el precio calculado
        self.price_with_iva = float(price_with_iva) if price_with_iva is not None else 0.0

    def __str__(self):
        return f"{self.name} (Materials: {self.materials})"