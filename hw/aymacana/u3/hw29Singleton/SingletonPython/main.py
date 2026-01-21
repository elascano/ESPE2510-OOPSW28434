# main.py
import tkinter as tk
from view.frm_pay_rent import FrmPayRent

def main():
    root = tk.Tk()
    root.withdraw()  
    
    app = FrmPayRent()
    
    app.update_idletasks()
    width = app.winfo_width()
    height = app.winfo_height()
    x = (app.winfo_screenwidth() // 2) - (width // 2)
    y = (app.winfo_screenheight() // 2) - (height // 2)
    app.geometry(f'{width}x{height}+{x}+{y}')
    
    app.mainloop()

if __name__ == "__main__":
    main()