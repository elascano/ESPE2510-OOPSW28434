from pymongo import MongoClient
import json

def guardar_en_mongo(student):
    try:
        
        client = MongoClient("mongodb+srv://Steven:Steven2001@cluster0.mp8muds.mongodb.net/?appName=Cluster0")
        db = client["escuela"]                
        collection = db["estudiantes"]        
        
        collection.insert_one(student)
        print("✔ Estudiante guardado correctamente en MongoDB")

    except Exception as e:
        print(" Error de conexión:", e)


def guardar_en_json(student, archivo="students.json"):
    try:
       
        try:
            with open(archivo, "r") as f:
                data = json.load(f)
        except FileNotFoundError:
            data = []

       
        data.append(student)

        
        with open(archivo, "w") as f:
            json.dump(data, f, indent=4)

        print(" Estudiante guardado en archivo JSON")

    except Exception as e:
        print(" Error guardando JSON:", e)


def main():
    print("=== Registro de Estudiantes ===")
    nombre = input("Nombre: ")
    edad = int(input("Edad: "))
    correo = input("Correo: ")

    student = {
        "nombre": nombre,
        "edad": edad,
        "correo": correo
    }

   
    guardar_en_mongo(student)


    guardar_en_json(student)


if __name__ == "__main__":
    main()
