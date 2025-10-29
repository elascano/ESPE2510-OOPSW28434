import os
import sys
import json

CURRENT_DIR = os.path.dirname(os.path.abspath(__file__))
ROOT_DIR = os.path.dirname(CURRENT_DIR)
if ROOT_DIR not in sys.path:
    sys.path.append(ROOT_DIR)

from model.Chicken import Chicken
from model.Chicken_Coop import ChickenCoop

DATA_FILE = os.path.join(ROOT_DIR, "farm_data.json")


# ---------- Guardar / Cargar ----------
def save_to_json(coops):
    data = []
    for coop in coops:
        coop_data = {"id": coop.id, "chickens": []}
        for c in coop.chickens:
            coop_data["chickens"].append({
                "id": c.id,
                "name": c.name,
                "color": c.color,
                "age": c.age,
                "is_molting": c.is_molting
            })
        data.append(coop_data)

    with open(DATA_FILE, "w", encoding="utf-8") as file:
        json.dump(data, file, indent=4)


def load_from_json():
    if not os.path.exists(DATA_FILE):
        return []

    with open(DATA_FILE, "r", encoding="utf-8") as file:
        data = json.load(file)

    coops = []
    for coop_data in data:
        coop = ChickenCoop(id=coop_data["id"])
        for c_data in coop_data.get("chickens", []):
            coop.add(Chicken(
                id=c_data["id"],
                name=c_data["name"],
                color=c_data["color"],
                age=c_data["age"],
                is_molting=c_data["is_molting"]
            ))
        coops.append(coop)
    return coops


# ---------- Programa principal ----------
def main():
    coops = load_from_json()

    # Si no existen gallineros, crear unos nuevos
    if not coops:
        coop1 = ChickenCoop(id=1)
        coop2 = ChickenCoop(id=2)
        coop1.add(Chicken(1, "Lucy", "White", 2, False))
        coop1.add(Chicken(2, "Maruja", "Brown", 1, True))
        coop2.add(Chicken(3, "Pio", "Black", 3, False))
        coops = [coop1, coop2]
        save_to_json(coops)

    while True:
        print("\n===== Chicken Coop Menu =====")
        print("1. Ver gallineros y gallinas")
        print("2. Añadir nueva gallina")
        print("3. Salir")
        opcion = input("Selecciona una opción: ")

        if opcion == "1":
            for coop in coops:
                print(coop.list_chickens())

        elif opcion == "2":
            try:
                coop_id = int(input("ID del gallinero (1 o 2): "))
                coop = next((c for c in coops if c.id == coop_id), None)
                if not coop:
                    print("Gallinero no encontrado.")
                    continue

                name = input("Nombre: ")
                color = input("Color: ")
                age = int(input("Edad: "))
                molting_input = input("¿Está mudando plumas? (s/n): ").lower()
                is_molting = molting_input == "s"
                new_id = max((c.id for cp in coops for c in cp.chickens), default=0) + 1

                coop.add(Chicken(new_id, name, color, age, is_molting))
                save_to_json(coops)
                print(f"✅ Gallina '{name}' añadida correctamente al gallinero {coop_id}.")
            except ValueError:
                print("⚠️ Entrada inválida.")

        elif opcion == "3":
            print("👋 Saliendo del simulador...")
            break
        else:
            print("Opción no válida.")


if __name__ == "__main__":
    main()

