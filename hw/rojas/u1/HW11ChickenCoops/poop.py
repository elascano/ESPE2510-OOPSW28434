class Poop:
    def __init__(self, id, chicken_id):
        self.id = id
        self.chicken_id = chicken_id

    def __str__(self):
        return f"Poop#{self.id} from Chicken#{self.chicken_id}"
