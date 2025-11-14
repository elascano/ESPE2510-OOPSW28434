from chicken_model import Chicken
from chickenCoop_model import ChickenCoop

class ChickenFarmSimulator:
    def __init__(self):
        self.coop = ChickenCoop()

    def showMenu(self):
        print("\n Welcome to my Chicken Farm Simulator ")
        print("-----------------------------------------")
        print("1. Add Chicken")
        print("2. Visualize Chickens")
        print("3. Search Chicken by ID")
        print("4. Edit Chicken")
        print("5. Delete Chicken by ID")
        print("6. Exit")
        print("-----------------------------------------")

    def askChickensNumber(self):
        while True:
            try:
                return int(input("How many chickens do you want to save? "))
            except ValueError:
                print(" Please enter a valid number.")

    def askChickenInformation(self):
        print("\n New Chicken Information")
        name = input("Name: ")
        color = input("Color: ")
        while True:
            try:
                age = int(input("Age in years: "))
                break
            except ValueError:
                print( "Please enter a valid number for age.")

        while True:
            isMoltingInput = input("Is molting? (y/n): ").lower()
            if isMoltingInput in ['y', 'n']:
                break
            print("Please enter 'y' for Yes or 'n' for No.")
            
        is_molting_value = True if isMoltingInput == "y" else False

        return Chicken(name, color, age, is_molting_value)

    def addChicken(self):
        amount = self.askChickensNumber()
        for _ in range(amount):
            chicken = self.askChickenInformation()
            self.coop.addChicken(chicken)
            print(f"Chicken '{chicken.name}' added with ID: {chicken.id}") 
        self.coop.saveToJson()
        print("Chickens saved successfully!")

    def showChicken(self, chickenList):
        if not chickenList:
            print("There are no chickens to display.")
            return

        print(f"\nIt founds {len(chickenList)} chickens:\n")

        header =    "| ID |    Name  |   Color  | Age | Molting |"
        separator = "+----+----------+----------+-----+---------+"
        print(separator)
        print(header)
        print(separator)
        
        for chicken in chickenList:
            moltingStatus = "Yes" if chicken.is_molting else "No" 
            print(f"| {chicken.id:<2} | {chicken.name:<8} | {chicken.color:<8} | {chicken.age:<3} | {moltingStatus:<7} |")
            
        print(separator)

    def visualizeAllChickens(self):
        self.coop.loadFromJson() 
        self.showChicken(self.coop.getChickens())
    
    def searchChicken(self):
        chickenId = input("Enter the ID of the chicken to search: ")
        chicken = self.coop.findChickenById(chickenId)
        if chicken:
            self.showChicken([chicken])
        else:
            print(f"Chicken with ID '{chickenId}' not found.")

    def editChicken(self):
        chickenId = input("Enter the ID of the chicken to edit: ")
        chicken = self.coop.findChickenById(chickenId)

        if chicken:
            newName = chicken.name
            newColor = chicken.color
            newAge = chicken.age
            newIsMolting = chicken.is_molting 
            while True:
                print(f"\n--- Editing Chicken ID: {chicken.id} ({chicken.name}) ---")
                print("Current Values:")
                print(f"1. Name: {newName}")
                print(f"2. Color: {newColor}")
                print(f"3. Age: {newAge}")
                print(f"4. Molting: {'Yes' if newIsMolting else 'No'}")
                print("5. Save Changes and Exit")
                print("6. Cancel and Exit")
                
                editOption = input("Which field do you want to edit? (1-6): ")

                if editOption == '1':
                    newName = input("New Name: ") or newName
                    print("Name updated.")
                elif editOption == '2':
                    newColor = input("New Color: ") or newColor
                    print("Color updated.")
                elif editOption == '3':
                    while True:
                        try:
                            newAgeInput = input("New Age (years): ")
                            if newAgeInput:
                                newAge = int(newAgeInput)
                            print("Age updated.")
                            break
                        except ValueError:
                            print("Please enter a valid number for age.")
                elif editOption == '4':
                    while True:
                        moltingInput = input(f"Is molting? (y/n) (current: {'y' if newIsMolting else 'n'}): ").lower()
                        if moltingInput in ['y', 'n']:
                            newIsMolting = True if moltingInput == 'y' else False
                            print("Molting status updated.")
                            break
                        print("Please enter 'y' for Yes or 'n' for No.")
                elif editOption == '5':
                    if self.coop.editChicken(chickenId, newName, newColor, newAge, newIsMolting):
                        print(f"Chicken with ID {chickenId} updated successfully!")
                    else:
                        print(f" Error updating chicken with ID {chickenId}.")
                    break
                elif editOption == '6':
                    print(" Editing cancelled. No changes saved.")
                    break
                else:
                    print(" Invalid option, please choose a number between 1 and 6.")
        else:
            print(f" Chicken with ID '{chickenId}' not found.")

    def deleteChicken(self):
        chickenId = input("Enter the ID of the chicken to delete: ")
        if self.coop.deleteChicken(chickenId):
            print(f"Chicken with ID {chickenId} deleted successfully!")
        else:
            print(f"Chicken with ID '{chickenId}' not found.")

    def run(self):
        while True:
            self.showMenu()
            option = input("Choose an option: ")

            if option == "1":
                self.addChicken()
            elif option == "2":
                self.visualizeAllChickens()
            elif option == "3":
                self.searchChicken()
            elif option == "4":
                self.editChicken()
            elif option == "5":
                self.deleteChicken()
            elif option == "6":
                print(" Bye!")
                break
            else:
                print("Invalid option, please try again.")