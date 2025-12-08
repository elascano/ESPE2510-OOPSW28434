import tkinter as tk
from tkinter import ttk, messagebox
from tkcalendar import DateEntry
from pymongo import MongoClient
from datetime import date, datetime

class FrmContactsVisual:
    def __init__(self, root):
        self.root = root
        self.root.title("Contacts Book")
        self.root.geometry("720x550") 

        MONGO_URI = 'mongodb+srv://Arelys:Arelys1234@cluster0.3u6ujwz.mongodb.net/Contacts?retryWrites=true&w=majority' 
        DB_NAME = 'Contacts'
        COLLECTION_NAME = 'ContactsBook'
        
        try:
            self.client = MongoClient(MONGO_URI)
            self.db = self.client[DB_NAME]
            self.collection = self.db[COLLECTION_NAME]
            self.client.admin.command('ping') 
        except Exception as e:
            messagebox.showerror("Error de Conexión", f"No se pudo conectar a MongoDB: {e}")
            self.client = None 
            
        self.id_var = tk.StringVar(value="") 
        
        self.create_widgets()
        self.id_label.grid(row=0, column=1, sticky="w", pady=5)

    def create_widgets(self):
        lbl_title = tk.Label(self.root, text="CONTACTS", font=("Segoe UI", 24, "bold"))
        lbl_title.pack(pady=20)
        
        main_frame = tk.Frame(self.root)
        main_frame.pack(expand=True, fill="both", padx=30)

        tk.Label(main_frame, text="id:").grid(row=0, column=0, sticky="w", pady=5)
        self.id_label = tk.Label(main_frame, textvariable=self.id_var) 
        
        tk.Label(main_frame, text="First Name:").grid(row=1, column=0, sticky="w", pady=5)
        self.txt_first = tk.Entry(main_frame, width=20)
        self.txt_first.grid(row=1, column=1, sticky="w", pady=5)

        tk.Label(main_frame, text="Last Name:").grid(row=2, column=0, sticky="w", pady=5)
        self.txt_last = tk.Entry(main_frame, width=20)
        self.txt_last.grid(row=2, column=1, sticky="w", pady=5)

        tk.Label(main_frame, text="Birth Date:").grid(row=3, column=0, sticky="w", pady=5)

        today = date.today()
        initial_date = date(today.year - 20, today.month, today.day)
        
        self.cal_date = DateEntry(main_frame, width=17, background='darkblue',
                                 foreground='white', borderwidth=2, 
                                 date_pattern='dd/mm/yyyy',
                                 year=initial_date.year, 
                                 month=initial_date.month, 
                                 day=initial_date.day) 
        self.cal_date.grid(row=3, column=1, sticky="w", pady=5)

        tk.Label(main_frame, text="Type:").grid(row=5, column=0, sticky="w", pady=5)
        self.cmb_type = ttk.Combobox(main_frame, values=["Family", "Friend", "Job", "Unknown"], state="readonly")
        self.cmb_type.current(0)
        self.cmb_type.grid(row=5, column=1, sticky="w", pady=5)

        tk.Label(main_frame, text="Sex:").grid(row=6, column=0, sticky="nw", pady=5)
        self.sex_var = tk.StringVar(value="M") 
        
        frame_sex = tk.Frame(main_frame)
        frame_sex.grid(row=6, column=1, sticky="w")
        tk.Radiobutton(frame_sex, text="Male", variable=self.sex_var, value="M").pack(anchor="w")
        tk.Radiobutton(frame_sex, text="Female", variable=self.sex_var, value="F").pack(anchor="w")

        tk.Label(main_frame, text="Hobbies:").grid(row=7, column=0, sticky="nw", pady=5)
        
        frame_hobbies = tk.Frame(main_frame)
        frame_hobbies.grid(row=7, column=1, sticky="w")
        
        scrollbar = tk.Scrollbar(frame_hobbies)
        scrollbar.pack(side=tk.RIGHT, fill=tk.Y)
        
        self.lst_hobbies = tk.Listbox(frame_hobbies, height=4, width=20, 
                                     selectmode=tk.MULTIPLE, yscrollcommand=scrollbar.set)
        
        hobbies = ["Play Soccer", "Djing", "Read", "Cook", "Swim", "Sing", " Play an Instrument"]
        for h in hobbies:
            self.lst_hobbies.insert(tk.END, h)
            
        self.lst_hobbies.pack(side=tk.LEFT)
        scrollbar.config(command=self.lst_hobbies.yview)

        tk.Label(main_frame, text="Comments:").grid(row=0, column=2, sticky="nw", padx=(50,0))
        self.txt_comments = tk.Text(main_frame, width=30, height=18)
        self.txt_comments.grid(row=1, column=2, rowspan=8, padx=(50,0), sticky="nw")

        btn_save = tk.Button(self.root, text="Save", width=10, command=self.save_contact)
        btn_save.pack(pady=20)
        
    def get_selected_hobbies(self):
        selected_indices = self.lst_hobbies.curselection()
        selected_hobbies = [self.lst_hobbies.get(i) for i in selected_indices]
        return selected_hobbies

    def save_contact(self):
        if self.client is None:
            messagebox.showerror("Error", "No hay conexión a la base de datos.")
            return

        first_name = self.txt_first.get()
        last_name = self.txt_last.get()
        birth_date = self.cal_date.get_date()
        contact_type = self.cmb_type.get()
        sex = self.sex_var.get()
        hobbies = self.get_selected_hobbies()
        comments = self.txt_comments.get("1.0", tk.END).strip()

        if not first_name or not last_name:
            messagebox.showwarning("Advertencia", "Los campos Nombre y Apellido son obligatorios.")
            return

        try:
            birth_date_dt = datetime.combine(birth_date, datetime.min.time()) 
            created_at_dt = datetime.combine(date.today(), datetime.min.time())
        except ValueError as e:
            messagebox.showerror("Error de Datos", f"Fecha inválida: {e}")
            return
            
        contact_data = {
            "first_name": first_name,
            "last_name": last_name,
            "birth_date": birth_date_dt,
            "type": contact_type,
            "sex": sex,
            "hobbies": hobbies,
            "comments": comments,
            "created_at": created_at_dt 
        }

        try:
            result = self.collection.insert_one(contact_data)
            self.id_var.set(str(result.inserted_id))
            messagebox.showinfo("Éxito", f"Contacto guardado con ID: {result.inserted_id}")
            self.clear_form()
        except Exception as e:
            messagebox.showerror("Error de Guardado", f"Ocurrió un error al guardar el contacto: {e}")

    def clear_form(self):
        self.txt_first.delete(0, tk.END)
        self.txt_last.delete(0, tk.END)
        initial_date = date.today().replace(year=date.today().year - 20)
        self.cal_date.set_date(initial_date)
        self.cmb_type.current(0)
        self.sex_var.set("M")
        self.lst_hobbies.selection_clear(0, tk.END)
        self.txt_comments.delete("1.0", tk.END)
        self.id_var.set("") 

if __name__ == "__main__":
    root = tk.Tk()
    app = FrmContactsVisual(root)
    root.mainloop()
