import tkinter as tk
from tkinter import ttk, messagebox
from typing import Callable, List, Dict, Any


class SalesView:
    """GUI view for sales management."""
    
    def __init__(self, root: tk.Tk) -> None:
        self.root = root
        self.root.title("Sales Management System")
        self.root.geometry("800x600")
        
        # Callbacks
        self.on_apply_discount_callback: Callable[[float], bool] = None
        self.on_create_sale_callback: Callable[[str, float], Any] = None
        
        self._setup_ui()
    
    def _setup_ui(self) -> None:
        """Setup the user interface."""
        # Main container
        main_frame = ttk.Frame(self.root, padding="10")
        main_frame.grid(row=0, column=0, sticky=(tk.W, tk.E, tk.N, tk.S))
        
        # Configure grid weights
        self.root.columnconfigure(0, weight=1)
        self.root.rowconfigure(0, weight=1)
        main_frame.columnconfigure(1, weight=1)
        
        # Discount control section
        discount_frame = ttk.LabelFrame(main_frame, text="Discount Control", padding="10")
        discount_frame.grid(row=0, column=0, columnspan=2, sticky=(tk.W, tk.E), pady=(0, 10))
        
        ttk.Label(discount_frame, text="Discount Percentage:").grid(row=0, column=0, padx=(0, 5))
        
        self.discount_var = tk.StringVar(value="10.0")
        self.discount_entry = ttk.Entry(discount_frame, textvariable=self.discount_var, width=15)
        self.discount_entry.grid(row=0, column=1, padx=(0, 10))
        
        ttk.Label(discount_frame, text="%").grid(row=0, column=2, padx=(0, 10))
        
        self.apply_discount_btn = ttk.Button(
            discount_frame, 
            text="Apply Discount",
            command=self._on_apply_discount
        )
        self.apply_discount_btn.grid(row=0, column=3)
        
        # Current discount display
        self.current_discount_label = ttk.Label(discount_frame, text="Current Discount: 10.0%")
        self.current_discount_label.grid(row=0, column=4, padx=(20, 0))
        
        # Sales input section
        input_frame = ttk.LabelFrame(main_frame, text="New Sale", padding="10")
        input_frame.grid(row=1, column=0, columnspan=2, sticky=(tk.W, tk.E), pady=(0, 10))
        
        ttk.Label(input_frame, text="Item Name:").grid(row=0, column=0, padx=(0, 5))
        self.item_name_var = tk.StringVar()
        self.item_name_entry = ttk.Entry(input_frame, textvariable=self.item_name_var, width=30)
        self.item_name_entry.grid(row=0, column=1, padx=(0, 10))
        
        ttk.Label(input_frame, text="Price:").grid(row=0, column=2, padx=(0, 5))
        self.price_var = tk.StringVar()
        self.price_entry = ttk.Entry(input_frame, textvariable=self.price_var, width=15)
        self.price_entry.grid(row=0, column=3, padx=(0, 10))
        
        self.create_sale_btn = ttk.Button(
            input_frame, 
            text="Create Sale",
            command=self._on_create_sale
        )
        self.create_sale_btn.grid(row=0, column=4)
        
        # Sales table
        table_frame = ttk.LabelFrame(main_frame, text="Sales History", padding="10")
        table_frame.grid(row=2, column=0, columnspan=2, sticky=(tk.W, tk.E, tk.N, tk.S))
        
        # Configure table frame grid
        table_frame.columnconfigure(0, weight=1)
        table_frame.rowconfigure(0, weight=1)
        
        # Create Treeview with scrollbar
        columns = ('sale_id', 'name', 'original_price', 'discount_percentage', 
                  'discount_amount', 'final_price', 'sale_date')
        
        self.tree = ttk.Treeview(
            table_frame, 
            columns=columns,
            show='headings',
            height=10
        )
        
        # Define headings
        self.tree.heading('sale_id', text='Sale ID')
        self.tree.heading('name', text='Item Name')
        self.tree.heading('original_price', text='Original Price')
        self.tree.heading('discount_percentage', text='Discount %')
        self.tree.heading('discount_amount', text='Discount Amount')
        self.tree.heading('final_price', text='Final Price')
        self.tree.heading('sale_date', text='Sale Date')
        
        # Define columns
        self.tree.column('sale_id', width=120)
        self.tree.column('name', width=150)
        self.tree.column('original_price', width=100)
        self.tree.column('discount_percentage', width=100)
        self.tree.column('discount_amount', width=100)
        self.tree.column('final_price', width=100)
        self.tree.column('sale_date', width=150)
        
        # Add scrollbar
        scrollbar = ttk.Scrollbar(table_frame, orient=tk.VERTICAL, command=self.tree.yview)
        self.tree.configure(yscrollcommand=scrollbar.set)
        
        # Grid tree and scrollbar
        self.tree.grid(row=0, column=0, sticky=(tk.W, tk.E, tk.N, tk.S))
        scrollbar.grid(row=0, column=1, sticky=(tk.N, tk.S))
    
    def set_on_apply_discount_callback(self, callback: Callable[[float], bool]) -> None:
        """Set callback for applying discount."""
        self.on_apply_discount_callback = callback
    
    def set_on_create_sale_callback(self, callback: Callable[[str, float], Any]) -> None:
        """Set callback for creating sale."""
        self.on_create_sale_callback = callback
    
    def _on_apply_discount(self) -> None:
        """Handle apply discount button click."""
        if not self.on_apply_discount_callback:
            return
        
        try:
            discount_value = float(self.discount_var.get())
            success = self.on_apply_discount_callback(discount_value)
            
            if success:
                self.update_current_discount(discount_value)
                messagebox.showinfo("Success", f"Discount updated to {discount_value}%")
            else:
                messagebox.showerror("Error", "Failed to update discount")
        except ValueError:
            messagebox.showerror("Error", "Please enter a valid number for discount")
        except Exception as e:
            messagebox.showerror("Error", f"Failed to apply discount: {str(e)}")
    
    def _on_create_sale(self) -> None:
        """Handle create sale button click."""
        if not self.on_create_sale_callback:
            return
        
        item_name = self.item_name_var.get().strip()
        price_str = self.price_var.get().strip()
        
        if not item_name:
            messagebox.showerror("Error", "Please enter an item name")
            return
        
        if not price_str:
            messagebox.showerror("Error", "Please enter a price")
            return
        
        try:
            price = float(price_str)
            if price <= 0:
                raise ValueError("Price must be greater than zero")
            
            self.on_create_sale_callback(item_name, price)
            
            # Clear input fields
            self.item_name_var.set("")
            self.price_var.set("")
            
        except ValueError as e:
            messagebox.showerror("Error", f"Invalid price: {str(e)}")
    
    def update_sales_table(self, sales_data: List[Dict[str, Any]]) -> None:
        """Update the sales table with new data."""
        # Clear existing items
        for item in self.tree.get_children():
            self.tree.delete(item)
        
        # Add new items
        for sale in sales_data:
            self.tree.insert('', tk.END, values=(
                sale['id'],
                sale['name'],
                f"${sale['original_price']:.2f}",
                f"{sale['discount_percentage']:.1f}%",
                f"${sale['discount_amount']:.2f}",
                f"${sale['final_price']:.2f}",
                sale['sale_date']
            ))
    
    def update_current_discount(self, discount: float) -> None:
        """Update current discount display."""
        self.current_discount_label.config(text=f"Current Discount: {discount:.1f}%")
    
    def show_error(self, message: str) -> None:
        """Show error message."""
        messagebox.showerror("Error", message)