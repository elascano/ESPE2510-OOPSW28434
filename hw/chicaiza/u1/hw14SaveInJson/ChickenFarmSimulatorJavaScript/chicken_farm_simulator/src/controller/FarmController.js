import { ChickenFarmer } from '../model/ChickenFarmer.js';
import { ChickenCoop } from '../model/ChickenCoop.js';
import { Chicken } from '../model/Chicken.js';
import fs from 'fs';
import path from 'path';

export class FarmController {
    constructor(dataFile = 'data/farms.json') {
        this.dataFile = dataFile;
        this.farmer = null;
        this.loadData();
    }
    
    loadData() {
        try {
            if (fs.existsSync(this.dataFile)) {
                const data = JSON.parse(fs.readFileSync(this.dataFile, 'utf8'));
                this.farmer = ChickenFarmer.fromDict(data);
            } else {
                this.farmer = new ChickenFarmer("Default Farmer");
            }
        } catch (error) {
            console.log("Error loading data, creating new farmer...");
            this.farmer = new ChickenFarmer("Default Farmer");
        }
    }
    
    saveData() {
        try {
            const dir = path.dirname(this.dataFile);
            if (!fs.existsSync(dir)) {
                fs.mkdirSync(dir, { recursive: true });
            }
            fs.writeFileSync(this.dataFile, JSON.stringify(this.farmer.toDict(), null, 2));
            return true;
        } catch (error) {
            console.log("Error saving data:", error.message);
            return false;
        }
    }
    
    createCoop(coopId) {
        if (this.farmer.getCoop(coopId)) {
            return false;
        }
        const coop = new ChickenCoop(coopId);
        this.farmer.add(coop);
        return this.saveData();
    }
    
    addChickenToCoop(coopId, chickenId, name, color, age, isMolting) {
        const coop = this.farmer.getCoop(coopId);
        if (!coop) {
            return false;
        }
        
        if (coop.getChicken(chickenId)) {
            return false;
        }
        
        const chicken = new Chicken(chickenId, name, color, age, isMolting);
        coop.add(chicken);
        return this.saveData();
    }
    
    getAllCoops() {
        return this.farmer.coops;
    }
    
    getCoop(coopId) {
        return this.farmer.getCoop(coopId);
    }
    
    updateChicken(coopId, chickenId, name, color, age, isMolting) {
        const coop = this.farmer.getCoop(coopId);
        if (!coop) {
            return false;
        }
        
        const success = coop.updateChicken(chickenId, name, color, age, isMolting);
        if (success) {
            return this.saveData();
        }
        return false;
    }
    
    deleteChicken(coopId, chickenId) {
        const coop = this.farmer.getCoop(coopId);
        if (!coop) {
            return false;
        }
        
        coop.remove(chickenId);
        return this.saveData();
    }
    
    performChickenAction(coopId, chickenId, action) {
        const coop = this.farmer.getCoop(coopId);
        if (!coop) {
            return false;
        }
        
        const chicken = coop.getChicken(chickenId);
        if (!chicken) {
            return false;
        }
        
        const actions = {
            'cluck': () => chicken.cluck(),
            'wander': () => chicken.wander(),
            'eat': () => chicken.eat(),
            'drink': () => chicken.drink(),
            'poop': () => chicken.poop(),
            'lay_egg': () => chicken.layAnEgg()
        };
        
        if (actions[action]) {
            actions[action]();
            return true;
        }
        return false;
    }
    
    getChickenCount() {
        return this.farmer.coops.reduce((total, coop) => total + coop.chickens.length, 0);
    }
    
    getCoopCount() {
        return this.farmer.coops.length;
    }
}