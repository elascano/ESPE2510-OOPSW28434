import { Chicken } from '../models/Chicken.js';

export { ChickenController };

class ChickenController {
    constructor(model) {
        this.model = model;
    }

    insertChicken(id, name, color, age, isMolting) {
        if (!id || !name || !name.trim() || !color || !color.trim() || age < 0) {
            return false;
        }

        const chicken = new Chicken(id, name.trim(), color.trim(), age, isMolting);
        return this.model.addChicken(chicken);
    }

    listChickens() {
        return this.model.getAllChickens();
    }

    deleteChicken(chickenId) {
        return this.model.deleteChicken(chickenId);
    }

    updateChicken(chickenId, name, color, age, isMolting) {
        const existingChicken = this.model.getChickenById(chickenId);
        if (!existingChicken) {
            return false;
        }

        const updatedChicken = new Chicken(chickenId, name, color, age, isMolting);
        return this.model.updateChicken(chickenId, updatedChicken);
    }

    findChickens(name) {
        return this.model.findChickensByName(name);
    }

    getChickenById(chickenId) {
        return this.model.getChickenById(chickenId);
    }

    getNextAvailableId() {
        const chickens = this.model.getAllChickens();
        if (chickens.length === 0) {
            return 1;
        }
        return Math.max(...chickens.map(chicken => chicken.id)) + 1;
    }
}