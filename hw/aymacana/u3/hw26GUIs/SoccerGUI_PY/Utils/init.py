# Exam Solution/init_db.py
from pymongo import MongoClient

def initialize_database():
    client = MongoClient("mongodb+srv://Mateo:Mateo2006@cluster0.2mp0ve2.mongodb.net/?appName=Cluster0")
    db = client["TestDB"]
    
    # Crear colección Counters si no existe
    if "Counters" not in db.list_collection_names():
        db.create_collection("Counters")
        print("Created Counters collection")
    
    # Crear colección SoccerTeam si no existe
    if "SoccerTeam" not in db.list_collection_names():
        db.create_collection("SoccerTeam")
        print("Created SoccerTeam collection")
    
    print("Database initialized successfully")

if __name__ == "__main__":
    initialize_database()