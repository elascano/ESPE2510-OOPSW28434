from abc import ABC, abstractmethod
from typing import List

class ITool_View(ABC):
    @abstractmethod
    def get_form_data(self) -> dict: pass
    
    @abstractmethod
    def show_message(self, title: str, msg: str, is_error: bool = False): pass
    
    @abstractmethod
    def update_list(self, tools: List): pass
    
    @abstractmethod
    def clear_form(self): pass
    
    @abstractmethod
    def set_controller(self, controller): pass