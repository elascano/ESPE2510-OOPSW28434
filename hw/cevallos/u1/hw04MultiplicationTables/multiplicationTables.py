while True:
    num = int(input("Ingresa un numero para ver su tabla de multiplicar: "))
    
    for i in range(1, 13):

     resultado = num * i
     print(f"{num}X{i}={resultado}")


    print("Quieres ver otra tabla de multiplicar? (s/n)")
    
    respuesta = input("Ingresa s para si o n para no: ")
    
    if respuesta.lower() == 'n':

     break






