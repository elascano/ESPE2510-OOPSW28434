class ChickenCoop {
  constructor(id, name) {
    this.id = id; this.name = name;
    this.chickens = []; this.eggs = []; this.poops = [];
  }
  addChicken(chicken) { this.chickens.push(chicken); }
  collectEgg(egg) { if (egg) this.eggs.push(egg); }
  collectPoop(poop) { if (poop) this.poops.push(poop); }
  showInfo() {
    console.log(`\nCoop ${this.id} - ${this.name}`);
    console.log(`  Chickens: ${this.chickens.length}`);
    this.chickens.forEach(c => console.log("   • " + c.toString()));
    console.log(`  Eggs: ${this.eggs.length}, Poops: ${this.poops.length}`);
  }
}
export { ChickenCoop };
