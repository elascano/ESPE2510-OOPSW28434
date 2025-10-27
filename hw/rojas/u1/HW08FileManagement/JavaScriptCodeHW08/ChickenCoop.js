class ChickenCoop {
  constructor(id, name) {
    this.id = id;
    this.name = name;
    this.chickens = [];
    this.eggs = [];
    this.poops = [];
  }

  addChicken(chicken) {
    this.chickens.push(chicken);
  }

  collectEgg(egg) {
    if (egg) this.eggs.push(egg);
  }

  collectPoop(poop) {
    if (poop) this.poops.push(poop);
  }

  showInfo() {
    console.log(`\nCoop ID: ${this.id} | Name: ${this.name}`);
    console.log(`Chickens (${this.chickens.length} total):`);
    this.chickens.forEach(c => console.log(` → ${c.toString()}`));
    console.log(`Eggs collected: ${this.eggs.length}`);
    console.log(`Poops collected: ${this.poops.length}`);
  }
}

export { ChickenCoop };
