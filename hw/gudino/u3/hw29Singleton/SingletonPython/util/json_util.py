import json
from model.product import Product

FILE_PATH = "products.json"

def save_products(products):
    data = [{"name": p.name, "stock": p.stock} for p in products]
    with open(FILE_PATH, "w") as file:
        json.dump(data, file, indent=4)

def load_products():
    try:
        with open(FILE_PATH, "r") as file:
            data = json.load(file)
            return [Product(d["name"], d["stock"]) for d in data]
    except FileNotFoundError:
        return []
