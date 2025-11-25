class Cage:
    def __init__(self, id, description, type):
        self.id = id
        self.description = description
        self.type = type  # 1 coop, 2 stable, 3 pens

    def __str__(self):
        return (
            f"Cage:\n"
            f"  ID: {self.id}\n"
            f"  Description: {self.description}\n"
            f"  Type: {self.type}"
        )
