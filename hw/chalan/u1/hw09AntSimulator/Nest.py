class Nest:
    def __init__(self, area, location):
        self.area = area
        self.location = location
        self.food_stock = 0 
        self.colony = None 
        area.get_cell(location).nest = self 
    def add_food(self, food_object):
        self.food_stock += food_object.amount
        print(f"Food deposited in the nest at {self.location}. Total stock: {self.food_stock}mg.")
    def retrieve_food(self, mg=1):
        retrieved = min(mg, self.food_stock)
        self.food_stock -= retrieved
        return retrieved
