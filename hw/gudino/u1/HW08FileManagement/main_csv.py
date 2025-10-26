import csv  # Importamos la librería para trabajar con CSV

# ====== 1. Definimos los datos ======
# Es una lista de diccionarios (cada persona es un registro)
personas = [
    {"id": 1, "nombre": "Ana", "edad": 24},
    {"id": 2, "nombre": "Carlos", "edad": 29},
    {"id": 3, "nombre": "Lady", "edad": 33},
    {"id": 4, "nombre": "Pedro", "edad": 28}
]

# Aquí agregamos el parámetro delimiter=';'
with open("personas.csv", "w", newline="", encoding="utf-8") as archivo:
    columnas = ["id", "nombre", "edad"]
    escritor = csv.DictWriter(archivo, fieldnames=columnas, delimiter=';')
    escritor.writeheader()
    escritor.writerows(personas)

print("✅ Archivo 'personas.csv' creado con punto y coma como separador.")

