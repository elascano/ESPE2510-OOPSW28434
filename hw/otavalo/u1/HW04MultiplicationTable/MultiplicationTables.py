print("✨ Welcome to the Multiplication Tables ✨\n")

def printTable(number):
    print("+" + "-"*28 + "+")
    print(f"| Multiplication Table of {number:2}|")
    print("+" + "-"*28 + "+")
    
    for i in range(1, 13):
        result = number * i
        print(f"|   {number:2} x {i:2} = {result:3}{" " * 10}|")
    print("+" + "-"*28 + "+\n")

def askUserForNumber():
    while True:  
        userInput = input("👉 Which multiplication table would you like to see? (1–12): ")

        if userInput.isdigit():
            number = int(userInput)
            if 1 <= number <= 12:
                printTable(number)
            else:
                print("Please choose a number between 1 and 12.\n")
                continue
        else:
            print("Invalid input. Please enter a number from 1 to 12.\n")
            continue

 
        while True:
            decision = input("Do you want to see another table? (1 = Yes, 0 = No): ")

            if decision == "1":
                print()          
                break            

            if decision == "0":
                print("\n👋 Goodbye! Thanks for using the program.")
                return        


            else:
                print("Invalid option. Please enter 1 or 0.\n")


askUserForNumber()

