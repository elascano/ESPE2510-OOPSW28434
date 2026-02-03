DOMAIN = {
    "title": "Laptop Store",
    "collection": "laptops",

    "idField": "serial",

    "fields": [
        {"name": "serial", "type": str},
        {"name": "brand", "type": str},
        {"name": "model", "type": str},
        {"name": "cpu", "type": str},
        {"name": "ram_gb", "type": int},
        {"name": "storage_gb", "type": int},
        {"name": "screen_inches", "type": float},
        {"name": "price", "type": float},
        {"name": "stock", "type": int}
    ],

    "priceField": "price",
    "stockField": "stock"
}
