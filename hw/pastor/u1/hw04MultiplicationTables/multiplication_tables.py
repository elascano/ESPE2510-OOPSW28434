print("***Welcome to Mathews Pastor's Multiplication Table***")
while True:
    try:
        user_number = int(input("Enter the number that said you were viewing the table: "))
        print(f"---Multiplication Table of {user_number}---")
        for i in range(1, 12):
            result = i * user_number
            print(f"{i} x {user_number} = {result}")
    except ValueError:
        print("Invalid number entered. Please enter again.")
        continue
    user_answer = input("Do you want to continue? (\"y\" for YES/any other letter not to): ")
    if user_answer != 'y' and user_answer != 'Y':
        print("Thank you for using the multiplication table program. Goodbye!")
        break   


