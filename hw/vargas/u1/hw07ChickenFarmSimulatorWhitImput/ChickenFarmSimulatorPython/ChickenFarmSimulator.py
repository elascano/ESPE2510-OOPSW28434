from Chicken_model import Chicken

print("Welcome to the Chicken Farm Simulator by Vargas Cesar \n")

id = 1
name = "Lucy"
color = "White and Brown"
age = 2
isMolting = False

chicken = Chicken(id, name, color, age, isMolting)

chicken.doStuff()
enterNewChicken = "yes"
while enterNewChicken == "yes":
 print("\nNow is your moment to Register a new chicken")
 id = int(input("Enter chicken ID: "))
 name = input("Enter chicken name: ")
 color = input("Enter chicken color: ")
 ageInRange = False
 while not ageInRange:
     age = int(input("Enter chicken age: "))
     if 0 < age < 11:
         ageInRange = True
     else:
        print("Please enter a valid age between 1 and 10.")
 isMolting = input("Is the chicken molting? (yes/no): ").lower() == "yes"

 chicken2 = Chicken(id, name, color, age, isMolting)
 print(f"\nYou have registered a new chicken:\n{chicken2._str_()}\n")
 print("Watch the chicken do its actions:")
 chicken2.doStuff()
 user_option = input("Do you want to see the chicken do its actions again? (yes/no): ").strip().lower()
 while user_option == "yes":
    chicken2.doStuff()
    user_option = input("Do you want to see the chicken do its actions again? (yes/no): ").strip().lower()
 enterNewChicken = input("\nDo you want to register a new chicken? (yes/no): ").strip().lower()
print("Ending simulation")