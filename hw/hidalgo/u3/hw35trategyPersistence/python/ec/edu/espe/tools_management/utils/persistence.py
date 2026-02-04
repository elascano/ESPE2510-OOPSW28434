from abc import ABC, abstractmethod
from typing import List
from model.tool import Tool

class Persistence(ABC):
    
    @abstractmethod
    def create(self, tool: Tool) -> bool:
        pass

    @abstractmethod
    def read(self) -> List[Tool]:
        pass

    @abstractmethod
    def update(self, tool: Tool) -> bool:
        pass

    @abstractmethod
    def delete(self, tool_id: str) -> bool:
        pass

    @abstractmethod
    def find(self, tool_id: str) -> Tool:
        pass