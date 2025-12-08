import tkinter as tk
from tkinter import ttk, messagebox
from contact import Contact 
from pymongo import MongoClient
from pymongo.server_api import ServerApi

uri = "mongodb+srv://maryuri:maryuri2007@cluster0.iektq66.mongodb.net/?retryWrites=true&w=majority"
DB_NAME = "ContactsBookDB"
COLLECTION_NAME = "Contacts"

class FrmContacts:
    def __init__(self, master):
        self.master = master
        master.title("CONTACTS")

        self.mongo_client = None
        self.db = None
        self.contacts_collection = None
        self._connect_to_mongo()

        self.contact = Contact()
        self.sex_var = tk.StringVar(value="Male")
        
        main_frame = ttk.Frame(master, padding="10")
        main_frame.grid(row=0, column=0, sticky="nsew")

        title_label = ttk.Label(main_frame, text="CONTACTS", font=('Arial', 20, 'bold'))
        title_label.grid(row=0, column=0, columnspan=3, pady=10)
        
        ttk.Label(main_frame, text="First Name:").grid(row=2, column=0, sticky="w", pady=5)
        self.txtFirstName = ttk.Entry(main_frame, width=25)
        self.txtFirstName.grid(row=2, column=1, sticky="w", pady=5)
        
        ttk.Label(main_frame, text="Last Name:").grid(row=3, column=0, sticky="w", pady=5)
        self.txtLastName = ttk.Entry(main_frame, width=25)
        self.txtLastName.grid(row=3, column=1, sticky="w", pady=5)

        ttk.Label(main_frame, text="Birth Date:").grid(row=4, column=0, sticky="w", pady=5)
        self.ftdBirthDate = ttk.Entry(main_frame, width=25)
        self.ftdBirthDate.grid(row=4, column=1, sticky="w", pady=5)
        self.ftdBirthDate.insert(0, "M/d/yy") 

        ttk.Label(main_frame, text="Age:").grid(row=5, column=0, sticky="w", pady=5)
        self.txtAge = ttk.Entry(main_frame, width=25)
        self.txtAge.grid(row=5, column=1, sticky="w", pady=5) 

        ttk.Label(main_frame, text="Type:").grid(row=6, column=0, sticky="w", pady=5)
        self.cmbType = ttk.Combobox(main_frame, 
                                    values=["Family", "Friend", "Job", "Unknown"], 
                                    state="readonly")
        self.cmbType.current(0)
        self.cmbType.grid(row=6, column=1, sticky="w", pady=5)
        
        ttk.Label(main_frame, text="Sex:").grid(row=7, column=0, sticky="nw", pady=5)
        ttk.Radiobutton(main_frame, text="Male", variable=self.sex_var, value="Male").grid(row=7, column=1, sticky="w")
        ttk.Radiobutton(main_frame, text="Female", variable=self.sex_var, value="Female").grid(row=8, column=1, sticky="w")

        ttk.Label(main_frame, text="Hobbies:").grid(row=9, column=0, sticky="nw", pady=5)
        hobbies_list = ["PlaySoccer", "Dijing", "Read", "Cook", "Swim", "Sing", "Play an instrument"]
        self.lstHobbies = tk.Listbox(main_frame, selectmode=tk.SINGLE, height=5, width=20)
        for item in hobbies_list:
            self.lstHobbies.insert(tk.END, item)
        self.lstHobbies.grid(row=9, column=1, sticky="w", pady=5)

        ttk.Label(main_frame, text="Comments:").grid(row=2, column=2, sticky="nw", padx=20, pady=5)
        self.txaComments = tk.Text(main_frame, height=10, width=30)
        self.txaComments.grid(row=3, column=2, rowspan=7, sticky="n", padx=20, pady=5)

        button_frame = ttk.Frame(master, padding="10")
        button_frame.grid(row=1, column=0, sticky="ew")
        
        self.btmSave = ttk.Button(button_frame, text="Save", command=self.btmSaveActionPerformed)
        self.btmSave.pack(pady=10)

    def _connect_to_mongo(self):
        try:
            self.mongo_client = MongoClient(uri, server_api=ServerApi('1'))
            self.mongo_client.admin.command('ping')
            self.db = self.mongo_client.get_database(DB_NAME)
            self.contacts_collection = self.db.get_collection(COLLECTION_NAME)
            print("Conexión a MongoDB Atlas establecida.")
            
            db_list = self.mongo_client.list_database_names()
            print("Bases de datos disponibles:", db_list)

        except Exception as e:
            messagebox.showerror("Error de Conexión", f"No se pudo conectar a MongoDB: {e}")
            self.mongo_client = None
            self.db = None
            self.contacts_collection = None
            print(f"Error al conectar o hacer ping a MongoDB: {e}")

    def _save_to_mongo(self):
        # CORRECCIÓN: Comprobar si la colección es None
        if self.contacts_collection is None:
            messagebox.showwarning("Advertencia", "No hay conexión a la base de datos. Los datos no se guardaron.")
            return False

        contact_data = {
            "id": self.contact.age, 
            "firstName": self.contact.firstName,
            "lastName": self.contact.lastName,
            "age": self.contact.age,
            "typeOfContact": self.contact.typeOfContact,
            "sex": self.contact.sex,
            "hobbies": self.contact.hobbies,
            "comments": self.contact.comments,
        }
        
        try:
            self.contacts_collection.insert_one(contact_data)
            return True
        except Exception as e:
            messagebox.showerror("Error de Guardado", f"Fallo al insertar el contacto en MongoDB: {e}")
            return False

    def emptyFields(self):
        self.txtFirstName.delete(0, tk.END)
        self.txtLastName.delete(0, tk.END)
        self.ftdBirthDate.delete(0, tk.END)
        self.ftdBirthDate.insert(0, "M/d/yy")
        self.txtAge.delete(0, tk.END)
        self.cmbType.current(0)
        self.sex_var.set("Female")
        self.lstHobbies.selection_clear(0, tk.END)
        self.txaComments.delete(1.0, tk.END) 

    def readValues(self):
        firstName = self.txtFirstName.get()
        lastName = self.txtLastName.get()
        
        try:
            age = int(self.txtAge.get().strip())
        except ValueError:
            messagebox.showerror("Error de Entrada", "La edad debe ser un número entero.")
            age = 0 
        
        typeOfContact = self.cmbType.get()
        sex = self.sex_var.get()
        
        selected_hobbies = []
        try:
            selected_indices = self.lstHobbies.curselection()
            if selected_indices:
                hobby = self.lstHobbies.get(selected_indices[0]) 
                selected_hobbies.append(hobby)
        except Exception as e:
            print(f"Error al obtener hobby: {e}")
            
        comments = self.txaComments.get("1.0", tk.END).strip()
        
        self.contact = Contact(age, firstName, lastName, age , typeOfContact, sex, selected_hobbies, comments)
        
    def btmSaveActionPerformed(self):
        self.readValues()
        
        if self.contact.age == 0:
            return

        response = messagebox.askyesnocancel("SAVE CONTACTS", f"Saying contact -->{self.contact}")

        if response is True:
            if self._save_to_mongo():
                messagebox.showinfo("Saved", "El contacto fue guardado exitosamente en MongoDB.")
                self.emptyFields()
            else:
                 pass 
        elif response is False:
            messagebox.showwarning("", "Your data will be lost")
            self.emptyFields()
        else:
            self.txtFirstName.focus_set()

if __name__ == '__main__':
    root = tk.Tk()
    app = FrmContacts(root)
    
    def on_closing():
        if app.mongo_client:
            app.mongo_client.close()
            print("Conexión a MongoDB cerrada.")
        root.destroy()
        
    root.protocol("WM_DELETE_WINDOW", on_closing)
    root.mainloop()