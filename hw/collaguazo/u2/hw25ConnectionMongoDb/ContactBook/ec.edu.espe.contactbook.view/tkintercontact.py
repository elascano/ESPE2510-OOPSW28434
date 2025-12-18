from tkinter import *
from tkinter.ttk import Combobox
import pymongo
from pymongo import MongoClient
from tkinter import messagebox 
from datetime import datetime 
MONGO_URI = 'mongodb+srv://Psblo:Pablo2006@cluster0.cadn1kx.mongodb.net/?retryWrites=true&w=majority'

DB_NAME = 'agenda_contactos_db'
COLLECTION_NAME = 'contactos'
def guardar_contacto():
    id_value = caja_text1.get()
    first_name = caja_text2.get()
    last_name = caja_text3.get()
    birth_date = caja_text4.get()
    type_contact = combo_Type.get()
    sex = sex_var.get()

    try:
        selection_index = list_hobbies.curselection()
        hobby = list_hobbies.get(selection_index)
    except IndexError:
        hobby = "None"

    comments = text_comments.get("1.0", END).strip()

    if not id_value:
        messagebox.showerror("Error", "El campo ID no puede estar vacio.")
        return

    # Validar que ID sea numérico
    if not id_value.isdigit():
        messagebox.showerror("Error", "El ID debe contener solo numeros.")
        return

    # Validar que el nombre tenga solo letras
    if not first_name.replace(" ", "").isalpha():
        messagebox.showerror("Error", "El nombre solo puede contener letras.")
        return

    if not last_name.replace(" ", "").isalpha():
        messagebox.showerror("Error", "El apellido solo puede contener letras.")
        return

    formatos_validos = ["%d/%m/%Y", "%Y-%m-%d"]
    fecha_valida = False

    for formato in formatos_validos:
        try:
            datetime.strptime(birth_date, formato)
            fecha_valida = True
            break
        except ValueError:
            pass

    if not fecha_valida:
        messagebox.showerror(
            "Error en Fecha",
            "Formato de fecha invalido.\nFormatos aceptados:\n- DD/MM/YYYY\n- YYYY-MM-DD"
        )
        return

    # -----------------------------

    nuevo_contacto = {
        "_id": id_value,
        "nombre": first_name,
        "apellido": last_name,
        "fecha_nacimiento": birth_date,
        "tipo_contacto": type_contact,
        "sexo": sex,
        "hobby": hobby,
        "comentarios": comments
    }

    try:
        cliente = MongoClient(MONGO_URI, serverSelectionTimeoutMS=5000)
        cliente.admin.command('ping')

        bd = cliente[DB_NAME]
        coleccion = bd[COLLECTION_NAME]

        resultado = coleccion.insert_one(nuevo_contacto)

        messagebox.showinfo("Éxito", f"Contacto guardado con ID: {resultado.inserted_id}")

        cliente.close()

    except pymongo.errors.DuplicateKeyError:
        messagebox.showerror("Error de ID", f"Ya existe un contacto con el ID: {id_value}.")
    except pymongo.errors.ServerSelectionTimeoutError as err:
        messagebox.showerror("Error de Conexión", f"No se pudo conectar a MongoDB Atlas.\n{err}")
    except Exception as e:
        messagebox.showerror("Error General", f"Ocurrió un error inesperado: {e}")

def cancelar():
    caja_text1.delete(0, END)
    caja_text2.delete(0, END)
    caja_text3.delete(0, END)
    caja_text4.delete(0, END)
    combo_Type.set("Family")
    sex_var.set("Male")
    list_hobbies.selection_clear(0, END)
    text_comments.delete("1.0", END)

main_window = Tk()
main_window.title("Contact")
Label1 = Label(text= "ID:", font=("Arial, 10"))
Label1.grid(column=0, row=1)
caja_text1 = Entry(width=20, font=("Arial, 12"))
caja_text1.grid(column=1, row=1,) 
Label2 = Label(text="First Name:", font=("Arial, 10"))
Label2.grid(column=0, row=2)
caja_text2 = Entry(width=20, font=("Arial, 12"))
caja_text2.grid(column=1, row=2)
Label3 = Label(text= "Last Name", font=("Arial, 10"))
Label3.grid(column=0, row=3)
caja_text3 = Entry(width=20, font=("Arial, 12"))
caja_text3.grid(column=1, row=3)
Label4 = Label(text = "Birth Date", font=("Arial, 10"))
Label4.grid(column=0, row=4)
caja_text4 = Entry(width=20, font=("Arial, 12"))
caja_text4.grid(column=1, row=4)
Label5 = Label(text= "Type Of Contact", font=("Arial, 10"))
Label5.grid(column=0, row=5)
combo_Type = Combobox(main_window, width=20, font=("Arial", 12))
combo_Type["values"] = ("Family", "Friend", "Job", "Unknown")
combo_Type.current(0)
combo_Type.grid(column=1, row=5)
Label6 = Label(text= "Sex", font=("Arial, 10")).grid(column=0, row=6)
sex_var = StringVar()
sex_var.set("Male")   # valor por defecto

radio_male = Radiobutton(main_window, text="Male", variable=sex_var, value="Male")
radio_male.grid(column=1, row=6, sticky="w")

radio_female = Radiobutton(main_window, text="Female", variable=sex_var, value="Female")
radio_female.grid(column=2, row=6, sticky="w")
Label7= Label(main_window, text = "Hobbies", font=("Arial" , 10))
Label7.grid(column=0, row=7)
list_hobbies = Listbox(main_window, selectmode=SINGLE, width=20, height=5)
list_hobbies.grid(column=1, row=7)
hobbies = ["Play soccer", "Swim", "DJ", "Play a the instrument", "Read", "Cook"]
for h in hobbies:
    list_hobbies.insert(END, h)
from tkinter import *

Label8 = Label(main_window, text="Comments:", font=("Arial", 10))
Label8.grid(column=2, row=6, sticky="nw")   # A la derecha del hobbies

text_comments = Text(main_window, width=30, height=5, font=("Arial", 10))
text_comments.grid(column=3, row=6, sticky="w")
btn_guardar = Button(main_window, text="Guardar", font=("Arial", 12), command=lambda: guardar_contacto())
btn_guardar.grid(column=1, row=8, pady=10)
btn_cancelar = Button(main_window, text="Cancelar", font=("Arial", 12), command=cancelar)
btn_cancelar.grid(column=2, row=8, pady=10)




main_window.mainloop()
