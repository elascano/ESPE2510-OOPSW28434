// chickenFarmSimulator.js
const readline = require('readline');

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

function chickenFarmSimulator() {
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout
    });
    
    console.log("=== CHICKEN FARM SIMULATOR (JAVASCRIPT) ===");
    console.log("\nIngrese los datos de la gallina:");
    
    rl.question("ID: ", (chickenId) => {
        rl.question("Nombre: ", (name) => {
            rl.question("Color: ", (color) => {
                rl.question("Edad: ", (age) => {
                    rl.question("¿Está mudando plumas? (si/no): ", (isMolting) => {
                        // Crear objeto Chicken
                        const chicken = new Chicken(
                            parseInt(chickenId),
                            name,
                            color,
                            parseInt(age),
                            isMolting.toLowerCase() === "si"
                        );
                        
                        // Mostrar datos
                        console.log("\n--- Datos de la gallina ---");
                        console.log(chicken.toString());
                        
                        // Crear objetos adicionales
                        const egg = new Egg('M');
                        const poop = new Poop(5);
                        
                        console.log(egg.toString());
                        console.log(poop.toString());
                        
                        rl.close();
                    });
                });
            });
        });
    });
}

// Ejecutar el programa
chickenFarmSimulator();