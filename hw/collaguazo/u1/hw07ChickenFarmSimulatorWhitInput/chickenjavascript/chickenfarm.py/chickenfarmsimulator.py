from Chiken import Chicken

print("Welcome to the Chicken Farm Simulator") 

id = 1
name = "Lucy"
color = "White and Brown"
age = 2
isMolting = False

chicken = Chicken(id, name, color, age, isMolting)

print("For explample, the chicken can do the following actions:")
print(f"The chicken is: \n{chicken}")
chicken.doStuff()

print("\nNow is your turn. Register a chicken")
id = int(input("Enter chicken ID: "))
name = input("Enter chicken name: ")
color = input("Enter chicken color: ")
age = int(input("Enter chicken age: "))
isMolting = input("Is the chicken molting? (yes/no): ").lower() == "yes"

chicken2 = Chicken(id, name, color, age, isMolting)
print(f"\nYou have registered the following chicken: \n{chicken2}")
print("Now, watch the chicken do its actions:")
chicken2.doStuff()
user_option = input("Do you want to see the chicken do its actions again? (yes/no): ").strip().lower()
while user_option == "yes":
    chicken2.doStuff()
    user_option = input("Do you want to see the chicken do its actions again? (yes/no): ").strip().lower()
print("Thank you for using the Chicken Farm Simulator.!")
