import tkinter as tk
from tkinter import ttk, messagebox
from datetime import datetime
import calendar

class FrmContacts:
    def __init__(self, root):
        self.root = root
        self.root.title("CONTACTS BOOK")
        self.root.geometry("800x700")
        
        # Variables
        self.sex_var = tk.StringVar(value="")
        self.age_var = tk.StringVar(value="0")
        
        self.setup_ui()
        self.init_calendar()
        
    def setup_ui(self):
        # Panel 1 - Título
        self.panel1 = tk.Frame(self.root, bg="white", height=100)
        self.panel1.pack(fill="x", pady=(20, 0))
        
        self.lbl_title = tk.Label(self.panel1, text="CONTACTS", 
                                  font=("Segoe UI", 25), bg="white")
        self.lbl_title.pack(pady=20)
        
        # Panel 2 - Formulario principal
        self.panel2 = tk.Frame(self.root)
        self.panel2.pack(fill="both", expand=True, padx=20, pady=20)
        
        # Fila 1 - Nombre
        self.lbl_first_name = tk.Label(self.panel2, text="First Name:")
        self.lbl_first_name.grid(row=0, column=0, sticky="e", padx=5, pady=5)
        
        self.txt_first_name = tk.Entry(self.panel2, width=30)
        self.txt_first_name.grid(row=0, column=1, padx=5, pady=5, sticky="w")
        
        self.lbl_comments = tk.Label(self.panel2, text="Comments:")
        self.lbl_comments.grid(row=0, column=2, sticky="e", padx=5, pady=5)
        
        # Área de texto para comentarios
        self.txt_comments = tk.Text(self.panel2, width=30, height=5)
        self.txt_comments.grid(row=1, column=2, rowspan=6, padx=5, pady=5, sticky="n")
        
        # Fila 2 - Apellido
        self.lbl_last_name = tk.Label(self.panel2, text="Last Name:")
        self.lbl_last_name.grid(row=1, column=0, sticky="e", padx=5, pady=5)
        
        self.txt_last_name = tk.Entry(self.panel2, width=30)
        self.txt_last_name.grid(row=1, column=1, padx=5, pady=5, sticky="w")
        
        # Fila 3 - Fecha de nacimiento
        self.lbl_birth_date = tk.Label(self.panel2, text="Birth Date:")
        self.lbl_birth_date.grid(row=2, column=0, sticky="e", padx=5, pady=5)
        
        # Comboboxes para fecha
        frame_date = tk.Frame(self.panel2)
        frame_date.grid(row=2, column=1, padx=5, pady=5, sticky="w")
        
        # Año
        self.cmb_year = ttk.Combobox(frame_date, width=8, state="readonly")
        self.cmb_year.pack(side="left", padx=2)
        self.cmb_year.bind("<<ComboboxSelected>>", self.on_date_change)
        
        # Mes
        self.cmb_month = ttk.Combobox(frame_date, width=6, state="readonly")
        self.cmb_month.pack(side="left", padx=2)
        self.cmb_month.bind("<<ComboboxSelected>>", self.on_date_change)
        
        # Día
        self.cmb_day = ttk.Combobox(frame_date, width=6, state="readonly")
        self.cmb_day.pack(side="left", padx=2)
        self.cmb_day.bind("<<ComboboxSelected>>", self.on_date_change)
        
        # Fila 4 - Edad
        self.lbl_age = tk.Label(self.panel2, text="Age:")
        self.lbl_age.grid(row=3, column=0, sticky="e", padx=5, pady=5)
        
        self.lbl_age_value = tk.Label(self.panel2, textvariable=self.age_var, 
                                      fg="blue", font=("Arial", 10, "bold"))
        self.lbl_age_value.grid(row=3, column=1, padx=5, pady=5, sticky="w")
        
        # Fila 5 - Tipo
        self.lbl_type = tk.Label(self.panel2, text="Type:")
        self.lbl_type.grid(row=4, column=0, sticky="e", padx=5, pady=5)
        
        self.cmb_type = ttk.Combobox(self.panel2, width=28, state="readonly")
        self.cmb_type['values'] = ("Family", "Friend", "Job", "Unknown")
        self.cmb_type.current(0)
        self.cmb_type.grid(row=4, column=1, padx=5, pady=5, sticky="w")
        
        # Fila 6 - Sexo
        self.lbl_sex = tk.Label(self.panel2, text="Sex:")
        self.lbl_sex.grid(row=5, column=0, sticky="e", padx=5, pady=5)
        
        frame_sex = tk.Frame(self.panel2)
        frame_sex.grid(row=5, column=1, padx=5, pady=5, sticky="w")
        
        self.rad_male = tk.Radiobutton(frame_sex, text="Male", 
                                       variable=self.sex_var, value="Male",
                                       command=self.on_sex_selected)
        self.rad_male.pack(side="left", padx=10)
        
        self.rad_female = tk.Radiobutton(frame_sex, text="Female", 
                                         variable=self.sex_var, value="Female",
                                         command=self.on_sex_selected)
        self.rad_female.pack(side="left", padx=10)
        
        # Fila 7 - Hobbies (Etiqueta)
        self.lbl_hobbies = tk.Label(self.panel2, text="Hobbies:")
        self.lbl_hobbies.grid(row=6, column=0, sticky="ne", padx=5, pady=5)
        
        # Frame para hobbies
        frame_hobbies = tk.Frame(self.panel2)
        frame_hobbies.grid(row=6, column=1, padx=5, pady=5, sticky="w")
        
        # Variables para checkboxes de hobbies
        self.hobbies_vars = []
        hobbies_list = [
            "Play Soccer", "DJ", "Read", "Cook", 
            "Swim", "Sing", "Play an Instrument"
        ]
        
        for i, hobby in enumerate(hobbies_list):
            var = tk.BooleanVar()
            self.hobbies_vars.append(var)
            chk = tk.Checkbutton(frame_hobbies, text=hobby, variable=var)
            chk.pack(anchor="w")
        
        # Botón Guardar
        self.btn_save = tk.Button(self.panel2, text="Save", 
                                  command=self.on_save, bg="#4CAF50", 
                                  fg="white", width=15, height=2)
        self.btn_save.grid(row=7, column=1, pady=20, sticky="w")
        
        # Panel 3 (vacío como en Java)
        self.panel3 = tk.Frame(self.root, height=82, bg="lightgray")
        self.panel3.pack(fill="x", padx=25, pady=(0, 22))
        
    def init_calendar(self):
        """Inicializa los comboboxes de fecha con valores por defecto"""
        # Años (1975-2024)
        years = [str(y) for y in range(1975, 2025)]
        self.cmb_year['values'] = years
        self.cmb_year.set("2000")  # Año por defecto
        
        # Meses (1-12)
        months = [str(m).zfill(2) for m in range(1, 13)]
        self.cmb_month['values'] = months
        self.cmb_month.set("01")  # Mes por defecto
        
        # Actualizar días según mes seleccionado
        self.update_days()
        
        # Seleccionar primer día
        self.cmb_day.current(0)
        
        # Calcular edad inicial
        self.calculate_age()
    
    def update_days(self):
        """Actualiza los días disponibles según el mes y año seleccionados"""
        try:
            # Guardar selección actual si existe
            current_day = self.cmb_day.get()
            
            # Obtener año y mes
            year = int(self.cmb_year.get())
            month = int(self.cmb_month.get())
            
            # Calcular días en el mes
            days_in_month = calendar.monthrange(year, month)[1]
            
            # Actualizar días
            days = [str(d).zfill(2) for d in range(1, days_in_month + 1)]
            self.cmb_day['values'] = days
            
            # Mantener selección anterior si es válida
            if current_day in days:
                self.cmb_day.set(current_day)
            elif days:
                self.cmb_day.set(days[0])
        except:
            # En caso de error, usar 31 días por defecto
            days = [str(d).zfill(2) for d in range(1, 32)]
            self.cmb_day['values'] = days
            if days:
                self.cmb_day.set(days[0])
    
    def calculate_age(self):
        """Calcula la edad automáticamente a partir de la fecha de nacimiento"""
        try:
            # Obtener valores de fecha
            year = int(self.cmb_year.get())
            month = int(self.cmb_month.get())
            day = int(self.cmb_day.get())
            
            # Fecha actual
            today = datetime.now()
            birth_date = datetime(year, month, day)
            
            # Calcular edad
            age = today.year - birth_date.year
            
            # Ajustar si aún no ha pasado el cumpleaños este año
            if (today.month, today.day) < (birth_date.month, birth_date.day):
                age -= 1
            
            # Actualizar variable
            self.age_var.set(str(age))
            
        except Exception as e:
            # En caso de error, mostrar 0
            self.age_var.set("0")
    
    def on_date_change(self, event=None):
        """Manejador de eventos para cambios en la fecha"""
        self.update_days()
        self.calculate_age()
    
    def on_sex_selected(self):
        """Manejador para selección de sexo"""
        # Esta función asegura que solo un radio button esté seleccionado
        # tkinter ya maneja esto automáticamente con variable compartida
        pass
    
    def on_save(self):
        """Manejador para el botón Guardar"""
        # Obtener datos del formulario
        first_name = self.txt_first_name.get()
        last_name = self.txt_last_name.get()
        birth_date = f"{self.cmb_year.get()}-{self.cmb_month.get()}-{self.cmb_day.get()}"
        age = self.age_var.get()
        contact_type = self.cmb_type.get()
        sex = self.sex_var.get()
        comments = self.txt_comments.get("1.0", tk.END).strip()
        
        # Obtener hobbies seleccionados
        hobbies_selected = []
        hobbies_list = [
            "Play Soccer", "DJ", "Read", "Cook", 
            "Swim", "Sing", "Play an Instrument"
        ]
        for i, var in enumerate(self.hobbies_vars):
            if var.get():
                hobbies_selected.append(hobbies_list[i])
        
        # Validación básica
        if not first_name or not last_name:
            messagebox.showwarning("Warning", "Please enter first name and last name")
            return
        
        # Mostrar datos en consola (o aquí podrías guardar en base de datos)
        print("\n=== CONTACT SAVED ===")
        print(f"Name: {first_name} {last_name}")
        print(f"Birth Date: {birth_date}")
        print(f"Age: {age}")
        print(f"Type: {contact_type}")
        print(f"Sex: {sex}")
        print(f"Hobbies: {', '.join(hobbies_selected) if hobbies_selected else 'None'}")
        print(f"Comments: {comments}")
        print("====================\n")
        
        # Mostrar mensaje de éxito
        messagebox.showinfo("Success", "Contact saved successfully!")
        
        # Aquí puedes agregar lógica para guardar en base de datos o archivo
    
    def run(self):
        """Ejecuta la aplicación"""
        self.root.mainloop()


def main():
    root = tk.Tk()
    app = FrmContacts(root)
    app.run()


if __name__ == "__main__":
    main()