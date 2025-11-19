class SoundMixer:
    def __init__(self, id, name, volume, bass, treble):
        self.id = id
        self.name = name
        self.volume = volume
        self.bass = bass
        self.treble = treble

    def to_dict(self):
        return {
            "id": self.id,
            "name": self.name,
            "volume": self.volume,
            "bass": self.bass,
            "treble": self.treble
        }

    @classmethod
    def from_dict(cls, data):
        return cls(
            data["id"],
            data["name"],
            data["volume"],
            data["bass"],
            data["treble"]
        )

    def __str__(self):
        return (
            f"ID: {self.id}, Name: {self.name}, Volume: {self.volume}, "
            f"Bass: {self.bass}, Treble: {self.treble}"
        )