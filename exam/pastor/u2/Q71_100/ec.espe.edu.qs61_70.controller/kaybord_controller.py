from pymongo import MongoClient
from tkinter import messagebox
class keybord_controller():
    def __init__(self):
        super().__init__()
        self.title("CONTACTS BOOK")
        self.geometry("900x750")

        try:
            self.mongo_uri = "mongodb+srv://Mathews:Mathews2007@cluster0.6l9ibfh.mongodb.net/"
            self.client = MongoClient(self.mongo_uri)
            self.db = self.client['QS61_70'] 
            self.collection = self.db['Keybords']
        except Exception as e:
            messagebox.showerror("Error: {str(e)}")