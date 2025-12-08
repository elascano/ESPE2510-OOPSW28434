import tkinter as tk
from tkinter import ttk
from tkinter import messagebox

class BillGUI:

    def __init__(self, root):
        self.root = root
        root.title("Bill Manager")
        root.geometry("450x520")
        root.configure(bg="#F3F4F6")

        # -------- TITLE --------
        title = tk.Label(root, text="Create Bill", font=("Arial", 15, "bold"), bg="#F3F4F6")
        title.pack(pady=10)

        frame = tk.Frame(root, bg="#F3F4F6")
        frame.pack(pady=10)

        # -------- BILL ID (label only) --------
        tk.Label(frame, text="Bill ID:", bg="#F3F4F6").grid(row=0, column=0, sticky="w")
        self.lbl_bill_id = tk.Label(frame, text="(auto)", fg="blue", bg="#F3F4F6")
        self.lbl_bill_id.grid(row=0, column=1, sticky="w")

        # -------- CUSTOMER ID (ComboBox) --------
        tk.Label(frame, text="Customer ID:", bg="#F3F4F6").grid(row=1, column=0, sticky="w")
        self.customer_combo = ttk.Combobox(frame, values=[1, 2, 3, 4, 5], width=10)
        self.customer_combo.grid(row=1, column=1)

        # -------- EVENT ID (ComboBox) --------
        tk.Label(frame, text="Event ID:", bg="#F3F4F6").grid(row=2, column=0, sticky="w")
        self.event_combo = ttk.Combobox(frame, values=[10, 20, 30, 40], width=10)
        self.event_combo.grid(row=2, column=1)

        # -------- PAYMENT STATUS (RadioButtons) --------
        tk.Label(frame, text="Status:", bg="#F3F4F6").grid(row=3, column=0, sticky="w")
        self.pay_status = tk.StringVar(value="pending")

        tk.Radiobutton(frame, text="Pending", variable=self.pay_status, value="pending", bg="#F3F4F6").grid(row=3, column=1, sticky="w")
        tk.Radiobutton(frame, text="Paid", variable=self.pay_status, value="paid", bg="#F3F4F6").grid(row=4, column=1, sticky="w")

        # -------- TOGGLE BUTTON FOR NOTES --------
        tk.Label(frame, text="Notes:", bg="#F3F4F6").grid(row=5, column=0, sticky="nw")

        self.notes_enabled = tk.BooleanVar(value=False)

        self.toggle_btn = tk.Checkbutton(frame, text="Enable notes", variable=self.notes_enabled,
                                         bg="#F3F4F6", command=self.toggle_notes)
        self.toggle_btn.grid(row=5, column=1, sticky="w")

        self.text_notes = tk.Text(frame, width=30, height=3, state="disabled")
        self.text_notes.grid(row=6, column=0, columnspan=2, pady=5)

        # -------- AMOUNT (ComboBox) --------
        tk.Label(frame, text="Amount ($):", bg="#F3F4F6").grid(row=7, column=0, sticky="w")
        self.amount_combo = ttk.Combobox(frame, values=[50, 100, 150, 200, 300], width=10)
        self.amount_combo.grid(row=7, column=1)

        # -------- BUTTON SAVE --------
        btn = tk.Button(root, text="Create Bill", bg="#4F46E5", fg="white",
                        font=("Arial", 12, "bold"), command=self.create_bill)
        btn.pack(pady=10)

        # -------- RESULT --------
        self.result = tk.Label(root, text="", bg="#F3F4F6", fg="black", justify="left", font=("Arial", 11))
        self.result.pack(pady=10)


    def toggle_notes(self):
        if self.notes_enabled.get():
            self.text_notes.config(state="normal")
        else:
            self.text_notes.delete("1.0", tk.END)
            self.text_notes.config(state="disabled")

    def create_bill(self):
        cust = self.customer_combo.get()
        event = self.event_combo.get()
        amount = self.amount_combo.get()

        if not cust or not event or not amount:
            messagebox.showerror("Error", "All required fields must be completed.")
            return

        status = "PAID" if self.pay_status.get() == "paid" else "PENDING"
        notes = self.text_notes.get("1.0", tk.END).strip() if self.notes_enabled.get() else "None"

        summary = f"""
        BILL CREATED:
        ------------------------------
        Customer ID: {cust}
        Event ID: {event}
        Amount: ${amount}
        Status: {status}
        Notes: {notes}
        """

        self.result.config(text=summary)


# -------- RUN --------
root = tk.Tk()
app = BillGUI(root)
root.mainloop()
