class Cage:
    def __init__(self, id, description, type, location):
        self.id = id
        self.description = description
        self.type = type  # 1=coop, 2=table, 3=pens
        self.location = location
    
    def __str__(self):
        return f"Cage{{id={self.id}, description={self.description}, type={self.type}, location={self.location}}}"