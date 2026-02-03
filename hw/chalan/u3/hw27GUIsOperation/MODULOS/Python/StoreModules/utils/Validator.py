class Validator:
    @staticmethod
    def validate(value, fieldType):
        if fieldType == "string":
            return isinstance(value, str) and value.strip() != ""
        if fieldType == "int":
            return value.isdigit()
        if fieldType == "float":
            try:
                float(value)
                return True
            except:
                return False
        return False
