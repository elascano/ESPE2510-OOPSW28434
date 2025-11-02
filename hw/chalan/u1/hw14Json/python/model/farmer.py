class Farmer:
    def __init__(self, farmer_id, name):
        self.farmer_id = farmer_id
        self.name = name
        self.coop_ids = []  # Lista de IDs de gallineros que posee
    
    def add_coop(self, coop_id):
        if coop_id not in self.coop_ids:
            self.coop_ids.append(coop_id)
    
    def to_dict(self):
        return {
            'farmer_id': self.farmer_id,
            'name': self.name,
            'coop_ids': self.coop_ids
        }
    
    @classmethod
    def from_dict(cls, data):
        farmer = cls(data['farmer_id'], data['name'])
        farmer.coop_ids = data['coop_ids']
        return farmer
    
    def __str__(self):
        return f"Farmer ID: {self.farmer_id}, Name: {self.name}, Coops: {len(self.coop_ids)}"