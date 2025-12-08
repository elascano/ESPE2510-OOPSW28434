import tkinter as tk
from tkinter import ttk
from tkcalendar import DateEntry
from datetime import date


def calculateAge(birthDate):
    today = date.today()
    return today.year - birthDate.year - (
        (today.month, today.day) < (birthDate.month, birthDate.day)
    )


def updateAge(event):
    try:
        fecha = birthDate.get_date()
        edad = calculateAge(fecha)
        entryAge.delete(0, tk.END)
        entryAge.insert(0, str(edad))
    except:
        pass


def saveData():
    print("ID:", entryId.get())
    print("Nombres:", entryFirst.get())
    print("Apellidos:", entryLast.get())
    print("Género:", gender.get())
    print("Birth Date:", birthDate.get())
    print("Edad:", entryAge.get())
    print("Dirección:", entryAddress.get())
    print("Teléfono:", entryPhone.get())
    print("Email:", entryEmail.get())
    print("Tipo de sangre:", bloodType.get())
    print("Notas:", textNotes.get("1.0", tk.END))
    print("-------------------------")





root = tk.Tk()
root.title("Pacientes")
root.geometry("700x550")
root.configure(bg="#dfe6e9")

title = tk.Label(root, text="PACIENTES", font=("Arial", 22, "bold"), bg="#dfe6e9")
title.pack(pady=15)

frame = tk.Frame(root, bg="#dfe6e9")
frame.pack(pady=5)


padx = 10
pady = 8



tk.Label(frame, text="ID:", bg="#dfe6e9").grid(row=0, column=0, sticky="e", padx=padx, pady=pady)
entryId = tk.Entry(frame, width=25)
entryId.grid(row=0, column=1, sticky="w")


tk.Label(frame, text="Nombres:", bg="#dfe6e9").grid(row=1, column=0, sticky="e", padx=padx, pady=pady)
entryFirst = tk.Entry(frame, width=25)
entryFirst.grid(row=1, column=1, sticky="w")


tk.Label(frame, text="Apellidos:", bg="#dfe6e9").grid(row=2, column=0, sticky="e", padx=padx, pady=pady)
entryLast = tk.Entry(frame, width=25)
entryLast.grid(row=2, column=1, sticky="w")


tk.Label(frame, text="Género:", bg="#dfe6e9").grid(row=3, column=0, sticky="e", padx=padx, pady=pady)
gender = tk.StringVar()
genderFrame = tk.Frame(frame, bg="#dfe6e9")
genderFrame.grid(row=3, column=1, sticky="w")

tk.Radiobutton(genderFrame, text="Masculino", variable=gender, value="Masculino", bg="#dfe6e9").pack(side="left", padx=5)
tk.Radiobutton(genderFrame, text="Femenino", variable=gender, value="Femenino", bg="#dfe6e9").pack(side="left", padx=5)


tk.Label(frame, text="Fecha Nacimiento:", bg="#dfe6e9").grid(row=4, column=0, sticky="e", padx=padx, pady=pady)
birthDate = DateEntry(frame, date_pattern="dd/MM/yyyy", width=22)
birthDate.grid(row=4, column=1, sticky="w")
birthDate.bind("<<DateEntrySelected>>", updateAge)

tk.Label(frame, text="Edad:", bg="#dfe6e9").grid(row=5, column=0, sticky="e", padx=padx, pady=pady)
entryAge = tk.Entry(frame, width=25)
entryAge.grid(row=5, column=1, sticky="w")


tk.Label(frame, text="Dirección:", bg="#dfe6e9").grid(row=6, column=0, sticky="e", padx=padx, pady=pady)
entryAddress = tk.Entry(frame, width=40)
entryAddress.grid(row=6, column=1, sticky="w")


tk.Label(frame, text="Teléfono:", bg="#dfe6e9").grid(row=7, column=0, sticky="e", padx=padx, pady=pady)
entryPhone = tk.Entry(frame, width=25)
entryPhone.grid(row=7, column=1, sticky="w")


tk.Label(frame, text="Email:", bg="#dfe6e9").grid(row=8, column=0, sticky="e", padx=padx, pady=pady)
entryEmail = tk.Entry(frame, width=25)
entryEmail.grid(row=8, column=1, sticky="w")


tk.Label(frame, text="Tipo Sangre:", bg="#dfe6e9").grid(row=9, column=0, sticky="e", padx=padx, pady=pady)
bloodType = ttk.Combobox(frame, values=["A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"], width=22)
bloodType.grid(row=9, column=1, sticky="w")
bloodType.current(0)


tk.Label(frame, text="Notas:", bg="#dfe6e9").grid(row=10, column=0, sticky="ne", padx=padx, pady=pady)
textNotes = tk.Text(frame, width=40, height=6)
textNotes.grid(row=10, column=1, sticky="w")


btnSave = tk.Button(root, text="Guardar", width=12, font=("Arial", 12), command=saveData)
btnSave.pack(pady=15)

root.mainloop()
