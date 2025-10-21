def imprimir_tabla(numero):
    print(f"\nTabla de multiplicar del {numero}:")
    print("-" * 20)
    for i in range(1, 13):
        resultado = numero * i
        print(f"{numero} x {i} = {resultado}")

def main():
    while True:
        try:
            num = int(input("Ingresa un número del 1 al 12 (0 para salir): "))
            if num == 0:
                print("¡Adiós!")
                break
            elif 1 <= num <= 12:
                imprimir_tabla(num)
            else:
                print("Por favor, ingresa un número entre 1 y 12.")
        except ValueError:
            print("Eso no es un número válido. Intenta de nuevo.")

if __name__ == "__main__":
    main()
