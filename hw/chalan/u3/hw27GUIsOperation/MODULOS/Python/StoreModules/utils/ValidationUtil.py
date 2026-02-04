class ValidationUtil:
    @staticmethod
    def validate(fields, entries):
        data = {}
        for field in fields:
            name = field["name"]
            fieldType = field["type"]
            rawValue = entries[name].get().strip()

            if not rawValue:
                raise ValueError(f"{name} is required")

            try:
                if fieldType == int:
                    value = int(rawValue)
                elif fieldType == float:
                    value = float(rawValue)
                else:
                    value = rawValue
            except:
                raise ValueError(f"{name} must be {fieldType.__name__}")

            data[name] = value

        return data
