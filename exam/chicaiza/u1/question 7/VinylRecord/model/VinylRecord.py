
class VinylRecord:
    def __init__(self, id, name_song, year, duration):
        self.id = id
        self.name_song= name_song
        self.year = year
        self.duration = duration
        

    def to_dict(self):
        return {
            "id": self.id,
            "name": self.name,
            "year":self.year,
            "duration":self.duration,
        }

    @staticmethod
    def from_dict(data):
        return VinylRecord(
            data["id"],
            data["name"],
            data["duration"],

        )
    
import json
