class Chicken:
    def __init__(self, id, name, color, age, isMolting):
        self.id = id
        self.name = name
        self.color = color
        self.age = age
        self.isMolting = isMolting

    def __str__(self):
        return f"Chicken: \nid --> \t\t{self.id} \nname --> \t{self.name} \ncolor --> \t{self.color} \nage --> \t{self.age} \nisMolting --> \t{self.isMolting}"

    def id(self):
        return self._id
    
    def id(self, id):
        self._id = id

    def name(self):
        return self._name   
    
    def name(self, name):
        self._name = name   
    
    def color(self):
        return self._color
    
    def color(self, color):
        self._color = color 
    
    def age(self):
        return self._age   
    
    def age(self, age):
        self._age = age 
    
    def isMolting(self):
        return self._isMolting
    
    def isMolting(self, isMolting):
        self._isMolting = isMolting