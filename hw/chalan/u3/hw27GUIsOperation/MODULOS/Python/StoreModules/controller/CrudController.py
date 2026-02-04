from utils.MongoUtil import MongoUtil
from domain.domain import DOMAIN
import re

class CrudController:
    def __init__(self, collectionName, priceField, stockField):
        self.collection = MongoUtil.getCollection(collectionName)
        self.priceField = priceField
        self.stockField = stockField
        self.idField = DOMAIN["idField"]
        self.fields = DOMAIN["fields"]

    def _validate_and_cast(self, rawData):
        data = {}

        for field in self.fields:
            name = field["name"]
            ftype = field["type"]

            if name not in rawData:
                raise ValueError(f"Missing field: {name}")

            value = rawData[name].strip()

            if value == "":
                raise ValueError(f"{name} cannot be empty")

            # 🔒 VALIDACIÓN ESTRICTA
            if ftype == str:
                if not re.fullmatch(r"[A-Za-zÁÉÍÓÚáéíóúñÑ ]+", value):
                    raise ValueError(f"{name} must contain ONLY letters")

                data[name] = value

            elif ftype == int:
                if not value.isdigit():
                    raise ValueError(f"{name} must be an integer")

                data[name] = int(value)

            elif ftype == float:
                try:
                    data[name] = float(value)
                except:
                    raise ValueError(f"{name} must be a decimal number")

                if data[name] < 0:
                    raise ValueError(f"{name} must be positive")

            else:
                raise ValueError(f"Unsupported type for {name}")

        return data

    def add(self, rawData):
        data = self._validate_and_cast(rawData)
        self.collection.insert_one(data)

    def getAll(self):
        return list(self.collection.find({}, {"_id": 0}))

    def delete(self, identifier):
        self.collection.delete_one({self.idField: identifier})

    def buy(self, identifier, quantity):
        if quantity <= 0:
            return None

        item = self.collection.find_one({self.idField: identifier})

        if not item or quantity > item[self.stockField]:
            return None

        total = quantity * item[self.priceField]

        self.collection.update_one(
            {self.idField: identifier},
            {"$set": {self.stockField: item[self.stockField] - quantity}}
        )

        return total
