import json
from sound_mixer import SoundMixer

class SoundMixerManager:
    def __init__(self, json_file):
        self.json_file = json_file
        self.mixers = []

    def add_mixer(self, mixer):
        self.mixers.append(mixer)

    def save_to_json(self):
        data = [mix.to_dict() for mix in self.mixers]
        with open(self.json_file, "w") as file:
            json.dump(data, file, indent=4)
        print("Mixers successfully written to JSON.")

    def load_from_json(self):
        try:
            with open(self.json_file, "r") as file:
                data = json.load(file)
            self.mixers = [SoundMixer.from_dict(item) for item in data]
            print("Mixers successfully loaded from JSON.")
        except FileNotFoundError:
            print("JSON file not found. Starting empty.")