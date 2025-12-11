import customtkinter as ctk
import tkinter as tk
<<<<<<< HEAD
from tkcalendar import Calendar
from datetime import date
=======
import re
from tkcalendar import Calendar
from datetime import date, datetime
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
from pymongo import MongoClient
from tkinter import messagebox

class FrmContacts(ctk.CTk):
    def __init__(self):
        super().__init__()
        self.title("CONTACTS BOOK")
<<<<<<< HEAD
        self.geometry("850x750")
        
        self.mongo_uri = "mongodb+srv://Mathews:Mathews2007@cluster0.6l9ibfh.mongodb.net/"
        self.client = MongoClient(self.mongo_uri)
        self.db = self.client['ContactBook'] 
        self.collection = self.db['Contact']
=======
        self.geometry("900x750")

        try:
            self.mongo_uri = "mongodb+srv://Mathews:Mathews2007@cluster0.6l9ibfh.mongodb.net/"
            self.client = MongoClient(self.mongo_uri)
            self.db = self.client['ContactBook'] 
            self.collection = self.db['Contact']
        except Exception as e:
            messagebox.showerror("Error de Conexión", f"No se pudo conectar a MongoDB: {str(e)}")
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8

        self.grid_rowconfigure(0, weight=0)
        self.grid_rowconfigure(1, weight=1) 
        self.grid_rowconfigure(2, weight=0) 
        self.grid_columnconfigure(0, weight=1)

        self.button_group_sex = tk.StringVar(value="Unknown")
        self._initComponents()
        
<<<<<<< HEAD
    def _initComponents(self):


=======
        self._set_initial_state()
        
    def _initComponents(self):

>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
        self.jPanel1 = ctk.CTkFrame(self, fg_color="#F0F0F0") 
        self.jPanel2 = ctk.CTkFrame(self, fg_color="#F0F0F0")
        self.jPanel3 = ctk.CTkFrame(self, fg_color="#F0F0F0")

<<<<<<< HEAD

        self.jPanel3.grid_columnconfigure(1, weight=1) 
        self.jPanel3.grid_columnconfigure(3, weight=1) 
        
        content_parent = self.jPanel3
        
        self.txtId = ctk.CTkEntry(content_parent)
        self.txtFirstName = ctk.CTkEntry(content_parent)
        self.txtLastName = ctk.CTkEntry(content_parent)
        self.cmbType = ctk.CTkComboBox(content_parent, values=["Family", "Friend", "Job", "Unknown"])
        self.jTextArea1 = ctk.CTkTextbox(content_parent, height=100)
        self.Calendar = Calendar(content_parent, selectmode='day', date_pattern='dd/MM/yyyy', date=date.today()) 
        
        self.radSexMale = ctk.CTkRadioButton(content_parent, text="Male", variable=self.button_group_sex, value="Male", command=self._radSexMaleActionPerformed)
        self.radSexFemale = ctk.CTkRadioButton(content_parent, text="Female", variable=self.button_group_sex, value="Female", command=self._radSexFemaleActionPerformed)
        self.txtAge = ctk.CTkLabel(content_parent, text="--")
        self.jScrollPane1 = ctk.CTkFrame(content_parent) 
        
        hobbies_list = ["Play Soccer", "Djing", "Read", "Cook", "Swim", "Sing", "Play an instrument"]
        self.lstHobbies = tk.Listbox(self.jScrollPane1, listvariable=tk.StringVar(value=hobbies_list), height=5, 
                                     selectmode=tk.MULTIPLE, relief=tk.FLAT)

        self.jScrollPane1.grid_rowconfigure(0, weight=1)
        self.jScrollPane1.grid_columnconfigure(0, weight=1)
        self.lstHobbies.grid(row=0, column=0, sticky="nsew") 
        
        self.jLabel1 = ctk.CTkLabel(self.jPanel1, text="CONTACTS", font=ctk.CTkFont(family="Britannic Bold", size=30, slant="italic"), text_color="#006699")

        self.jButton1 = ctk.CTkButton(self.jPanel2, text="SAVE", font=ctk.CTkFont(family="Footlight MT Light", size=16), fg_color="#000099",command=self._save_to_mongodb)

=======
        self.jPanel3.grid_columnconfigure(1, weight=1) 
        self.jPanel3.grid_columnconfigure(3, weight=1) 
        content_parent = self.jPanel3
        

        ctk.CTkLabel(content_parent, text="id:", font=ctk.CTkFont(family="Footlight MT Light", size=16), text_color="#000099").grid(row=0, column=0, padx=5, pady=5, sticky="w")
        self.txtId = ctk.CTkEntry(content_parent)
        self.txtId.grid(row=0, column=1, padx=5, pady=5, sticky="ew")

        ctk.CTkLabel(content_parent, text="First Name:", font=ctk.CTkFont(family="Footlight MT Light", size=16), text_color="#000099").grid(row=1, column=0, padx=5, pady=5, sticky="w")
        self.txtFirstName = ctk.CTkEntry(content_parent)
        self.txtFirstName.grid(row=1, column=1, padx=5, pady=5, sticky="ew") 

        ctk.CTkLabel(content_parent, text="Last Name:", font=ctk.CTkFont(family="Footlight MT Light", size=16), text_color="#000099").grid(row=2, column=0, padx=5, pady=5, sticky="w")
        self.txtLastName = ctk.CTkEntry(content_parent)
        self.txtLastName.grid(row=2, column=1, padx=5, pady=5, sticky="ew")
        
        ctk.CTkLabel(content_parent, text="Birth Day:", font=ctk.CTkFont(family="Footlight MT Light", size=16), text_color="#000099").grid(row=3, column=0, padx=5, pady=5, sticky="w")
        self.Calendar = Calendar(content_parent, selectmode='day', date_pattern='dd/MM/yyyy', date=date.today()) 
        self.Calendar.grid(row=3, column=1, rowspan=2, padx=5, pady=5, sticky="ew")
        self.Calendar.bind("<<CalendarSelected>>", self._CalendarPropertyChange)

        ctk.CTkLabel(content_parent, text="Age:", font=ctk.CTkFont(family="Footlight MT Light", size=16), text_color="#000099").grid(row=5, column=0, padx=5, pady=5, sticky="w")
        self.txtAge = ctk.CTkLabel(content_parent, text="0")
        self.txtAge.grid(row=5, column=1, padx=5, pady=5, sticky="w")
        
        ctk.CTkLabel(content_parent, text="Type:", font=ctk.CTkFont(family="Footlight MT Light", size=16), text_color="#000099").grid(row=1, column=2, padx=5, pady=5, sticky="w")
        self.cmbType = ctk.CTkComboBox(content_parent, values=["Select", "Family", "Friend", "Job", "Unknown"])
        self.cmbType.set("Select")
        self.cmbType.grid(row=1, column=3, padx=5, pady=5, sticky="ew") 
        
        ctk.CTkLabel(content_parent, text="Sex:", font=ctk.CTkFont(family="Footlight MT Light", size=16), text_color="#000099").grid(row=2, column=2, padx=5, pady=5, sticky="w")
        self.radSexMale = ctk.CTkRadioButton(content_parent, text="Male", variable=self.button_group_sex, value="Male", command = self._radSexMaleActionPerformed)
        self.radSexFemale = ctk.CTkRadioButton(content_parent, text="Female", variable=self.button_group_sex, value="Female", command = self._radSexFemaleActionPerformed)
        self.radSexMale.grid(row=2, column=3, padx=(5, 70), pady=5, sticky="w") 
        self.radSexFemale.grid(row=2, column=3, padx=(70, 5), pady=5, sticky="e") 

        ctk.CTkLabel(content_parent, text="Hobbies:", font=ctk.CTkFont(family="Footlight MT Light", size=16), text_color="#000099").grid(row=3, column=2, padx=5, pady=5, sticky="nw")
        self.jScrollPane1 = ctk.CTkFrame(content_parent) 
        self.hobbies_list = ["Play Soccer", "Djing", "Read", "Cook", "Swim", "Sing", "Play an instrument"]
        self.lstHobbies = tk.Listbox(self.jScrollPane1, listvariable=tk.StringVar(value=self.hobbies_list), height=5, selectmode=tk.MULTIPLE, relief=tk.FLAT)
        self.jScrollPane1.grid_rowconfigure(0, weight=1)
        self.jScrollPane1.grid_columnconfigure(0, weight=1)
        self.lstHobbies.grid(row=0, column=0, sticky="nsew") 
        self.jScrollPane1.grid(row=3, column=3, rowspan=3, padx=5, pady=5, sticky="nsew")

        ctk.CTkLabel(content_parent, text="Comments:", font=ctk.CTkFont(family="Footlight MT Light", size=16), text_color="#000099").grid(row=6, column=0, columnspan=4, padx=5, pady=(20, 5), sticky="w")
        self.jTextArea1 = ctk.CTkTextbox(content_parent, height=100)
        self.jTextArea1.grid(row=7, column=0, columnspan=4, padx=5, pady=5, sticky="ew")

        self.jLabel1 = ctk.CTkLabel(self.jPanel1, text="CONTACTS", font=ctk.CTkFont(family="Britannic Bold", size=30, slant="italic"), text_color="#006699")
        self.jLabel1.grid(row=0, column=0, pady=40, sticky="n") 
        self.jPanel1.grid_columnconfigure(0, weight=1)

        self.btnSave = ctk.CTkButton(self.jPanel2, text="SAVE", font=ctk.CTkFont(family="Footlight MT Light", size=16), fg_color="#000099", command=self._save_to_mongodb)
        self.btnSearch = ctk.CTkButton(self.jPanel2, text="SEARCH", font=ctk.CTkFont(family="Footlight MT Light", size=16), fg_color="#000099", command=self._search_contact)
        self.btnDelete = ctk.CTkButton(self.jPanel2, text="DELETE", font=ctk.CTkFont(family="Footlight MT Light", size=16), fg_color="#000099", command=self._delete_contact)
        self.btnUpdate = ctk.CTkButton(self.jPanel2, text="UPDATE", font=ctk.CTkFont(family="Footlight MT Light", size=16), fg_color="#000099", command=self._update_contact)

        self.btnSave.grid(row=0, column=0, padx=10, pady=30)
        self.btnSearch.grid(row=0, column=1, padx=10, pady=30)
        self.btnDelete.grid(row=0, column=2, padx=10, pady=30)
        self.btnUpdate.grid(row=0, column=3, padx=10, pady=30)

        self.jPanel2.grid_columnconfigure((0,1,2,3), weight=1)
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8

        self.jPanel1.grid(row=0, column=0, sticky="ew", padx=10, pady=(10, 5))
        self.jPanel3.grid(row=1, column=0, sticky="nsew", padx=10, pady=5)
        self.jPanel2.grid(row=2, column=0, sticky="ew", padx=10, pady=(5, 10))

<<<<<<< HEAD
        self.jPanel1.grid_columnconfigure(0, weight=1)
        self.jLabel1.grid(row=0, column=0, pady=40, sticky="n") 
        
        ctk.CTkLabel(content_parent, text="id:", font=ctk.CTkFont(family="Footlight MT Light", size=16), text_color="#000099").grid(row=0, column=0, padx=5, pady=5, sticky="w")

        ctk.CTkLabel(content_parent, text="First Name:", font=ctk.CTkFont(family="Footlight MT Light", size=16), text_color="#000099").grid(row=1, column=0, padx=5, pady=5, sticky="w")
        self.txtFirstName.grid(row=1, column=1, padx=5, pady=5, sticky="ew") 

        ctk.CTkLabel(content_parent, text="Last Name:", font=ctk.CTkFont(family="Footlight MT Light", size=16), text_color="#000099").grid(row=2, column=0, padx=5, pady=5, sticky="w")
        self.txtLastName.grid(row=2, column=1, padx=5, pady=5, sticky="ew")
        
        ctk.CTkLabel(content_parent, text="Birth Day:", font=ctk.CTkFont(family="Footlight MT Light", size=16), text_color="#000099").grid(row=3, column=0, padx=5, pady=5, sticky="w")
        self.Calendar.grid(row=3, column=1, rowspan=2, padx=5, pady=5, sticky="ew")

        ctk.CTkLabel(content_parent, text="Age:", font=ctk.CTkFont(family="Footlight MT Light", size=16), text_color="#000099").grid(row=5, column=0, padx=5, pady=5, sticky="w")
        self.txtAge.grid(row=5, column=1, padx=5, pady=5, sticky="w")
        
        ctk.CTkLabel(content_parent, text="Type:", font=ctk.CTkFont(family="Footlight MT Light", size=16), text_color="#000099").grid(row=1, column=2, padx=5, pady=5, sticky="w") # Etiqueta Type
        self.cmbType.grid(row=1, column=3, padx=5, pady=5, sticky="ew") 
        
        ctk.CTkLabel(content_parent, text="Sex:", font=ctk.CTkFont(family="Footlight MT Light", size=16), text_color="#000099").grid(row=2, column=2, padx=5, pady=5, sticky="w") # Etiqueta Sex
        self.radSexMale.grid(row=2, column=3, padx=(5, 70), pady=5, sticky="w") 
        self.radSexFemale.grid(row=2, column=3, padx=(70, 5), pady=5, sticky="e") 

        ctk.CTkLabel(content_parent, text="Hobbies:", font=ctk.CTkFont(family="Footlight MT Light", size=16), text_color="#000099").grid(row=3, column=2, padx=5, pady=5, sticky="nw")
 
        self.jScrollPane1.grid(row=3, column=3, rowspan=3, padx=5, pady=5, sticky="nsew")

        ctk.CTkLabel(content_parent, text="Comments:", font=ctk.CTkFont(family="Footlight MT Light", size=16), text_color="#000099").grid(row=6, column=0, columnspan=4, padx=5, pady=(20, 5), sticky="w")

        self.jTextArea1.grid(row=7, column=0, columnspan=4, padx=5, pady=5, sticky="ew")

        self.jPanel2.grid_columnconfigure(0, weight=1)
        self.jButton1.grid(row=0, column=0, pady=30) 

        self.Calendar.bind("<<CalendarSelected>>", self._CalendarPropertyChange)
        
=======

    def _set_initial_state(self):

        self.btnSave.configure(state="normal")
        self.btnSearch.configure(state="normal")
        self.btnDelete.configure(state="disabled")
        self.btnUpdate.configure(state="disabled")
        self.txtId.configure(state="normal", fg_color="white")

    def _validate_fields(self):

        if not self.txtId.get().strip() or not self.txtFirstName.get().strip() or not self.txtLastName.get().strip() or self.cmbType.get() == "Select" or self.button_group_sex.get() == "Unknown":
            messagebox.showwarning("Incomplete Data", "YOU MUST COMPLETE THE REQUIRED FIELDS ")
            self.txtId.configure(state="normal", fg_color="red")
            self.txtFirstName.configure(state="normal", fg_color="red")
            self.txtLastName.configure(state="normal", fg_color="red")
            self.cmbType.configure(fg_color="red")
            self.radSexMale.configure(text_color="red")
            self.radSexFemale.configure(text_color="red")
            return False
        else:
            self.txtId.configure(state="normal", fg_color="white")
            self.txtFirstName.configure(state="normal", fg_color="white")
            self.txtLastName.configure(state="normal", fg_color="white")
            self.cmbType.configure(fg_color="white")
            self.radSexMale.configure(text_color="black")
            self.radSexFemale.configure(text_color="black")

            name_pattern = r"^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$"
            if not re.match(name_pattern, self.txtFirstName.get().strip()):
                messagebox.showwarning("Invalid Input", "First Name must contain only letters.")
                self.txtFirstName.configure(fg_color="red") 
                return False

            if not re.match(name_pattern, self.txtLastName.get().strip()):
                messagebox.showwarning("Invalid Input", "Last Name must contain only letters.")
                self.txtLastName.configure(fg_color="red") 
                return False
        return True

>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
    def _calculateAge(self, birth_date):
        if birth_date is None:
            return 0
        birth = birth_date
        now = date.today()
        age = now.year - birth.year
        if (now.month, now.day) < (birth.month, birth.day):
            age -= 1
        return age
<<<<<<< HEAD
    
    def _radSexMaleActionPerformed(self):
        self.radSexFemale.configure(state='disabled' if self.button_group_sex.get() == "Male" else 'normal')

    def _radSexFemaleActionPerformed(self):
        self.radSexMale.configure(state='disabled' if self.button_group_sex.get() == "Female" else 'normal')
=======
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8

    def _CalendarPropertyChange(self, event):
        try:
            date_selected = self.Calendar.selection_get() 
            age = self._calculateAge(date_selected)
            self.txtAge.configure(text=str(age))
        except Exception as e:
            self.txtAge.configure(text="N/A")

<<<<<<< HEAD
    def _save_to_mongodb(self):
        try:
            selected_indices = self.lstHobbies.curselection()
            selected_hobbies = [self.lstHobbies.get(i) for i in selected_indices]
            document = {
                "id": self.txtId.get(),
                "firstName": self.txtFirstName.get(),
                "lastName": self.txtLastName.get(),
=======
    def _radSexMaleActionPerformed(self):
        pass

    def _radSexFemaleActionPerformed(self):
        pass

    def _save_to_mongodb(self):
        if not self._validate_fields():
            return

        try:
            id = self.txtId.get().strip()
            existing_doc = self.collection.find_one({"id": id})
            if existing_doc:
                messagebox.showwarning("Duplicate ID", f"The ID {id} already exists. Please use a different one.")
                return
            
            selected_indices = self.lstHobbies.curselection()
            selected_hobbies = [self.lstHobbies.get(i) for i in selected_indices]
            
            document = {
                "id": id,
                "firstName": self.txtFirstName.get().strip(),
                "lastName": self.txtLastName.get().strip(),
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
                "age": self.txtAge.cget("text"),
                "type": self.cmbType.get(),
                "sex": self.button_group_sex.get(),
                "hobbies": selected_hobbies,
<<<<<<< HEAD
                "comments": self.jTextArea1.get("1.0", "end-1c")
            }
            confirm = messagebox.askyesno("Save", f"Do you want to save {document['firstName']}?")
            if confirm:
                self.collection.insert_one(document)
                messagebox.showinfo("Successful", "Contact saved successfully in the cloud.")
=======
                "comments": self.jTextArea1.get("1.0", "end-1c").strip()
            }

            confirm = messagebox.askyesno("Save", f"Do you want to save {document['firstName']}?")
            if confirm:
                self.collection.insert_one(document)
                messagebox.showinfo("Successful", "Contact saved successfully.")
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
                self._empty_fields()
                
        except Exception as e:
            messagebox.showerror("Error", f"Could not save: {str(e)}")
<<<<<<< HEAD
    def _empty_fields(self):
=======

    def _search_contact(self):
        id = self.txtId.get().strip()
        if not id:
            messagebox.showwarning("Search", "Please enter an ID to search.")
            return

        try:
            doc = self.collection.find_one({"id": id})
            
            if doc:
                self.txtFirstName.delete(0, 'end')
                self.txtFirstName.insert(0, doc.get("firstName", ""))
                
                self.txtLastName.delete(0, 'end')
                self.txtLastName.insert(0, doc.get("lastName", ""))
                
                self.txtAge.configure(text=str(doc.get("age", "0")))
                
                raw_type = doc.get("type") or doc.get("typeOfContact")
                if isinstance(raw_type, list):
                    saved_type = str(raw_type[0]) if len(raw_type) > 0 else "Select"
                else:
                    saved_type = str(raw_type) if raw_type else "Select"
                self.cmbType.set(saved_type)

                saved_sex = doc.get("sex", "Unknown")
                if isinstance(saved_sex, list):
                    saved_sex = str(saved_sex[0]) if len(saved_sex) > 0 else "Unknown"
                
                self.button_group_sex.set(saved_sex)
                
                self.jTextArea1.delete("1.0", "end")
                self.jTextArea1.insert("1.0", doc.get("comments", ""))

                self.lstHobbies.selection_clear(0, 'end')
                saved_hobbies = doc.get("hobbies", [])
                for i, hobby in enumerate(self.hobbies_list):
                    if hobby in saved_hobbies:
                        self.lstHobbies.selection_set(i)

                self.txtId.configure(state="disabled", fg_color="#D3D3D3") 
                
                self.btnSave.configure(state="disabled")
                self.btnDelete.configure(state="normal")
                self.btnUpdate.configure(state="normal")
                
                messagebox.showinfo("Found", "Contact found")
            else:
                messagebox.showinfo("Not Found", "Contact not found.")
                self._empty_fields()

        except Exception as e:
            messagebox.showerror("Error", f"Search failed: {str(e)}")

    def _delete_contact(self):
        id = self.txtId.get()
        if not id: return

        confirm = messagebox.askyesno("Delete", f"Are you sure you want to DELETE: {self.txtFirstName.get()} {self.txtLastName.get()}?")
        if confirm:
            try:
                result = self.collection.delete_one({"id": id})
                if result.deleted_count > 0:
                    messagebox.showinfo("Success", "Contact deleted successfully.")
                    self._empty_fields()
                else:
                    messagebox.showwarning("Error", "Could not delete document.")
            except Exception as e:
                messagebox.showerror("Error", f"Delete failed: {str(e)}")

    def _update_contact(self):
        if not self._validate_fields():
            return
            
        id = self.txtId.get()
        
        confirm = messagebox.askyesno("Update", f"Are you sure you want to UPDATE info for: {self.txtFirstName.get()}?")
        if confirm:
            try:
                selected_indices = self.lstHobbies.curselection()
                selected_hobbies = [self.lstHobbies.get(i) for i in selected_indices]

                update_data = {
                    "$set": {
                        "firstName": self.txtFirstName.get().strip(),
                        "lastName": self.txtLastName.get().strip(),
                        "age": self.txtAge.cget("text"),
                        "type": self.cmbType.get(),
                        "sex": self.button_group_sex.get(),
                        "hobbies": selected_hobbies,
                        "comments": self.jTextArea1.get("1.0", "end-1c").strip()
                    }
                }
                
                self.collection.update_one({"id": id}, update_data)
                messagebox.showinfo("Success", "Contact updated successfully.")
                self._empty_fields()
                
            except Exception as e:
                messagebox.showerror("Error", f"Update failed: {str(e)}")

    def _empty_fields(self):
        self.txtId.configure(state="normal")
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
        self.txtId.delete(0, 'end')
        self.txtFirstName.delete(0, 'end')
        self.txtLastName.delete(0, 'end')
        self.jTextArea1.delete("1.0", "end")
        self.lstHobbies.selection_clear(0, 'end')
        self.txtAge.configure(text="0")
<<<<<<< HEAD
=======
        self.cmbType.set("Select")
        self.button_group_sex.set("Unknown")
        self.radSexMale.configure(state='normal')
        self.radSexFemale.configure(state='normal')

        self._set_initial_state()
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8

if __name__ == "__main__":
    ctk.set_appearance_mode("Light")
    ctk.set_default_color_theme("blue")

    app = FrmContacts()
    app.mainloop()