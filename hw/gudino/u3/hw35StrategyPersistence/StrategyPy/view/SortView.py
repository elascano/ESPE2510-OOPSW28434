import tkinter as tk
from tkinter import messagebox
from controller.SortContext import SortContext
from model.SortRepository import SortRepository

class SortView:
    def __init__(self):
        self.repository = SortRepository()

        self.root = tk.Tk()
        self.root.title("Strategy Pattern Sorting")

        tk.Label(self.root, text="Enter numbers separated by commas:").pack()
        self.entry = tk.Entry(self.root, width=50)
        self.entry.pack()

        tk.Button(self.root, text="Sort", command=self.sort).pack()
        self.root.mainloop()

    def sort(self):
        try:
            data = list(map(int, self.entry.get().split(',')))
            context = SortContext(data.copy(), self.repository)
            result = context.sort()

            messagebox.showinfo(
                "Result",
                f"Algorithm: {context.strategy.__class__.__name__}\n"
                f"Sorted: {result}"
            )
        except Exception as e:
            messagebox.showerror("Error", str(e))
