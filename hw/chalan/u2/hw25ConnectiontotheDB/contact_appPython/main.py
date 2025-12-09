from tkinter import Tk
from database.mongo_connection import MongoConnection
from controller.contact_controller import ContactController
from view.contact_view import ContactView

if __name__ == "__main__":
    db = MongoConnection()  
    controller = ContactController(db)

    root = Tk()
    app = ContactView(root, controller)
    root.mainloop()
