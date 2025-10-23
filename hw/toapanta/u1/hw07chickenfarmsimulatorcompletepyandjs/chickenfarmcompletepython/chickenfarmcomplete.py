class Chicken:
    def __init__(self, id, name, color, age, is_molting):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.is_molting = is_molting
    
    def __str__(self):
        return f"""
Chicken:
  id --> {self.id}
  name --> {self.name}
  color --> {self.color}
  age --> {self.age}
  isMolting --> {self.is_molting}
"""

class Egg:
    def __init__(self, size):
        self.size = size
    
    def __str__(self):
        return f"Egg: size={self.size}"

class Poop:
    def __init__(self, amount):
        self.amount = amount
    
    def __str__(self):
        return f"Poop: amount={self.amount}"

def chicken_farm_simulator_python():
    print("=== CHICKEN FARM SIMULATOR (PYTHON) ===")
    
    # Solicitar datos de la gallina
    print("\nIngrese los datos de la gallina:")
    chicken_id = int(input("ID: "))
    name = input("Nombre: ")
    color = input("Color: ")
    age = int(input("Edad: "))
    is_molting = input("¿Está mudando plumas? (si/no): ").lower() == "si"
    
    # Crear objeto Chicken
    chicken = Chicken(chicken_id, name, color, age, is_molting)
    
    # Mostrar datos
    print("\n--- Datos de la gallina ---")
    print(chicken)
    
    # Crear objetos adicionales
    egg = Egg('M')  # Tamaño mediano
    poop = Poop(5)  # Cantidad de excremento
    
    print(egg)
    print(poop)

# Ejecutar el simulador en Python
if __name__ == "__main__":
    chicken_farm_simulator_python()