from model.Toy import Toy

class ToyController:
    def __init__(self):
        pass

    def get_price_with_iva(self, toy: Toy) -> float:
        return toy.final_price()
