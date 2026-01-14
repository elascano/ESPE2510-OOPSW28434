class GenericEntity {
    constructor(type) {
        this._type = type;
        this._id = null;
        this._data = {}; 
    }

    setData(key, value) {
        this._data[key] = value;
    }

    getData(key) {
        return this._data[key];
    }

    static fromDocument(doc, type) {
        const entity = new GenericEntity(type);
        entity._id = doc._id;
        for (let key in doc) {
            if (key !== '_id') {
                entity.setData(key, doc[key]);
            }
        }
        return entity;
    }

    toDocument() {
        return { ...this._data };
    }
}

module.exports = GenericEntity;