const fs = require('fs');
const Store = require('../model/Store');

class CsvStrategy {
    constructor() {
        this.path = './Store.csv';
    }

    async loadAll() {
        if (!fs.existsSync(this.path)) return [];
        const data = fs.readFileSync(this.path, 'utf8');
        return data.split('\n').filter(line => line.trim()).map(line => {
            const [id, name, price, category] = line.split(';');
            return new Store(id, name, price, category);
        });
    }

    async create(s) {
        const line = `${s.id};${s.name};${s.price};${s.category}\n`;
        fs.appendFileSync(this.path, line);
    }

    async find(id) {
        const list = await this.loadAll();
        return list.find(s => s.id === Number(id)) || null;
    }

    async delete(id) {
        let list = await this.loadAll();
        list = list.filter(s => s.id !== Number(id));
        this._rewrite(list);
    }

    _rewrite(list) {
        const content = list.map(s => `${s.id};${s.name};${s.price};${s.category}`).join('\n') + '\n';
        fs.writeFileSync(this.path, content);
    }
}

module.exports = CsvStrategy;