// chickenFarmSimulatorComplete.js
const readline = require('readline');
const fs = require('fs');

class Chicken {
    constructor(id, name, color, age, isMolting) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.isMolting = isMolting;
    }
    
    toString() {
        return `
Chicken:
  id --> ${this.id}
  name --> ${this.name}
  color --> ${this.color}
  age --> ${this.age}
  isMolting --> ${this.isMolting}
`;
    }
    
    toCSV() {
        return `${this.id},${this.name},${this.color},${this.age},${this.isMolting}`;
    }
}

class ChickenCoop {
    constructor(id, name, capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.chickens = [];
    }
    
    addChicken(chicken) {
        if (this.chickens.length < this.capacity) {
            this.chickens.push(chicken);
            return true;
        }
        return false;
    }
    
    removeChicken(chickenId) {
        const index = this.chickens.findIndex(chicken => chicken.id === chickenId);
        if (index !== -1) {
            this.chickens.splice(index, 1);
            return true;
        }
        return false;
    }
    
    toString() {
        let result = `
Chicken Coop:
  id --> ${this.id}
  name --> ${this.name}
  capacity --> ${this.capacity}
  current chickens --> ${this.chickens.length}
`;
        
        if (this.chickens.length > 0) {
            result += "  Chickens in this coop:\n";
            this.chickens.forEach(chicken => {
                result += `    - ${chicken.name} (ID: ${chicken.id})\n`;
            });
        }
        
        return result;
    }
}

class Egg {
    constructor(size) {
        this.size = size;
    }
    
    toString() {
        return `Egg: size=${this.size}`;
    }
}

class Poop {
    constructor(amount) {
        this.amount = amount;
    }
    
    toString() {
        return `Poop: amount=${this.amount}`;
    }
}

class ChickenFarm {
    constructor() {
        this.coops = [];
        this.allChickens = [];
    }
    
    addCoop(coop) {
        this.coops.push(coop);
    }
    
    addChicken(chicken, coopId) {
        const coop = this.coops.find(c => c.id === coopId);
        if (coop && coop.addChicken(chicken)) {
            this.allChickens.push(chicken);
            return true;
        }
        return false;
    }
    
    displayFarm() {
        console.log("\n=== CHICKEN FARM STATUS ===");
        console.log(`Total coops: ${this.coops.length}`);
        console.log(`Total chickens: ${this.allChickens.length}`);
        
        this.coops.forEach(coop => {
            console.log(coop.toString());
        });
    }
    
    saveToCSV(filename) {
        const header = "ID,Name,Color,Age,IsMolting,CoopID\n";
        let csvContent = header;
        
        this.coops.forEach(coop => {
            coop.chickens.forEach(chicken => {
                csvContent += `${chicken.toCSV()},${coop.id}\n`;
            });
        });
        
        fs.writeFileSync(filename, csvContent);
        console.log(`Data saved to ${filename}`);
    }
}

// Static data creation
function createStaticFarm() {
    const farm = new ChickenFarm();
    
    // Create coops
    const coop1 = new ChickenCoop(1, "Main Coop", 6);
    const coop2 = new ChickenCoop(2, "Secondary Coop", 4);
    
    farm.addCoop(coop1);
    farm.addCoop(coop2);
    
    // Create 10 chickens with static data
    const chickens = [
        new Chicken(1, "Henrietta", "Brown", 2, false),
        new Chicken(2, "Cluck Norris", "Black", 3, true),
        new Chicken(3, "Eggatha", "White", 1, false),
        new Chicken(4, "Feathers", "Red", 2, false),
        new Chicken(5, "Bok Choy", "Yellow", 1, true),
        new Chicken(6, "Nugget", "Brown", 4, false),
        new Chicken(7, "Drumstick", "Black", 2, false),
        new Chicken(8, "Sunny", "Yellow", 1, false),
        new Chicken(9, "Penny", "Red", 3, true),
        new Chicken(10, "Ginger", "Brown", 2, false)
    ];
    
    // Assign chickens to coops
    chickens.slice(0, 6).forEach(chicken => farm.addChicken(chicken, 1));
    chickens.slice(6).forEach(chicken => farm.addChicken(chicken, 2));
    
    return farm;
}

// Interactive data creation
function createInteractiveFarm() {
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });
    
    const farm = new ChickenFarm();
    
    return new Promise((resolve) => {
        console.log("=== CHICKEN FARM CREATION ===");
        
        // Create coops
        const coop1 = new ChickenCoop(1, "Main Coop", 5);
        const coop2 = new ChickenCoop(2, "Secondary Coop", 5);
        farm.addCoop(coop1);
        farm.addCoop(coop2);
        
        let chickenCount = 0;
        const maxChickens = 10;
        
        function askForChicken() {
            if (chickenCount >= maxChickens) {
                rl.close();
                resolve(farm);
                return;
            }
            
            console.log(`\n--- Creating Chicken ${chickenCount + 1} of ${maxChickens} ---`);
            
            rl.question("ID: ", (id) => {
                rl.question("Name: ", (name) => {
                    rl.question("Color: ", (color) => {
                        rl.question("Age: ", (age) => {
                            rl.question("Is Molting? (true/false): ", (isMolting) => {
                                rl.question("Assign to Coop (1 or 2): ", (coopId) => {
                                    const chicken = new Chicken(
                                        parseInt(id),
                                        name,
                                        color,
                                        parseInt(age),
                                        isMolting.toLowerCase() === "true"
                                    );
                                    
                                    if (farm.addChicken(chicken, parseInt(coopId))) {
                                        console.log("Chicken added successfully!");
                                    } else {
                                        console.log("Failed to add chicken. Coop might be full.");
                                    }
                                    
                                    chickenCount++;
                                    askForChicken();
                                });
                            });
                        });
                    });
                });
            });
        }
        
        askForChicken();
    });
}

// Main function
function chickenFarmSimulatorComplete() {
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });
    
    console.log("=== CHICKEN FARM SIMULATOR COMPLETE ===");
    console.log("Choose an option:");
    console.log("1. Use static data (predefined chickens and coops)");
    console.log("2. Enter data interactively");
    
    rl.question("Your choice (1 or 2): ", async (choice) => {
        let farm;
        
        if (choice === "1") {
            farm = createStaticFarm();
            // Display farm status
            farm.displayFarm();
            
            // Save to CSV
            farm.saveToCSV("chicken_farm_data.csv");
            
            // Create some eggs and poop for demonstration
            const egg = new Egg('M');
            const poop = new Poop(5);
            
            console.log("\n--- Farm Products ---");
            console.log(egg.toString());
            console.log(poop.toString());
            
            rl.close();
        } else if (choice === "2") {
            rl.close();
            farm = await createInteractiveFarm();
            
            // Display farm status
            farm.displayFarm();
            
            // Save to CSV
            farm.saveToCSV("chicken_farm_data.csv");
            
            // Create some eggs and poop for demonstration
            const egg = new Egg('M');
            const poop = new Poop(5);
            
            console.log("\n--- Farm Products ---");
            console.log(egg.toString());
            console.log(poop.toString());
        } else {
            console.log("Invalid choice. Please run the program again.");
            rl.close();
        }
    });
}

// Execute the program
chickenFarmSimulatorComplete();