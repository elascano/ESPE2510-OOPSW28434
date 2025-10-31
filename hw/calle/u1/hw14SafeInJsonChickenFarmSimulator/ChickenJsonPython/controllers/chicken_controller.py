from typing import List, Optional
from models.chicken import Chicken, ChickenModel

class ChickenController:
    def __init__(self):
        self._model = ChickenModel()
    
    def insert_chicken(self, id: int, name: str, color: str, age: int, is_molting: bool) -> bool:
        if not all([id, name.strip(), color.strip(), age >= 0]):
            return False
        
        chicken = Chicken(id, name.strip(), color.strip(), age, is_molting)
        return self._model.add_chicken(chicken)
    
    def list_chickens(self) -> List[Chicken]:
        return self._model.get_all_chickens()
    
    def delete_chicken(self, chicken_id: int) -> bool:
        return self._model.delete_chicken(chicken_id)
    
    def update_chicken(self, chicken_id: int, name: str, color: str, age: int, is_molting: bool) -> bool:
        existing_chicken = self._model.get_chicken_by_id(chicken_id)
        if not existing_chicken:
            return False
        
        updated_chicken = Chicken(chicken_id, name, color, age, is_molting)
        return self._model.update_chicken(chicken_id, updated_chicken)
    
    def find_chickens(self, name: str) -> List[Chicken]:
        return self._model.find_chickens_by_name(name)
    
    def get_chicken_by_id(self, chicken_id: int) -> Optional[Chicken]:
        return self._model.get_chicken_by_id(chicken_id)
    
    def get_next_available_id(self) -> int:
        chickens = self._model.get_all_chickens()
        if not chickens:
            return 1
        return max(chicken.id for chicken in chickens) + 1