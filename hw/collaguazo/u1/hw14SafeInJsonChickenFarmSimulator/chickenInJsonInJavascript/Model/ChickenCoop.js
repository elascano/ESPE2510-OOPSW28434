const fs = require('fs');
const { Chicken } = require('./Chicken.js');

class ChickenCoop {
    constructor(storageKey = "chickensData.json") {
        this.chickens = [];
        this.storageKey = storageKey;
        this.loadFromStorage();
    }

    addChicken(chicken) {
        this.chickens.push(chicken);
        this.saveToStorage();
    }

    getChickens() {
        return this.chickens;
    }

    findChickenById(chickenId) {
        return this.chickens.find(ch => ch.id === String(chickenId));
    }

    editChicken(chickenId, newName, newColor, newAge, newIsMolting) {
        const chicken = this.findChickenById(chickenId);
        if (chicken) {
            chicken.name = newName;
            chicken.color = newColor;
            chicken.age = newAge;
            chicken.isMolting = newIsMolting;
            this.saveToStorage();
            return true;
        }
        return false;
    }

    deleteChicken(chickenId) {
        const initialLength = this.chickens.length;
        this.chickens = this.chickens.filter(ch => ch.id !== String(chickenId));
        if (this.chickens.length < initialLength) {
            this.saveToStorage();
            return true;
        }
        return false;
    }

    saveToStorage() {
        const data = this.chickens.map(ch => ch.toDict());
        try {
            fs.writeFileSync(this.storageKey, JSON.stringify(data, null, 4), 'utf-8');
        } catch (error) {
            console.error(`Error saving data: ${error.message}`);
        }
    }

    loadFromStorage() {
        try {
            if (!fs.existsSync(this.storageKey)) {
                this.chickens = [];
                Chicken.nextId = 1;
                return;
            }
            const raw = fs.readFileSync(this.storageKey, 'utf-8');
            const array = JSON.parse(raw);

            this.chickens = [];
            let maxId = 0;

            for (const item of array) {
                try {
                    const chicken = Chicken.fromDict(item);
                    this.chickens.push(chicken);
                    const idNum = parseInt(chicken.id);
                    if (idNum > maxId) maxId = idNum;
                } catch {
                    console.warn("⚠️ Warning: Invalid chicken data ignored.");
                }
            }

            Chicken.nextId = maxId + 1;

        } catch (e) {
            console.error(`Error loading storage: ${e.message}`);
            this.chickens = [];
            Chicken.nextId = 1;
        }
    }
}

module.exports = { ChickenCoop };
