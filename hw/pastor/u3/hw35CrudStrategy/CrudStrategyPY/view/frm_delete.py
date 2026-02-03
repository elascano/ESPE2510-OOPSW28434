import tkinter as tk
from tkinter import messagebox
from controller.customer_controller import CustomerController

class FrmDelete(tk.Toplevel):
    def __init__(self, parent):
        super().__init__(parent)
        self.controller = CustomerController()
        
        self.title("Delete Customer")
        self.geometry("400x200")
        self.resizable(False, False)
        
        self.update_idletasks()
        width = self.winfo_width()
        height = self.winfo_height()
        x = (self.winfo_screenwidth() // 2) - (width // 2)
        y = (self.winfo_screenheight() // 2) - (height // 2)
        self.geometry(f'{width}x{height}+{x}+{y}')
        
        self.create_widgets()
    
    def create_widgets(self):
        title_label = tk.Label(self, text="Delete Customer", font=("Arial", 16, "bold"))
        title_label.pack(pady=10)
        
        form_frame = tk.Frame(self)
        form_frame.pack(pady=30, padx=40, fill="x")
        
        tk.Label(form_frame, text="Customer ID:").grid(row=0, column=0, sticky="w", pady=5)
        self.txt_id = tk.Entry(form_frame, width=20)
        self.txt_id.grid(row=0, column=1, pady=5, padx=(10, 0))
        
        self.btn_search = tk.Button(form_frame, text="Search", command=self.search,
                                   bg="blue", fg="white", width=10)
        self.btn_search.grid(row=0, column=2, padx=(10, 0))
        
        button_frame = tk.Frame(self)
        button_frame.pack(pady=20)
        
        self.btn_cancel = tk.Button(button_frame, text="Cancel", command=self.cancel,
                                   bg="gray", fg="white", width=10)
        self.btn_cancel.pack()
    
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
                self.clear_fields()
                return
            
            message = (
                f"CUSTOMER INFORMATION\n\n"
                f"ID: {customer_found.id}\n"
                f"Name: {customer_found.name}\n"
                f"Apartment: {customer_found.apartment_number}\n"
                f"Email: {customer_found.email}\n"
                f"Phone: {customer_found.phone}\n\n"
                f"Storage format: {found_in_format}\n\n"
                f"Are you SURE you want to delete this customer?\n"
                f"This action CANNOT be undone."
            )
            
            confirm = messagebox.askyesno("CONFIRM DELETION", message)
            
            if confirm:
                self.controller.set_storage_type(found_in_format)
                success = self.controller.delete_customer(customer_id)
                
                if success:
                    messagebox.showinfo("Deletion Successful",
                        f"Customer successfully deleted from {found_in_format} format")
                    self.clear_fields()
                else:
                    messagebox.showerror("Deletion Error", "Error deleting customer")
        
        except Exception as e:
            messagebox.showerror("Error", f"Error processing deletion: {str(e)}")
    
    def clear_fields(self):
        self.txt_id.delete(0, tk.END)
        self.txt_id.focus_set()
    
    def cancel(self):
        self.clear_fields()
        self.destroy()