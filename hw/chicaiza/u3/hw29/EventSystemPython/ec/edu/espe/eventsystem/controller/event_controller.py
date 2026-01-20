from ec.edu.espe.eventsystem.model.discount import Discount
from ec.edu.espe.eventsystem.model.event import Event
from ec.edu.espe.eventsystem.utils.json_operations import read_discount, save_discount


class EventController:

    def __init__(self):
        percentage = read_discount()
        self.discount = Discount(percentage)

    def get_discount(self) -> float:
        return self.discount.get_percentage()

    def update_discount(self, percentage: float):
        if percentage < 0 or percentage > 100:
            raise ValueError("Discount must be between 0 and 100")

        self.discount.set_percentage(percentage)
        save_discount(percentage)

    def calculate_total(self, event: Event) -> float:
        return event.price - (event.price * self.discount.get_percentage() / 100)
