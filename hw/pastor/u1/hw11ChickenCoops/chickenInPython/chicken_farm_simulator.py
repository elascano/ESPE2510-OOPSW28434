from Chiken import Chicken
from ChickenCoop import ChickenCoop

print("Welcome to the Chicken Farm Simulator by Mathews Pastor!")

chicken_coop1 = ChickenCoop(1)
chicken_coop2 = ChickenCoop(2)
chicken_coop3 = ChickenCoop(3)


chicken1 = Chicken(1,"Lucy", "White and Brown", "2", False)
chicken_coop1.add_chicken(chicken1)
chicken2 = Chicken(2,"Clueca", "Brown", "2", False)
chicken_coop1.add_chicken(chicken2)
chicken3 = Chicken(3,"Maruja", "White", "1", True)
chicken_coop1.add_chicken(chicken3)
chicken4 = Chicken(4,"Gusepa", "Yellow", "2", False)
chicken_coop1.add_chicken(chicken4)
chicken5 = Chicken(5,"Martha", "Black and White", "1", False)
chicken_coop1.add_chicken(chicken5)
chicken6 = Chicken(6,"Pepa", "Pig", "3", True)
chicken_coop1.add_chicken(chicken6)


chicken7 = Chicken(7,"Nora", "Red", "1", False)
chicken_coop2.add_chicken(chicken7)
chicken8 = Chicken(8,"Pipa", "Brown and Red", "2", False)
chicken_coop2.add_chicken(chicken8)
chicken9 = Chicken(9,"Dolores", "White", "1", True)
chicken_coop2.add_chicken(chicken9)
chicken10 = Chicken(10,"Josefa", "Black", "1", False)
chicken_coop2.add_chicken(chicken10)

chicken_coop1.show_chickens()
chicken_coop2.show_chickens()
chicken_coop3.show_chickens()


