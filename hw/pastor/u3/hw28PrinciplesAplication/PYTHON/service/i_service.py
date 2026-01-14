from abc import ABC, abstractmethod

class IService(ABC):
    @abstractmethod
    def save_new_item(self, input_data: dict):
        pass

    @abstractmethod
    def get_processed_data(self):
        pass