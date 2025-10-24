
import { Chiken } from './Chiken.js';
import * as readline from 'node:readline/promises'; 
import { stdin as input, stdout as output } from 'node:process'; 

function getRandomInt(min, max) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

async function main() { 
    console.log("My Chicken Farm Simulator from Kevin Chalan");
    const rl = readline.createInterface({ input, output });

    let num_chickens = 3; 

    try {
        const inputStr = await rl.question("How many chickens do you want on your farm? ");
        
        const parsedNum = parseInt(inputStr);
        if (!isNaN(parsedNum) && parsedNum > 0) {
            num_chickens = parsedNum;
        }
    } catch (e) {
        console.error("Error al leer la entrada. Usando 3 gallinas por defecto.");
    } finally {

        rl.close();
    }
    const chickens = [];
    
    const colors = ["white", "black", "brown", "yellow", "spotted", "red", "black and brown"];
    const egg_sizes = ['S', 'M', 'L']; 
    const name_prefixes = ["Lucy", "Polly", "Henrietta", "Goldie", "Pepper", "Chick", "Roxie"];
    for (let i = 1; i <= num_chickens; i++) {
        const name_prefix = name_prefixes[getRandomInt(0, name_prefixes.length - 1)];
        const random_digits = getRandomInt(100, 999);
        const name = `${name_prefix}${random_digits}`;
        
        const color = colors[getRandomInt(0, colors.length - 1)];
        const age = getRandomInt(1, 5);
        const is_molting = Math.random() < 0.5;
        
        const chicken = new Chiken(i, name, color, age, is_molting); 
        chickens.push(chicken);
    }
    for (const chicken of chickens) {
        console.log("\n");
        console.log(`The chiken is -->`);
        console.log(chicken.toString()); 
        console.log(`chiken id -->${chicken.id}${chicken.name}`);
        
        chicken._eat();
        chicken._cluck();
        chicken.poop(getRandomInt(1, 5));
        chicken.wander();
        chicken.lay_an_egg(egg_sizes[getRandomInt(0, egg_sizes.length - 1)]);
    }
}
main();