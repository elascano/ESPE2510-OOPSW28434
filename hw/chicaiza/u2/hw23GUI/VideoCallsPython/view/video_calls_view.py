import tkinter as tk
from tkinter import ttk
from tkcalendar import DateEntry  # debes instalarlo con: pip install tkcalendar


class VideoCallsView(tk.Frame):
    def __init__(self, master):
        super().__init__(master)
        self.configure(bg="#e6f2ff")
        self.create_widgets()

    def create_widgets(self):
        # Title
        title = tk.Label(self, text="VIDEO CALLS", font=("Arial", 18, "bold"), bg="#e6f2ff")
        title.grid(row=0, column=0, columnspan=2, pady=10)

        # Call ID
        tk.Label(self, text="Call ID:", bg="#e6f2ff").grid(row=1, column=0, sticky="w", pady=5)
        self.txtCallId = tk.Entry(self, width=30)
        self.txtCallId.grid(row=1, column=1, pady=5)

        # Customer ID
        tk.Label(self, text="Customer ID:", bg="#e6f2ff").grid(row=2, column=0, sticky="w", pady=5)
        self.txtCustomerId = tk.Entry(self, width=30)
        self.txtCustomerId.grid(row=2, column=1, pady=5)

        # Event ID
        tk.Label(self, text="Event ID:", bg="#e6f2ff").grid(row=3, column=0, sticky="w", pady=5)
        self.txtEventId = tk.Entry(self, width=30)
        self.txtEventId.grid(row=3, column=1, pady=5)

        # Video Call Date
        tk.Label(self, text="Video Call Date:", bg="#e6f2ff").grid(row=4, column=0, sticky="w", pady=5)
        self.dateChooser = DateEntry(self, width=27)
        self.dateChooser.grid(row=4, column=1, pady=5)

        # Medium
        tk.Label(self, text="Medium:", bg="#e6f2ff").grid(row=5, column=0, sticky="nw", pady=5)

        self.mediumList = tk.Listbox(self, height=4)
        self.mediumList.grid(row=5, column=1, pady=5)

        for m in ["Zoom", "Meet", "Teams"]:
            self.mediumList.insert(tk.END, m)

        # Buttons
        btnSave = tk.Button(self, text="Save (not implemented)", bg="#d9d9d9")
        btnSave.grid(row=6, column=0, columnspan=2, pady=15)
