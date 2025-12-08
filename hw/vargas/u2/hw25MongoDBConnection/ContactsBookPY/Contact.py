class Contact:
    
    def __init__(self, id=0, firstName="", lastName="", age=0, typeOfContact="Unknown", sex="", hobbies=None, comments=""):
        self._id = id
        self._firstName = firstName
        self._lastName = lastName
        self._age = age
        self._typeOfContact = typeOfContact # Family, Friend, Job, Unknown
        self._sex = sex # male, female
        self._hobbies = hobbies if hobbies is not None else [] 
        self._comments = comments

    @property
    def id(self):
        return self._id

    @id.setter
    def id(self, value):
        self._id = value

    @property
    def firstName(self):
        return self._firstName

    @firstName.setter
    def firstName(self, value):
        self._firstName = value
        
    @property
    def lastName(self):
        return self._lastName

    @lastName.setter
    def lastName(self, value):
        self._lastName = value

    @property
    def age(self):
        return self._age

    @age.setter
    def age(self, value):
        self._age = value
        
    @property
    def typeOfContact(self):
        return self._typeOfContact

    @typeOfContact.setter
    def typeOfContact(self, value):
        self._typeOfContact = value
        
    @property
    def sex(self):
        return self._sex

    @sex.setter
    def sex(self, value):
        self._sex = value
        
    @property
    def hobbies(self):
        return self._hobbies

    @hobbies.setter
    def hobbies(self, value):
        self._hobbies = value
    @property
    def comments(self):
        return self._comments

    @comments.setter
    def comments(self, value):
        self._comments = value
        
    def __str__(self):
        return (f"Contact{{\n"
                f"id={self._id}, \n"
                f"firstName={self._firstName}, \n"
                f"lastName={self._lastName}, \n"
                f"age={self._age}, \n"
                f"typeOfContact={self._typeOfContact}, \n"
                f"sex={self._sex}, \n"
                f"hobbies={self._hobbies}, \n"
                f"comments={self._comments}}}")
