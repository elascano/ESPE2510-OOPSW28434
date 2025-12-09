import tkinter as tk
from tkinter import ttk, messagebox
from tkcalendar import DateEntry
from datetime import datetime
from ContactController import ContactController

class ContactsBookApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Contacts Book App")
        self.root.geometry("600x680")
        self.root.configure(bg="#ece9d8")
        
        self.controller = ContactController()
        self.chkHobbiesVars = {}
        self.radSex = tk.StringVar(value="Female")
        
        self.createWidgets()

    def createWidgets(self):
        lblStyle = {"bg": "#ece9d8", "font": ("Arial", 10, "bold"), "anchor": "e"}

        lblTitle = tk.Label(self.root, text="CONTACTS", bg="#ece9d8", font=("Arial", 18))
        lblTitle.place(x=0, y=20, width=600)

        lblId = tk.Label(self.root, text="id:", **lblStyle)
        lblId.place(x=50, y=70, width=80)
        lblIdVal = tk.Label(self.root, text="(Auto)", bg="#ece9d8", anchor="w")
        lblIdVal.place(x=140, y=70)

        lblFirstName = tk.Label(self.root, text="First Name:", **lblStyle)
        lblFirstName.place(x=50, y=100, width=80)
        self.txtFirstName = tk.Entry(self.root)
        self.txtFirstName.place(x=140, y=100, width=160)

        lblLastName = tk.Label(self.root, text="Last Name:", **lblStyle)
        lblLastName.place(x=50, y=130, width=80)
        self.txtLastName = tk.Entry(self.root)
        self.txtLastName.place(x=140, y=130, width=160)

        lblBirthDate = tk.Label(self.root, text="Birth Date:", **lblStyle)
        lblBirthDate.place(x=50, y=160, width=80)
        self.dtpBirthDate = DateEntry(self.root, width=12, background='darkblue', foreground='white', borderwidth=2, date_pattern='yyyy-mm-dd')
        self.dtpBirthDate.place(x=140, y=160, width=160)
        self.dtpBirthDate.bind("<<DateEntrySelected>>", self.updateAge)

        lblAge = tk.Label(self.root, text="Age:", **lblStyle)
        lblAge.place(x=50, y=190, width=80)
        self.lblAgeVal = tk.Label(self.root, text="0", bg="#ece9d8", anchor="w")
        self.lblAgeVal.place(x=140, y=190)

        lblComments = tk.Label(self.root, text="Comments:", **lblStyle)
        lblComments.place(x=330, y=70)
        self.txaComments = tk.Text(self.root, width=25, height=7)
        self.txaComments.place(x=330, y=95)

        lblType = tk.Label(self.root, text="Type:", **lblStyle)
        lblType.place(x=50, y=230, width=80)
        self.cmbType = ttk.Combobox(self.root, values=["Family", "Friend", "Job", "Unknown"], state="readonly")
        self.cmbType.current(0)
        self.cmbType.place(x=140, y=230, width=120)

        lblSex = tk.Label(self.root, text="Sex:", **lblStyle)
        lblSex.place(x=50, y=270, width=80)
        rdbMale = tk.Radiobutton(self.root, text="Male", variable=self.radSex, value="Male", bg="#ece9d8")
        rdbMale.place(x=140, y=270)
        rdbFemale = tk.Radiobutton(self.root, text="Female", variable=self.radSex, value="Female", bg="#ece9d8")
        rdbFemale.place(x=140, y=290)

        lblHobbies = tk.Label(self.root, text="Hobbies:", **lblStyle)
        lblHobbies.place(x=50, y=330, width=80)
        frameHobbies = tk.Frame(self.root, bg="white", bd=1, relief="sunken")
        frameHobbies.place(x=140, y=330, width=160, height=150)

        hobbiesList = ["Play Soccer", "Dijing", "Read", "Cook", "Swim", "Sing", "Play Instrument"]
        for hobby in hobbiesList:
            var = tk.BooleanVar()
            chkHobby = tk.Checkbutton(frameHobbies, text=hobby, variable=var, bg="white", anchor="w")
            chkHobby.pack(fill="x")
            self.chkHobbiesVars[hobby] = var

        btnSave = tk.Button(self.root, text="SAVE", command=self.saveData, bg="#e1e1e1", width=12, font=("Arial", 10, "bold"))
        btnSave.place(x=180, y=530)

        btnCancel = tk.Button(self.root, text="CANCEL", command=self.cancelForm, bg="#ffcccc", width=12, font=("Arial", 10, "bold"))
        btnCancel.place(x=300, y=530)

    def updateAge(self, event=None):
        birthDate = self.dtpBirthDate.get_date()
        age = self.controller.getAge(birthDate)
        self.lblAgeVal.config(text=str(age))

    def clearFields(self):
        self.txtFirstName.delete(0, tk.END)
        self.txtLastName.delete(0, tk.END)
        self.dtpBirthDate.set_date(datetime.now())
        self.lblAgeVal.config(text="0")
        self.cmbType.current(0)
        self.radSex.set("Female")
        self.txaComments.delete("1.0", tk.END)
        for var in self.chkHobbiesVars.values():
            var.set(False)
        self.txtFirstName.focus()

    def cancelForm(self):
        if messagebox.askyesno("Cancel", "Are you sure you want to clear the form?"):
            self.clearFields()

    def saveData(self):
        firstName = self.txtFirstName.get().strip()
        lastName = self.txtLastName.get().strip()
        birthDate = self.dtpBirthDate.get_date()
        age = int(self.lblAgeVal.cget("text"))
        cType = self.cmbType.get()
        sex = self.radSex.get()
        comments = self.txaComments.get("1.0", tk.END).strip()
        
        hobbies = []
        for name, var in self.chkHobbiesVars.items():
            if var.get():
                hobbies.append(name)

        if messagebox.askyesno("Confirm", "Do you want to save this contact to MongoDB?"):
            success, msg = self.controller.addContact(firstName, lastName, birthDate, age, cType, sex, hobbies, comments)
            
            if success:
                messagebox.showinfo("Success", msg)
                self.clearFields()
            else:
                messagebox.showwarning("Error", msg)

if __name__ == "__main__":
    root = tk.Tk()
    app = ContactsBookApp(root)
    root.mainloop()