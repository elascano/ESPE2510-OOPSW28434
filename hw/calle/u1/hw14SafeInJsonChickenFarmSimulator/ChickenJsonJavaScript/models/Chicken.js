import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'url';

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);

export { Chicken, ChickenModel };

class Chicken {
    constructor(id, name, color, age, isMolting) {
        this._id = id;
        this._name = name;
        this._color = color;
        this._age = age;
        this._isMolting = isMolting;
    }

    get id() {
        return this._id;
    }

    get name() {
        return this._name;
    }

    set name(value) {
        this._name = value;
    }

    get color() {
        return this._color;
    }

    set color(value) {
        this._color = value;
    }

    get age() {
        return this._age;
    }

    set age(value) {
        this._age = value;
    }

    get isMolting() {
        return this._isMolting;
    }

    set isMolting(value) {
        this._isMolting = value;
    }

    toJSON() {
        return {
            id: this._id,
            name: this._name,
            color: this._color,
            age: this._age,
            isMolting: this._isMolting
        };
    }

    static fromJSON(data) {
        return new Chicken(
            data.id,
            data.name,
            data.color,
            data.age,
            data.isMolting
        );
    }

    toString() {
        return `ID: ${this._id}, Name: ${this._name}, Color: ${this._color}, Age: ${this._age}, Molting: ${this._isMolting ? 'true' : 'false'}`;
    }
}

class ChickenModel {
    constructor() {
        this.dataDir = path.join(__dirname, '../data');
        this.dataFile = path.join(this.dataDir, 'chickens.json');
        this._ensureDataDirectory();
    }

    _ensureDataDirectory() {
        if (!fs.existsSync(this.dataDir)) {
            fs.mkdirSync(this.dataDir, { recursive: true });
        }
    }

    _readChickens() {
        try {
            if (fs.existsSync(this.dataFile)) {
                const data = fs.readFileSync(this.dataFile, 'utf8');
                return JSON.parse(data);
            }
        } catch (error) {
            console.error('Error reading chickens file:', error);
        }
        return [];
    }

    _writeChickens(chickensData) {
        try {
            fs.writeFileSync(this.dataFile, JSON.stringify(chickensData, null, 4));
            return true;
        } catch (error) {
            console.error('Error writing chickens file:', error);
            return false;
        }
    }

    getAllChickens() {
        const chickensData = this._readChickens();
        return chickensData.map(chickenData => Chicken.fromJSON(chickenData));
    }

    getChickenById(chickenId) {
        const chickensData = this._readChickens();
        const chickenData = chickensData.find(chicken => chicken.id === chickenId);
        return chickenData ? Chicken.fromJSON(chickenData) : null;
    }

    addChicken(chicken) {
        const chickensData = this._readChickens();
        
        if (chickensData.some(existingChicken => existingChicken.id === chicken.id)) {
            return false;
        }
        
        chickensData.push(chicken.toJSON());
        return this._writeChickens(chickensData);
    }

    updateChicken(chickenId, updatedChicken) {
        const chickensData = this._readChickens();
        const index = chickensData.findIndex(chicken => chicken.id === chickenId);
        
        if (index !== -1) {
            chickensData[index] = updatedChicken.toJSON();
            return this._writeChickens(chickensData);
        }
        return false;
    }

    deleteChicken(chickenId) {
        const chickensData = this._readChickens();
        const index = chickensData.findIndex(chicken => chicken.id === chickenId);
        
        if (index !== -1) {
            chickensData.splice(index, 1);
            return this._writeChickens(chickensData);
        }
        return false;
    }

    findChickensByName(name) {
        const chickensData = this._readChickens();
        const searchTerm = name.toLowerCase();
        const filteredData = chickensData.filter(chicken => 
            chicken.name.toLowerCase().includes(searchTerm)
        );
        return filteredData.map(chickenData => Chicken.fromJSON(chickenData));
    }
}