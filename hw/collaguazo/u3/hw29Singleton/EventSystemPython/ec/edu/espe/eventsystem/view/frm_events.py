import tkinter as tk
from tkinter import messagebox

from ec.edu.espe.eventsystem.controller.event_controller import EventController
from ec.edu.espe.eventsystem.model.event import Event


class FrmEvents(tk.Tk):

    def __init__(self):
        super().__init__()

        self.title("Event System")
        self.geometry("360x280")

        self.controller = EventController()
        self.event = Event()

        self._build_ui()

    def _build_ui(self):

        tk.Label(self, text="Event Name").pack()
        self.txt_name = tk.Entry(self)
        self.txt_name.pack()

        tk.Label(self, text="Price").pack()
        self.txt_price = tk.Entry(self)
        self.txt_price.pack()

        tk.Label(self, text="Discount (%)").pack()
        self.txt_discount = tk.Entry(self)
        self.txt_discount.pack()
        self.txt_discount.insert(0, str(self.controller.get_discount()))

        tk.Button(self, text="Update Discount", command=self.update_discount).pack(pady=5)
        tk.Button(self, text="Show Total", command=self.show_total).pack(pady=10)

    def update_discount(self):
        try:
            discount = float(self.txt_discount.get())
            self.controller.update_discount(discount)
            messagebox.showinfo("Success", "Discount updated")
        except ValueError:
            messagebox.showerror("Error", "Invalid discount")

    def show_total(self):
        try:
            self.event.name = self.txt_name.get()
            self.event.price = float(self.txt_price.get())

            total = self.controller.calculate_total(self.event)

            messagebox.showinfo(
                "Total",
                f"Event: {self.event.name}\n"
                f"Discount: {self.controller.get_discount()}%\n"
                f"Total: ${total:.2f}"
            )
        except ValueError:
            messagebox.showerror("Error", "Invalid price")
