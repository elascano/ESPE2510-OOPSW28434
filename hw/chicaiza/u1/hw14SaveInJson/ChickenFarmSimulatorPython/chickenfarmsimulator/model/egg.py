class Egg:
    def __init__(self, id: int):
        self.id = id
    
    def to_dict(self):
        return {'id': self.id}
    
    @classmethod
    def from_dict(cls, data):
        return cls(id=data['id'])