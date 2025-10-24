def display_multiplication_table(number):
    """
    Displays the multiplication table of the given number from 1 to 10.
    """
    print(f"\nMultiplication Table {number}")
    for multiplier in range(1, 11):
        print(f"{number} x {multiplier} = {number * multiplier}")

def multiplication_tables_program():
    """
    Main function that allows the user to view multiplication tables.
    """
    while True:
        try:
            number = int(input("\nEnter the number of the table 1-10: "))
            if 1 <= number <= 10:
                display_multiplication_table(number)
            else:
                print("Please enter a number between 1 and 10.")
                continue
        except ValueError:
            print("Please enter a valid number.")
            continue

        user_response = input("Do you want to see another table? (y/n): ").strip().lower()
        if user_response != 'y':
            print("\nGoodbye thanks")
            break


if __name__ == "__main__":
    multiplication_tables_program()
