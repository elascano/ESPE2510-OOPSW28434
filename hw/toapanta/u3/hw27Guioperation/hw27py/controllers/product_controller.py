from tkinter import messagebox

class ProductController:
    def __init__(self, model, view):
        self.model = model
        self.view = view
        
       
        self.view.btn_guardar.config(command=self.procesar_guardado)
        
       
        self.actualizar_pantalla()

    def procesar_guardado(self):
        """Obtiene datos de la vista, realiza cálculos y los manda al modelo."""
        try:
          
            id_val = int(self.view.ent_id.get())
            nombre = self.view.ent_name.get()
            cant = float(self.view.ent_qty.get())
            precio = float(self.view.ent_price.get())

            if not nombre:
                messagebox.showwarning("Atención", "El nombre no puede estar vacío")
                return

            
            subtotal = round(cant * precio, 2)
            iva = round(subtotal * 0.12, 2)
            total = round(subtotal + iva, 2)


            nuevo_producto = {
                "id": id_val,
                "name": nombre,
                "quantity": cant,
                "price": precio,
                "subtotal": subtotal,
                "iva": iva,
                "total": total
            }


            self.model.guardar_producto(nuevo_producto)

            self.limpiar_campos()
            self.actualizar_pantalla()
            messagebox.showinfo("Éxito", f"Producto '{nombre}' guardado correctamente")

        except ValueError:
            messagebox.showerror("Error", "Por favor, ingresa valores numéricos válidos en ID, Cantidad y Precio")

    def actualizar_pantalla(self):
        """Refresca la tabla y recalcula el total general."""

        for item in self.view.tabla.get_children():
            self.view.tabla.delete(item)
        

        productos = self.model.obtener_todos()
        
        suma_acumulada = 0.0
        
        for p in productos:
           
            id_p = p.get('id', 0)
            nom = p.get('name', 'Sin nombre')
            can = p.get('quantity', 0)
            pre = p.get('price', 0)
            sub = p.get('subtotal', 0)
            iva = p.get('iva', 0)
            tot = p.get('total', 0)

            self.view.tabla.insert("", "end", values=(
                id_p, nom, can, f"${pre:.2f}", f"${sub:.2f}", f"${iva:.2f}", f"${tot:.2f}"
            ))
            
            suma_acumulada += float(tot)
        
       
        total_final = round(suma_acumulada, 2)
        self.view.lbl_gran_total.config(text=f"TOTAL GENERAL: ${total_final:.2f}")

    def limpiar_campos(self):
        """Borra el texto de los cuadros de entrada."""
        self.view.ent_id.delete(0, 'end')
        self.view.ent_name.delete(0, 'end')
        self.view.ent_qty.delete(0, 'end')
        self.view.ent_price.delete(0, 'end')
        self.view.ent_id.focus() 