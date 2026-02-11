import tkinter as tk
from tkinter import messagebox
from controller.sorting_controller import SortingController

class SortingView:

    def __init__(self):
        self.controller = SortingController()

        self.window = tk.Tk()
        self.window.title("Number Sorting Algorithm")

        tk.Label(self.window, text="Enter the numbers:").pack(pady=5)

        self.entry = tk.Entry(self.window, width=40)
        self.entry.pack(pady=5)

        tk.Button(self.window, text="Order", command=self.sort).pack(pady=10)

        self.result_label = tk.Label(self.window, text="")
        self.result_label.pack(pady=5)

        self.window.mainloop()

    def sort(self):
        try:
            numbers = list(map(int, self.entry.get().split(",")))
            sorted_numbers = self.controller.sort_numbers(numbers)
            self.result_label.config(text=f"The ordered arrangment is: : {sorted_numbers}")
        except ValueError:
            messagebox.showerror("Error")
