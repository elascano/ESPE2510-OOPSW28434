import json
import os

class JsonFileUtil:
    _instance = None # static

    def __new__(cls): # 
        if cls._instance is None:
            # I
            cls._instance = super(JsonFileUtil, cls).__new__(cls)
        return cls._instance # G

    def save(self, photographer):
        data = {
            "name": photographer.name,
            "specialty": photographer.specialty,
            "experience": photographer.experience,
            "hourlyRate": photographer.hourly_rate
        }
        with open("photographers.json", "a", encoding="utf-8") as file:
            file.write(json.dumps(data) + "\n")