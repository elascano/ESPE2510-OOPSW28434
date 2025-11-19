from sound_mixer import SoundMixer
from sound_mixer_manager import SoundMixerManager

def read_mixers(manager):
    print("\n--- Reading Sound Mixers ---")
    if not manager.mixers:
        print("No mixers found.")
    else:
        for i, mixer in enumerate(manager.mixers, start=1):
            print(f"{mixer}")
    print()

def add_mixer(manager):
    print("\n--- Add Sound Mixer ---")

    new_id = 1 if not manager.mixers else max(manag.id for manag in manager.mixers) + 1

    name = input("Enter mixer name: ")
    volume = input("Enter volume level: ")
    bass = input("Enter bass level: ")
    treble = input("Enter treble level: ")

    mixer = SoundMixer(new_id, name, volume, bass, treble)
    manager.add_mixer(mixer)

    manager.save_to_json()

    print("Mixer added successfully.\n")

def main():
    manager = SoundMixerManager("mixers_data.json")
    manager.load_from_json()

    while True:
        print("------ SOUND MIXER MENU ------")
        print("1. Read mixers")
        print("2. Add a mixer")
        print("3. Exit")

        choice = input("Choose an option (1-3): ")

        if choice == "1":
            read_mixers(manager)
        elif choice == "2":
            add_mixer(manager)
        elif choice == "3":
            print("Exiting program.")
            break
        else:
            print("Invalid input. Try again.\n")

if __name__ == "__main__":
    main()