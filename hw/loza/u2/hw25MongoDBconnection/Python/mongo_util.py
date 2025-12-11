# mongo_util.py

from pymongo import MongoClient


CONNECTION_STRING = (
    "mongodb+srv://Steven:Steven2001@cluster0.mp8muds.mongodb.net/?appName=Cluster0"
)

_client = None


def get_database(db_name="contactsdb"):
    global _client
    if _client is None:
        _client = MongoClient(CONNECTION_STRING)
    return _client[db_name]


def get_contacts_collection():
    db = get_database("contactsdb")  
    return db["contacts"]            
