import tkinter as tk
from tkinter import ttk, messagebox
from controller.customer_controller import CustomerController

class FrmRead(tk.Toplevel):
    def __init__(self, parent):
        super().__init__(parent)
        self.controller = CustomerController()
        self.title("Read Customers")
        self.geometry("800x500")
        
        self.update_idletasks()
        width = self.winfo_width()
        height = self.winfo_height()
        x = (self.winfo_screenwidth() // 2) - (width // 2)
        y = (self.winfo_screenheight() // 2) - (height // 2)
        self.geometry(f'{width}x{height}+{x}+{y}')
        
        self.create_widgets()
    
    def create_widgets(self):
        title_label = tk.Label(self, text="Read Customers", font=("Arial", 16, "bold"))
        title_label.pack(pady=10)
    
        control_frame = tk.Frame(self)
        control_frame.pack(pady=10, padx=20, fill="x")
        
        tk.Label(control_frame, text="Select Format:").pack(side="left", padx=(0, 10))
        
        self.cbx_file = ttk.Combobox(control_frame, 
                                     values=["Select format...", "JSON", "CSV", "MongoDB", "ALL (All formats)"],
                                     state="readonly",
                                     width=20)
        self.cbx_file.pack(side="left", padx=(0, 10))
        self.cbx_file.current(0)
        
        self.btn_search = tk.Button(control_frame, text="Search", command=self.search,
                                   bg="blue", fg="white", width=10)
        self.btn_search.pack(side="left", padx=(0, 10))
        
        self.btn_clear = tk.Button(control_frame, text="Clear", command=self.clear_table,
                                  bg="gray", fg="white", width=10)
        self.btn_clear.pack(side="left")
        
        table_frame = tk.Frame(self)
        table_frame.pack(pady=10, padx=20, fill="both", expand=True)
        
        self.create_table(table_frame)
        
        self.lbl_count = tk.Label(self, text="Customers found: 0", font=("Arial", 10))
        self.lbl_count.pack(pady=5)
    
    def create_table(self, parent):
        columns = ("ID", "Name", "Apartment", "Email", "Phone", "Format")
        self.table_info = ttk.Treeview(parent, columns=columns, show="headings")
        
        self.table_info.heading("ID", text="ID")
        self.table_info.heading("Name", text="Name")
        self.table_info.heading("Apartment", text="Apartment")
        self.table_info.heading("Email", text="Email")
        self.table_info.heading("Phone", text="Phone")
        self.table_info.heading("Format", text="Format")
    
        self.table_info.column("ID", width=50)
        self.table_info.column("Name", width=150)
        self.table_info.column("Apartment", width=100)
        self.table_info.column("Email", width=150)
        self.table_info.column("Phone", width=100)
        self.table_info.column("Format", width=80)
        
        scrollbar = ttk.Scrollbar(parent, orient="vertical", command=self.table_info.yview)
        self.table_info.configure(yscrollcommand=scrollbar.set)
        
        self.table_info.pack(side="left", fill="both", expand=True)
        scrollbar.pack(side="right", fill="y")
    
    def search(self):
        selected_option = self.cbx_file.get()
        
        if selected_option == "Select format...":
            messagebox.showwarning("Selection Required", "Please select a format")
            return
        
        try:
            if selected_option == "ALL (All formats)":
                self.load_all_formats()
            else:
                self.load_single_format(selected_option)
        
        except Exception as e:
            messagebox.showerror("Error", f"Error loading data: {str(e)}")
    
    def load_single_format(self, format_name):
        self.clear_table()
        
        self.controller.set_storage_type(format_name)
        
        customers = self.controller.get_all_customers()
        
        if not customers:
            messagebox.showinfo("No Data", f"No customers found in {format_name} format")
            return
        
        for customer in customers:
            self.table_info.insert("", "end", values=(
                customer.id,
                customer.name,
                customer.apartment_number,
                customer.email,
                customer.phone,
                format_name
            ))
    
        self.lbl_count.config(text=f"Customers found: {len(customers)} (Format: {format_name})")
    
    def load_all_formats(self):
        self.clear_table()
        
        total_customers = 0

        self.controller.set_storage_type("JSON")
        json_customers = self.controller.get_all_customers()
        for customer in json_customers:
            self.table_info.insert("", "end", values=(
                customer.id,
                customer.name,
                customer.apartment_number,
                customer.email,
                customer.phone,
                "JSON"
            ))
        total_customers += len(json_customers)
        
        self.controller.set_storage_type("CSV")
        csv_customers = self.controller.get_all_customers()
        for customer in csv_customers:
            self.table_info.insert("", "end", values=(
                customer.id,
                customer.name,
                customer.apartment_number,
                customer.email,
                customer.phone,
                "CSV"
            ))
        total_customers += len(csv_customers)
        
        self.controller.set_storage_type("MongoDB")
        mongo_customers = self.controller.get_all_customers()
        for customer in mongo_customers:
            self.table_info.insert("", "end", values=(
                customer.id,
                customer.name,
                customer.apartment_number,
                customer.email,
                customer.phone,
                "MongoDB"
            ))
        total_customers += len(mongo_customers)
        
        if total_customers == 0:
            messagebox.showinfo("No Data", "No customers found in any format")
        
        self.lbl_count.config(text=f"Total customers in all formats: {total_customers}")
    
    def clear_table(self):
        for item in self.table_info.get_children():
            self.table_info.delete(item)
        self.lbl_count.config(text="Customers found: 0")