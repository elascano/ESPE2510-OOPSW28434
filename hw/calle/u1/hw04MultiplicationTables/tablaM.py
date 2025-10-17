def print_multiplication_table(table_number):
    print(f"\nMultiplication Table of {table_number}")
    print("=" * 25)
    
    for i in range(1, 13):
        result = table_number * i
        print(f"{table_number} × {i:2} = {result:3}")
    
    print("=" * 25)

def main():
    print("Welcome to the Multiplication Tables Program!")
    
    while True:
        try:
            table_number = int(input("\nWhich multiplication table would you like to see? (1-12): "))
            
            if 1 <= table_number <= 12:
                print_multiplication_table(table_number)
            else:
                print("Please enter a number between 1 and 12.")
                continue
                
        except ValueError:
            print("Please enter a valid number.")
            continue
        
        while True:
            another_table = input("\nWould you like to see another multiplication table? (yes/no): ").lower().strip()
            
            if another_table in ['yes', 'y', 'no', 'n']:
                break
            else:
                print("Please enter 'yes' or 'no'.")
        
        if another_table in ['no', 'n']:
            break
    
    print("\nThat's the multiplication table!")

if __name__ == "__main__":
    main()