from Chicken import  Chicken

def main():
    # Usando constructor con parámetros
    chicken1 = Chicken("Lucy", "Brown", 3, True)
    
    # Usando constructor vacío y setters
    chicken2 = Chicken()
    chicken2.set_name("Molly")
    chicken2.set_color("White")
    chicken2.set_age(1)
    chicken2.set_is_molting(False)

    # Imprimir datos de las gallinas
    print(chicken1)
    print(chicken2)

if __name__ == "__main__":
    main()