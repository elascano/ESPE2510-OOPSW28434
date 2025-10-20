while True:
    try:
        print("    Choose a number between one and twelve")
        number = int(input("    Which number's table do you want? "))
        if number < 1 or number > 12:
            print("Please enter a number between 1 and 12.\n")
            continue
    except ValueError:
        print("Please enter a valid number.\n")
        continue

    print("\n-  -  -  -  -  -  -  -  -  -  -  -  -  -")
    print(f"       Multiplication table of {number}       ")
    print("-  -  -  -  -  -  -  -  -  -  -  -  -  -\n")

    for i in range(1, 13):
        print(f" ♦   {number} x {i} = {number * i}")

    print("\n-  -  -  -  -  -  -  -  -  -  -  -  -  -")

    choice = input("\nDo you want to use the program again? [y / n] ").lower()
    if choice != 'y':
        print("\nBye, bye!")
        break
    else:
        print("\n")
