import tkinter as tk
from tkinter import font
from utils.mongo_connection import MongoConnection
from view.frm_add import FrmAdd
from view.frm_read import FrmRead
from view.frm_update import FrmUpdate
from view.frm_delete import FrmDelete

class MainApp:
    def __init__(self, root):
        self.root = root
        self.root.title("CRUD Strategy System")
        self.root.geometry("600x400")
        self.root.resizable(False, False)
        
        self.center_window()
        
        self.create_menu()
        
        self.create_interface()
        
        self.root.protocol("WM_DELETE_WINDOW", self.on_closing)
    
    def center_window(self):
        self.root.update_idletasks()
        width = self.root.winfo_width()
        height = self.root.winfo_height()
        x = (self.root.winfo_screenwidth() // 2) - (width // 2)
        y = (self.root.winfo_screenheight() // 2) - (height // 2)
        self.root.geometry(f'{width}x{height}+{x}+{y}')
    
    def create_menu(self):
        menubar = tk.Menu(self.root)
        self.root.config(menu=menubar)
        
        file_menu = tk.Menu(menubar, tearoff=0)
        menubar.add_cascade(label="File", menu=file_menu)
        file_menu.add_command(label="Exit", command=self.on_closing)
        
        help_menu = tk.Menu(menubar, tearoff=0)
        menubar.add_cascade(label="Help", menu=help_menu)
        help_menu.add_command(label="About", command=self.show_about)
    
    def create_interface(self):
        title_font = font.Font(family="Arial", size=24, weight="bold")
        title_label = tk.Label(self.root, text="CRUD Strategy System", 
                              font=title_font, fg="navy")
        title_label.pack(pady=20)
        
        subtitle_label = tk.Label(self.root, 
                                 text="Customer Management with Multiple Storage Formats",
                                 font=("Arial", 12))
        subtitle_label.pack(pady=5)
        
        buttons_frame = tk.Frame(self.root)
        buttons_frame.pack(pady=40)
        
        button_style = {
            "font": ("Arial", 12, "bold"),
            "width": 20,
            "height": 2,
            "bd": 3,
            "relief": "raised"
        }
        
        self.btn_add = tk.Button(buttons_frame, text="➕ Add Customer", 
                                command=self.open_add, bg="lightgreen",
                                **button_style)
        self.btn_add.grid(row=0, column=0, padx=10, pady=10)
        
        self.btn_read = tk.Button(buttons_frame, text="📖 Read Customers", 
                                 command=self.open_read, bg="lightblue",
                                 **button_style)
        self.btn_read.grid(row=0, column=1, padx=10, pady=10)
        
        self.btn_update = tk.Button(buttons_frame, text="✏️ Update Customer", 
                                   command=self.open_update, bg="lightyellow",
                                   **button_style)
        self.btn_update.grid(row=1, column=0, padx=10, pady=10)
        
        self.btn_delete = tk.Button(buttons_frame, text="🗑️ Delete Customer", 
                                   command=self.open_delete, bg="lightcoral",
                                   **button_style)
        self.btn_delete.grid(row=1, column=1, padx=10, pady=10)
        
        self.status_bar = tk.Label(self.root, text="Ready", bd=1, relief=tk.SUNKEN, anchor=tk.W)
        self.status_bar.pack(side=tk.BOTTOM, fill=tk.X)
    
    def open_add(self):
        frm_add = FrmAdd(self.root)
        frm_add.grab_set()
    
    def open_read(self):
        frm_read = FrmRead(self.root)
        frm_read.grab_set()
    
    def open_update(self):
        frm_update = FrmUpdate(self.root)
        frm_update.grab_set()
    
    def open_delete(self):
        frm_delete = FrmDelete(self.root)
        frm_delete.grab_set()
    
    def show_about(self):
        about_text = (
            "CRUD Strategy System v1.0\n\n"
            "A Python application demonstrating the Strategy Pattern\n"
            "for CRUD operations with multiple storage formats:\n"
            "- JSON files\n"
            "- CSV files\n"
            "- MongoDB database\n\n"
            "Created for educational purposes"
        )
        tk.messagebox.showinfo("About", about_text)
    
    def on_closing(self):
        MongoConnection.close_connection()
        self.root.quit()

def main():
    root = tk.Tk()
    app = MainApp(root)
    root.mainloop()

if __name__ == "__main__":
    main()