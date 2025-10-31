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
    }

    getChickens() {
        return this.chickens;
    }

    findChickenById(chickenId) {
        return this.chickens.find(chicken => chicken.id === String(chickenId));
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
        this.chickens = this.chickens.filter(chicken => chicken.id !== String(chickenId));
        
        if (this.chickens.length < initialLength) {
            this.saveToStorage();
            return true;
        }
        return false;
    }

    saveToStorage() {
        const data = this.chickens.map(chicken => chicken.toDict());
        try {
            const jsonString = JSON.stringify(data, null, 4);
            fs.writeFileSync(this.storageKey, jsonString, 'utf-8');
        } catch (error) {
             console.error(`Error saving data to file: ${error.message}`);
        }
    }

    loadFromStorage() {
        try {
            const dataString = fs.readFileSync(this.storageKey, 'utf-8');
            
            const data = JSON.parse(dataString);
            this.chickens = [];
            let maxId = 0;

            for (const item of data) {
                try {
                    const chicken = Chicken.fromDict(item);
                    this.chickens.push(chicken); 
                    
                    const currentId = parseInt(chicken.id);
                    if (currentId > maxId) {
                        maxId = currentId;
                    }
                } catch {
                    console.warn(`Warning: Error loading a chicken. Data ignored.`); 
                }
            }

            Chicken.nextId = maxId + 1;

        } catch (e) {
            if (e.code === 'ENOENT') { 
                this.chickens = [];
                Chicken.nextId = 1;
            } else if (e instanceof SyntaxError) {
                console.error(`Error: Starting with empty list.`);
                this.chickens = [];
                Chicken.nextId = 1;
            } else {
                console.error(`Error loading or decoding data ${e.message}`);
                this.chickens = [];
                Chicken.nextId = 1;
            }
        }
    }
}

module.exports = { ChickenCoop };