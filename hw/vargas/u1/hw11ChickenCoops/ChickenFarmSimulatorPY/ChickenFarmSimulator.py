from Chicken_model import Chicken
from ChickenCoop_model import ChickenCoop
def main():
    print("This is my Chicken Farm Simulator By Vargas César")
    
    chicken2 = Chicken(0, "Maruja", "White", 1, True)
    
    owner = "César Vargas"
    id = 1
    name = "Lucy"
    color = "White and Brown"
    age = 2
    is_molting = False  
    chicken = Chicken(id, name, color, age, is_molting)
    all_chickens = [
        chicken, chicken2,
        Chicken(3, "Lola", "White", 2, True),
        Chicken(4, "Pipa", "Black", 1, False),
        Chicken(5, "Pancracia", "Brown", 4, False),
        Chicken(6, "Pancha", "Gray", 2, True),
        Chicken(7, "Turuleca", "Brown", 1, False),
        Chicken(8, "Manola", "White", 3, False),
        Chicken(9, "Zoe", "Black", 2, False),
        Chicken(10, "Lula", "Brown", 1, True),
    ]
    coop1 = ChickenCoop(name="Chicken Coop A")
    coop2 = ChickenCoop(name="Chicken Coop B")

    for i in range(5):
        coop1.add_chicken(all_chickens[i]) 

    for i in range(5, 10):
        coop2.add_chicken(all_chickens[i])
    print("\n\n=== Showing Coop Contents ===")
    coop1.show_coop_contents()
    coop2.show_coop_contents()

if __name__ == "__main__":
    main()