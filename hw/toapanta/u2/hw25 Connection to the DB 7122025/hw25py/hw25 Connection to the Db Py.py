import tkinter as tk
from tkinter import messagebox
from tkcalendar import DateEntry

from datetime import date, datetime 
import re
import uuid


from pymongo import MongoClient
from pymongo.errors import PyMongoError 


MONGO_URI = "mongodb://localhost:27017/" 
DATABASE_NAME = "PatientRegist"
COLLECTION_NAME = "Patient"

class FrmRegisterPatient(tk.Toplevel):
    def __init__(self, master=None):
        super().__init__(master)
        self.title("Registro pacientes (MongoDB)")
        self.geometry("550x300")
        self.config(bg="#F3EBF3") 

        self.client = None
        self.db_collection = None
        self.connect_to_mongodb()

        # Variables para el formulario
        self.id_var = tk.StringVar() 
        self.full_name_var = tk.StringVar()
        self.age_var = tk.StringVar()
        self.gender_var = tk.StringVar()
        self.phone_var = tk.StringVar()
        self.address_var = tk.StringVar()
        self.email_var = tk.StringVar()

        self.create_widgets()

    def connect_to_mongodb(self):
        """Intenta establecer la conexión con el servidor MongoDB."""
        try:
            self.client = MongoClient(MONGO_URI, serverSelectionTimeoutMS=5000) 
            self.client.admin.command('ping') 
            
            db = self.client[DATABASE_NAME]
            self.db_collection = db[COLLECTION_NAME]
            print(f"Conexión a MongoDB exitosa. Usando colección: {COLLECTION_NAME}")
            
        except PyMongoError: 
            messagebox.showerror("Error de Conexión", 
                                 "No se pudo conectar a MongoDB. Asegúrate de que el servidor esté corriendo.")
            self.client = None
        except Exception as e:
            messagebox.showerror("Error", f"Ocurrió un error general al conectar a la DB: {e}")
            self.client = None
            
    
    def create_widgets(self):
        header_label = tk.Label(self, text="Registro pacientes", font=("Verdana", 14, "bold"), bg="#F3EBF3")
        header_label.grid(row=0, column=0, columnspan=4, pady=10)

      
        tk.Label(self, text="ID:", font=("Segoe UI", 10), bg="#F3EBF3").grid(row=1, column=0, sticky="e", padx=5, pady=2)
        id_entry = tk.Entry(self, textvariable=self.id_var, width=20) 
        id_entry.grid(row=1, column=1, sticky="w", padx=5, pady=2)
        id_entry.bind('<KeyRelease>', self.validate_id)

        
        tk.Label(self, text="Nombre completo:", font=("Segoe UI", 10), bg="#F3EBF3").grid(row=2, column=0, sticky="e", padx=5, pady=2)
        txtFullName = tk.Entry(self, textvariable=self.full_name_var, width=50)
        txtFullName.grid(row=2, column=1, columnspan=3, sticky="w", padx=5, pady=2)
        txtFullName.bind('<KeyRelease>', self.validate_full_name)

        
        tk.Label(self, text="Género:", font=("Segoe UI", 10), bg="#F3EBF3").grid(row=3, column=0, sticky="e", padx=5, pady=2)
        radGenderMale = tk.Radiobutton(self, text="Masculino", variable=self.gender_var, value="Masculino", bg="#F3EBF3")
        radGenderMale.grid(row=3, column=1, sticky="w", padx=5, pady=2)
        radGenderFemale = tk.Radiobutton(self, text="Femenino", variable=self.gender_var, value="Femenino", bg="#F3EBF3")
        radGenderFemale.grid(row=3, column=2, sticky="w", padx=5, pady=2)
        
        tk.Label(self, text="Teléfono:", font=("Segoe UI", 10), bg="#F3EBF3").grid(row=3, column=2, sticky="e", padx=5, pady=2)
        txPhoneNumber = tk.Entry(self, textvariable=self.phone_var, width=20)
        txPhoneNumber.grid(row=3, column=3, sticky="w", padx=5, pady=2)
        txPhoneNumber.bind('<KeyRelease>', self.validate_phone_number)

        
        tk.Label(self, text="Birth Date:", font=("Segoe UI", 10), bg="#F3EBF3").grid(row=4, column=0, sticky="e", padx=5, pady=2)
        self.txtBirthDate = DateEntry(self, width=17, background='darkblue', foreground='white', borderwidth=2, date_pattern='dd/MM/yyyy')
        self.txtBirthDate.grid(row=4, column=1, sticky="w", padx=5, pady=2)
        self.txtBirthDate.bind('<<DateEntrySelected>>', self.calculate_age)

        tk.Label(self, text="Edad:", font=("Segoe UI", 10), bg="#F3EBF3").grid(row=4, column=2, sticky="e", padx=5, pady=2)
        txtAge = tk.Entry(self, textvariable=self.age_var, state='readonly', width=10)
        txtAge.grid(row=4, column=3, sticky="w", padx=5, pady=2)

       
        tk.Label(self, text="Dirección:", font=("Segoe UI", 10), bg="#F3EBF3").grid(row=5, column=0, sticky="e", padx=5, pady=2)
        txtAdress = tk.Entry(self, textvariable=self.address_var, width=20)
        txtAdress.grid(row=5, column=1, sticky="w", padx=5, pady=2)

        tk.Label(self, text="Email:", font=("Segoe UI", 10), bg="#F3EBF3").grid(row=5, column=2, sticky="e", padx=5, pady=2)
        txtEmail = tk.Entry(self, textvariable=self.email_var, width=20)
        txtEmail.grid(row=5, column=3, sticky="w", padx=5, pady=2)

        
        btnSave = tk.Button(self, text="Save", command=self.btnSaveActionPerformed, width=10)
        btnSave.grid(row=6, column=1, columnspan=2, pady=20)
        
        btnBack = tk.Button(self, text="Regresar", command=self.btnBackActionPerformed, width=10)
        btnBack.grid(row=7, column=0, sticky="w", padx=10, pady=5)


 
    def calculate_age(self, event):
        try:
            birth_date_str = self.txtBirthDate.get_date()
            today = date.today()
            age = today.year - birth_date_str.year - ((today.month, today.day) < (birth_date_str.month, birth_date_str.day))
            self.age_var.set(str(age))
        except ValueError:
            self.age_var.set("") 

    def validate_full_name(self, event):
        current_text = self.full_name_var.get()
        new_text = re.sub(r'[^a-zA-Z\s]', '', current_text)
        if new_text != current_text:
            self.full_name_var.set(new_text)
            messagebox.showwarning("Error de Entrada", "Números no están permitidos en el nombre.", parent=self)
            
    def validate_id(self, event):
        current_text = self.id_var.get()
        new_text = re.sub(r'[^0-9]', '', current_text)
        if new_text != current_text:
            self.id_var.set(new_text)
            messagebox.showwarning("Error de Entrada", "El ID debe ser un número entero (solo dígitos).", parent=self)

    def validate_phone_number(self, event):
        current_text = self.phone_var.get()
        new_text = re.sub(r'[^0-9]', '', current_text)
        if new_text != current_text:
            self.phone_var.set(new_text)
            messagebox.showwarning("Error de Entrada", "Solo se permiten números en el teléfono.", parent=self)
            
            
    
    def btnSaveActionPerformed(self):
        
   
        patient_id_str = self.id_var.get().strip()
        fullName = self.full_name_var.get().strip()
        address = self.address_var.get().strip()
        phone = self.phone_var.get().strip()
        email = self.email_var.get().strip()
        age = self.age_var.get().strip()
        gender = self.gender_var.get()
        
        try:
            birth_date = self.txtBirthDate.get_date() 
        except Exception:
            birth_date = None


        if not (patient_id_str and fullName and address and phone and email and age and gender and birth_date):
            messagebox.showwarning("Advertencia", "Por favor ingrese todos los datos.")
            return

        
        try:
            patient_id_int = int(patient_id_str)
        except ValueError:
            messagebox.showwarning("Advertencia", "El ID debe ser un número entero válido.", parent=self)
            return

        
        if self.db_collection is None:
            messagebox.showerror("Error", "No hay conexión a la base de datos.")
            return

        patient_document = {
            "patient_id": patient_id_int, 
            "fullName": fullName,
            
           
            "birthDate": datetime.combine(birth_date, datetime.min.time()), 
            
            "age": int(age), 
            "gender": gender,
            "phone": phone,
            "address": address,
            "email": email,
            
          
            "registration_date": datetime.combine(date.today(), datetime.min.time()) 
        }


        try:
            insert_result = self.db_collection.insert_one(patient_document)
            
            messagebox.showinfo("Éxito", 
                                f"Información guardada con éxito en MongoDB. ID de Documento: {insert_result.inserted_id}")
            
            self.clear_form()

        except Exception as e:
            messagebox.showerror("Error", f"Ocurrió un error al guardar en MongoDB: {e}")
            
    
    def btnBackActionPerformed(self):
        """Cierra la conexión a MongoDB y la ventana."""
        if self.client:
            self.client.close() 
        self.destroy()

    def clear_form(self):
        """Limpia todos los campos del formulario."""
        self.id_var.set("") 
        self.full_name_var.set("")
        self.age_var.set("")
        self.gender_var.set("")
        self.phone_var.set("")
        self.address_var.set("")
        self.email_var.set("")
        self.txtBirthDate.set_date(date.today()) 

if __name__ == '__main__':
    root = tk.Tk()
    root.withdraw() 
    
    app = FrmRegisterPatient(root)
    app.grab_set() 
    
    root.mainloop()