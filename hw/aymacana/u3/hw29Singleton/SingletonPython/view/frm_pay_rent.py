# view/frm_pay_rent.py
import tkinter as tk
from tkinter import ttk, messagebox, font
from controller.rent_controller import RentController
from utils.rental_manager import RentalManager

class FrmPayRent(tk.Tk):
    def __init__(self):
        super().__init__()
        self.title("Pay Rent - Monthly Rent System")
        self.geometry("450x450")  
        self.resizable(False, False)
        self.configure(bg="#f0f0f0")
        
        self.controller = RentController()
        
        self.resident_id = 1756055065
        self.resident_name = "John Doe"
        
        self.title_font = font.Font(family="Arial", size=24, weight="bold")
        self.label_font = font.Font(family="Arial", size=11)
        self.value_font = font.Font(family="Arial", size=11, weight="bold")
        
        self._create_widgets()
        
        self._load_initial_rent()
    
    def _create_widgets(self):
        lbl_title = tk.Label(
            self,
            text="PAY RENT",
            font=self.title_font,
            bg="#f0f0f0",
            fg="#2c3e50"
        )
        lbl_title.pack(pady=20)
        
        main_frame = tk.Frame(self, bg="#f0f0f0", padx=30, pady=10)
        main_frame.pack(fill=tk.BOTH, expand=True)
        
        row1 = tk.Frame(main_frame, bg="#f0f0f0")
        row1.pack(fill=tk.X, pady=10)
        
        tk.Label(
            row1,
            text="Resident ID:",
            font=self.label_font,
            bg="#f0f0f0"
        ).pack(side=tk.LEFT, padx=(0, 10))
        
        self.lbl_resident_id = tk.Label(
            row1,
            text=str(self.resident_id),
            font=self.value_font,
            bg="#f0f0f0",
            fg="#3498db",
            width=15,
            anchor="w"
        )
        self.lbl_resident_id.pack(side=tk.LEFT)
        
        row2 = tk.Frame(main_frame, bg="#f0f0f0")
        row2.pack(fill=tk.X, pady=10)
        
        tk.Label(
            row2,
            text="Select Months (1-12):",
            font=self.label_font,
            bg="#f0f0f0"
        ).pack(side=tk.LEFT, padx=(0, 10))
        
        self.cbx_month = ttk.Combobox(
            row2,
            values=[str(i) for i in range(1, 13)],  # 1 a 12
            state="readonly",
            width=10
        )
        self.cbx_month.pack(side=tk.LEFT)
        self.cbx_month.bind("<<ComboboxSelected>>", self._on_month_selected)
        
        row3 = tk.Frame(main_frame, bg="#f0f0f0")
        row3.pack(fill=tk.X, pady=10)
        
        tk.Label(
            row3,
            text="Monthly Rent:",
            font=self.label_font,
            bg="#f0f0f0"
        ).pack(side=tk.LEFT, padx=(0, 10))
        
        self.lbl_monthly_rent = tk.Label(
            row3,
            text="$0.00",
            font=self.value_font,
            bg="#f0f0f0",
            fg="#27ae60",
            width=10,
            anchor="w"
        )
        self.lbl_monthly_rent.pack(side=tk.LEFT)
        
        row4 = tk.Frame(main_frame, bg="#f0f0f0")
        row4.pack(fill=tk.X, pady=10)
        
        tk.Label(
            row4,
            text="Total to Pay:",
            font=self.label_font,
            bg="#f0f0f0"
        ).pack(side=tk.LEFT, padx=(0, 10))
        
        self.lbl_total = tk.Label(
            row4,
            text="$0.00",
            font=self.value_font,
            bg="#f0f0f0",
            fg="#e74c3c",
            width=10,
            anchor="w"
        )
        self.lbl_total.pack(side=tk.LEFT)
        
        self.btn_pay = tk.Button(
        main_frame,
        text="PAY",
        command=self._process_payment,
        state="disabled",
        font=font.Font(family="Arial", size=12, weight="bold"),
        bg="#3498db",
        fg="white",
        relief="raised",
        cursor="hand2",
        activebackground="#2980b9"
    )
        self.btn_pay.pack(pady=30, ipadx=20, ipady=8)
        
        btn_exit = tk.Button(
            main_frame,
            text="Exit",
            command=self.quit,
            font=self.label_font,
            bg="#95a5a6",
            fg="white",
            width=10,
            relief="flat",
            cursor="hand2"
        )
        btn_exit.pack()
    
    def _load_initial_rent(self):
        try:
            monthly_rent = RentalManager.get_instance().get_monthly_rent()
            self.lbl_monthly_rent.config(text=f"${monthly_rent:.2f}")
        except Exception as e:
            messagebox.showerror("Error", f"Failed to load rent value: {str(e)}")
            self.lbl_monthly_rent.config(text="$20.00")
    
    def _on_month_selected(self, event=None):
        selected = self.cbx_month.get()
        
        if selected and selected.isdigit():
            months = int(selected)
            
            try:
                total = self.controller.get_total_to_pay(
                    self.resident_id,
                    self.resident_name,
                    months
                )
                
                self.lbl_total.config(text=f"${total:.2f}")
                
                self.btn_pay.config(state="normal", bg="#2ecc71")
                
            except Exception as e:
                messagebox.showerror("Error", f"Calculation error: {str(e)}")
                self.btn_pay.config(state="disabled", bg="#3498db")
        else:
            self.lbl_total.config(text="$0.00")
            self.btn_pay.config(state="disabled", bg="#3498db")
    
    def _process_payment(self):
        selected = self.cbx_month.get()
        
        if not selected or not selected.isdigit():
            messagebox.showerror("Error", "Please select a valid number of months")
            return
        
        months = int(selected)
        
        try:
            total = self.controller.get_total_to_pay(
                self.resident_id,
                self.resident_name,
                months
            )
            
            confirm = messagebox.askyesno(
                "Confirm Payment",
                f"Confirm payment of ${total:.2f} for {months} month(s)?"
            )
            
            if confirm:
                messagebox.showinfo(
                    "Payment Successful",
                    f"Payment processed successfully!\n\n"
                    f"Resident ID: {self.resident_id}\n"
                    f"Months: {months}\n"
                    f"Amount: ${total:.2f}"
                )
                
                self._reset_form()
                
        except Exception as e:
            messagebox.showerror("Error", f"Payment failed: {str(e)}")
    
    def _reset_form(self):
        self.cbx_month.set('')
        self.lbl_total.config(text="$0.00")
        self.btn_pay.config(state="disabled", bg="#3498db")


if __name__ == "__main__":
    app = FrmPayRent()
    app.mainloop()