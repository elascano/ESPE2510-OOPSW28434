menu = 1

while menu == 1:
    print("===============================")
    print("      MULTIPLICATION TABLE      ")
    print("===============================")

    numero = int(input("Enter a number (1–12): "))

    if 1 <= numero <= 12:
        print(f"\nTable of {numero}")
        print("-------------------------------")
        for i in range(1, 13):
            print(f"{numero} x {i} = {numero * i}")
        print("\nEnd of table.")
    else:
        print("\nNumber not within the range (1–12).")

    menu = int(input("\nDo you want to try another number? (1=Yes / 2=No): "))

print("\nThanks for using the program.")
