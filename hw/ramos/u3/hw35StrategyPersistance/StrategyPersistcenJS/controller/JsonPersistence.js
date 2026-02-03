const fs = require('fs');
const Store = require('../model/Store');

class JsonStrategy {
    constructor() {
        this.path = './Store.json';
    }

    async loadAll() {
        if (!fs.existsSync(this.path)) return [];
        const data = fs.readFileSync(this.path, 'utf8');
        try {
            const json = JSON.parse(data);
            return json.map(s => new Store(s.id, s.name, s.price, s.category));
        } catch (e) {
            return [];
        }
    }

    async create(store) {
        const list = await this.loadAll();
        list.push(store);
        fs.writeFileSync(this.path, JSON.stringify(list, null, 2));
    }

    async find(id) {
        const list = await this.loadAll();
        return list.find(s => s.id === Number(id)) || null;
    }

    async update(id, store) {
        let list = await this.loadAll();
        const index = list.findIndex(s => s.id === Number(id));
        if (index !== -1) {
            list[index] = store;
            fs.writeFileSync(this.path, JSON.stringify(list, null, 2));
        }
    }

    async delete(id) {
        let list = await this.loadAll();
        list = list.filter(s => s.id !== Number(id));
        fs.writeFileSync(this.path, JSON.stringify(list, null, 2));
    }
}

module.exports = JsonStrategy;