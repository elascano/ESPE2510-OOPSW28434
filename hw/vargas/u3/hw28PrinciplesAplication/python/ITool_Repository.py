from abc import ABC, abstractmethod
from typing import List

class ITool_Repository(ABC):
    @abstractmethod
    def save(self, tool) -> bool: pass
    
    @abstractmethod
    def find_by_id(self, tool_id: str): pass
    
    @abstractmethod
    def get_all(self) -> List: pass
    
    @abstractmethod
    def delete(self, tool_id: str) -> bool: pass