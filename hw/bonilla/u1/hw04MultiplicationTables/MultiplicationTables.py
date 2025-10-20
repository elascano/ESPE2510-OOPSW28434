def show_table(number):
    print(f"\n{'-'*31}")
    print(f"Multiplication Table of {number}".center(31))
    print(f"{'-'*31}")
    for i in range(1, 13):
        print(f"{number:>2} x {i:>2} = {number*i:>3}")
    print(f"{'-'*31}\n")

def ask_for_table():
    """Asks for a valid table number (1–12) and returns it."""
    while True:
        try:
            choice = int(input("Enter the number of the table you want to see: "))
            if 1 <= choice <= 12:
                return choice
            else:
                print("\nError: Please choose a number between 1 and 12.\n")
        except ValueError:
            print("\nError: You must enter a valid number (no letters or symbols).\n")

def main():
    print("------ Welcome to the Multiplication Table Program ------")
    print("You can choose a table from 1 to 12.\n")

    while True:
        number = ask_for_table()
        show_table(number)

        again = input("Do you want to see another table? (yes/no): ").strip().lower()
        if again not in ("yes", "y"):
            print("\nThank you for using the program.\n")
            break
        print() 

if __name__ == "__main__":
    main()