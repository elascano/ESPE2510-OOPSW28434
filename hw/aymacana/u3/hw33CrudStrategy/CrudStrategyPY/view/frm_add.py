import tkinter as tk
from tkinter import messagebox, ttk
from controller.customer_controller import CustomerController
from model.customer import Customer
from utils.id_generator import IdGenerator

class FrmAdd(tk.Toplevel):
    def __init__(self, parent):
        super().__init__(parent)
        self.controller = CustomerController()
        self.title("Add Customer")
        self.geometry("400x350")
        self.resizable(False, False)
        
        self.update_idletasks()
        width = self.winfo_width()
        height = self.winfo_height()
        x = (self.winfo_screenwidth() // 2) - (width // 2)
        y = (self.winfo_screenheight() // 2) - (height // 2)
        self.geometry(f'{width}x{height}+{x}+{y}')
        
        self.create_widgets()
    
    def create_widgets(self):
        title_label = tk.Label(self, text="Add New Customer", font=("Arial", 16, "bold"))
        title_label.pack(pady=10)
        
        form_frame = tk.Frame(self)
        form_frame.pack(pady=20, padx=20, fill="x")
        
        tk.Label(form_frame, text="Name:").grid(row=0, column=0, sticky="w", pady=5)
        self.txt_name = tk.Entry(form_frame, width=30)
        self.txt_name.grid(row=0, column=1, pady=5, padx=(10, 0))
        
        tk.Label(form_frame, text="Apartment Number:").grid(row=1, column=0, sticky="w", pady=5)
        self.txt_apartment = tk.Entry(form_frame, width=30)
        self.txt_apartment.grid(row=1, column=1, pady=5, padx=(10, 0))
        
        tk.Label(form_frame, text="Email:").grid(row=2, column=0, sticky="w", pady=5)
        self.txt_email = tk.Entry(form_frame, width=30)
        self.txt_email.grid(row=2, column=1, pady=5, padx=(10, 0))
        
        tk.Label(form_frame, text="Phone:").grid(row=3, column=0, sticky="w", pady=5)
        self.txt_phone = tk.Entry(form_frame, width=30)
        self.txt_phone.grid(row=3, column=1, pady=5, padx=(10, 0))
        
        button_frame = tk.Frame(self)
        button_frame.pack(pady=20)
        
        self.btn_add = tk.Button(button_frame, text="Add", command=self.add_customer,
                                bg="green", fg="white", width=10)
        self.btn_add.pack(side="left", padx=5)
        
        self.btn_cancel = tk.Button(button_frame, text="Cancel", command=self.cancel,
                                   bg="red", fg="white", width=10)
        self.btn_cancel.pack(side="left", padx=5)
    
    def add_customer(self):
        name = self.txt_name.get().strip()
        apartment = self.txt_apartment.get().strip()
        email = self.txt_email.get().strip()
        phone = self.txt_phone.get().strip()
        
        if not name or not apartment or not email or not phone:
            messagebox.showwarning("Empty Fields", "Please complete all fields")
            return
        
        try:
            storage_format = self.ask_storage_format()
            if not storage_format:
                return
            
            customer_id = IdGenerator.generate_unique_id()
            customer = Customer(customer_id, name, apartment, email, phone)
            
            self.controller.set_storage_type(storage_format)
            
            success = self.controller.add_customer(customer)
            
            if success:
                messagebox.showinfo("Success", 
                    f"Customer added successfully in {storage_format} format\n"
                    f"Assigned ID: {customer_id}")
                self.clear_fields()
            else:
                messagebox.showerror("Error", f"Error adding customer in {storage_format} format")
        
        except ValueError as e:
            messagebox.showwarning("Validation Errors", str(e))
        except Exception as e:
            messagebox.showerror("Error", f"Unexpected error: {str(e)}")
    
    def ask_storage_format(self):
        dialog = tk.Toplevel(self)
        dialog.title("Select Storage Format")
        dialog.geometry("300x150")
        dialog.resizable(False, False)
        dialog.transient(self)
        dialog.grab_set()
        
        dialog.update_idletasks()
        width = dialog.winfo_width()
        height = dialog.winfo_height()
        x = (dialog.winfo_screenwidth() // 2) - (width // 2)
        y = (dialog.winfo_screenheight() // 2) - (height // 2)
        dialog.geometry(f'{width}x{height}+{x}+{y}')
        
        tk.Label(dialog, text="Where do you want to save the customer?", 
                font=("Arial", 10)).pack(pady=10)
        
        selected_format = tk.StringVar(value="JSON")
        
        formats = ["JSON", "CSV", "MongoDB"]
        for fmt in formats:
            tk.Radiobutton(dialog, text=fmt, variable=selected_format, 
                          value=fmt).pack(anchor="w", padx=50)
        
        result = {"format": None}
        
        def on_ok():
            result["format"] = selected_format.get()
            dialog.destroy()
        
        def on_cancel():
            dialog.destroy()
        
        button_frame = tk.Frame(dialog)
        button_frame.pack(pady=10)
        
        tk.Button(button_frame, text="OK", command=on_ok, width=10).pack(side="left", padx=5)
        tk.Button(button_frame, text="Cancel", command=on_cancel, width=10).pack(side="left", padx=5)
        
        dialog.wait_window()
        return result["format"]
    
    def clear_fields(self):
        self.txt_name.delete(0, tk.END)
        self.txt_apartment.delete(0, tk.END)
        self.txt_email.delete(0, tk.END)
        self.txt_phone.delete(0, tk.END)
        self.txt_name.focus_set()
    
    def cancel(self):
        self.clear_fields()
        self.destroy()