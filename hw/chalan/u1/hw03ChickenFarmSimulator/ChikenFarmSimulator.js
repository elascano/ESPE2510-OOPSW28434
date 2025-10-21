const Chiken = require('./Chiken');

function main() {
    console.log("This is my Chiken Farm Simulator from Kevin Chalan");

    const chiken2 = new Chiken(0, "Maruja", "white", 1, true);

    const id = 1;
    const name = "Lucy";
    const color = "black and brown";
    const age = 2;
    const isMolting = false;

    const chiken = new Chiken(id, name, color, age, isMolting);
    console.log("The chiken is ------>", chiken.toString());

    console.log("chiken id ----->", chiken.id, chiken.name);
    chiken.doStuff();

    console.log("chiken2 id ----->", chiken2.id, chiken2.name);
    chiken2.doStuff();
}

main();
