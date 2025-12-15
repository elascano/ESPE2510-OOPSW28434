class Monitor:
    
    def __init__(self, id=0, brand="", dateOfFabrication = 0,age=0):
        self._id = id
        self._brand = brand
        self._dateOfFabrication = dateOfFabrication
        self._age = age

    @property
    def id(self):
        return self._id

    @id.setter
    def id(self, value):
        self._id = value

    @property
    def brand(self):
        return self._brand

    @brand.setter
    def brand(self, value):
        self._brand = value
        
    @property
    def dateOfFabrication(self):
        return self.dateOfFabrication

    @dateOfFabrication.setter
    def dateOfFabrication(self, value):
        self.dateOfFabrication = value
    
    @property
    def age(self):
        return self._age
    
    @age.setter
    def age(self, value):
        self._age = value
    def __str__(self):
        return (f"Contact{{\n"
                f"id={self._id}, \n"
                f"brand={self._brand}, \n"
                f"dateOfFabrication={self._dateOfFabrication}, \n"
                f"age={self._age}}}"
                )