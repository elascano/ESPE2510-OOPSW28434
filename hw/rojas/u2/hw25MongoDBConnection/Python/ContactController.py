from Contact import ContactModel
from ValidationUtils import ValidationUtils

class ContactController:
    def __init__(self):
        self.model = ContactModel()

    def getAge(self, birthDate):
        return ValidationUtils.calculateAge(birthDate)

    def addContact(self, firstName, lastName, birthDate, age, cType, sex, hobbies, comments):
        isValid, message = ValidationUtils.validateNames(firstName, lastName)
        if not isValid:
            return False, message

        document = {
            "firstName": firstName,
            "lastName": lastName,
            "birthDate": str(birthDate),
            "age": age,
            "type": cType,
            "sex": sex,
            "hobbies": hobbies,
            "comments": comments
        }

        try:
            if self.model.save(document):
                return True, "Contact saved successfully!"
            else:
                return False, "Error connecting to Database"
        except Exception as e:
            return False, f"Error saving contact: {e}"