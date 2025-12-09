import tkinter as tk
from tkinter import ttk, messagebox
from pymongo import MongoClient
import certifi
from datetime import datetime
from tkcalendar import DateEntry

try:
    URI = "mongodb+srv://Josue:Josue2006@cluster0.da07rsq.mongodb.net/?retryWrites=true&w=majority"
    client = MongoClient(URI, tlsCAFile=certifi.where())
    db = client['ConectionMongoDB']
    collection = db['PyContactsBook']
    print("CONNECTED")
except Exception as e:
    messagebox.showerror("Connection Error", f"{e}")

def calculateAge(event=None):
    try:
        birthDate = dtpBirthDate.get_date()
        today = datetime.now().date()
        age = today.year - birthDate.year - ((today.month, today.day) < (birthDate.month, birthDate.day))
        lblAgeVal.config(text=str(age))
    except:
        lblAgeVal.config(text="0")

def clearFields():
    txtFirstName.delete(0, tk.END)
    txtLastName.delete(0, tk.END)
    dtpBirthDate.set_date(datetime.now())
    lblAgeVal.config(text="0")
    cmbType.current(0)
    radSex.set("Female")
    txaComments.delete("1.0", tk.END)
    for var in chkHobbiesVars.values():
        var.set(False)
    txtFirstName.focus()

def cancelAction():
    if messagebox.askyesno("Cancel", "Are you sure you want to clear the form?"):
        clearFields()

def saveAction():
    firstName = txtFirstName.get().strip()
    lastName = txtLastName.get().strip()
    birthDate = dtpBirthDate.get() 
    
    if not firstName:
        messagebox.showwarning("Validation Error", "First Name is required")
        txtFirstName.focus()
        return

    if any(char.isdigit() for char in firstName):
        messagebox.showwarning("Validation Error", "First Name cannot contain numbers")
        txtFirstName.focus()
        return
    
    if not lastName:
        messagebox.showwarning("Validation Error", "Last Name is required")
        txtLastName.focus()
        return

    if any(char.isdigit() for char in lastName):
        messagebox.showwarning("Validation Error", "Last Name cannot contain numbers")
        txtLastName.focus()
        return

    selectedHobbies = []
    for hobbyName, hobbyVar in chkHobbiesVars.items():
        if hobbyVar.get():
            selectedHobbies.append(hobbyName)

    document = {
        "firstName": firstName,
        "lastName": lastName,
        "birthDate": birthDate,
        "age": int(lblAgeVal.cget("text")),
        "type": cmbType.get(),
        "sex": radSex.get(),
        "hobbies": selectedHobbies,
        "comments": txaComments.get("1.0", tk.END).strip()
    }

    if messagebox.askyesno("Confirm", "Do you want to save this contact to MongoDB?"):
        try:
            collection.insert_one(document)
            messagebox.showinfo("Success", "Contact saved successfully!")
            clearFields()
        except Exception as e:
            messagebox.showerror("Error", f"Could not save: {e}")

root = tk.Tk()
root.title("Contacts Book App")
root.geometry("600x680")
root.configure(bg="#ece9d8")

lblStyle = {"bg": "#ece9d8", "font": ("Arial", 10, "bold"), "anchor": "e"}

lblTitle = tk.Label(root, text="CONTACTS", bg="#ece9d8", font=("Arial", 18))
lblTitle.place(x=0, y=20, width=600)

lblId = tk.Label(root, text="id:", **lblStyle)
lblId.place(x=50, y=70, width=80)
lblIdVal = tk.Label(root, text="(Auto)", bg="#ece9d8", anchor="w")
lblIdVal.place(x=140, y=70)

lblFirstName = tk.Label(root, text="First Name:", **lblStyle)
lblFirstName.place(x=50, y=100, width=80)
txtFirstName = tk.Entry(root)
txtFirstName.place(x=140, y=100, width=160)

lblLastName = tk.Label(root, text="Last Name:", **lblStyle)
lblLastName.place(x=50, y=130, width=80)
txtLastName = tk.Entry(root)
txtLastName.place(x=140, y=130, width=160)

lblBirthDate = tk.Label(root, text="Birth Date:", **lblStyle)
lblBirthDate.place(x=50, y=160, width=80)
dtpBirthDate = DateEntry(root, width=12, background='darkblue', foreground='white', borderwidth=2, date_pattern='yyyy-mm-dd')
dtpBirthDate.place(x=140, y=160, width=160)
dtpBirthDate.bind("<<DateEntrySelected>>", calculateAge)

lblAge = tk.Label(root, text="Age:", **lblStyle)
lblAge.place(x=50, y=190, width=80)
lblAgeVal = tk.Label(root, text="0", bg="#ece9d8", anchor="w")
lblAgeVal.place(x=140, y=190)

lblComments = tk.Label(root, text="Comments:", **lblStyle)
lblComments.place(x=330, y=70)
txaComments = tk.Text(root, width=25, height=7)
txaComments.place(x=330, y=95)

lblType = tk.Label(root, text="Type:", **lblStyle)
lblType.place(x=50, y=230, width=80)
cmbType = ttk.Combobox(root, values=["Family", "Friend", "Job", "Unknown"], state="readonly")
cmbType.current(0)
cmbType.place(x=140, y=230, width=120)

lblSex = tk.Label(root, text="Sex:", **lblStyle)
lblSex.place(x=50, y=270, width=80)
radSex = tk.StringVar(value="Female")
rdbMale = tk.Radiobutton(root, text="Male", variable=radSex, value="Male", bg="#ece9d8")
rdbMale.place(x=140, y=270)
rdbFemale = tk.Radiobutton(root, text="Female", variable=radSex, value="Female", bg="#ece9d8")
rdbFemale.place(x=140, y=290)

lblHobbies = tk.Label(root, text="Hobbies:", **lblStyle)
lblHobbies.place(x=50, y=330, width=80)
frameHobbies = tk.Frame(root, bg="white", bd=1, relief="sunken")
frameHobbies.place(x=140, y=330, width=160, height=150)

hobbiesList = ["Play Soccer", "Dijing", "Read", "Cook", "Swim", "Sing", "Play Instrument"]
chkHobbiesVars = {}

for hobby in hobbiesList:
    var = tk.BooleanVar()
    chkHobby = tk.Checkbutton(frameHobbies, text=hobby, variable=var, bg="white", anchor="w")
    chkHobby.pack(fill="x")
    chkHobbiesVars[hobby] = var

btnSave = tk.Button(root, text="SAVE", command=saveAction, bg="#e1e1e1", width=12, font=("Arial", 10, "bold"))
btnSave.place(x=180, y=530)

btnCancel = tk.Button(root, text="CANCEL", command=cancelAction, bg="#ffcccc", width=12, font=("Arial", 10, "bold"))
btnCancel.place(x=300, y=530)

root.mainloop()