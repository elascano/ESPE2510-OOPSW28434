import tkinter as tk
from view.contact_view import ContactView

def main():
    root = tk.Tk()
    root.title("Contacts App")
    root.geometry("420x500")

    app = ContactView(root)
    app.pack(fill="both", expand=True)

    root.mainloop()

if __name__ == "__main__":
    main()
