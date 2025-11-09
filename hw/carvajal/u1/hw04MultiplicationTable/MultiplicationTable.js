let number = parseInt(prompt("Which multiplication table would you like to see? "));

console.log(`\ntable of ${number}\n`);
console.log("Number| Result");
console.log("-------------------");

for (let i = 1; i <= 10; i++) {
  console.log(`${number.toString().padStart(6)} x ${i.toString().padEnd(2)} = ${(number * i).toString().padEnd(3)}`);
}
