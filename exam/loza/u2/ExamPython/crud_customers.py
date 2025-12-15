from database import get_database
from bson.objectid import ObjectId

db = get_database()
customers = db["customers"]

def create_customer(name, email, balance):
    customers.insert_one({
        "name": name,
        "email": email,
        "balance": balance
    })

def get_customers():
    return list(customers.find())

def update_customer(customer_id, balance):
    customers.update_one(
        {"_id": ObjectId(customer_id)},
        {"$set": {"balance": balance}}
    )

def delete_customer(customer_id):
    customers.delete_one({"_id": ObjectId(customer_id)})