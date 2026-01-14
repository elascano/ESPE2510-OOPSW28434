class GenericEntity:
    def __init__(self, type_name):
        self._id = None
        self._type = type_name
        self._data = {} 

    def set_data(self, key, value):
        self._data[key] = value

    def get_data(self, key):
        return self._data.get(key)
    
    def get_all_data(self):
        return self._data
    
    def set_id(self, id_val):
        self._id = id_val
        
    def get_id(self):
        return self._id

    def to_dict(self):
        doc = self._data.copy()
        if self._id:
            doc["_id"] = self._id
        return doc