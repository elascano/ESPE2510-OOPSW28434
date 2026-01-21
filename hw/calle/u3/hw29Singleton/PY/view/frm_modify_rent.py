import tkinter as tk
from tkinter import messagebox
from utils.rental_manager import RentalManager

class FrmModifyRent(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Modify Rent - ESPE")
        self.geometry("350x250")
        self.setup_ui()

    def setup_ui(self):
        tk.Label(self, text="Modify Rent", font=("Perpetua", 24, "bold italic")).pack(pady=20)

        frame = tk.Frame(self)
        frame.pack(pady=10)

        tk.Label(frame, text="Change Rent:", font=("Perpetua", 12)).grid(row=0, column=0, padx=5)
        
        self.txt_value = tk.Entry(frame)
        self.txt_value.grid(row=0, column=1, padx=5)

        btn_update = tk.Button(self, text="Update", font=("Perpetua", 10), command=self.btn_update_rent_action)
        btn_update.pack(pady=15)

        menubar = tk.Menu(self)
        file_menu = tk.Menu(menubar, tearoff=0)
        file_menu.add_command(label="Return to the menu", command=self.return_to_menu)
        menubar.add_cascade(label="File", menu=file_menu)
        self.config(menu=menubar)

    def btn_update_rent_action(self):
        """Lógica equivalente a btnUpdateRentActionPerformed en Java"""
        try:
            new_rent = float(self.txt_value.get())

            if new_rent <= 0:
                messagebox.showerror("Error", "Rent value must be greater than 0")
                return

            RentalManager.get_instance().update_monthly_rent(new_rent)

            messagebox.showinfo("Success", 
                                f"Rent value updated successfully!\nNew monthly rent: ${new_rent:.2f}")
            
            self.txt_value.delete(0, tk.END)

        except ValueError:
            messagebox.showerror("Error", "Please enter a valid numeric value")

    def return_to_menu(self):
        """Lógica equivalente a jMenuItem1ActionPerformed en Java"""
        from view.frm_menu import FrmMenu
        self.destroy()
        app = FrmMenu()
        app.mainloop()

if __name__ == "__main__":
    app = FrmModifyRent()
    app.mainloop()