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
    print("First Name:", entryFirst.get())
    print("Last Name:", entryLast.get())
    print("Birth Date:", birthDate.get())
    print("Age:", entryAge.get())
    print("Type:", comboType.get())
    print("Sex:", sex.get())
    print("Hobbies:", [hobbiesList.get(i) for i in hobbiesList.curselection()])
    print("Comments:", textComments.get("1.0", tk.END))
    print("--------------------------------------")


root = tk.Tk()
root.title("CONTACTS")
root.geometry("700x520")
root.configure(bg="#dfe6e9")

title = tk.Label(root, text="CONTACTS", font=("Arial", 22, "bold"), bg="#dfe6e9")
title.pack(pady=10)

frame = tk.Frame(root, bg="#dfe6e9")
frame.pack(pady=5)

padx = 10
pady = 7



tk.Label(frame, text="ID:", bg="#dfe6e9").grid(row=0, column=0, sticky="e", padx=padx, pady=pady)
entryId = tk.Entry(frame, width=25)
entryId.grid(row=0, column=1, sticky="w")

tk.Label(frame, text="First Name:", bg="#dfe6e9").grid(row=1, column=0, sticky="e", padx=padx, pady=pady)
entryFirst = tk.Entry(frame, width=25)
entryFirst.grid(row=1, column=1, sticky="w")

tk.Label(frame, text="Last Name:", bg="#dfe6e9").grid(row=2, column=0, sticky="e", padx=padx, pady=pady)
entryLast = tk.Entry(frame, width=25)
entryLast.grid(row=2, column=1, sticky="w")

tk.Label(frame, text="Birth Date:", bg="#dfe6e9").grid(row=3, column=0, sticky="e", padx=padx, pady=pady)
birthDate = DateEntry(frame, date_pattern="dd/MM/yyyy", width=22)
birthDate.grid(row=3, column=1, sticky="w")
birthDate.bind("<<DateEntrySelected>>", updateAge)

tk.Label(frame, text="Age:", bg="#dfe6e9").grid(row=4, column=0, sticky="e", padx=padx, pady=pady)
entryAge = tk.Entry(frame, width=25)
entryAge.grid(row=4, column=1, sticky="w")

tk.Label(frame, text="Type:", bg="#dfe6e9").grid(row=5, column=0, sticky="e", padx=padx, pady=pady)
comboType = ttk.Combobox(frame, values=["Job", "Family", "Friend", "Other"], width=22)
comboType.grid(row=5, column=1, sticky="w")
comboType.current(0)

tk.Label(frame, text="Sex:", bg="#dfe6e9").grid(row=6, column=0, sticky="e", padx=padx, pady=pady)
sex = tk.StringVar()

sexFrame = tk.Frame(frame, bg="#dfe6e9")
sexFrame.grid(row=6, column=1, sticky="w")

tk.Radiobutton(sexFrame, text="Male", variable=sex, value="Male", bg="#dfe6e9").pack(side="left", padx=5)
tk.Radiobutton(sexFrame, text="Female", variable=sex, value="Female", bg="#dfe6e9").pack(side="left", padx=5)

tk.Label(frame, text="Hobbies", bg="#dfe6e9").grid(row=7, column=0, sticky="ne", padx=padx, pady=pady)
hobbiesList = tk.Listbox(frame, height=6, width=22, selectmode="multiple")
hobbiesList.grid(row=7, column=1, sticky="w")

hobbies = ["Play Soccer", "DJing", "Read", "Cook", "Swim", "Sing", "Play an instrument"]
for h in hobbies:
    hobbiesList.insert(tk.END, h)


tk.Label(frame, text="Comments:", bg="#dfe6e9").grid(row=0, column=2, sticky="nw", padx=padx, pady=pady)
textComments = tk.Text(frame, width=35, height=12)
textComments.grid(row=0, column=3, rowspan=8, sticky="w", padx=10)


btnSave = tk.Button(root, text="Save", width=12, font=("Arial", 12), command=saveData)
btnSave.pack(pady=15)

root.mainloop()
