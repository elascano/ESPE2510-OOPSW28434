import tkinter as tk
from tkinter import ttk, Listbox, MULTIPLE, messagebox

class FrmBilling(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Sistema de Facturación - Clínica")
        self.geometry("500x350")
        self._create_widgets()

    def _create_widgets(self):
        # 1. Panel Superior (Título)
        # ---------------------------
        header_frame = ttk.Frame(self, padding="10 10 10 10")
        header_frame.pack(fill='x')

        ttk.Label(header_frame, text="Facturación Clínica", font=('Segoe UI', 20, 'bold')).pack(pady=5)

        # 2. Panel Central (Campos de Facturación)
        # ----------------------------------------
        form_frame = ttk.Frame(self, padding="10")
        form_frame.pack(fill='both', expand=True)
        
        # Uso de Grid
        form_frame.columnconfigure(1, weight=1) 

        row = 0
        
        # 1. Cédula
        ttk.Label(form_frame, text="Cédula/ID:").grid(row=row, column=0, sticky='w', padx=5, pady=8)
        self.txt_cedula = ttk.Entry(form_frame, width=35)
        self.txt_cedula.grid(row=row, column=1, sticky='we', padx=5, pady=8)
        
        row += 1
        # 2. Nombre
        ttk.Label(form_frame, text="Nombre Paciente:").grid(row=row, column=0, sticky='w', padx=5, pady=8)
        self.txt_nombre = ttk.Entry(form_frame, width=35)
        self.txt_nombre.grid(row=row, column=1, sticky='we', padx=5, pady=8)
        
        row += 1
        # 3. Dirección de Casa
        ttk.Label(form_frame, text="Dirección:").grid(row=row, column=0, sticky='w', padx=5, pady=8)
        self.txt_direccion = ttk.Entry(form_frame, width=35)
        self.txt_direccion.grid(row=row, column=1, sticky='we', padx=5, pady=8)

        row += 1
        # 4. Costo a Cobrar
        ttk.Label(form_frame, text="Costo a Cobrar ($):").grid(row=row, column=0, sticky='w', padx=5, pady=8)
        self.txt_costo = ttk.Entry(form_frame, width=35)
        self.txt_costo.grid(row=row, column=1, sticky='we', padx=5, pady=8)
        
        # 3. Panel Inferior (Botón de Guardar)
        # ------------------------------------
        footer_frame = ttk.Frame(self, padding="10")
        footer_frame.pack(fill='x', side='bottom')
        
        # Centrar el botón
        footer_frame.columnconfigure(0, weight=1) 
        
        self.save_button = ttk.Button(footer_frame, text="Guardar Factura", command=self.save_billing)
        self.save_button.grid(row=0, column=0, pady=10)


    def save_billing(self):
        """Función que se llama al pulsar el botón de guardar."""
        
        # Obtener los datos de los campos
        cedula = self.txt_cedula.get()
        nombre = self.txt_nombre.get()
        direccion = self.txt_direccion.get()
        costo = self.txt_costo.get()
        
        # Validación básica (opcional)
        if not (cedula and nombre and direccion and costo):
            messagebox.showerror("Error", "Todos los campos deben ser llenados.")
            return

        # Aquí es donde integrarías la lógica para guardar en una base de datos o archivo
        billing_data = {
            "Cédula": cedula,
            "Nombre Paciente": nombre,
            "Dirección": direccion,
            "Costo": f"${costo}"
        }

        print("--- Factura Generada ---")
        for key, value in billing_data.items():
            print(f"{key}: {value}")
        print("------------------------")
        
        messagebox.showinfo("Éxito", f"Factura de {nombre} por ${costo} guardada correctamente.")

if __name__ == "__main__":
    app = FrmBilling()
    app.mainloop()