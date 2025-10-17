while True:
    print("=" * 45)
    print("         MULTIPLICATION TABLE MAKER    ")
    print("=" * 45)

    number = int(input("\nEnter a number to see its multiplication table: "))

    print(f"\n Multiplication Table of {number}\n" + "    -" * 4)

    for i in range(1, 13):
        result = number * i
        print(f"    | {number:>2} x {i:>2} = {result:>4} |")

    print("    -" * 4)

    while True:
        option = input("\nDo you want to continue? (Y = YES / N = NO): ").strip().lower()
        if option in ["y", "n"]:
            break
        print("Invalid option! Please enter Y or N.")

    if option == "n":
        print("Thanks for using the program!")
        break
    print("\n")