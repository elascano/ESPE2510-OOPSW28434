class Chicken:
    def __init__(self, id, name, color, age, is_molting):
       
        self.id = str(id)
        self.name = name
        self.color = color
        self._age = self._set_age(age)
        self.is_molting = bool(is_molting)

    
    @property
    def age(self):
        return self._age

    def _set_age(self, age):
        """Internal validation for age."""
        if isinstance(age, int) and age >= 0:
            return age
        else:
            raise ValueError("Age must be a non-negative integer.")

    def cluck(self):
        
        return f"The chicken {self.name} says 'Cluck!'"

    def to_dict(self):
        return {
            "id": self.id,
            "name": self.name,
            "color": self.color,
            "age": self.age, 
            "is_molting": self.is_molting
        }

    
    @classmethod
    def from_dict(cls, data):
        
        return cls(
            data["id"],
            data["name"],
            data["color"],
            data["age"],
            bool(data.get("is_molting", False))
        )