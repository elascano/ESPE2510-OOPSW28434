import tkinter as tk
from tkinter import ttk, messagebox, scrolledtext
from controller.event_controller import EventController
from model.event import Event
from strategy.factory import StorageStrategyFactory

class CalendarGUI:
    def __init__(self, root):
        self.root = root
        self.root.title("Sistema Calendar MVC - Python")
        self.root.geometry("600x500")
        
        # Inicializar controlador
        self.controller = EventController()
        
        # Variables
        self.storage_type_var = tk.StringVar(value="json")
        
        self.setup_ui()
    
    def setup_ui(self):
        # Frame principal
        main_frame = ttk.Frame(self.root, padding="10")
        main_frame.grid(row=0, column=0, sticky=(tk.W, tk.E, tk.N, tk.S))
        
        # Configurar grid weights
        self.root.columnconfigure(0, weight=1)
        self.root.rowconfigure(0, weight=1)
        main_frame.columnconfigure(1, weight=1)
        
        # 1. Selector de almacenamiento
        ttk.Label(main_frame, text="Tipo de Almacenamiento:").grid(
            row=0, column=0, padx=5, pady=5, sticky=tk.W)
        
        storage_combo = ttk.Combobox(
            main_frame, 
            textvariable=self.storage_type_var,
            values=["json", "csv", "mongodb"],
            state="readonly",
            width=15
        )
        storage_combo.grid(row=0, column=1, padx=5, pady=5, sticky=tk.W)
        storage_combo.bind('<<ComboboxSelected>>', self.change_storage_strategy)
        
        # 2. Formulario de eventos
        form_frame = ttk.LabelFrame(main_frame, text="Datos del Evento", padding="10")
        form_frame.grid(row=1, column=0, columnspan=2, padx=5, pady=10, sticky=(tk.W, tk.E))
        form_frame.columnconfigure(1, weight=1)
        
        # ID
        ttk.Label(form_frame, text="ID:").grid(row=0, column=0, padx=5, pady=5, sticky=tk.W)
        self.id_entry = ttk.Entry(form_frame, width=30)
        self.id_entry.grid(row=0, column=1, padx=5, pady=5, sticky=(tk.W, tk.E))
        
        # Nombre
        ttk.Label(form_frame, text="Nombre:").grid(row=1, column=0, padx=5, pady=5, sticky=tk.W)
        self.name_entry = ttk.Entry(form_frame, width=30)
        self.name_entry.grid(row=1, column=1, padx=5, pady=5, sticky=(tk.W, tk.E))
        
        # Fecha
        ttk.Label(form_frame, text="Fecha (YYYY-MM-DD):").grid(
            row=2, column=0, padx=5, pady=5, sticky=tk.W)
        self.date_entry = ttk.Entry(form_frame, width=30)
        self.date_entry.grid(row=2, column=1, padx=5, pady=5, sticky=(tk.W, tk.E))
        
        # 3. Botones CRUD
        button_frame = ttk.Frame(main_frame)
        button_frame.grid(row=2, column=0, columnspan=2, padx=5, pady=10)
        
        ttk.Button(button_frame, text="Agregar", 
                  command=self.add_event).pack(side=tk.LEFT, padx=5)
        ttk.Button(button_frame, text="Actualizar", 
                  command=self.update_event).pack(side=tk.LEFT, padx=5)
        ttk.Button(button_frame, text="Eliminar", 
                  command=self.delete_event).pack(side=tk.LEFT, padx=5)
        ttk.Button(button_frame, text="Buscar", 
                  command=self.read_event).pack(side=tk.LEFT, padx=5)
        ttk.Button(button_frame, text="Limpiar", 
                  command=self.clear_form).pack(side=tk.LEFT, padx=5)
        
        # 4. Área de resultados
        ttk.Label(main_frame, text="Resultados:").grid(
            row=3, column=0, padx=5, pady=5, sticky=tk.W)
        
        self.result_text = scrolledtext.ScrolledText(main_frame, width=60, height=10)
        self.result_text.grid(row=4, column=0, columnspan=2, padx=5, pady=5, 
                             sticky=(tk.W, tk.E, tk.N, tk.S))
        
        # Configurar expansión
        main_frame.rowconfigure(4, weight=1)
    
    def change_storage_strategy(self, event=None):
        storage_type = self.storage_type_var.get()
        try:
            self.controller.set_storage_strategy(storage_type)
            self.show_message(f"Estrategia cambiada a: {storage_type}")
        except Exception as e:
            messagebox.showerror("Error", f"No se pudo cambiar la estrategia: {str(e)}")
    
    def add_event(self):
        try:
            event = self.get_event_from_form()
            if event:
                success = self.controller.add_event(event)
                if success:
                    messagebox.showinfo("Éxito", "Evento agregado exitosamente!")
                    self.clear_form()
                else:
                    messagebox.showerror("Error", "No se pudo agregar el evento")
        except ValueError as e:
            messagebox.showerror("Error de Validación", str(e))
    
    def update_event(self):
        try:
            event = self.get_event_from_form()
            if event:
                success = self.controller.update_event(event)
                if success:
                    messagebox.showinfo("Éxito", "Evento actualizado exitosamente!")
                else:
                    messagebox.showerror("Error", "No se pudo actualizar el evento")
        except ValueError as e:
            messagebox.showerror("Error de Validación", str(e))
    
    def delete_event(self):
        event_id = self.id_entry.get().strip()
        if not event_id:
            messagebox.showwarning("Advertencia", "Ingrese un ID para eliminar")
            return
        
        confirm = messagebox.askyesno(
            "Confirmar Eliminación",
            f"¿Está seguro de eliminar el evento con ID: {event_id}?"
        )
        
        if confirm:
            success = self.controller.delete_event(event_id)
            if success:
                messagebox.showinfo("Éxito", "Evento eliminado exitosamente!")
                self.clear_form()
            else:
                messagebox.showerror("Error", "No se pudo eliminar el evento")
    
    def read_event(self):
        event_id = self.id_entry.get().strip()
        if not event_id:
            messagebox.showwarning("Advertencia", "Ingrese un ID para buscar")
            return
        
        event = self.controller.read_event(event_id)
        if event:
            self.name_entry.delete(0, tk.END)
            self.name_entry.insert(0, event.name)
            
            self.date_entry.delete(0, tk.END)
            self.date_entry.insert(0, event.date)
            
            self.show_message(f"Evento encontrado:\n{event}")
        else:
            messagebox.showinfo("Resultado", "Evento no encontrado")
    
    def get_event_from_form(self):
        event_id = self.id_entry.get().strip()
        name = self.name_entry.get().strip()
        date = self.date_entry.get().strip()
        
        if not all([event_id, name, date]):
            messagebox.showwarning("Advertencia", "Todos los campos son requeridos")
            return None
        
        try:
            event = Event()
            event.id = event_id
            event.name = name
            event.date = date
            return event
        except ValueError as e:
            raise e
    
    def clear_form(self):
        self.id_entry.delete(0, tk.END)
        self.name_entry.delete(0, tk.END)
        self.date_entry.delete(0, tk.END)
        self.result_text.delete(1.0, tk.END)
        self.id_entry.focus()
    
    def show_message(self, message: str):
        self.result_text.insert(tk.END, message + "\n")
        self.result_text.see(tk.END)
    
    def run(self):
        self.root.mainloop()