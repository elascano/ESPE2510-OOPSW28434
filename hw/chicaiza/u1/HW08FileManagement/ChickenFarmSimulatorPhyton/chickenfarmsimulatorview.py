from Chicken import Chicken
from Coop import Coop
import csv

def save_chickens_to_csv(chickens, filename="chickens.csv"):
    with open(filename, mode="w", newline="", encoding="utf-8") as file:
        writer = csv.writer(file)
        writer.writerow(["Name", "Color", "Age", "IsMolting"])
        for ch in chickens:
            writer.writerow([ch._name, ch._color, ch._age, ch._is_molting])
    print(f"Data saved in {filename}")

def main():
    chickens = [
        Chicken("Lola", "Brown", 2, False),
        Chicken("Pepa", "White", 1, True),
        Chicken("Cuca", "Black", 3, False),
        Chicken("Tita", "Gray", 2, True),
        Chicken("Chispa", "White", 1, False),
        Chicken("Luna", "Brown", 4, True),
        Chicken("Rosita", "Gray", 2, False),
        Chicken("Clara", "White", 3, False),
        Chicken("Mimi", "Black", 2, True),
        Chicken("Coco", "Brown", 1, False)
    ]

    coop1 = Coop("Coop North")
    coop2 = Coop("Coop South")

    for i in range(5):
        coop1.add_chicken(chickens[i])
    for i in range(5, 10):
        coop2.add_chicken(chickens[i])

    coop1.show_chickens()
    coop2.show_chickens()

    all_chickens = coop1.chickens + coop2.chickens
    save_chickens_to_csv(all_chickens)

if __name__ == "__main__":
    main()