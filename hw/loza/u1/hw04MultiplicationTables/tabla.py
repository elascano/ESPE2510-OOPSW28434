numero = int(input("Ingresa un número del 1 al 10: "))

for i in range(1, 11):
    for j in range(1, 11):
        if i == numero:
            print(f"{i} x {j} = {i * j}")
