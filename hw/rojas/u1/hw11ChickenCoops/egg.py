from datetime import datetime
class Egg:
    def __init__(self, id, chicken_id, laid_at=None):
        self.id = id
        self.chicken_id = chicken_id
        self.laid_at = laid_at or datetime.now()

    def __str__(self):
        return f"Egg#{self.id} from Chicken#{self.chicken_id} at {self.laid_at.strftime('%Y-%m-%d %H:%M:%S')}"
