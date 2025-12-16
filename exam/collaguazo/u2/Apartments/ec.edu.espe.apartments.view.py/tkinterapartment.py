from tkinter import *
from tkinter.ttk import Combobox
from tkinter import messagebox

def Delete (): 
    
    caja_text1.delete(0, END)
    caja_text2.delete(0, END)
    caja_text3.delete(0, END)
    caja_text4.delete(0, END)

main_window = Tk()
main_window.title("Apartments")

Label1 = Label(main_window, text="ID:", font=("Arial", 10))
Label1.grid(column=0, row=1)

caja_text1 = Entry(main_window, width=20, font=("Arial", 12))
caja_text1.grid(column=1, row=1)

Label2 = Label(main_window, text="Description:")
Label2.grid(column=0, row=2)

caja_text2 = Entry(main_window, width=20, font=("Arial", 12))
caja_text2.grid(column=1, row=2)

Label3 = Label(main_window, text="Age:")
Label3.grid(column=0, row=3)

caja_text3 = Entry(main_window, width=20, font=("Arial", 12))
caja_text3.grid(column=1, row=3)

Label4 = Label(main_window, text="Price:")
Label4.grid(column=0, row=4)

caja_text4 = Entry(main_window, width=20, font=("Arial", 12))
caja_text4.grid(column=1, row=4)

btn_Delete = Button(
    main_window,
    text="Delete",
    font=("Arial", 12),
    command=Delete
)
btn_Delete.grid(column=2, row=8, pady=10)

main_window.mainloop()


