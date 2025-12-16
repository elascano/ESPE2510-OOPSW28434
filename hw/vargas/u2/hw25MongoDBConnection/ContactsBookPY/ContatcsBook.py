import tkinter as tk
from tkinter import ttk, messagebox
from tkcalendar import DateEntry
from datetime import date
from Contact import Contact
from ContactController import ContactController
<<<<<<< HEAD
from ValidationUtils import GUIValidation 
=======
<<<<<<< HEAD
=======
from ValidationUtils import GUIValidation 
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e

class FrmContacts(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Contacts Book")
        self.geometry("850x600")
        
        self.lbl_title = tk.Label(self, text="CONTACTS", font=("Segoe UI", 24))
        self.lbl_title.pack(pady=20)

        self.frm_form = tk.Frame(self)
        self.frm_form.pack(expand=True, fill="both", padx=20)

        tk.Label(self.frm_form, text="id:").grid(row=0, column=0, sticky="w", pady=5)

        tk.Label(self.frm_form, text="First Name:").grid(row=1, column=0, sticky="w", pady=5)
        self.txt_first = tk.Entry(self.frm_form, width=20)
        self.txt_first.grid(row=1, column=1, sticky="w", pady=5)

        tk.Label(self.frm_form, text="Last Name:").grid(row=2, column=0, sticky="w", pady=5)
        self.txt_last = tk.Entry(self.frm_form, width=20)
        self.txt_last.grid(row=2, column=1, sticky="w", pady=5)

        tk.Label(self.frm_form, text="Birth Date:").grid(row=3, column=0, sticky="w", pady=5)
        self.cal_date = DateEntry(self.frm_form, width=17, background='darkblue',
                                  foreground='white', borderwidth=2, date_pattern='dd/mm/yyyy')
        self.cal_date.grid(row=3, column=1, sticky="w", pady=5)

        tk.Label(self.frm_form, text="Age:").grid(row=4, column=0, sticky="w", pady=5)
        self.lbl_age_val = tk.Label(self.frm_form, text="19")
        self.lbl_age_val.grid(row=4, column=1, sticky="w", pady=5)

        tk.Label(self.frm_form, text="Type:").grid(row=5, column=0, sticky="w", pady=5)
        self.cmb_type = ttk.Combobox(self.frm_form, values=["Family", "Friend", "Job", "Unknown"], state="readonly")
        self.cmb_type.current(0)
        self.cmb_type.grid(row=5, column=1, sticky="w", pady=5)

        tk.Label(self.frm_form, text="Sex:").grid(row=6, column=0, sticky="nw", pady=5)
        self.var_sex = tk.StringVar(value="Male")
        
        frm_sex = tk.Frame(self.frm_form)
        frm_sex.grid(row=6, column=1, sticky="w")
        tk.Radiobutton(frm_sex, text="Male", variable=self.var_sex, value="Male").pack(anchor="w")
        tk.Radiobutton(frm_sex, text="Female", variable=self.var_sex, value="Female").pack(anchor="w")

        tk.Label(self.frm_form, text="Hobbies:").grid(row=7, column=0, sticky="nw", pady=5)
        
        frm_hobbies = tk.Frame(self.frm_form)
        frm_hobbies.grid(row=7, column=1, sticky="w")
        
        scrollbar_h = tk.Scrollbar(frm_hobbies)
        scrollbar_h.pack(side=tk.RIGHT, fill=tk.Y)
        
        self.lst_hobbies = tk.Listbox(frm_hobbies, height=5, width=20, 
                                      selectmode=tk.MULTIPLE, yscrollcommand=scrollbar_h.set)
        hobbies_list = ["Play Soccer", "Djing", "Read", "Cook", "Swim", "Sing", "Play an instrument"]
        for item in hobbies_list:
            self.lst_hobbies.insert(tk.END, item)
            
        self.lst_hobbies.pack(side=tk.LEFT)
        scrollbar_h.config(command=self.lst_hobbies.yview)

        tk.Label(self.frm_form, text="Comments:").grid(row=0, column=3, sticky="nw", padx=(50,0))
        
        frm_comments = tk.Frame(self.frm_form)
        frm_comments.grid(row=1, column=3, rowspan=8, padx=(50,0), sticky="nw")
        
        scrollbar_c = tk.Scrollbar(frm_comments)
        scrollbar_c.pack(side=tk.RIGHT, fill=tk.Y)
        
        self.txt_comments = tk.Text(frm_comments, width=30, height=15, yscrollcommand=scrollbar_c.set)
        self.txt_comments.pack(side=tk.LEFT)
        scrollbar_c.config(command=self.txt_comments.yview)

        self.frm_buttons = tk.Frame(self)
        self.frm_buttons.pack(side=tk.BOTTOM, pady=20)
        
        self.btn_save = tk.Button(self.frm_buttons, text="Save", width=10, command=self.btn_save_action)
        self.btn_save.pack()

    def read_values(self):
        id_val = 1
        first_name = self.txt_first.get()
        last_name = self.txt_last.get()
        
<<<<<<< HEAD
        birth_date = self.cal_date.get_date()
        birth_year = birth_date.year
=======
<<<<<<< HEAD
        birth_year = self.cal_date.get_date().year
=======
        birth_date = self.cal_date.get_date()
        birth_year = birth_date.year
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
        current_year = date.today().year
        age = current_year - birth_year
        self.lbl_age_val.config(text=str(age))

        type_contact = self.cmb_type.get()
        sex = self.var_sex.get()
        
        selected_indices = self.lst_hobbies.curselection()
        hobbies = [self.lst_hobbies.get(i) for i in selected_indices]
        
        comments = self.txt_comments.get("1.0", tk.END).strip()

        return Contact(
            id=id_val, 
            firstName=first_name, 
            lastName=last_name, 
            age=age, 
            typeOfContact=type_contact, 
            sex=sex, 
            hobbies=hobbies, 
            comments=comments
        )

    def btn_save_action(self):
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
        first_name = self.txt_first.get()
        last_name = self.txt_last.get()
        birth_date = self.cal_date.get_date() 

        if not GUIValidation.is_not_empty(first_name):
            messagebox.showerror("Validation error", "The First Name field cannot be empty.")
            self.txt_first.focus_set()
            return
        if not GUIValidation.is_valid_name(first_name):
            messagebox.showerror("Validation error", "The First Name contains invalid characters (letters only).")
            self.txt_first.focus_set()
            return

        if not GUIValidation.is_not_empty(last_name):
            messagebox.showerror("Validation error", "The Last Name field cannot be empty.")
            self.txt_last.focus_set()
            return
        if not GUIValidation.is_valid_name(last_name):
            messagebox.showerror("Validation error", "The Last Name contains invalid characters (letters only).")
            self.txt_last.focus_set()
            return
            
        if not GUIValidation.is_date_not_future(birth_date):
            messagebox.showerror("Validation error", "The Birth Date cannot be a future date.")
            self.cal_date.focus_set()
            return
            
<<<<<<< HEAD
=======
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
        contact = self.read_values()

        response = messagebox.askyesnocancel("SAVE CONTACTS?", f"saving contact --> {contact}")

        if response is True:
            saved = ContactController.save(contact)
            if saved:
                messagebox.showinfo("Success", f"your contact is saved --> {contact}")
                self.empty_fields()
            else:
                messagebox.showerror("Error", "Could not save to MongoDB")
                
        elif response is False:
<<<<<<< HEAD
            messagebox.showwarning("Warning", "Your data will be lost")
=======
<<<<<<< HEAD
             messagebox.showwarning("Warning", "Your data will be lost")
=======
            messagebox.showwarning("Warning", "Your data will be lost")
>>>>>>> 95b5ff6cf684ae826e202c2e63b274c2ad0a5ba8
>>>>>>> 58e5d161bc1779e9dbf6e4ea38d18547b28d0a4e
        else:
            self.txt_first.focus_set()

    def empty_fields(self):
        self.txt_first.delete(0, tk.END)
        self.txt_last.delete(0, tk.END)
        self.cal_date.set_date(date.today())
        self.lbl_age_val.config(text="0")
        self.cmb_type.current(0)
        self.var_sex.set("Male")
        self.lst_hobbies.selection_clear(0, tk.END)
        self.txt_comments.delete("1.0", tk.END)

if __name__ == "__main__":
    app = FrmContacts()
    app.mainloop()