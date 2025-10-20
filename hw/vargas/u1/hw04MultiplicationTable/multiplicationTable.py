while True:
    while True:
        try:
            number = int(input("Enter a number from 1 to 12 to see its multiplication table:"))
            if 1 <= number <= 12:
                break  
            else:
                print("Please enter a valid number in range of 1 and 12.")
        except ValueError:
            print(" Please enter a valid integer.")
    print("===================================")
    print(f"\nMultiplication table of number:",number)
    for i in range(1, 13):
        result = number * i
        print(f"|| {number:2} x {i:2} = {result:3} ||")
    print("===================================")
    again = input("\nDo you want to see another multiplication table? (y/n): ").strip().lower()
    if again != 'y':
        break