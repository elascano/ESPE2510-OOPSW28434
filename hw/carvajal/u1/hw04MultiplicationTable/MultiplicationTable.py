number = int(input("Which multiplication table would you like to see? "))

print(f"\nTabla del {number}\n")
print("Number| Result")
print("-------------------")

for i in range(1, 11):
    print(f"{number:>6} x {i:<2} = {number * i:<3}")