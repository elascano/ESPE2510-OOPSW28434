import tkinter as tk
from tkinter import ttk, scrolledtext, Listbox, SINGLE, MULTIPLE

class FrmContacts(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Contacts Book - Python/Tkinter")
        self.geometry("700x650") # Establece un tamaño inicial similar al de la captura de Java
        self._create_widgets()

    def _create_widgets(self):
        # 1. Panel Superior (Título)
        # ---------------------------
        header_frame = ttk.Frame(self, padding="10 10 10 10")
        header_frame.pack(fill='x')

        ttk.Label(header_frame, text="Contacts", font=('Segoe UI', 24)).pack(pady=10)

        # 2. Panel Central (Campos del Formulario)
        # ----------------------------------------
        form_frame = ttk.Frame(self, padding="10")
        form_frame.pack(fill='both', expand=True)
        
        # Uso de Grid para organizar el formulario en columnas y filas
        form_frame.columnconfigure(1, weight=1) # Columna de entrada se expande

        # --- Columna Izquierda (Datos Personales) ---

        row = 0
        ttk.Label(form_frame, text="id:").grid(row=row, column=0, sticky='w', padx=5, pady=5)
        self.txt_id = ttk.Entry(form_frame, width=30)
        self.txt_id.grid(row=row, column=1, sticky='w', padx=5, pady=5)
        
        row += 1
        ttk.Label(form_frame, text="First Name:").grid(row=row, column=0, sticky='w', padx=5, pady=5)
        self.txt_first_name = ttk.Entry(form_frame, width=30)
        self.txt_first_name.grid(row=row, column=1, sticky='w', padx=5, pady=5)
        
        row += 1
        ttk.Label(form_frame, text="Last Name:").grid(row=row, column=0, sticky='w', padx=5, pady=5)
        self.txt_last_name = ttk.Entry(form_frame, width=30)
        self.txt_last_name.grid(row=row, column=1, sticky='w', padx=5, pady=5)

        row += 1
        ttk.Label(form_frame, text="Birth Date:").grid(row=row, column=0, sticky='w', padx=5, pady=5)
        self.txt_birth_date = ttk.Entry(form_frame, width=30)
        self.txt_birth_date.grid(row=row, column=1, sticky='w', padx=5, pady=5)
        
        row += 1
        ttk.Label(form_frame, text="Age:").grid(row=row, column=0, sticky='w', padx=5, pady=5)
        self.txt_age = ttk.Entry(form_frame, width=30)
        self.txt_age.grid(row=row, column=1, sticky='w', padx=5, pady=5)
        
        row += 1
        ttk.Label(form_frame, text="Type:").grid(row=row, column=0, sticky='w', padx=5, pady=5)
        type_options = ["Family", "Friend", "Job", "Unknow"]
        self.cmb_type = ttk.Combobox(form_frame, values=type_options, state="readonly", width=27)
        self.cmb_type.set("Family") # Establecer valor por defecto
        self.cmb_type.grid(row=row, column=1, sticky='w', padx=5, pady=5)
        
        row += 1
        ttk.Label(form_frame, text="Sex:").grid(row=row, column=0, sticky='nw', padx=5, pady=10)
        self.sex_var = tk.StringVar(value="Male")
        male_radio = ttk.Radiobutton(form_frame, text="Male", variable=self.sex_var, value="Male")
        female_radio = ttk.Radiobutton(form_frame, text="Female", variable=self.sex_var, value="Female")
        male_radio.grid(row=row, column=1, sticky='w', padx=5, pady=5)
        female_radio.grid(row=row + 1, column=1, sticky='w', padx=5, pady=5)

        # Hobbies (usando el mismo 'row' inicial para el label, pero el listbox ocupa más)
        row += 2 
        ttk.Label(form_frame, text="Hobbies:").grid(row=row, column=0, sticky='nw', padx=5, pady=5)
        
        self.lst_hobbies = Listbox(
            form_frame, 
            selectmode=MULTIPLE, # Propiedad clave: HABILITA SELECCIÓN MÚLTIPLE
            height=6, 
            width=25
        )
        # Inicializar los datos de la lista
        hobbies = ["Play Soccer", "Djing", "Read", "Cook", "Swim", "Sing", "Play an instrument"]
        for item in hobbies:
            self.lst_hobbies.insert(tk.END, item)
            
        # Colocamos el Listbox
        self.lst_hobbies.grid(row=row, column=1, sticky='w', padx=5, pady=5)
        
        # --- Columna Derecha (Comentarios) ---
        
        # Añadir un separador visual entre columnas
        ttk.Separator(form_frame, orient='vertical').grid(row=0, column=2, rowspan=10, sticky='ns', padx=20)
        
        # Área de Comentarios
        ttk.Label(form_frame, text="Comments").grid(row=0, column=3, sticky='w', padx=5, pady=5)
        self.txt_comments = scrolledtext.ScrolledText(form_frame, wrap=tk.WORD, width=35, height=10)
        self.txt_comments.grid(row=1, column=3, rowspan=5, sticky='nsew', padx=5, pady=5)
        
        # Configurar la columna de comentarios para expandirse
        form_frame.columnconfigure(3, weight=1) 
        
        # 3. Panel Inferior (Botón de Guardar)
        # ------------------------------------
        footer_frame = ttk.Frame(self, padding="10")
        footer_frame.pack(fill='x', side='bottom')
        
        # Centrar el botón
        footer_frame.columnconfigure(0, weight=1) 
        
        self.save_button = ttk.Button(footer_frame, text="Save", command=self.save_contact)
        self.save_button.grid(row=0, column=0, pady=20)


    def save_contact(self):
        """Función que se llama al pulsar el botón Save."""
        
        # 1. Obtener la selección de Hobbies (la respuesta a tu pregunta original)
        selected_indices = self.lst_hobbies.curselection()
        selected_hobbies = [self.lst_hobbies.get(i) for i in selected_indices]

        # 2. Obtener los demás datos
        contact_data = {
            "id": self.txt_id.get(),
            "First Name": self.txt_first_name.get(),
            "Last Name": self.txt_last_name.get(),
            "Birth Date": self.txt_birth_date.get(),
            "Age": self.txt_age.get(),
            "Type": self.cmb_type.get(),
            "Sex": self.sex_var.get(),
            "Hobbies": selected_hobbies,
            "Comments": self.txt_comments.get("1.0", tk.END).strip()
        }

        print("--- Contacto Guardado ---")
        for key, value in contact_data.items():
            print(f"{key}: {value}")
        print("-------------------------")

if __name__ == "__main__":
    app = FrmContacts()
    app.mainloop()