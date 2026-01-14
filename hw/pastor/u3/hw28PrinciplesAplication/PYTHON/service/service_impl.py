from service.i_service import IService
from model.generic_entity import GenericEntity

class CalculateService(IService):
    def __init__(self, repository):
        self.repository = repository

    def save_new_item(self, input_data: dict):
        entity = GenericEntity("product")

        for key, value in input_data.items():
            entity.set_data(key, value)
        
        self.repository.create(entity)

    def get_processed_data(self):
        items = self.repository.read_all()
        
        for item in items:
            try:
                price_base = float(item.get_data("priceBase"))
                tax = price_base * 0.15
                final_price = price_base + tax
                
                item.set_data("endPrice", final_price)
            except (TypeError, ValueError):
                pass 
        
        return items