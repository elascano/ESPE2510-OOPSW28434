# Exam Solution/Utils/CRUD_operations.py
from pymongo import MongoClient

class Mongo_CRUD:
    def __init__(self):
        self.client = MongoClient("mongodb+srv://Mateo:Mateo2006@cluster0.2mp0ve2.mongodb.net/?appName=Cluster0")
        self.dataBase = self.client["TestDB"]
        self.collection = self.dataBase["SoccerTeam"]
        self.counter_collection = self.dataBase["Counters"]
    
    def _get_next_team_id(self):
        """Obtener el siguiente ID autoincremental"""
        try:
            # Buscar o crear el contador para teams
            counter = self.counter_collection.find_one_and_update(
                {"_id": "team_id"},
                {"$inc": {"seq": 1}},
                upsert=True,
                return_document=True
            )
            
            if counter and 'seq' in counter:
                return counter['seq']
            else:
                # Si no existe, crear con ID 1
                self.counter_collection.insert_one({"_id": "team_id", "seq": 1})
                return 1
                
        except Exception as e:
            # Fallback: contar documentos y sumar 1
            count = self.collection.count_documents({})
            return count + 1
    
    def create(self, team):
        """Crear un nuevo equipo con ID autoincremental"""
        # Asignar ID autoincremental
        if not hasattr(team, 'team_id') or team.team_id is None:
            team.team_id = self._get_next_team_id()
        
        # Convertir a diccionario
        team_dict = team.__dict__
        
        # Insertar en MongoDB
        result = self.collection.insert_one(team_dict)
        return team.team_id
    
    def read(self, team_id):
        return self.collection.find_one({"team_id": team_id})
    
    def read_all(self):
        return list(self.collection.find())
    
    def update(self, team_id, data):
        result = self.collection.update_one(
            {"team_id": team_id},
            {"$set": data}
        )
        return result.modified_count > 0
    
    def delete(self, team_id):
        result = self.collection.delete_one({"team_id": team_id})
        return result.deleted_count > 0
    
    def get_last_team_id(self):
        last_team = self.collection.find_one(sort=[("team_id", -1)])
        if last_team and 'team_id' in last_team:
            return last_team['team_id']
        return 0