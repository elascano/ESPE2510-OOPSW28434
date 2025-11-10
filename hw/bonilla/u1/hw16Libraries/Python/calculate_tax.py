from IncomeTaxPython import calculate_tax  

def main():
    while True:
        print("====== INCOME TAX CALCULATOR ======")
        print("1. Calculate Income Tax")
        print("2. Exit")

        try:
            option = int(input("Choose an option: "))
        except ValueError:
            print("Please enter a valid number.\n")
            continue

        if option == 1:
            try:
                salary = float(input("Enter your monthly salary: $"))
                expenses = float(input("Enter your monthly expenses: $"))
                tax = calculate_tax(salary, expenses)
                print("------------------------------------")
                print(f"Your annual income tax is: ${tax:.2f}")
                print("------------------------------------\n")
            except ValueError:
                print("Invalid number. Please try again.\n")

        elif option == 2:
            print("Goodbye!")
            break
        else:
            print("Invalid option. Try again.\n")

if __name__ == "__main__":
    main()
