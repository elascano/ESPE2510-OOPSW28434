from view.frm_contacts import FrmContacts
from utils.mongo_connection import MongoDBConnection

if __name__ == "__main__":
    MongoDBConnection.connect()
    app = FrmContacts()
    app.mainloop()