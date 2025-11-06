class Egg:
   
    def __init__(self, size, color="white"):
        self.size = str(size).upper()
        self.color = color

    def describe(self):
        return f"A {self.size} {self.color} egg."



    
    