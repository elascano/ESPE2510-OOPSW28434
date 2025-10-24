import { Chicken } from '../model/chicken.js';
import { ChickenCoop } from '../model/chickenCoop.js';

export class FarmController {
    constructor() {
        this.coops = []; // array de gallineros
    }

    setupFarm() {
        const coop1 = new ChickenCoop(1);
        const coop2 = new ChickenCoop(2);


        const chickens = [
            new Chicken(1, "Lucy", "White", 2, false),
            new Chicken(2, "Molly", "Brown", 3, true),
            new Chicken(3, "Daisy", "Black", 1, false),
            new Chicken(4, "Rose", "Yellow", 4, true),
            new Chicken(5, "Martha", "Red", 2, false),
            new Chicken(6, "Luna", "White", 1, false),
            new Chicken(7, "Nora", "Gray", 2, true),
            new Chicken(8, "Ava", "Black", 3, false),
            new Chicken(9, "Olive", "Golden", 4, true),
            new Chicken(10, "Ruby", "Brown", 2, false)
        ];

        chickens.slice(0, 5).forEach(c => coop1.addChicken(c));
        chickens.slice(5).forEach(c => coop2.addChicken(c));

        this.coops.push(coop1, coop2);
    }

    showFarm() {
        this.coops.forEach(coop => console.log(coop.toString() + "\n"));
    }
}
