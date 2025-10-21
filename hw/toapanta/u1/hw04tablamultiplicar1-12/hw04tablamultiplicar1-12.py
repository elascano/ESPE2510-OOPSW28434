
for i in range(1, 13):  # del 1 al 12
    print(f"\nTabla del {i}:")
    print("-" * 15)
    for j in range(1, 13):
        resultado = i * j
        print(f"{i} x {j} = {resultado}")