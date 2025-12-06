import tkinter as tk
from tkinter import ttk, messagebox
import logging

class FrmPetShop:
    def __init__(self, root):
        self.root = root
        self.root.title("Pet Shop - Add Products")
        self.root.geometry("800x600")
        
        # Configurar logging
        logging.basicConfig(level=logging.INFO)
        self.logger = logging.getLogger(__name__)
        
        # Variables
        self.product_var = tk.StringVar(value="")  # Solo permite un producto
        self.animals_vars = {}  # Diccionario para animales (pueden ser varios)
        
        self.setup_ui()
    
    def setup_ui(self):
        # Panel 1 - Título
        self.panel1 = tk.Frame(self.root, bg="#66ffff", height=100, relief="solid", borderwidth=1)
        self.panel1.pack(fill="x", pady=(10, 0))
        
        self.lbl_title = tk.Label(self.panel1, text="ADD PRODUCTS TO THE STORE", 
                                  font=("Nirmala UI", 24, "italic"), bg="#66ffff")
        self.lbl_title.pack(pady=20)
        
        # Frame principal para organizar etiquetas y controles
        main_frame = tk.Frame(self.root)
        main_frame.pack(fill="both", expand=True, padx=20, pady=20)
        
        # Etiquetas a la izquierda
        labels_frame = tk.Frame(main_frame)
        labels_frame.pack(side="left", fill="y", padx=(0, 10))
        
        self.lbl_products = tk.Label(labels_frame, text="Product/s:", anchor="e", width=12)
        self.lbl_products.pack(pady=(30, 10), anchor="e")
        
        self.lbl_animals = tk.Label(labels_frame, text="Animal/s:", anchor="e", width=12)
        self.lbl_animals.pack(pady=10, anchor="e")
        
        self.lbl_make = tk.Label(labels_frame, text="Make:", anchor="e", width=12)
        self.lbl_make.pack(pady=10, anchor="e")
        
        self.lbl_details = tk.Label(labels_frame, text="Product Details:", anchor="e", width=12)
        self.lbl_details.pack(pady=10, anchor="e")
        
        self.lbl_total = tk.Label(labels_frame, text="Total payment:", anchor="e", width=12)
        self.lbl_total.pack(pady=10, anchor="e")
        
        # Panel 2 - Controles a la derecha
        self.panel2 = tk.Frame(main_frame, relief="solid", borderwidth=1)
        self.panel2.pack(side="left", fill="both", expand=True)
        
        # Productos (RadioButtons - solo uno seleccionable)
        self.lbl_product_type = tk.Label(self.panel2, text="Select Product Type:")
        self.lbl_product_type.grid(row=0, column=0, columnspan=6, padx=10, pady=(10, 5), sticky="w")
        
        # Frame para productos
        products_frame = tk.Frame(self.panel2)
        products_frame.grid(row=1, column=0, columnspan=6, padx=10, pady=5, sticky="w")
        
        # Lista de productos
        products = [
            ("MEDICINE", "radProductMedicine"),
            ("ACCESORIES", "radProductAccesory"),
            ("TOY", "radProductToy"),
            ("FOOD", "radProductFood")
        ]
        
        self.product_buttons = []
        for i, (text, name) in enumerate(products):
            rb = tk.Radiobutton(products_frame, text=text, variable=self.product_var, 
                               value=text, command=self.on_product_selected)
            rb.pack(side="left", padx=5)
            self.product_buttons.append(rb)
        
        # Animales (Checkboxes - múltiples seleccionables)
        self.lbl_animal_type = tk.Label(self.panel2, text="Select Animals (one or more):")
        self.lbl_animal_type.grid(row=2, column=0, columnspan=6, padx=10, pady=(15, 5), sticky="w")
        
        # Frame para animales
        animals_frame = tk.Frame(self.panel2)
        animals_frame.grid(row=3, column=0, columnspan=6, padx=10, pady=5, sticky="w")
        
        # Lista de animales
        animals = [
            ("Cat", "lstAnimalCat"),
            ("Dog", "lstAnimalDog"),
            ("Chicken", "lstAnimalChicken"),
            ("Horse", "lstAnimalHorse"),
            ("Pig", "lstAnimalPig"),
            ("Cow", "lstAnimalCow"),
            ("Hamster", "lstAnimalHamster")
        ]
        
        self.animal_checkboxes = []
        for i, (text, name) in enumerate(animals):
            var = tk.BooleanVar()
            chk = tk.Checkbutton(animals_frame, text=text, variable=var)
            chk.pack(side="left", padx=5)
            self.animals_vars[text] = var
            self.animal_checkboxes.append(chk)
        
        # Campo Make (Marca)
        self.lbl_make_input = tk.Label(self.panel2, text="Brand/Make:")
        self.lbl_make_input.grid(row=4, column=0, padx=10, pady=(15, 5), sticky="w")
        
        self.txt_make = tk.Entry(self.panel2, width=40)
        self.txt_make.grid(row=4, column=1, columnspan=5, padx=10, pady=(15, 5), sticky="ew")
        
        # Campo Product Details (Detalles del producto)
        self.lbl_details_input = tk.Label(self.panel2, text="Details:")
        self.lbl_details_input.grid(row=5, column=0, padx=10, pady=(10, 5), sticky="nw")
        
        self.txt_details = tk.Text(self.panel2, width=40, height=4)
        self.txt_details.grid(row=5, column=1, columnspan=5, padx=10, pady=(10, 5), sticky="ew")
        
        # Campo Total Payment (Pago total)
        self.lbl_total_input = tk.Label(self.panel2, text="Total ($):")
        self.lbl_total_input.grid(row=6, column=0, padx=10, pady=(10, 5), sticky="w")
        
        self.txt_total = tk.Entry(self.panel2, width=15)
        self.txt_total.grid(row=6, column=1, padx=10, pady=(10, 5), sticky="w")
        
        # Botones
        buttons_frame = tk.Frame(self.panel2)
        buttons_frame.grid(row=7, column=0, columnspan=6, pady=20)
        
        self.btn_save = tk.Button(buttons_frame, text="Save", command=self.on_save, 
                                  bg="#4CAF50", fg="white", width=10)
        self.btn_save.pack(side="left", padx=20)
        
        self.btn_print = tk.Button(buttons_frame, text="Print", command=self.on_print, 
                                   bg="#2196F3", fg="white", width=10)
        self.btn_print.pack(side="left", padx=20)
        
        # Panel 3 (vacío en la parte inferior)
        self.panel3 = tk.Frame(self.root, height=64, bg="lightgray")
        self.panel3.pack(fill="x", side="bottom", pady=(10, 0))
        
        # Configurar pesos para expansión
        self.panel2.grid_columnconfigure(1, weight=1)
        
    def on_product_selected(self):
        """Manejador para selección de producto"""
        selected_product = self.product_var.get()
        self.logger.info(f"Producto seleccionado: {selected_product}")
    
    def get_selected_animals(self):
        """Obtiene la lista de animales seleccionados"""
        selected = []
        for animal, var in self.animals_vars.items():
            if var.get():
                selected.append(animal)
        return selected
    
    def on_save(self):
        """Manejador para el botón Save"""
        try:
            # Validar selección de producto
            selected_product = self.product_var.get()
            if not selected_product:
                messagebox.showwarning("Advertencia", "Por favor, seleccione un tipo de producto")
                return
            
            # Obtener animales seleccionados
            selected_animals = self.get_selected_animals()
            if not selected_animals:
                messagebox.showwarning("Advertencia", "Por favor, seleccione al menos un animal")
                return
            
            # Obtener otros datos
            make = self.txt_make.get()
            details = self.txt_details.get("1.0", tk.END).strip()
            total = self.txt_total.get()
            
            # Validar campos requeridos
            if not make or not details:
                messagebox.showwarning("Advertencia", "Por favor, complete los campos Make y Product Details")
                return
            
            # Validar total
            try:
                total_float = float(total) if total else 0.0
            except ValueError:
                messagebox.showwarning("Advertencia", "El total debe ser un número válido")
                return
            
            # Mostrar datos en consola
            print("\n" + "="*50)
            print("PRODUCTO GUARDADO EN LA TIENDA")
            print("="*50)
            print(f"Tipo de Producto: {selected_product}")
            print(f"Animales: {', '.join(selected_animals)}")
            print(f"Marca: {make}")
            print(f"Detalles: {details}")
            print(f"Total: ${total_float:.2f}")
            print("="*50 + "\n")
            
            # Registrar en log
            self.logger.info(f"Producto guardado: {selected_product} para {selected_animals}")
            
            # Mostrar mensaje de éxito
            messagebox.showinfo("Éxito", "Producto guardado exitosamente en la tienda!")
            
            # Aquí podrías agregar código para guardar en base de datos o archivo
            
        except Exception as e:
            self.logger.error(f"Error al guardar producto: {str(e)}")
            messagebox.showerror("Error", f"Ocurrió un error: {str(e)}")
    
    def on_print(self):
        """Manejador para el botón Print"""
        try:
            # Validar que haya datos para imprimir
            selected_product = self.product_var.get()
            selected_animals = self.get_selected_animals()
            
            if not selected_product or not selected_animals:
                messagebox.showwarning("Advertencia", "No hay datos para imprimir")
                return
            
            # Obtener datos
            make = self.txt_make.get()
            details = self.txt_details.get("1.0", tk.END).strip()
            total = self.txt_total.get()
            
            # Crear contenido para imprimir
            print_content = f"""
            {'='*60}
            PET SHOP - RECIBO DE PRODUCTO
            {'='*60}
            
            TIPO DE PRODUCTO: {selected_product}
            ANIMALES: {', '.join(selected_animals)}
            MARCA: {make if make else 'No especificada'}
            
            DETALLES:
            {details if details else 'No especificados'}
            
            {'-'*60}
            TOTAL: ${total if total else '0.00'}
            {'='*60}
            
            ¡Gracias por su compra!
            """
            
            # En un sistema real, aquí enviarías a impresora
            # Por ahora, mostramos en un cuadro de diálogo
            print_dialog = tk.Toplevel(self.root)
            print_dialog.title("Vista Previa de Impresión")
            print_dialog.geometry("500x400")
            
            text_widget = tk.Text(print_dialog, wrap=tk.WORD)
            text_widget.pack(fill="both", expand=True, padx=10, pady=10)
            text_widget.insert("1.0", print_content)
            text_widget.config(state="disabled")
            
            # Botón para cerrar
            btn_close = tk.Button(print_dialog, text="Cerrar", 
                                  command=print_dialog.destroy)
            btn_close.pack(pady=10)
            
            self.logger.info("Vista previa de impresión generada")
            
        except Exception as e:
            self.logger.error(f"Error al imprimir: {str(e)}")
            messagebox.showerror("Error", f"Error al generar vista de impresión: {str(e)}")
    
    def run(self):
        """Ejecuta la aplicación"""
        self.root.mainloop()


def main():
    root = tk.Tk()
    app = FrmPetShop(root)
    app.run()


if __name__ == "__main__":
    main()