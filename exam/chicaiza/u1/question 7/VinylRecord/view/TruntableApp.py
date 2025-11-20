
import json
from VinylRecord.model.VinylRecord import VinylRecord
def main():
    records = load_from_json()

    while True:
        print("\n MENÚ ")
        print("1. Crear Vinyl Record")
       
        print("2. Ver Vinyl Records")
    
        choice = input("Opción: ")

        if choice == "1":
            record = create_record()
            records.append(record)
            save_to_json(records)
            print("Vinyl Record añadido.")

        elif choice == "2":
            records = load_from_json()
            print("Datos cargados desde JSON.")


        else:
            print("Opción no válida.")

if __name__ == "__main__":
    main()



JSON_FILE = "records.json"

def save_to_json(records):
    data = [record.to_dict() for record in records]
    with open(JSON_FILE, "w") as f:
        json.dump(data, f, indent=4)
    print("Datos guardados en records.json")

def load_from_json():
    try:
        with open(JSON_FILE, "r") as f:
            data = json.load(f)
            return [VinylRecord.from_dict(d) for d in data]
    except FileNotFoundError:
        print("El archivo JSON no existe todavía.")
        return []
    except json.JSONDecodeError:
        print("Error al leer el archivo JSON.")
        return []


def create_record():
    print("\n Crear Vinyl Record ")
    record_id = int(input("ID: "))
    name = input("Nombre: ")
    year = input ("Año:")
    duration = float(input("Duración (minutos): "))


    return VinylRecord(record_id, name, duration)



