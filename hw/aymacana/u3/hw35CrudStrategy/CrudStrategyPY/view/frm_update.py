import tkinter as tk
from tkinter import messagebox
from controller.customer_controller import CustomerController

class FrmUpdate(tk.Toplevel):
    def __init__(self, parent):
        super().__init__(parent)
        self.controller = CustomerController()
        self.current_customer = None
        self.current_storage_type = None
        
        self.title("Update Customer")
        self.geometry("500x450")
        self.resizable(False, False)
        
        self.update_idletasks()
        width = self.winfo_width()
        height = self.winfo_height()
        x = (self.winfo_screenwidth() // 2) - (width // 2)
        y = (self.winfo_screenheight() // 2) - (height // 2)
        self.geometry(f'{width}x{height}+{x}+{y}')
        
        self.create_widgets()
        self.disable_edit_fields()
    
    def create_widgets(self):
        title_label = tk.Label(self, text="Update Customer", font=("Arial", 16, "bold"))
        title_label.pack(pady=10)
        
        form_frame = tk.Frame(self)
        form_frame.pack(pady=20, padx=40, fill="x")
        
        tk.Label(form_frame, text="Customer ID:").grid(row=0, column=0, sticky="w", pady=5)
        self.txt_id = tk.Entry(form_frame, width=20)
        self.txt_id.grid(row=0, column=1, pady=5, padx=(10, 0))
        
        self.btn_search = tk.Button(form_frame, text="Search", command=self.search,
                                   bg="blue", fg="white", width=15)
        self.btn_search.grid(row=0, column=2, padx=(10, 0))
        
        tk.Label(form_frame, text="Name:").grid(row=1, column=0, sticky="w", pady=5)
        self.txt_name = tk.Entry(form_frame, width=30)
        self.txt_name.grid(row=1, column=1, columnspan=2, pady=5, padx=(10, 0), sticky="w")
        
        tk.Label(form_frame, text="Apartment Number:").grid(row=2, column=0, sticky="w", pady=5)
        self.txt_apartment = tk.Entry(form_frame, width=30)
        self.txt_apartment.grid(row=2, column=1, columnspan=2, pady=5, padx=(10, 0), sticky="w")
        
        tk.Label(form_frame, text="Email:").grid(row=3, column=0, sticky="w", pady=5)
        self.txt_email = tk.Entry(form_frame, width=30)
        self.txt_email.grid(row=3, column=1, columnspan=2, pady=5, padx=(10, 0), sticky="w")
        
        tk.Label(form_frame, text="Phone:").grid(row=4, column=0, sticky="w", pady=5)
        self.txt_phone = tk.Entry(form_frame, width=30)
        self.txt_phone.grid(row=4, column=1, columnspan=2, pady=5, padx=(10, 0), sticky="w")
        
        button_frame = tk.Frame(self)
        button_frame.pack(pady=20)
        
        self.btn_update = tk.Button(button_frame, text="Update", command=self.update_customer,
                                   bg="green", fg="white", width=10)
        self.btn_update.pack(side="left", padx=5)
        
        self.btn_cancel = tk.Button(button_frame, text="Cancel", command=self.cancel,
                                   bg="red", fg="white", width=10)
        self.btn_cancel.pack(side="left", padx=5)
    
    def disable_edit_fields(self):
        self.txt_name.config(state="disabled")
        self.txt_apartment.config(state="disabled")
        self.txt_email.config(state="disabled")
        self.txt_phone.config(state="disabled")
        self.btn_update.config(state="disabled")
    
    def enable_edit_fields(self):
        self.txt_name.config(state="normal")
        self.txt_apartment.config(state="normal")
        self.txt_email.config(state="normal")
        self.txt_phone.config(state="normal")
        self.btn_update.config(state="normal")
    
    def search(self):
        customer_id_text = self.txt_id.get().strip()
        
        if not customer_id_text:
            messagebox.showwarning("ID Required", "Please enter an ID to search")
            return
        
        try:
            customer_id = int(customer_id_text)
        except ValueError:
            messagebox.showwarning("Invalid ID", "ID must be a valid number")
            return
        
        try:
            found_in_format = None
            customer_found = None
            
            self.controller.set_storage_type("JSON")
            customer_in_json = self.controller.get_customer_by_id(customer_id)
            if customer_in_json:
                found_in_format = "JSON"
                customer_found = customer_in_json
        
            self.controller.set_storage_type("CSV")
            customer_in_csv = self.controller.get_customer_by_id(customer_id)
            if customer_in_csv:
                found_in_format = "CSV"
                customer_found = customer_in_csv
            
            self.controller.set_storage_type("MongoDB")
            customer_in_mongo = self.controller.get_customer_by_id(customer_id)
            if customer_in_mongo:
                found_in_format = "MongoDB"
                customer_found = customer_in_mongo
            
            if not customer_found:
                messagebox.showwarning("Customer Not Found", 
                    f"No customer found with ID {customer_id}")
                return
            
            self.current_customer = customer_found
            self.current_storage_type = found_in_format
            
            self.txt_name.delete(0, tk.END)
            self.txt_name.insert(0, customer_found.name)
            
            self.txt_apartment.delete(0, tk.END)
            self.txt_apartment.insert(0, customer_found.apartment_number)
            
            self.txt_email.delete(0, tk.END)
            self.txt_email.insert(0, customer_found.email)
            
            self.txt_phone.delete(0, tk.END)
            self.txt_phone.insert(0, customer_found.phone)
            
            self.enable_edit_fields()
            
            messagebox.showinfo("Customer Found",
                f"Customer found in {found_in_format} format\n"
                "You can edit the fields and click 'Update'")
        
        except Exception as e:
            messagebox.showerror("Error", f"Error searching for customer: {str(e)}")
    
    def update_customer(self):
        if not self.current_customer:
            messagebox.showwarning("No Customer Selected", 
                "First, you must search for a customer to update")
            return
        
        try:
            name = self.txt_name.get().strip()
            apartment = self.txt_apartment.get().strip()
            email = self.txt_email.get().strip()
            phone = self.txt_phone.get().strip()
            
            if not name or not apartment or not email or not phone:
                messagebox.showwarning("Empty Fields", "Please complete all fields")
                return
            
            confirm = messagebox.askyesno("Confirm Update",
                f"Are you sure you want to update customer with ID {self.current_customer.id}?\n"
                f"Format: {self.current_storage_type}")
            
            if not confirm:
                return
            
            success = self.controller.update_customer(
                self.current_customer.id,
                name,
                apartment,
                email,
                phone
            )
            
            if success:
                messagebox.showinfo("Update Successful",
                    f"Customer updated successfully in {self.current_storage_type} format")
                self.cancel()
            else:
                messagebox.showerror("Update Error", "Error updating customer")
        
        except ValueError as e:
            messagebox.showwarning("Validation Errors", str(e))
        except Exception as e:
            messagebox.showerror("Error", f"Unexpected error: {str(e)}")
    
    def cancel(self):
        self.txt_id.delete(0, tk.END)
        self.txt_name.delete(0, tk.END)
        self.txt_apartment.delete(0, tk.END)
        self.txt_email.delete(0, tk.END)
        self.txt_phone.delete(0, tk.END)
        self.current_customer = None
        self.current_storage_type = None
        self.disable_edit_fields()
        self.txt_id.focus_set()