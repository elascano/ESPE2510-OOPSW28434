while True:
	numeroIngresado = int(input("Enter the multiplication table number you want to view: "))
	print(f"\nTabla del numero {numeroIngresado}\n")
	for i in range(1,13):
		resultado = numeroIngresado * i
		print(f"{numeroIngresado} * {i} = {resultado}")
	
	print("\n Do you want to re-enter another number?")
	print("- Enter 1 to re-enter another number")
	print("- Enter 0 to exit")
	while True:
		numeroRepeticion = int(input("\nEnter an option: "))
		if numeroRepeticion != 1 and numeroRepeticion != 0:
			print("ERROR, invalid option. Please try again")
		else:
			break
	if numeroRepeticion == 1:
		continue
	else:
		print("Leaving now..")
		break 

