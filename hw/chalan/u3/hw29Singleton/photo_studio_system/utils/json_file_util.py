import json

class JsonFileUtil:
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(JsonFileUtil, cls).__new__(cls)
        return cls._instance

    def save(self, photographer):
        data = {
            "name": photographer.name,
            "specialty": photographer.specialty,
            "experience": photographer.experience,
            "hourlyRate": photographer.hourly_rate
        }

        with open("photographers.json", "a", encoding="utf-8") as file:
            file.write(json.dumps(data) + "\n")
