while True:
    table_number = int(input("Enter the number of the multiplication table you want (1 to 12): "))

    if 1 <= table_number <= 12:
        print(f"\nTable of {table_number}")
        print("-" * 15)
        for i in range(1, 13):
            print(f"{table_number} x {i} = {table_number * i}")
    else:
        print("Invalid number. Please enter a number between 1 and 12.")
        continue

    continue_choice = input("\nDo you want to generate another table? (y/n): ").lower()
    if continue_choice != "y":
        print("Thank you! Program finished.")
        break
