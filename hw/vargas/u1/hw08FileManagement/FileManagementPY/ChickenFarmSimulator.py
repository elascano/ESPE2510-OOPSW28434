from Chicken_model import Chicken
from SaveInCSV import File
print("Welcome to the Chicken Farm Simulator by Vargas Cesar \n")

id = 1
name = "Lucy"
color = "White and Brown"
age = 2
isMolting = False
header =["id","name","color","age","isMolting"]
file = File("MyChickens.csv", header)
chicken = Chicken(id, name, color, age, isMolting)
chicken2 = Chicken(2, "Maruja", "Black and White", 3, True)
allChickens = [
        chicken,
        chicken2,
        Chicken(3, "Lola", "White", 2, True),
        Chicken(4, "Pipa", "Black", 1, False),
        Chicken(5, "Pancracia", "Brown", 4, False),
        Chicken(6, "Pancha", "Gray", 2, True),
        Chicken(7, "Turuleca", "Brown", 1, False),
        Chicken(8, "Manola", "White", 3, False),
        Chicken(9, "Zoe", "Black", 2, False),
        Chicken(10, "Lula", "Brown", 1, True),
    ]


data = []
dataToDictionary = []
for chicken in allChickens:
    dataToDictionary.append(chicken.__dict__)

file.saveDatainCSV(file.Filename, file.headers, dataToDictionary)
