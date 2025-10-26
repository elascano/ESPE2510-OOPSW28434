import { Chicken } from "./Chicken_Model.js";
import { File } from "./SaveinCSV_model.js";
console.log("This is my Chicken Farm Simulator");
let chicken = new Chicken(1, "Lucy", "White and Brown", 2, false);
let chicken2 = new Chicken(2, "Maruja", "White", 1, true);

let allChickens = [
    chicken, chicken2, 
    new Chicken(3, "Lola", "White", 2, true),
    new Chicken(4, "Pepa", "Black", 1, false),
    new Chicken(5, "Gusepa", "Brown and white", 4, false),
    new Chicken(6, "Pancracia", "Gray", 2, true),
    new Chicken(7, "Federica", "Brown", 1, false),
    new Chicken(8, "Pancha", "White", 3, false),
    new Chicken(9, "Zoe", "Black", 2, false),
    new Chicken(10, "Lina", "Brown and white", 1, true),
];
const dataToSave = allChickens.map(chicken => chicken.toDataCSV()).join('\n');
let header = ["id", "name", "color", "age", "isMolting"];
let file = new File("MyChickens.csv", header);
for (const chicken of allChickens) {
    file.addRegister(chicken.toDataCSV());
}
file.saveDatainCSV();
