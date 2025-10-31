import json
import os
from chickenfarmsimulator.model.chicken_farmer import ChickenFarmer
from chickenfarmsimulator.model.chicken_coop import ChickenCoop
from chickenfarmsimulator.model.chicken import Chicken

class FarmController:
    def __init__(self, data_file='data/farms.json'):
        self.data_file = data_file
        self.farmer = None
        self.load_data()
    
    def load_data(self):
        """Carga los datos desde el archivo JSON"""
        if os.path.exists(self.data_file):
            try:
                with open(self.data_file, 'r', encoding='utf-8') as f:
                    data = json.load(f)
                    self.farmer = ChickenFarmer.from_dict(data)
            except (json.JSONDecodeError, KeyError):
                # Si hay error en el archivo, crear nuevo farmer
                self.farmer = ChickenFarmer("Default Farmer")
        else:
            self.farmer = ChickenFarmer("Default Farmer")
    
    def save_data(self):
        """Guarda los datos en el archivo JSON"""
        os.makedirs(os.path.dirname(self.data_file), exist_ok=True)
        with open(self.data_file, 'w', encoding='utf-8') as f:
            json.dump(self.farmer.to_dict(), f, indent=2, ensure_ascii=False)
    
    def create_coop(self, coop_id: int):
        """Crea una nueva granja"""
        if self.farmer.get_coop(coop_id):
            return False  # Ya existe una granja con ese ID
        coop = ChickenCoop(coop_id)
        self.farmer.add(coop)
        self.save_data()
        return True
    
    def add_chicken_to_coop(self, coop_id: int, chicken_id: int, name: str, color: str, age: int, is_molting: bool):
        """Añade un pollo a una granja"""
        coop = self.farmer.get_coop(coop_id)
        if not coop:
            return False  # Granja no encontrada
        
        # Verificar si el pollo ya existe
        if coop.get_chicken(chicken_id):
            return False  # Pollo ya existe
        
        chicken = Chicken(chicken_id, name, color, age, is_molting)
        coop.add(chicken)
        self.save_data()
        return True
    
    def get_all_coops(self):
        """Obtiene todas las granjas"""
        return self.farmer.coops
    
    def get_coop(self, coop_id: int):
        """Obtiene una granja específica"""
        return self.farmer.get_coop(coop_id)
    
    def update_chicken(self, coop_id: int, chicken_id: int, name: str, color: str, age: int, is_molting: bool):
        """Actualiza un pollo"""
        coop = self.farmer.get_coop(coop_id)
        if not coop:
            return False
        
        success = coop.update_chicken(chicken_id, name, color, age, is_molting)
        if success:
            self.save_data()
        return success
    
    def delete_chicken(self, coop_id: int, chicken_id: int):
        """Elimina un pollo"""
        coop = self.farmer.get_coop(coop_id)
        if not coop:
            return False
        
        coop.remove(chicken_id)
        self.save_data()
        return True
    
    def perform_chicken_action(self, coop_id: int, chicken_id: int, action: str):
        """Realiza una acción del pollo"""
        coop = self.farmer.get_coop(coop_id)
        if not coop:
            return False
        
        chicken = coop.get_chicken(chicken_id)
        if not chicken:
            return False
        
        actions = {
            'cluck': chicken.cluck,
            'wander': chicken.wander,
            'eat': chicken.eat,
            'drink': chicken.drink,
            'poop': chicken.poop,
            'lay_egg': chicken.lay_an_egg
        }
        
        if action in actions:
            actions[action]()
            return True
        return False
    
    def get_chicken_count(self):
        """Obtiene el número total de pollos"""
        total = 0
        for coop in self.farmer.coops:
            total += len(coop.chickens)
        return total
    
    def get_coop_count(self):
        """Obtiene el número total de granjas"""
        return len(self.farmer.coops)