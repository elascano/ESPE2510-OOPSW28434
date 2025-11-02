class ChickenCoop:
    def __init__(self, coop_id, farmer_id):
        self.coop_id = coop_id
        self.farmer_id = farmer_id  # ID del granjero dueño
        self.chickens = []  # Lista de objetos Chicken
    
    def add_chicken(self, chicken):
        self.chickens.append(chicken)
    
    def to_dict(self):
        return {
            'coop_id': self.coop_id,
            'farmer_id': self.farmer_id,
            'chickens': [chicken.to_dict() for chicken in self.chickens]
        }
    
    @classmethod
    def from_dict(cls, data):
        coop = cls(data['coop_id'], data['farmer_id'])
        from model.chicken import Chicken
        for chicken_data in data['chickens']:
            coop.add_chicken(Chicken.from_dict(chicken_data))
        return coop
    
    def __str__(self):
        chickens_info = "\n".join([f"  {chicken}" for chicken in self.chickens])
        return f"ChickenCoop(id={self.coop_id}, owner={self.farmer_id})\n{chickens_info}"